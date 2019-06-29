package ru.borsk.common.interfaces;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public interface Presentable {
  @Contract(pure = true)
  @NotNull String getPresentation();
}
