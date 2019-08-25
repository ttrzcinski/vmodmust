package org.ttrzcinski.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Generated unit test in pointed project.
 */
public class UnitTestGenerator {

  /**
   * Path to code sources.
   */
  private String srcPath;
  /**
   * Path to test catalog.
   */
  private String testPath;
  /**
   * Flag marking overriding existing files.
   */
  private boolean overrideFlag;

  /**
   * Creates new instance of unit test generator.
   */
  public UnitTestGenerator() { }

  /**
   * Sets tests directory path with given directory.
   *
   * @param directory given directory
   * @return prepared instance of unit test generator
   */
  public final UnitTestGenerator withTests(String directory) {
    if (ParamCheck.isPath(directory)) {
      this.srcPath = directory;
    }
    return this;
  }

  /**
   * Sets source path with given directory.
   *
   * @param directory given directory
   * @return prepared instance of unit test generator
   */
  public final UnitTestGenerator withSource(String directory) {
    if (ParamCheck.isPath(directory)) {
      this.srcPath = directory;
    }
    return this;
  }

  /**
   * Marks, that old unit test case classes will be overridden.
   *
   * @return prepared instance of unit test generator
   */
  public final UnitTestGenerator withOverride() {
    this.overrideFlag = true;
    return this;
  }

  /**
   * Checks, if mandatory parameters are set.
   *
   * @return true means all needed parameters are set, false otherwise
   */
  public final boolean validateParameters() {
    // Check, if source directory is set
    // Check, if test directory is set
    return (this.srcPath != null) && (this.testPath != null);
  }

  /**
   * Generates unit test cases after prior setting params.
   */
  public final void generate() {
    System.out.println("Starting to generate unit tests..");
    final List<String> srcFiles = FilesExt.allFileNamesOf(this.srcPath);
    // Generate unit test classes one by one
    for (String fullFilePath : srcFiles) {
      try {
        // Prepare unit test file path
        final String unitPath = fullFilePath.replace(this.srcPath, this.testPath);
        // If unit test file already exists, omit it
        final File tempFile = new File(unitPath);
        if (tempFile.exists()) { continue; }
        // Create empty file with test case
        Files.createFile(Paths.get(unitPath));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
