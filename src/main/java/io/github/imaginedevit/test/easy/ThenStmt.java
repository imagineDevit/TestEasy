package io.github.imaginedevit.test.easy;

import io.github.imaginedevit.test.easy.fns.ThenFns;

public record ThenStmt<T,R>(TestCase<T,R> testCase) {
    public <F extends ThenFns> ThenStmt<T,R> and(String message, F fn) {
        testCase.andThen(message, fn);
        return this;
    }

    public void report() {
        testCase.report();
    }
}
