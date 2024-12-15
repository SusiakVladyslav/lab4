package org.example;

class UITest implements TestInterface {
    @Override
    public TestInterface cloneTest() {
        return new UITest();
    }

    @Override
    public void execute() {
        System.out.println("Executing UI Test...");
    }
}
