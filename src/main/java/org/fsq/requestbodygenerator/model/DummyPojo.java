package org.fsq.requestbodygenerator.model;

import lombok.Data;

@Data
public class DummyPojo {
    private String testName;
    private Long testLargeNumber;
    private Integer testSmallNumber;
    private int testPrimitiveNumber;
    private Object willNotProvideDummyData;
}