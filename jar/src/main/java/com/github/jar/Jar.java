package com.github.jar;

/**
 * @author lotty
 */
public class Jar {

  private final static int MAX = 10;

  public static void entry() {
    System.out.println("this is jar module entry, test for java module merge");
    for (int i = 0; i < MAX; i++) {
      System.out.println("timing is " + i);
    }
  }
}
