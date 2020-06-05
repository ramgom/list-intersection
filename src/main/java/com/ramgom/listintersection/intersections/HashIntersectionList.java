package com.ramgom.listintersection.intersections;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum HashIntersectionList {

    FIRST("First"), SECOND("Second");

    private final String readableValue;
}
