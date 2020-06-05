package com.ramgom.listintersection.random;

public class RandomsGeneratorFactory {

    public static RandomsGenerator getLocalRandomGenerator() {
        return ThreadLocalRandomsGenerator.getInstance();
    }
}
