package org.lytsiware;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import java.io.File;
import org.apache.maven.artifact.Artifact;

@JsonAutoDetect(
		fieldVisibility = JsonAutoDetect.Visibility.NONE,
		setterVisibility = JsonAutoDetect.Visibility.NONE,
		getterVisibility = JsonAutoDetect.Visibility.NONE,
		isGetterVisibility = JsonAutoDetect.Visibility.NONE,
		creatorVisibility = JsonAutoDetect.Visibility.NONE
)
public interface MavenProjectMixin {
	@JsonProperty
	@JsonUnwrapped
	Artifact getArtifact();

	@JsonProperty("pom")
	File getFile();

	@JsonProperty
	File getBasedir();

}
