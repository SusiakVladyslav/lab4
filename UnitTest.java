package org.example;

class UnitTest implements TestInterface {
    private final String unitTestName;
    private final Runnable testLogic;

    public UnitTest(String unitTestName, Runnable testLogic) {
        this.unitTestName = unitTestName;
        this.testLogic = testLogic;
    }

    @Override
    public TestInterface cloneTest() {
        return new UnitTest(this.unitTestName, this.testLogic);
    }

    @Override
    public void execute() {
        System.out.println("Executing Unit Test: " + unitTestName);
        try {
            testLogic.run();
            System.out.println("Unit Test '" + unitTestName + "' passed!");
        } catch (AssertionError e) {
            System.out.println("Unit Test '" + unitTestName + "' failed: " + e.getMessage());
        }
    }
}