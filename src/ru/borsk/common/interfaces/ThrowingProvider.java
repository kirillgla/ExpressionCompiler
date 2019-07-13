package ru.borsk.common.interfaces;

@FunctionalInterface
public interface ThrowingProvider<TResult, TException extends Exception> {
  TResult provide() throws TException;
}
