package org.ttrzcinski.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Parses other classes.
 */
public class ClassParser {

  /**
   * Creates new instance of pointed class.
   *
   * @param classFullName pointed class
   * @return instance of that class
   * @throws ClassNotFoundException if pointed class doesn't exist
   * @throws InstantiationException if class has blocked constructors or none
   * @throws IllegalAccessException if class suppose not to be reflected
   */
  public Object createInstanceOfClass(String classFullName)
      throws ClassNotFoundException, InstantiationException,
      IllegalAccessException {
    Class classTemp = Class.forName(classFullName);
    Object obj = classTemp.newInstance();
    return obj;
  }

  /**
   * Lists methods of pointed class.
   *
   * It looks like:<br/> public static java.lang.String org.ttrzcinski.utils.StringFix.simple(java.lang.String)
   *
   * @param classFullName given class name
   */
  public void listMethods(String classFullName) {
    try {
      Class thisClass = Class.forName(classFullName);
      Method[] methods = thisClass.getDeclaredMethods();

      for (Method method : methods) {
        System.out.println(method.toString());
      }
    } catch (Throwable e) {
      System.err.println(e);
    }
  }

  public void listVariables(String classFullName) {
    Field[] fields = new Field[1];
    try {
      Class classTemp = Class.forName(classFullName);
      fields = classTemp.getClass().getDeclaredFields();
    } catch (SecurityException iae) {
      iae.printStackTrace();
      System.err.println(iae);
    } catch (Throwable e) {
      System.err.println(e);
    }
    //
    if (fields.length > 0) {
      for (Field field : fields) {
        System.out.println(field.toString());
      }
    } else {
      System.out.println("No public fields found.");
    }
  }
}
