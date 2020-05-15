package com.github.merge;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
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
    if (task.getName().contains("compileDebugJavaWithJavac")) {
      //File file = hostProject.file("/Users/lotty/android/github/assemble/common/src/main/java/com/github/common/Common.java");
      Set<File> outputs = task.getOutputs().getFiles().getFiles();
      Set<File> inputs = task.getInputs().getFiles().getFiles();
      Iterator<File> it = inputs.iterator();
      Iterator<File> ot = outputs.iterator();
      while (it.hasNext()) {
        System.out.println("input ----------> " + it.next().getAbsolutePath());
      }

      while (ot.hasNext()) {
        System.out.println("output ----------> " + ot.next().getAbsolutePath());
      }
    }
  }

  @Override public void afterExecute(Task task, TaskState taskState) {

  }
}
