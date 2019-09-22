package org.ttrzcinski.utils;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Parameter (and argument) validation methods.
 */
public final class ParamCheck {

  /**
   * Hidden constructor - there is no point of initialization.
   */
  private ParamCheck() {
  }

  /**
   * Pattern for file paths.
   */
  private static Pattern filePathPattern;

  /**
   * Checks, if given param array contains any value.
   *
   * @param params given param to check
   * @return true means is is set, false otherwise
   */
  public static boolean isSet(final Object[] params) {
    // If there is nothing outside to check
    if (params == null || params.length == 0) {
      return false;
    }
    // If there is inside at least one empty item, else it's ok
    return (!(params instanceof String[])) ?
        Arrays.stream(params).allMatch(param -> isSet(param)) :
        isSet((String[]) params);
  }

  /**
   * Checks, if given params string array contains only values.
   *
   * @param params given param to check
   * @return true means is is set, false otherwise
   */
  public static boolean isSet(final String[] params) {
    // If there is inside at least one empty item, else it's ok
    return (params != null && params.length != 0) ?
        Arrays.stream(params).allMatch(param -> isSet(param)) :
        false;
  }

  /**
   * Checks, if given param contains any value after trim.
   *
   * @param param given param to check
   * @return true means is is set, false otherwise
   */
  public static boolean isSet(final String param) {
    return (param != null) ?
        ((String) param).trim().length() > 0 :
        false;
  }

  /**
   * Checks, if given param contains any value.
   *
   * @param param given param to check
   * @return true means is is set, false otherwise
   */
  public static boolean isSet(final Object param) {
    return param != null;
  }

  /**
   * Checks, if given value is positive.
   *
   * @param given given value
   * @return true means it is, false otherwise
   */
  public static boolean isPositive(final int given) {
    return given > -1;
  }

  /**
   * Checks, if given value is in between given limits
   *
   * @param given given value to check
   * @param leftLimit left side limit
   * @param rightLimit right side limit
   * @return true means, if is between those two, false otherwise
   */
  public static boolean inBetween(final int given, final int leftLimit, final int rightLimit) {
    return leftLimit <= given && given <= rightLimit;
  }

  /**
   * Validates, if given string is run argument.
   *
   * @param given given argument
   * @return true means it is, false otherwise
   */
  public static boolean isArgument(final String given) {
    if (!isSet(given)) {
      return false;
    }
    String passedValue = given.trim();
    if (passedValue.startsWith("-")
        || passedValue.startsWith("--")
        || !passedValue.startsWith("---")) {
      return true;
    }
    return false;
  }

  /**
   * Validates, if given path is a right directory path.
   *
   * @param path given path
   * @return true means, if is a right path, false otherwise
   */
  public static boolean isPath(String path) {
    // Check, if entered param has value
    if (!isSet(path)) {
      return false;
    }
    // Initialize pattern matcher
    if (filePathPattern == null) {
      filePathPattern =
          Pattern.compile("([A-Z|a-z]:\\\\[^*|\"<>?\\n]*)|(\\\\\\\\.*?\\\\.*)");
    }
    return filePathPattern.matcher(path).matches();
  }

}
