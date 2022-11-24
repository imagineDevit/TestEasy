package io.github.imaginedevit.test.easy;

import io.github.imaginedevit.test.easy.fns.GivenFns;
import io.github.imaginedevit.test.easy.fns.WhenFns;

public record GivenStmt<T,R>(TestCase<T,R> testCase) {

    public <F extends GivenFns> GivenStmt<T,R> and(String message, F fn) {
        testCase.andGiven(message, fn);
        return this;
    }

    public <F extends WhenFns> WhenStmt<T,R> when(String message, F fn) {
        return testCase.when(message, fn);
    }

}
