package ninja.mspp;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.prefs.Preferences;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import ninja.mspp.core.annotation.clazz.Listener;
import ninja.mspp.core.model.listener.ListenerInfo;
import ninja.mspp.core.model.listener.ListenerMethod;
import ninja.mspp.core.model.ms.Chromatogram;
import ninja.mspp.core.model.ms.Sample;
import ninja.mspp.core.model.ms.Spectrum;


public class MsppManager {
	private static MsppManager instance = null;
	
	private List<ListenerInfo> listeners;
	
	private List<Sample> openedSamples;
	
	private Sample activeSample;
	private Spectrum activeSpectrum;
	private Chromatogram activeChromatogram;
	private Preferences preferences;
	private Properties status;
	private ResourceBundle config;
	private ResourceBundle messages;	
	private File configFolder;
	private SessionFactory sessionFactory;
	
	private MsppManager() {
		this.openedSamples = new ArrayList<Sample>();
		this.listeners = null;
		this.activeSample = null;
		this.activeSpectrum = null;
		this.activeChromatogram = null;
		this.preferences = Preferences.userRoot().node("mspp4.1/parameters");
		this.status = new Properties();
		this.config = null;
		this.configFolder = null;
	}
	
	public List<ListenerInfo> getListeners() {
		if (this.listeners == null) {
			this.listeners = this.createListenerList();
		}
		
		return this.listeners;
	}
	
	private List<ListenerInfo> createListenerList() {
		List<ListenerInfo> listeners = new ArrayList<ListenerInfo>();
		
		Reflections reflections = new Reflections(new ConfigurationBuilder()
				.setUrls(ClasspathHelper.forPackage("ninja.mspp"))
				.setScanners(new SubTypesScanner(false))
				.filterInputsBy(new FilterBuilder().include("ninja.mspp.*")));

		Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);
		for (Class<?> clazz : classes) {
			Listener listener = clazz.getAnnotation(Listener.class);
			if (listener != null) {
				String name = listener.value();
				try {
					Constructor<?> constructor = clazz.getConstructor();
					Object object = constructor.newInstance();
					ListenerInfo info = new ListenerInfo(object, name);
					listeners.add(info);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		}

		return listeners;
	}
	
	
	public <T extends Annotation> List<ListenerMethod<T>> getMethods(Class<T> clazz) {
		List<ListenerInfo> listeners = this.getListeners();
		List<ListenerMethod<T>> methods = new ArrayList<ListenerMethod<T>>();
		for (ListenerInfo listener : listeners) {
			Object object = listener.getObject();
			Method[] classMethods = object.getClass().getMethods();
			for (Method method : classMethods) {
				T annotation = method.getDeclaredAnnotation(clazz);
				if (annotation != null) {
					ListenerMethod<T> listenerMethod = new ListenerMethod<T>(object, method, annotation);
					methods.add(listenerMethod);
				}
			}
		}
		
        try {
            Method orderMethod = clazz.getMethod("order");
            methods.sort(
            	Comparator.comparingInt(
            		m -> {
            			try {
            				return (int) orderMethod.invoke(m.getAnnotation());
            			}
            			catch (Exception e) {
            				return 5;
            			}
            		}
            	)
            );
        }
        catch (NoSuchMethodException e) {
        }

		return methods;
	}

	
	public <T extends Annotation> void invoke(Class<T> clazz, Object... args) {
		List<ListenerMethod<T>> methods = this.getMethods(clazz);
		for (ListenerMethod<T> method : methods) {
			method.invoke(args);
		}
	}
		
	public Sample getActiveSample() {
		return activeSample;
	}

	public void setActiveSample(Sample activeSample) {
		this.activeSample = activeSample;
	}

	public Spectrum getActiveSpectrum() {
		return activeSpectrum;
	}

	public void setActiveSpectrum(Spectrum activeSpectrum) {
		this.activeSpectrum = activeSpectrum;
	}

	public Chromatogram getActiveChromatogram() {
		return activeChromatogram;
	}

	public void setActiveChromatogram(Chromatogram activeChromatogram) {
		this.activeChromatogram = activeChromatogram;
	}
	
	public List<Sample> getOpenedSamples() {
		return openedSamples;
	}
	
	public void saveParameter(String key, String value) {
		this.preferences.put(key, value);
	}
	
	public String getParameter(String key) {
		return this.preferences.get(key, null);
	}
	
	public void setStatus(String key, String value) {
		this.status.setProperty(key, value);
	}
	
	public String getStatus(String key) {
		return this.status.getProperty(key, null);
	}
	
	public ResourceBundle getConfig() {
		if (this.config == null) {
			this.config = ResourceBundle.getBundle("ninja.mspp.conf.config");
		}

		return this.config;
	}
	
	public ResourceBundle getMessages() {
		if (this.messages == null) {
			this.messages = ResourceBundle.getBundle("ninja.mspp.conf.messages");
		}

		return this.messages;
	}
	
	public File getConfigFolder() {
		if(this.configFolder == null) {
			this.configFolder = new File(System.getProperty("user.home"), ".mspp4.1");
			if (!this.configFolder.exists()) {
				this.configFolder.mkdir();
			}
		}

		return this.configFolder;
	}
	
	public Session getSession() {
		if (this.sessionFactory == null) {
			this.sessionFactory = new Configuration().configure().buildSessionFactory();
		}
		return this.sessionFactory.openSession();
	}
	
	public void closeSession() {
		if (this.sessionFactory != null) {
			this.sessionFactory.close();
		}
	}
	
	public static MsppManager getInstance() {
		if (MsppManager.instance == null) {
            MsppManager.instance = new MsppManager();
        }
        return MsppManager.instance;
    }
}
