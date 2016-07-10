package com.jnape.palatable.lambda.functions.builtin.fn2;

import com.jnape.palatable.lambda.functions.Fn2;
import org.junit.Test;

import static com.jnape.palatable.lambda.functions.builtin.fn2.Partial2.partial2;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class Partial2Test {

    @Test
    public void partiallyAppliesFunction() {
        Fn2<Integer, Integer, Integer> subtract = (minuend, subtrahend) -> minuend - subtrahend;

        assertThat(partial2(subtract, 3).apply(2), is(1));
    }
}
