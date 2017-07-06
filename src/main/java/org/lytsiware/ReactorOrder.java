package org.lytsiware;

import java.util.stream.Collectors;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

@Mojo(name = "print", inheritByDefault = false, requiresProject = false)
public class ReactorOrder extends AbstractMojo {

	@Parameter(defaultValue = "${session}", readonly = true)
	MavenSession mavenSession;
	
	/**
	 * if set to true (default value) the output will always be printed (even if quiet mode is used).
	 * That can be useful when you only want to print this output without showing the rest maven info
	 */
	@Parameter(defaultValue = "true", property= "echo")
	boolean echo;
	
	/**
	 * The delimiter to split the projects in the output string 
	 */
	@Parameter(defaultValue = "\r\n", property = "delimiter")
	String delimiter;

	public void execute() throws MojoExecutionException, MojoFailureException {
		String output = mavenSession.getProjects().stream().map(MavenProject::getArtifactId)
				.collect(Collectors.joining(delimiter));
		if (!echo) {
			getLog().info(output);
		} else {
			System.out.println(output);
		}
	}
}
