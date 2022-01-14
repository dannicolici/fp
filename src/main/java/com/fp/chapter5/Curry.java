package com.fp.chapter5;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.function.BiFunction;
import java.util.function.Function;

import io.vavr.Function2;

public class Curry {

    // (A, B) -> C
    // curry(f) => A -> B -> C (curriedF)
    // curriedF(x) => B -> C (x: A)

    // BiFunction<A, B, C> -> Function<A, Function<B, C>>
    static <A, B, C> Function<A, Function<B, C>> curry(BiFunction<A, B, C> f) {
        return a -> b -> f.apply(a, b);
    }

    public static void main(String[] args) {
        BiFunction<OutputStream, Character, OutputStream> write = (w, n) -> {
            try {
                w.write(n);
            } catch (IOException e) { }

            return w;
        };

        var curriedWrite = curry(write);

        var consoleWriter = curriedWrite.apply(System.out);
        var sw = new StringWriter();
        OutputStream swo = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                sw.write(b);
            }
        };
        var stringWriter = curriedWrite.apply(swo);
        consoleWriter.apply('5');
        stringWriter.apply('t');
        System.out.println(sw);

        Function2<Integer, Integer, Integer> add = (a, b) -> a + b;
        var curriedAdd = add.curried();
        var add5 = curriedAdd.apply(5);
        System.out.println(add5.apply(10));
    }
}
