package com.fp.chapter2;

public class EffectsOnTheSide {

    static int inc(int x) {
        // Outch... this alters global state (differently with every call)
        System.out.println(System.currentTimeMillis());
        return x + 1;
    }

    public static void main(String[] args) {
        System.out.println(inc(1));
    }
}
