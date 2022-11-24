package io.github.imaginedevit.test.easy.fns;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public sealed interface WhenFns {

    non-sealed interface TSupplier<R> extends WhenFns, Supplier<R> {}
    non-sealed interface TConsumer<T> extends WhenFns, Consumer<Optional<T>> {}
    non-sealed interface TFunction<T,R> extends WhenFns, Function<Optional<T>,R> {}
}
