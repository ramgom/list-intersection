package com.ramgom.listintersection.calculator;

import com.google.common.base.Stopwatch;
import com.ramgom.listintersection.intersections.IntersectionFunction;
import com.ramgom.listintersection.random.RandomsGenerator;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class IntersectionCalculatorService {

    private final RandomsGenerator randomsGenerator;

    public IntersectionCalculatorResponse calculateIntersection(
            int sizeFirstList,
            int sizeSecondList,
            IntersectionFunction function) {

        List<Integer> firstList = randomsGenerator.generateRandoms(sizeFirstList);
        List<Integer> secondList = randomsGenerator.generateRandoms(sizeSecondList);

        Stopwatch stopwatch = Stopwatch.createStarted();
        Set<Integer> intersection = function.calculateIntersection(firstList, secondList);
        stopwatch.stop();

        return new IntersectionCalculatorResponse(intersection.size(), stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }
}
