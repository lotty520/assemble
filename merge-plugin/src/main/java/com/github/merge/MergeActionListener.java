package com.github.merge;

import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.execution.TaskActionListener;

/**
 * 监听任务中的动作Action
 * @author lotty
 */
public class MergeActionListener implements TaskActionListener {
  private Project hostProject;

  public MergeActionListener(Project hostProject) {
    this.hostProject = hostProject;
  }

  @Override public void beforeActions(Task task) {
    System.out.println("ActionListener:beforeActions:" + task.getName());
  }

  @Override public void afterActions(Task task) {
    System.out.println("ActionListener:beforeActions:" + task.getName());
  }
}
