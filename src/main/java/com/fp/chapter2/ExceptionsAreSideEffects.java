package com.fp.chapter2;

import java.util.stream.IntStream;

public class ExceptionsAreSideEffects {

    static int div(int x, int y) {
        // This has bad side effects
        return x / y; // -> new exit point on exception
    }

    public static void main(String[] args) {
        var a = div(10, 2); // 5
        var b = div(5, 2); // 2
        var c = div(3, 0); // <- BOOM

        System.out.print(IntStream.of(a, b, c).sum());
    }
}
