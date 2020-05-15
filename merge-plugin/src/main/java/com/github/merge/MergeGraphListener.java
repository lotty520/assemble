package com.github.merge;

import java.util.List;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.execution.TaskExecutionGraph;
import org.gradle.api.execution.TaskExecutionGraphListener;

/**
 * 统计所有需要参加构建的任务，可以在此处对任务进行开关，缩减构建时间
 *
 * @author lotty
 */
public class MergeGraphListener implements TaskExecutionGraphListener {

  private Project hostProject;

  public MergeGraphListener(Project hostProject) {
    this.hostProject = hostProject;
  }

  @Override public void graphPopulated(TaskExecutionGraph taskExecutionGraph) {
    List<Task> allTasks = taskExecutionGraph.getAllTasks();
    for (int i = 0; i < allTasks.size(); i++) {
      Task task = allTasks.get(i);
      if (hostProject == task.getProject() && task.getName().contains("compileDebugJavaWithJavac")) {
        System.out.println("*****************************");
        task.getInputs().file("/Users/lotty/android/github/assemble/pack/src/main/Common.java");
      }
    }
  }
}
