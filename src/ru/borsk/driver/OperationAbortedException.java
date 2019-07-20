package ru.borsk.driver;

public final class OperationAbortedException extends Exception {
  public OperationAbortedException(final String message) {
    super(message);
  }
}
