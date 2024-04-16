package org.lytsiware;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.maven.shared.transfer.artifact.DefaultArtifactCoordinate;
import org.apache.maven.shared.transfer.artifact.resolve.ArtifactResolver;
import org.apache.maven.shared.transfer.artifact.resolve.ArtifactResolverException;
import org.apache.maven.shared.transfer.artifact.resolve.ArtifactResult;

@Mojo(name = "print", inheritByDefault = false, requiresProject = false)
public class ReactorOrder extends AbstractMojo {

	@Component
	ArtifactResolver artifactResolver;

	@Parameter(defaultValue = "${session}", readonly = true)
	MavenSession mavenSession;

	/**
	 * if set to true (default value) the output will always be printed (even if quiet mode is used).
	 * That can be useful when you only want to print this output without showing the rest maven info
	 */
	@Parameter(defaultValue = "true", property = "echo")
	boolean echo;

//	@Parameter(defaultValue = "false", property = "skip-release-version")
//	boolean skipReleaseVersion;

	public void execute() throws MojoExecutionException, MojoFailureException {
		ObjectMapper objectMapper = new ObjectMapper()
				.addMixIn(MavenProject.class, MavenProjectMixin.class)
				.addMixIn(Artifact.class, ArtifactMixin.class)
				.addMixIn(File.class, FileMixin.class)
				.enable(SerializationFeature.INDENT_OUTPUT);
		List<MavenProjectModel> models = mavenSession.getProjects().stream().map(mavenProject -> {
			String releaseVersion = getReleaseVersion(mavenProject);
			return new MavenProjectModel(mavenProject, releaseVersion);
		}).collect(Collectors.toList());
		try {
			if (!echo) {
				getLog().info(objectMapper.writeValueAsString(models));
			} else {
				System.out.println(objectMapper.writeValueAsString(models));
			}
		} catch (JsonProcessingException e) {
			throw new MojoExecutionException(e);
		}
	}

	private String getReleaseVersion(MavenProject mavenProject) {
//		if (skipReleaseVersion) {
//			return null;
//		}
		DefaultArtifactCoordinate coordinate = new DefaultArtifactCoordinate();
		coordinate.setGroupId(mavenProject.getGroupId());
		coordinate.setArtifactId(mavenProject.getArtifactId());
		coordinate.setVersion("RELEASE");
		coordinate.setExtension(mavenProject.getPackaging());
		try {
			return artifactResolver
					.resolveArtifact(mavenSession.getProjectBuildingRequest(), coordinate)
					.getArtifact()
					.getVersion();
		} catch (ArtifactResolverException e) {
			// not found (may be a better way of handling this, i.e. there are other reasons this might happen)
		}
		return null;
	}

}
