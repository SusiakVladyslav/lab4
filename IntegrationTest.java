package org.example;

class IntegrationTest implements TestInterface {
    private final String testName;
    private final Runnable testLogic;

    public IntegrationTest(String testName, Runnable testLogic) {
        this.testName = testName;
        this.testLogic = testLogic;
    }

    @Override
    public TestInterface cloneTest() {
        return new IntegrationTest(testName, testLogic); // Клонуємо тест із тим самим ім'ям і логікою
    }

    @Override
    public void execute() {
        System.out.println("Executing Integration Test: " + testName);
        try {
            testLogic.run(); // Виконуємо логіку тесту
            System.out.println("Integration Test '" + testName + "' passed!\n");
        } catch (AssertionError e) {
            System.out.println("Integration Test '" + testName + "' failed: " + e.getMessage() + "\n");
        }
    }
}
