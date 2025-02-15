package ninja.mspp.core.api.glycoworkbench.model.ms;

import java.util.UUID;

import ninja.mspp.core.model.ms.Sample;

public class GlycoWorkbenchSample extends Sample {
	public GlycoWorkbenchSample() {
		super(
			UUID.randomUUID().toString(),
			"GlycoWorkbench Data"
		);
	}
}
