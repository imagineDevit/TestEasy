package io.github.imaginedevit.test.easy;

import io.github.imaginedevit.test.easy.fns.GivenFns;
import io.github.imaginedevit.test.easy.fns.ThenFns;
import io.github.imaginedevit.test.easy.fns.WhenFns;

public record WhenStmt<T,R>(TestCase<T,R> testCase) {

    public <F extends WhenFns> WhenStmt<T,R> and(String message, F fn) {
        testCase.andWhen(message, fn);
        return this;
    }

    public <F extends ThenFns> ThenStmt<T,R> then(String message, F fn) {
        return testCase.then(message, fn);
    }

}
