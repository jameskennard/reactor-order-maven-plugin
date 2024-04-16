package org.lytsiware;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.versioning.ArtifactVersion;
import org.apache.maven.artifact.versioning.OverConstrainedVersionException;

@JsonAutoDetect(
		fieldVisibility = JsonAutoDetect.Visibility.NONE,
		setterVisibility = JsonAutoDetect.Visibility.NONE,
		getterVisibility = JsonAutoDetect.Visibility.NONE,
		isGetterVisibility = JsonAutoDetect.Visibility.NONE,
		creatorVisibility = JsonAutoDetect.Visibility.NONE
)
public interface ArtifactMixin extends Artifact {

	@JsonProperty
	@Override
	String getId();

	@JsonProperty
	@Override
	String getGroupId();

	@JsonProperty
	@Override
	String getArtifactId();
	@JsonProperty
	@Override
	String getVersion();

	@JsonProperty
	@Override
	String getType();

	@JsonProperty
	@Override
	boolean isSnapshot();

	@JsonProperty
	@Override
	ArtifactVersion getSelectedVersion() throws OverConstrainedVersionException;

}
