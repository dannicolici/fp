package com.fp.chapter1;

import java.util.function.Function;

public class Functions {

    // Mathematics
    // f(x)=y, x:Domain->Codomain

    // JAVA
    // Classic style functions
    static int increment(int x) {
        return x + 1;
    }

    static int decrement(int x) {
        return x - 1;
    }


    public static void main(String[] args) {
        // Using classic style functions
        var a = increment(1);
        var b = decrement(3);
        assert a == b;

        // Compose classic style function calls
        var c = decrement(increment(2));
        assert c == 2;

        // Functions as first class citizens
        Function<Integer, Integer> inc = x -> x + 1;
        Function<Integer, Integer> dec = x -> x - 1;
        a = inc.apply(1);
        b = dec.apply(3);
        assert a == b;

        // Compose new style function calls
        c = inc.apply(dec.apply(2));
        assert c == 2;

        // Advantage of new style: lazy composition a.k.a. recipes, (g o f) but not applied to arguments
        var incDec = inc.compose(dec); // <- recipe (a.k.a algorithm), lazy
        c = incDec.apply(2);
        assert c == 1;
    }
}
