package com.jnape.palatable.lambda.traversable;

import java.util.Optional;

/**
 * Static factory methods for adapting core JDK types to {@link Traversable}.
 */
public final class Traversables {

    private Traversables() {
    }

    /**
     * Wrap an {@link Iterable} in a {@link Traversable}.
     *
     * @param as  the Iterable
     * @param <A> the Iterable element type
     * @return a Traversable wrapper around as
     */
    public static <A> TraversableIterable<A> traversable(Iterable<? extends A> as) {
        return TraversableIterable.wrap(as);
    }

    /**
     * Wrap an {@link Optional} in a {@link Traversable}.
     *
     * @param opt the Optional
     * @param <A> the Optional type
     * @return a Traversable wrapper around opt
     */
    public static <A> TraversableOptional<A> traversable(Optional<A> opt) {
        return TraversableOptional.wrap(opt);
    }
}
