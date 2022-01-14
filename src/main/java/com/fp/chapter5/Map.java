package com.fp.chapter5;

import java.util.ArrayList;
import java.util.function.Function;

import io.vavr.collection.Stream;

public class Map {

    // seq(A) [1, 2, 3, 4]
    // f: A -> B
    // seq(A) map f => seq(B)

    static <A, B> Stream<B> map(Stream<A> col, Function<A, B> f) {
        var result = Stream.<B>of();

        for (A a: col) {
            result = result.append(f.apply(a));
        }

        return result;
    }

    static <A, B> Stream<B> mapUsingFold(Stream<A> col, Function<A, B> f) {
        return Fold.foldLeft(col, (x, s) -> s.append(f.apply(x)), Stream.of());
    }

    public static void main(String[] args) {
        var squared = map(Stream.of(1, 2, 3, 4), x -> x * x);
        var squaredUsingFold = mapUsingFold(Stream.of(1, 2, 3, 4), x -> x * x);

        System.out.println(new ArrayList<>(squared.asJava()));
        System.out.println(new ArrayList<>(squaredUsingFold.asJava()));
    }

}
