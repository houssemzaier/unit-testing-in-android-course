package com.techyourchance.unittestingfundamentals.exercise3;

import com.techyourchance.unittestingfundamentals.example3.Interval;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class IntervalsAdjacencyDetectorTest {
    private IntervalsAdjacencyDetector SUT;

    @Before
    public void setUp() throws Exception {
        SUT = new IntervalsAdjacencyDetector();
    }

    @Test
    public void isAdjacent_intervalOneEnd_isEqualTo_intervalTwoStart_shouldReturn_True() {
        Interval intervalOne = new Interval(0, 5);
        Interval intervalTwo = new Interval(5, 6);
        boolean result = SUT.isAdjacent(intervalOne, intervalTwo);
        assertThat(result, is(true));
    }

    @Test
    public void isAdjacent_intervalOneStart_isEqualTo_intervalTwoEnd_shouldReturn_True() {
        Interval intervalOne = new Interval(0, 5);
        Interval intervalTwo = new Interval(-5, 0);
        boolean result = SUT.isAdjacent(intervalOne, intervalTwo);
        assertThat(result, is(true));
    }

    @Test
    public void isAdjacent_intervalOneEnd_isBefore_intervalTwoStart_shouldReturn_False() {
        Interval intervalOne = new Interval(0, 5);
        Interval intervalTwo = new Interval(7, 16);
        boolean result = SUT.isAdjacent(intervalOne, intervalTwo);
        assertThat(result, is(false));
    }

    @Test
    public void isAdjacent_intervalOneStart_isAfter_intervalTwoEnd_shouldReturn_False() {
        Interval intervalOne = new Interval(0, 5);
        Interval intervalTwo = new Interval(-5, -2);
        boolean result = SUT.isAdjacent(intervalOne, intervalTwo);
        assertThat(result, is(false));
    }

    @Test
    public void isAdjacent_intervalOneContainsIntervalTwo_shouldReturn_False() {
        Interval intervalOne = new Interval(0, 5);
        Interval intervalTwo = new Interval(2, 3);
        boolean result = SUT.isAdjacent(intervalOne, intervalTwo);
        assertThat(result, is(false));
    }

    @Test
    public void isAdjacent_intervalOneContainsIntervalTwo_Where_IntervalOneStart_isEqualTo_IntervalTwoStart_shouldReturn_False() {
        Interval intervalOne = new Interval(0, 5);
        Interval intervalTwo = new Interval(0, 3);
        boolean result = SUT.isAdjacent(intervalOne, intervalTwo);
        assertThat(result, is(false));
    }

    @Test
    public void isAdjacent_intervalOneContainsIntervalTwo_Where_IntervalOneEnd_isEqualTo_IntervalTwoEnd_shouldReturn_False() {
        Interval intervalOne = new Interval(0, 5);
        Interval intervalTwo = new Interval(3, 5);
        boolean result = SUT.isAdjacent(intervalOne, intervalTwo);
        assertThat(result, is(false));
    }
}
