package org.fsq.requestbodygenerator.data;

/**
 * May be used in Future to Wrap the Data provided by MockDataProvider
 */
public class MockDataWrapper {
    private Object data;

    public MockDataWrapper(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
