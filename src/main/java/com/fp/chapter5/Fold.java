package com.fp.chapter5;

import java.util.function.BiFunction;

import io.vavr.collection.Stream;

public class Fold {

    // seq(A) [1, 2, 3, 4]
    // f: (A, B) -> B
    // fold seq(A) f initialVal => B

    static <A, B> B foldLeft(Stream<A> col, BiFunction<A, B, B> f, B initial) {
        if (col.isEmpty()) return initial;

        var accumulator = f.apply(col.head(), initial);
        return foldLeft(col.tail(), f, accumulator);
    }

    static <A, B> B foldRight(Stream<A> col, BiFunction<A, B, B> f, B initial) {
        if (col.isEmpty()) return initial;

        return f.apply(col.head(), foldRight(col.tail(), f, initial));
    }

    public static void main(String[] args) {
        System.out.println(foldLeft(Stream.of(1, 2, 3, 4), Integer::sum, 0));
        System.out.println(foldRight(Stream.of(1, 2, 3, 4), Integer::sum, 0));

        System.out.println(Stream.of(1, 2, 3, 4).foldLeft(0, Integer::sum));
    }

}
