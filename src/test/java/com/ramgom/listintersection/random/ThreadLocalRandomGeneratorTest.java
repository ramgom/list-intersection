package com.ramgom.listintersection.random;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ThreadLocalRandomGeneratorTest {

    private static final int LIST_SIZE = 10;

    private final RandomsGenerator randomGenerator = ThreadLocalRandomsGenerator.getInstance();

    @Test
    public void generateRandom() {

        List<Integer> randoms = randomGenerator.generateRandoms(LIST_SIZE);

        assertThat(randoms).hasSize(LIST_SIZE);
    }
}