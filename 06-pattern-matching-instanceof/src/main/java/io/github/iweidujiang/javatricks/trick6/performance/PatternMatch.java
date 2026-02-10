package io.github.iweidujiang.javatricks.trick6.performance;

public class PatternMatch {

    public static void main(String[] args) {

    }

    public int test1() {
        Object obj = "abc";
        if (obj instanceof String s) {
            return s.length();
        }
        return 0;
    }

    public int test2() {
        Object obj = "abc";
        if (obj instanceof String) {
            String s = (String) obj;
            return s.length();
        }
        return 0;
    }
}
