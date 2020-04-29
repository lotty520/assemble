package com.github.merge;

import org.gradle.BuildListener;
import org.gradle.BuildResult;
import org.gradle.api.Project;
import org.gradle.api.initialization.Settings;
import org.gradle.api.invocation.Gradle;

/**
 * 监听构建事件的起点、终点
 * @author lotty
 */
public class MergeBuildListner implements BuildListener {

  private Project hostProject;

  public MergeBuildListner(Project hostProject) {
    this.hostProject = hostProject;
  }

  @Override public void buildStarted(Gradle gradle) {

  }

  @Override public void beforeSettings(Settings settings) {
    System.out.println("BuildListener:beforeSettings");
  }

  @Override public void settingsEvaluated(Settings settings) {
    System.out.println("BuildListener:settingsEvaluated");
  }

  @Override public void projectsLoaded(Gradle gradle) {
    System.out.println("BuildListener:projectsLoaded");
  }

  @Override public void projectsEvaluated(Gradle gradle) {
    System.out.println(":BuildListener:projectsEvaluated");
  }

  @Override public void buildFinished(BuildResult buildResult) {
    System.out.println(":BuildListener:buildFinished");
  }
}
