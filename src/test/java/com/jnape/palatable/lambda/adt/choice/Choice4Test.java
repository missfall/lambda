package com.jnape.palatable.lambda.adt.choice;

import com.jnape.palatable.traitor.annotations.TestTraits;
import com.jnape.palatable.traitor.runners.Traits;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import testsupport.traits.FunctorLaws;

import static com.jnape.palatable.lambda.adt.choice.Choice4.a;
import static com.jnape.palatable.lambda.adt.choice.Choice4.b;
import static com.jnape.palatable.lambda.adt.choice.Choice4.c;
import static com.jnape.palatable.lambda.adt.choice.Choice4.d;
import static org.junit.Assert.assertEquals;

@RunWith(Traits.class)
public class Choice4Test {

    private Choice4<Integer, String, Boolean, Double> a;
    private Choice4<Integer, String, Boolean, Double> b;
    private Choice4<Integer, String, Boolean, Double> c;
    private Choice4<Integer, String, Boolean, Double> d;

    @Before
    public void setUp() {
        a = a(1);
        b = b("two");
        c = c(true);
        d = d(4D);
    }

    @TestTraits({FunctorLaws.class})
    public Choice4<String, Integer, Boolean, Character> testSubjectA() {
        return a("foo");
    }

    @TestTraits({FunctorLaws.class})
    public Choice4<String, Integer, Boolean, Character> testSubjectB() {
        return b(1);
    }

    @TestTraits({FunctorLaws.class})
    public Choice4<String, Integer, Boolean, Character> testSubjectC() {
        return c(true);
    }

    @TestTraits({FunctorLaws.class})
    public Choice4<String, Integer, Boolean, Character> testSubjectD() {
        return d('a');
    }

    @Test
    public void convergeStaysInChoice() {
        assertEquals(Choice3.a(1), a.converge(d -> Choice3.b(d.toString())));
        assertEquals(Choice3.b("two"), b.converge(d -> Choice3.b(d.toString())));
        assertEquals(Choice3.c(true), c.converge(d -> Choice3.b(d.toString())));
        assertEquals(Choice3.b("4.0"), d.converge(d -> Choice3.b(d.toString())));
    }

    @Test
    public void divergeStaysInChoice() {
        assertEquals(Choice5.a(1), a.diverge());
        assertEquals(Choice5.b("two"), b.diverge());
        assertEquals(Choice5.c(true), c.diverge());
        assertEquals(Choice5.d(4D), d.diverge());
    }

    @Test
    public void bifunctorProperties() {
        assertEquals(a, a.biMap(c -> !c, d -> -d));
        assertEquals(b, b.biMap(c -> !c, d -> -d));
        assertEquals(c(false), c.biMap(c -> !c, d -> -d));
        assertEquals(d(-4D), d.biMap(c -> !c, d -> -d));
    }
}