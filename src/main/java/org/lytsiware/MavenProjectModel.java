package org.lytsiware;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.apache.maven.project.MavenProject;

public class MavenProjectModel {

	private final MavenProject mavenProject;

	private final String releaseVersion;

	public MavenProjectModel(MavenProject mavenProject, String releaseVersion) {
		this.mavenProject = mavenProject;
		this.releaseVersion = releaseVersion;
	}

	@JsonUnwrapped
	public MavenProject getMavenProject() {
		return mavenProject;
	}

	public String getReleaseVersion() {
		return releaseVersion;
	}
}
