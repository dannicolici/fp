package com.fp.chapter4;

import java.util.function.BiFunction;
import java.util.function.Function;

import io.vavr.control.Either;
import io.vavr.control.Try;

public class MoveSideEffectsOutside {

    interface IntDivider extends BiFunction<Integer, Integer, Either<Throwable, Integer>> {}

    // Pure function (same output for same input and no side effects)
    static IntDivider div = (x, y) -> Try.of(() -> x / y).toEither();

    // Decorate with side effect (logging in this case). System.out, but can be anything, e.g. a logger.
    static Function<String, IntDivider> divWithTimeLog = s -> (x, y) -> {
        System.out.printf(" %s %s %d / %d: ", System.currentTimeMillis(), s, x, y);
        return div.apply(x, y);
    };

    public static void main(String[] args) {
        var res1 = divWithTimeLog
                .apply("Dividing")
                .apply(10, 0);

        System.out.println(res1.mapLeft(t -> String.format("Cannot divide because of: %s", t)));

        var res2 = divWithTimeLog
                .apply("Result of dividing")
                .apply(10, 5);

        System.out.println(res2.mapLeft(t -> String.format("Cannot divide because of: %s", t)));
    }
}
