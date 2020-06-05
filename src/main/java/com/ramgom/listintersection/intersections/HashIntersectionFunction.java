package com.ramgom.listintersection.intersections;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HashIntersectionFunction implements IntersectionFunction {

    private final HashIntersectionList listToPutInHash;

    public HashIntersectionFunction(HashIntersectionList listToPutInHash) {
        this.listToPutInHash = listToPutInHash;
    }

    @Override
    public Set<Integer> calculateIntersection(List<Integer> firstList, List<Integer> secondList) {
        if (firstList.isEmpty() || secondList.isEmpty()) {
            return Collections.emptySet();
        }

        return listToPutInHash == HashIntersectionList.FIRST ?
                calculateIntersection(new HashSet<>(firstList), secondList) :
                calculateIntersection(new HashSet<>(secondList), firstList);
    }

    protected Set<Integer> calculateIntersection(Set<Integer> setValues, List<Integer> listValues) {
        return listValues.stream()
                .filter(setValues::contains)
                .collect(Collectors.toSet());
    }
}
