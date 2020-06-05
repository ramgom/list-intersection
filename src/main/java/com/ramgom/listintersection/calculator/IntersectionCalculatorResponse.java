package com.ramgom.listintersection.calculator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class IntersectionCalculatorResponse {
    private final int intersectionSize;
    private final long timeToCalculateInMilliseconds;
}
