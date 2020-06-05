package com.ramgom.listintersection.intersections;

import mockit.Expectations;
import mockit.FullVerifications;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class HashIntersectionFunctionTest {

    private static final List<Integer> FIRST_LIST = Arrays.asList(1, 2, 3, 3, 4);
    private static final List<Integer>  SECOND_LIST = Arrays.asList(3, 4, 5);
    private static final Set<Integer> INTERSECTION = Set.of(3, 4);

    @Test
    public void calculateIntersection_with_EmptyList() {
        HashIntersectionFunction function = new HashIntersectionFunction(HashIntersectionList.FIRST);

        new Expectations(function) {{
            function.calculateIntersection((Set<Integer>) any, (List<Integer>) any); times = 0;
        }};

        Set<Integer> result = function.calculateIntersection(Collections.emptyList(), SECOND_LIST);

        assertThat(result).isEmpty();

        result = function.calculateIntersection(FIRST_LIST, Collections.emptyList());

        assertThat(result).isEmpty();

        new FullVerifications() {{}};
    }

    @Test
    public void calculateIntersection_with_FirstListInHash() {
        HashIntersectionFunction function = new HashIntersectionFunction(HashIntersectionList.FIRST);

        new Expectations(function) {{}};

        Set<Integer> results = function.calculateIntersection(FIRST_LIST, SECOND_LIST);

        assertThat(results).isEqualTo(INTERSECTION);

        new FullVerifications() {{
            function.calculateIntersection(new HashSet<>(FIRST_LIST), SECOND_LIST); times = 1;
        }};
    }

    @Test
    public void calculateIntersection_with_SecondListInHash() {
        HashIntersectionFunction function = new HashIntersectionFunction(HashIntersectionList.SECOND);

        new Expectations(function) {{}};

        Set<Integer> results = function.calculateIntersection(FIRST_LIST, SECOND_LIST);

        assertThat(results).isEqualTo(INTERSECTION);

        new FullVerifications() {{
            function.calculateIntersection(new HashSet<>(SECOND_LIST), FIRST_LIST); times = 1;
        }};
    }
}