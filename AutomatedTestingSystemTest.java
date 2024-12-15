package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AutomatedTestingSystemTest {

    @Test
    void testUnitTestExecution() {
        Calculator calculator = new Calculator();

        UnitTest additionTest = new UnitTest("Addition Test", () -> {
            int result = calculator.add(2, 3);
            if (result != 5) {
                throw new AssertionError("Expected 2 + 3 = 5 but got " + result);
            }
        });

        assertDoesNotThrow(additionTest::execute, "Unit test execution threw an unexpected exception");
    }

    @Test
    void testIntegrationTestExecution() {
        Calculator calculator = new Calculator();

        IntegrationTest integrationTest = new IntegrationTest("Integration Test", () -> {
            int addResult = calculator.add(5, 3);
            if (addResult != 8) {
                throw new AssertionError("Expected 5 + 3 = 8 but got " + addResult);
            }

            int subtractResult = calculator.subtract(10, 5);
            if (subtractResult != 5) {
                throw new AssertionError("Expected 10 - 5 = 5 but got " + subtractResult);
            }
        });

        assertDoesNotThrow(integrationTest::execute, "Integration test execution threw an unexpected exception");
    }

    @Test
    void testTestRegistryAndExecutor() {
        Calculator calculator = new Calculator();

        TestRegistry registry = new TestRegistry();
        registry.registerTest("unit_add", new UnitTest("Addition Test", () -> {
            int result = calculator.add(2, 3);
            if (result != 5) {
                throw new AssertionError("Expected 2 + 3 = 5 but got " + result);
            }
        }));

        registry.registerTest("unit_subtract", new UnitTest("Subtraction Test", () -> {
            int result = calculator.subtract(10, 4);
            if (result != 6) {
                throw new AssertionError("Expected 10 - 4 = 6 but got " + result);
            }
        }));

        StandardTestExecutor executor = new StandardTestExecutor();

        TestInterface unitAddTest = registry.createTest("unit_add");
        assertDoesNotThrow(() -> executor.executeTest(unitAddTest), "Addition Unit Test execution failed");

        TestInterface unitSubtractTest = registry.createTest("unit_subtract");
        assertDoesNotThrow(() -> executor.executeTest(unitSubtractTest), "Subtraction Unit Test execution failed");
    }

    @Test
    void testCloneableTests() {
        TestRegistry registry = new TestRegistry();
        UnitTest originalTest = new UnitTest("Original Test", () -> {});
        registry.registerTest("unit_clone_test", originalTest);

        TestInterface clonedTest = registry.createTest("unit_clone_test");

        assertNotSame(originalTest, clonedTest, "Cloned test is the same instance as the original");
    }
}
