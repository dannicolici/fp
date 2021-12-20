package com.fp.chapter3;

import java.util.function.Function;

public class DeferredSideEffects {

    interface SideEffect extends Runnable {}

    static Function<SideEffect, Integer> inc(int x) {
        return sideEffect -> {
            sideEffect.run();
            return x + 1;
        };
    }

    // Exercise: How can we implement the strategy pattern using this technique?

    public static void main(String[] args) {
        SideEffect sideEffect = () -> System.out.println(System.currentTimeMillis());
        System.out.println(inc(1).apply(sideEffect));

        // Thanks to decoupling, we can "mute" the side effect - for testing, for example
        System.out.println(inc(1).apply(() -> {}));
    }
}
