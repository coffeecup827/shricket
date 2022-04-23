package org.example.playground;

public interface ILog {
  default void log(String text) {
    System.out.println(text);
  }
  default void divider() {
    gap();
    log("----------------------------------------");
    gap();
  }
  default void gap() {
    log("");
  }
}
