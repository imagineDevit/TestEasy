package io.github.imaginedevit.test.easy.data;

import java.util.Optional;

public sealed interface Option<T> {

    record Some<T>(T value) implements Option<T>{}

    record None<T>() implements Option<T>{}

    default T unwrap() throws Exception {
        return switch (this) {
            case Option.None<T> __ -> throw new Exception("");
            case Option.Some<T> some -> Optional.ofNullable(some.value).orElseThrow(() -> new Exception(""));
        };
    }
}
