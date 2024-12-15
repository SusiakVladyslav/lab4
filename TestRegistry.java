package org.example;
import java.util.HashMap;
import java.util.Map;


class TestRegistry {
    private Map<String, TestInterface> testPrototypes = new HashMap<>();

    public void registerTest(String testPrototypeName, TestInterface prototype) {
        testPrototypes.put(testPrototypeName, prototype);
    }

    public TestInterface createTest(String testPrototypeName) {
        TestInterface prototype = testPrototypes.get(testPrototypeName);
        if (prototype != null) {
            return prototype.cloneTest();
        }
        throw new IllegalArgumentException("Test type not registered: " + testPrototypeName);
    }
}
