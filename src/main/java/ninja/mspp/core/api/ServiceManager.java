package ninja.mspp.core.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ServiceManager {
	private static ServiceManager instance;
	
	private static final int PORT = 8191;
	
	private ServiceManager() {
	}
	
	public String callService(String serviceName, Object object) throws UnknownHostException, IOException, URISyntaxException {
		String response = "";
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonRequest = mapper.writeValueAsString(object);
		System.out.println("Request: " + jsonRequest);
		
		URI uri = new URI("http", null, "localhost", PORT, "/" + serviceName, null, null);
        URL url = uri.toURL();
        System.out.println("URL: " + url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        
        connection.setDoOutput(true);
        
        connection.getOutputStream().write(jsonRequest.getBytes());
        
        int responseCode = connection.getResponseCode();
        System.out.println("Response code: " + responseCode);
        
		BufferedReader in = new BufferedReader(
				new InputStreamReader(connection.getInputStream()));
		String line;
		while ((line = in.readLine()) != null) {
			response += line;
		}
		in.close();

		return response;
	}
	
	public static ServiceManager getInstance() {
		if (instance == null) {
			instance = new ServiceManager();
		}

		return instance;
	}
}
