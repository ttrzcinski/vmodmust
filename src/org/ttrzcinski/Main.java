package org.ttrzcinski;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.ttrzcinski.utils.ClassParser;
import org.ttrzcinski.utils.ConsoleMenu;
import org.ttrzcinski.utils.EnvArgument;
import org.ttrzcinski.utils.MultiOut;
import org.ttrzcinski.utils.MultiOut.Output;
import org.ttrzcinski.utils.ParamCheck;

/**
 * Console entry point of "V Model Must" application.
 */
public final class Main {

  private Main() { }

  /**
   * Applications entry point - won't process without given params.
   *
   * @param args given params
   */
  public static void main(final String[] args) {

    ClassParser classParser = new ClassParser();
    classParser.listVariables("java.lang.Integer");

    // Set multi output
    final MultiOut multiOut = MultiOut.getInstance();
    multiOut.put(Output.STD_OUTPUT);

    // Convert arguments to list
    final List<String> listOfArgs = Arrays.asList(args);

    // Check, if any param was passed
    if (!ParamCheck.isSet(args)) {
      multiOut.pass("No parameters were provided.");
      return;
    }

    // Show, what passed the validation
    showWholeArgumentsArray(args);

    // Check, if passed arguments contain known
    if (listOfArgs.contains("-h")) {
      System.out.println("VMM - V-Model Must - generate unit tests structure following code base in Java.\n");
      System.out.println("Possible arguments:");
      // TODO Change it to use plain text file
      List<EnvArgument> envArguments = new ArrayList<>();
      envArguments.addAll(
          Arrays.asList(new EnvArgument[]{
              new EnvArgument().withAcronym("h").withName("help")
                  .withDescription("Present help with possible arguments to use."),
              new EnvArgument().withAcronym("a").withName("author")
                  .withDescription("List of authors, who created the application."),
              new EnvArgument().withAcronym("src").withName("source")
                  .withDescription("Path to src catalog within the project."),
              new EnvArgument().withAcronym("t").withName("test")
                  .withDescription("Path to test catalog within the project.")
          })
      );
      envArguments.stream()
          .map(EnvArgument::asHelp)
          .forEach(System.out::println);
      return;
    } else if (listOfArgs.containsAll(Arrays.asList("--author", "-author"))) {
      System.out.println("Created by <Tomasz Trzciński> at trzcinski.tomasz.1988@gmail.com");
      return;
    }

    // Prepare console menu and show it
    new ConsoleMenu()
        .withHeader("V-Model Must - generator of unit test cases.")
        .withItem("Set location of src catalog.")
        .withItem("Set location of test catalog.")
        .withItem("Validate settings.")
        .withItem("Generate unit tests.")
        .show();
  }

  /**
   * Shows whole set of passed arguments - for test purposes.
   *
   * @param args given argument's array
   */
  private static void showWholeArgumentsArray(final String[] args) {
    final List<String> orderedArgs = new ArrayList<>();
    final List<String> unknownArgs = new ArrayList<>();
    for (String arg : args) {
      if (ParamCheck.isArgument(arg)) {
        orderedArgs.add(arg);
      } else {
        unknownArgs.add(arg);
      }
    }

    System.out.println("Those were right:");
    for (String theArg : orderedArgs) {
      System.out.println(theArg);
    }

    System.out.println("\nThose were not:");
    for (String theArg : unknownArgs) {
      System.out.println(theArg);
    }
  }

}
