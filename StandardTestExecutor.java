package org.example;

class StandardTestExecutor implements TestExecutor {
    @Override
    public void executeTest(TestInterface test) {
        test.execute();
        System.out.println("Test execution completed.\n");
    }
}