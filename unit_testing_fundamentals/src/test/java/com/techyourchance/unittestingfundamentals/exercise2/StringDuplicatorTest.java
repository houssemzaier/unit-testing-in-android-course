package com.techyourchance.unittestingfundamentals.exercise2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StringDuplicatorTest {
    private StringDuplicator SUT;

    @Before
    public void setUp() throws Exception {
        SUT = new StringDuplicator();
    }

    @Test
    public void duplicate_az_azaz() {
        String result = SUT.duplicate("az");
        assertThat(result, is("azaz"));
    }

    @Test
    public void duplicate_emptyCharacter_empty() {
        String result = SUT.duplicate("");
        assertThat(result, is(""));
    }

    @Test
    public void duplicate_whitespace_doubleWhiteSpace() {
        String result = SUT.duplicate(" ");
        assertThat(result, is("  "));
    }
}
