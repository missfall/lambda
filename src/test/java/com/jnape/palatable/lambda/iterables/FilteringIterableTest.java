package com.jnape.palatable.lambda.iterables;

import com.jnape.palatable.lambda.Predicate;
import org.junit.Test;
import testsupport.matchers.IterableMatcher;

import java.util.ArrayList;

import static com.jnape.palatable.lambda.iterables.FilteringIterable.filter;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertThat;
import static testsupport.matchers.IterableMatcher.iterates;

public class FilteringIterableTest {

    @Test
    public void filtersElementsUsingPredicate() {
        Iterable<String> stooges = asList("Moe", "Larry", "Curly");
        FilteringIterable<String> shortNames = filter(new Predicate<String>() {
            @Override
            public Boolean apply(String s) {
                return s.length() == 3;
            }
        }, stooges);

        assertThat(shortNames, iterates("Moe"));
    }

    @Test
    public void returnsEmptyIterableIfPredicateFailedForAllElements() {
        Iterable<Integer> odds = asList(1, 3, 5);
        FilteringIterable<Integer> evens = filter(new Predicate<Integer>() {
            @Override
            public Boolean apply(Integer integer) {
                return integer % 2 == 0;
            }
        }, odds);

        assertThat(evens, IterableMatcher.<Integer>iterates());
    }

    @Test
    public void filtersNothingFromEmptyList() {
        FilteringIterable<Integer> quotientsOfZero = filter(new Predicate<Integer>() {
            @Override
            public Boolean apply(Integer integer) {
                return integer / 0 == 0;
            }
        }, new ArrayList<Integer>());

        assertThat(quotientsOfZero, IterableMatcher.<Integer>iterates());
    }
}
