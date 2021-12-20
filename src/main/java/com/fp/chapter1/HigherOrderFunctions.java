package com.fp.chapter1;

import java.util.function.BiFunction;
import java.util.function.Function;

// A HOF is a function that can:
// 1. take one or more functions as parameters
// 2. return functions as results
// 3. both of the above
public class HigherOrderFunctions {

    // Just a type alias, to read better
    interface StringTransformer extends Function<String, String>{}

    public static void main(String[] args) {
        // Function that takes a function as argument
        BiFunction<String, StringTransformer,  String> formatter = (s, st) -> st.apply(s);

        var string = "fp is nice";

        StringTransformer toUpperCase = String::toUpperCase;
        var upper = formatter.apply(string, toUpperCase);

        StringTransformer reverser = s -> new StringBuilder(s).reverse().toString();
        var reversed = formatter.apply(string, reverser);

        var toUpperReverser = reverser.compose(toUpperCase);
        var toUpperReversed = toUpperReverser.apply(string);

        System.out.println(upper);
        System.out.println(reversed);
        System.out.println(toUpperReversed);

        // Function that returns a function
        Function<String, StringTransformer> xAppender = s -> inner -> String.format("%s%s", inner, s);
        var fpAppender = xAppender.apply(" FP"); // Note the early binding of the value
        System.out.println(fpAppender.apply("We're playing with"));
    }

}
