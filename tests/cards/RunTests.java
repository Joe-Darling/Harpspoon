package cards;

import tests.TestRunner;

/**
 * Runs all of the tests in this package.  Tests are any class with a name
 * that ends in "Test".
 *
 * Simply copy this class into any new package to run the tests in that
 * package.
 *
 * Created by robertstjacquesjr on 6/7/17.
 */
public class RunTests {
    public static void main(String[] args) {
        String packageName = RunTests.class.getPackage().getName();
        TestRunner.runTestsInPackage(packageName);
    }
}
