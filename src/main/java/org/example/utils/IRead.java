package org.example.utils;

import java.util.Scanner;

public interface IRead {
  Scanner scanner = new Scanner(System.in);

  default String scan() {
    return scanner.nextLine().toUpperCase();
  }
}
