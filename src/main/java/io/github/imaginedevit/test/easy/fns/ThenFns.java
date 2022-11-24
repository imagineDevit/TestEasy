package io.github.imaginedevit.test.easy.fns;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public sealed interface ThenFns {

    non-sealed interface TConsumer<R> extends ThenFns, Consumer<R> {}
    non-sealed interface TBiConsumer<R> extends ThenFns, BiConsumer<R, Optional<R>> {}

}
