package com.fp.chapter3;

import java.util.Optional;
import java.util.stream.Stream;

import io.vavr.control.Either;
import io.vavr.control.Try;

public class ExceptionsAsReturnType {

    static Optional<Integer> div(int x, int y) {
        try {
            return Optional.of(x / y);
        } catch (ArithmeticException e) {
            return Optional.empty();
        }
    }

    static Either<Throwable, Integer> div2(int x, int y) {
        return Try.of(() -> x / y).toEither();
    }

    public static void main(String[] args) {
        var a = div(10, 2); // Optional[5]
        var b = div(5, 2); // Optional[2]
        var c = div(3, 0); // <- empty

        System.out.println(Stream.of(a, b, c).mapToInt(o -> o.orElse(0)).sum());
        System.out.println(Stream.of(a, b, c).mapToInt(o -> o.orElse(1)).reduce(1, (x, y) -> x * y));

        var x = div2(10, 2); // Either.Right[5]
        var y = div2(5, 2); // Either.Right[2]
        var z = div2(3, 0); // <- Either.Left[ArithmeticException]

        System.out.println(Stream.of(x, y, z).mapToInt(e -> e.getOrElse(0)).sum());
    }
}
