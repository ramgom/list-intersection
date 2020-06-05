package com.ramgom.listintersection.intersections;

import java.util.List;
import java.util.Set;

@FunctionalInterface
public interface IntersectionFunction {

    Set<Integer> calculateIntersection(List<Integer> first, List<Integer> second);
}
