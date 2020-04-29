package com.github.merge;

import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.execution.TaskExecutionListener;
import org.gradle.api.tasks.TaskState;

/**
 * 监听每一个任务的入口和出口
 *
 * @author lotty
 */
public class MergeExecutionListener implements TaskExecutionListener {
  private Project hostProject;

  public MergeExecutionListener(Project hostProject) {
    this.hostProject = hostProject;
  }

  @Override public void beforeExecute(Task task) {

  }

  @Override public void afterExecute(Task task, TaskState taskState) {

  }
}
