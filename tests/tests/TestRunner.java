package tests;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Helper class that runs the main method in test classes.  You may examine
 * this code if you are curious, but don't be surprised if you find it
 * confusing.  We will not cover most of the code used here in CS2.
 *
 * Created by robertstjacquesjr on 6/7/17.
 */
public class TestRunner {
    // the name of the folder containing the tests in the current project
    private static final String TESTS_FOLDER = "tests";
    // the name of the package containing the tests helper classes in the current
    // project
    private static final String TESTS_PACKAGE = "tests";
    // the name of the main method
    private static final String MAIN = "main";
    // the arguments used to invoke the main method
    private static final Object[] ARGUMENTS = { new String[0] };
    // this safely creates an array of parameter types that contains only the class
    // representing a string array
    private static final Class<?>[] PARAMETERS = {ARGUMENTS[0].getClass()};
    // the file separator on the current operating system
    private static final String FILE_SEPARATOR = System.getProperty("file.separator");

    /**
     * Runs all of the tests in the specified package.  Looks for the package
     * in the "tests" folder of the current project.
     *
     * @param packageName  The name of the package in which to look for tests.
     */
    public static void runTestsInPackage(String packageName) {
        System.out.println("Running all tests in package: " + packageName);

        String packagePath = packageName.replace(".", FILE_SEPARATOR);

        File tests = new File("tests" + FILE_SEPARATOR + packagePath);

        String[] classes = tests.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith("Test.java");
            }
        });

        if(classes != null && classes.length > 0 ) {
            for (String classFileName : classes) {
                int dotIndex = classFileName.lastIndexOf('.');
                String className = packageName + "." + classFileName.substring(0, dotIndex);
                runTestClass(className);
            }
        }
        else {
            System.out.println("No tests found in package: " + packageName);
        }
    }

    /**
     * Runs the main method in the class with the specified name.
     *
     * @param className The name of the class on which to run the main method.
     */
    public static void runTestClass(String className) {
        System.out.println("Running Test: " + className);
        try {
            Class test = Class.forName(className);
            Method main = test.getMethod( MAIN, PARAMETERS );

            main.invoke(null, ARGUMENTS );

        } catch (ClassNotFoundException cnfe) {
            System.err.println("Failed to load the specified class!");
            cnfe.printStackTrace();
        } catch ( NoSuchMethodException nsme) {
            System.err.println("The specified class does not have a main method!");
            nsme.printStackTrace();
        } catch(Exception e) {
            System.err.println("Error invoking main (is it public?)!");
            e.printStackTrace();
        }
    }

    /**
     * Runs all of the tests in any package in the "tests" folder that is not
     * also named "tests."
     *
     * @param args Not used.
     */
    public static void main(String[] args) {
        File tests = new File(TESTS_FOLDER);

        String[] packages = tests.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                File potential = new File(dir, name);
                return !name.equals(TESTS_PACKAGE) && potential.isDirectory();
            }
        });

        if(packages != null) {
            for (String pkg : packages) {
                runTestsInPackage(pkg);
            }
        }
    }
}
