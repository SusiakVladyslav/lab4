package org.example;


public class AutomatedTestingSystem {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        TestRegistry registry = new TestRegistry();

        registry.registerTest("unit_add", new UnitTest("Calculator Add Test", () -> {
            int result = calculator.add(3, 7);
            if (result != 10) {
                throw new AssertionError("Expected 10 but got " + result);
            }
        }));

        registry.registerTest("unit_subtract", new UnitTest("Calculator Subtract Test", () -> {
            int result = calculator.subtract(10, 5);
            if (result != 5) {
                throw new AssertionError("Expected 5 but got " + result);  // Кидаємо помилку, якщо результат неправильний
            }
        }));


        registry.registerTest("integration_add_subtract", new IntegrationTest(
                "Calculator Integration Test",
                () -> {

                    int addResult = calculator.add(10, 3);
                    if (addResult != 13) {
                        throw new AssertionError("Expected 10 + 3 = 13 but got " + addResult);
                    }


                    int subtractResult = calculator.subtract(15, 1);
                    if (subtractResult != 14) {
                        throw new AssertionError("Expected 15 - 1 = 14 but got " + subtractResult);
                    }
                }
        ));

        registry.registerTest("ui", new UITest());

        TestExecutor executor = new StandardTestExecutor();

        TestInterface unitTest1 = registry.createTest("unit_add");
        executor.executeTest(unitTest1);

        TestInterface unitTest2 = registry.createTest("unit_subtract");
        executor.executeTest(unitTest2);

        TestInterface integrationTest = registry.createTest("integration_add_subtract");
        executor.executeTest(integrationTest);

        TestInterface uiTest = registry.createTest("ui");
        executor.executeTest(uiTest);
    }
}