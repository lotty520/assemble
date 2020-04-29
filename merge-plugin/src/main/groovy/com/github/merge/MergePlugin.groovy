package com.github.merge

import com.android.build.gradle.LibraryPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * @author lotty
 */
class MergePlugin implements Plugin<Project> {

  private final static String EXT = "mergeExt";

  private Project project;

  def lib

  @Override
  void apply(Project project) {
    println("=======================")
    println("      merge插件      ")
    println("=======================")

    if (!project.android) {
      throw new IllegalStateException(
          'Must apply \'com.android.application\' or \'com.android.library\' first!')
    }
    lib = project.plugins.withType(LibraryPlugin)
    if (!lib) {
      throw new IllegalStateException('\'android-library\' plugin required.')
    }
    project.extensions.create(EXT, PluginConfig)
    //    project.extensions.getByType(LibraryExtension).registerTransform(this)
    this.project = project
    project.gradle.addListener(new MergeActionListener(project))
    project.gradle.addListener(new MergeBuildListner(project))
    project.gradle.addListener(new MergeExecutionListener(project))
    project.gradle.addListener(new MergeGraphListener(project))
  }
}
