package org.ttrzcinski;

import java.util.Arrays;
import java.util.List;
import org.ttrzcinski.clui.CLUI;
import org.ttrzcinski.utils.ClassParser;
import org.ttrzcinski.utils.ConsoleMenu;
import org.ttrzcinski.utils.MultiOut;
import org.ttrzcinski.utils.MultiOut.Output;
import org.ttrzcinski.utils.ParamCheck;

/**
 * Console entry point of "V Model Must" application.
 */
public final class Main {

  /**
   * Backend class of C.L.U.I. interface.
   */
  private static CLUI clui;

  /**
   * Hidden constructor - there is no point of initialization.
   */
  private Main() { }

  /**
   * Applications entry point - won't process without given params.
   *
   * @param args given params
   */
  public static void main(final String[] args) {

    // Prepare C.L.U.I. instance with passed arguments
    clui = new CLUI(args);

    // Check, if any param was passed
    if (!ParamCheck.isSet(args)) {
      clui.pass("No parameters were provided.");
      return;
    }

    // Check, if passed arguments contain known
    if (clui.checkInPassed("help")) {
      clui.pass("VMM - V-Model Must - generate unit tests structure following code base in Java.\n");
      clui.showAsHelpLegend();
    } else if (clui.checkInPassed("authors")) {
      clui.showAuthors();
    } else if (clui.checkInPassed("class")) {
      ClassParser classParser = new ClassParser();
      classParser.listMethods("org.ttrzcinski.utils.UnitTestGenerator");
    } else {
      // Prepare console menu and show it
      new ConsoleMenu()
          .withHeader("V-Model Must - generator of unit test cases.")
          .withItem("Set location of src catalog.")
          .withItem("Set location of test catalog.")
          .withItem("Validate settings.")
          .withItem("Generate unit tests.")
          .show();
    }
  }

}
