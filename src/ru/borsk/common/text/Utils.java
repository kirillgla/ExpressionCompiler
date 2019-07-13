package ru.borsk.common.text;

import org.jetbrains.annotations.NotNull;

import java.util.AbstractList;
import java.util.List;

public final class Utils {
  private Utils() {
  }

  public static @NotNull String joinReadable(@NotNull List<@NotNull String> strings) {
    if (strings.size() == 0) throw new IllegalArgumentException("strings");
    if (strings.size() == 1) return strings.get(0);
    @NotNull final StringBuilder builder = new StringBuilder(strings.get(0));
    for (int i = 1; i < strings.size() - 1; i++) {
      builder.append(", ");
      builder.append(strings.get(0));
    }
    builder.append(" or ");
    builder.append(strings.get(strings.size() - 1));
    return builder.toString();
  }

  public static @NotNull List<@NotNull Character> asList(final @NotNull String string) {
    return new AbstractList<@NotNull Character>() {
      @Override
      public Character get(final int index) {
        return string.charAt(index);
      }

      @Override
      public int size() {
        return string.length();
      }
    };
  }
}
