package io.github.imaginedevit.test.easy.fns;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;


public sealed interface GivenFns {

    @FunctionalInterface
    non-sealed interface TRunnable extends GivenFns, Runnable{}
    @FunctionalInterface
    non-sealed interface TSupplier<T> extends GivenFns, Supplier<T> {}
    @FunctionalInterface
    non-sealed interface TConsumer<T> extends GivenFns, Consumer<Optional<T>> {}
    @FunctionalInterface
    non-sealed interface TFunction<T> extends GivenFns, Function<Optional<T>, T> {}

}
