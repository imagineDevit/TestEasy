package io.github.imaginedevit.test.easy;

import io.github.imaginedevit.test.easy.fns.GivenFns;
import io.github.imaginedevit.test.easy.fns.ThenFns;
import io.github.imaginedevit.test.easy.fns.WhenFns;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestCaseTest {

    record Result(Integer value){}

    @Test
    void test() {

        TestCase.create("test multiplication", 2, new Result(1))

                .<GivenFns.TFunction<Integer>>
                        given("state is multiplied by 2", state -> state.map(i -> i *2).orElse(0))

                .<WhenFns.TFunction<Integer, Result>>
                        when("state is divided by 4", state -> state.map(i -> new Result(i/4)).orElse(null))

                .<ThenFns.TConsumer<Result>>
                        then("result value should be equal to 1", result -> assertEquals(result.value, 1))

                .report();
        ;
    }
}