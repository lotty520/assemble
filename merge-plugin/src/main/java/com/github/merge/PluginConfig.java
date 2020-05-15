package com.github.merge;

import java.util.Arrays;

/**
 * @author lotty
 */
public class PluginConfig {

  /**
   * 主模块名
   */
  public String pkg;
  /**
   * 包含的模块
   */
  public String[] libs;

  /**
   * 是否打印日志
   */
  public boolean logOpen;

  @Override
  public String toString() {
    return "logOpen=" + logOpen + ", pkg=" + pkg + ", libs=" + Arrays.toString(libs);
  }
}
