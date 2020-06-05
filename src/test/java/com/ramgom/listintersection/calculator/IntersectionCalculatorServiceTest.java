package com.ramgom.listintersection.calculator;


import com.google.common.base.Stopwatch;
import com.ramgom.listintersection.intersections.IntersectionFunction;
import com.ramgom.listintersection.random.RandomsGenerator;
import mockit.Expectations;
import mockit.FullVerifications;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

class IntersectionCalculatorServiceTest {

    private static final long DURATION = 100;

    private static final int FIRST_LIST_SIZE = 10;
    private static final int SECOND_LIST_SIZE = 100;
    private static final List<Integer> FIRST_LIST = new ArrayList<>();
    private static final List<Integer> SECOND_LIST = new ArrayList<>();
    private static final Set<Integer> INTERSECTION = Set.of(1, 2, 4);

    @Tested
    private IntersectionCalculatorService calculatorService;

    @Injectable
    private RandomsGenerator randomsGenerator;

    @Test
    public void calculateIntersection(@Mocked IntersectionFunction intersectionFunction, @Mocked Stopwatch stopwatch) {

        new Expectations() {{
            randomsGenerator.generateRandoms(FIRST_LIST_SIZE); result = FIRST_LIST;
            randomsGenerator.generateRandoms(SECOND_LIST_SIZE); result = SECOND_LIST;

            Stopwatch.createStarted(); result = stopwatch;
            stopwatch.stop(); times = 1;
            stopwatch.elapsed(TimeUnit.MILLISECONDS); result = DURATION;

            intersectionFunction.calculateIntersection(FIRST_LIST, SECOND_LIST); result = INTERSECTION;
        }};

        IntersectionCalculatorResponse response = calculatorService.calculateIntersection(FIRST_LIST_SIZE, SECOND_LIST_SIZE, intersectionFunction);

        assertThat(response.getIntersectionSize()).isEqualTo(INTERSECTION.size());
        assertThat(response.getTimeToCalculateInMilliseconds()).isEqualTo(DURATION);

        new FullVerifications() {{}};
    }
}