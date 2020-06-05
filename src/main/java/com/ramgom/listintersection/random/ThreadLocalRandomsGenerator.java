package com.ramgom.listintersection.random;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class ThreadLocalRandomsGenerator implements RandomsGenerator {

    private static final int MIN_RANDOM = 0;
    private static final int MAX_RANDOM = Integer.MAX_VALUE;

    private static final RandomsGenerator INSTANCE = new ThreadLocalRandomsGenerator();

    static RandomsGenerator getInstance() {
        return INSTANCE;
    }

    private ThreadLocalRandomsGenerator() {}

    @Override
    public List<Integer> generateRandoms(int size) {
        return ThreadLocalRandom.current()
                .ints(size, MIN_RANDOM, MAX_RANDOM)
                .collect(ArrayList::new, List::add, (left, right) -> {});
    }
}
