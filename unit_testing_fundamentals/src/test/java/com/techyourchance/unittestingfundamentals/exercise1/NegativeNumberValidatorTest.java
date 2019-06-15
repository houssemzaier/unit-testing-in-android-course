package com.techyourchance.unittestingfundamentals.exercise1;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class NegativeNumberValidatorTest {
    private NegativeNumberValidator SUT;

    @Before
    public void setUp() {
        SUT = new NegativeNumberValidator();
    }

    @Test
    public void isNegative_negativeNumber_true() {
        boolean result = SUT.isNegative(-5);
        assertThat(result, is(true));
    }

    @Test
    public void isNegative_positiveNumber_false() {
        boolean result = SUT.isNegative(5);
        assertThat(result, is(false));
    }

    @Test
    public void isNegative_zero_false() {
        boolean result = SUT.isNegative(0);
        assertThat(result, is(false));
    }

}
