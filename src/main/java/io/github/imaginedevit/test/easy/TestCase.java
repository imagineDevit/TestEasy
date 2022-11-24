package io.github.imaginedevit.test.easy;

import io.github.imaginedevit.test.easy.fns.GivenFns;
import io.github.imaginedevit.test.easy.fns.ThenFns;
import io.github.imaginedevit.test.easy.fns.WhenFns;
import io.github.imaginedevit.test.easy.msgs.StmtMsg;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Test case representation
 * @param <T> test case state type
 * @param <R> test case result type
 */
public class TestCase<T,R> {

    private final String name;
    private T state;
    private R result;
    private final R expectedResult;

    /**
     * Fns
     */
    private final List<GivenFns> givenFns = new ArrayList<>();
    private final List<WhenFns> whenFns = new ArrayList<>();
    private final List<ThenFns> thenFns = new ArrayList<>();


    /**
     * Messages
     */
    private final List<StmtMsg> givenMsgs = new ArrayList<>();
    private final List<StmtMsg> whenMsgs = new ArrayList<>();
    private final List<StmtMsg> thenMsgs = new ArrayList<>();


    /**
     * Constructor with name
     * @param name the testCase name
     */
    private TestCase(String name) {
        this.name = name;
        this.expectedResult = null;
    }

    /**
     * Constructor with name and state
     * @param name the testCase name
     * @param state the testCase state
     */
    private TestCase(String name, T state) {
        this.name = name;
        this.state = state;
        this.expectedResult = null;
    }

    /**
     * Constructor with name, state and expected result
     * @param name the testCase name
     * @param state the testCase state
     * @param expectedResult the testCase expected result
     */
    private TestCase(String name, T state, R expectedResult) {
        this.name = name;
        this.state = state;
        this.expectedResult = expectedResult;
    }

    /**
     * Create method
     * @param name test case name
     * @return  the test case
     */
    public  static <T,R> TestCase<T,R> create(String name){
        return new TestCase<>(name);
    }

    /**
     * Create method
     * @param name test case name
     * @param state test case state
     * @return  the test case
     */
    public  static <T,R> TestCase<T,R> create(String name, T state){
        return new TestCase<>(name, state);
    }

    /**
     * Create method
     * @param name test case name
     * @param state test case state
     * @param expectedResult test case expected result
     * @return  the test case
     */
    public  static <T,R> TestCase<T,R> create(String name, T state, R expectedResult){
        return new TestCase<>(name, state, expectedResult);
    }

    public <F extends GivenFns> GivenStmt<T,R> given(String message, F fn) {
        this.andGiven(message, fn);
        return new GivenStmt<>(this);
    }

    protected <F extends GivenFns> void andGiven(String message, F fn) {
        this.givenMsgs.add(new StmtMsg(message));
        this.applyGiven(fn);
    }

    protected <F extends WhenFns> WhenStmt<T,R> when(String message, F fn) {
        this.andWhen(message, fn);
        return new WhenStmt<>(this);
    }

    protected <F extends WhenFns> void andWhen(String message, F fn) {
        this.whenMsgs.add(new StmtMsg(message));
        this.applyWhen(fn);
    }

    protected <F extends ThenFns> ThenStmt<T,R> then(String message, F fn) {
        andThen(message, fn);
        return new ThenStmt<>(this);
    }

    protected <F extends ThenFns> void andThen(String message, F fn) {
        this.applyThen(fn);
        thenMsgs.add(new StmtMsg(message));
    }

    public void report() {

    }

    @SuppressWarnings("unchecked")
    private void applyGiven(GivenFns fn) {
        switch (fn) {
            case GivenFns.TRunnable r -> r.run();
            case GivenFns.TConsumer<?> c -> ((GivenFns.TConsumer<T>) c).accept(Optional.ofNullable(state));
            case GivenFns.TFunction<?> f -> state = ((GivenFns.TFunction<T>) f).apply(Optional.ofNullable(state));
            case GivenFns.TSupplier<?> s -> state = ((GivenFns.TSupplier<T>) s).get();
        }
    }

    @SuppressWarnings("unchecked")
    private void applyWhen(WhenFns fn) {
        switch (fn) {
            case WhenFns.TConsumer<?> c -> ((WhenFns.TConsumer<T>) c).accept(Optional.ofNullable(state));
            case WhenFns.TFunction<?,?> f -> result = ((WhenFns.TFunction<T,R>) f).apply(Optional.ofNullable(state));
            case WhenFns.TSupplier<?> s -> result = ((WhenFns.TSupplier<R>) s).get();
        }
    }

    @SuppressWarnings("unchecked")
    private void applyThen(ThenFns fn) {
        switch (fn) {
            case ThenFns.TConsumer<?> c -> ((ThenFns.TConsumer<R>) c).accept(result);
            case ThenFns.TBiConsumer<?> bc -> ((ThenFns.TBiConsumer<R>) bc).accept(result, Optional.ofNullable(expectedResult));
        }
    }

}
