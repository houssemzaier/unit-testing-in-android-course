package com.techyourchance.unittestingfundamentals.exercise3;

import com.techyourchance.unittestingfundamentals.example3.Interval;

public class IntervalsAdjacencyDetector {

    /**
     * @return true if the intervals are adjacent, but don't overlap
     */
    public boolean isAdjacent(Interval intervalOne, Interval intervalTwo) {
        // this implementation contains two bugs:
        // 1. will erroneously report adjacent if intervalOne and intervalTwo are the same
        // 2. will erroneously report adjacent if intervalOne after intervalTwo
        return isAdjentOnEnd(intervalOne, intervalTwo) ||
                isAdjustOnStart(intervalOne, intervalTwo) ||
                isSameIntervals(intervalOne, intervalTwo);
    }

    private boolean isAdjustOnStart(Interval intervalOne, Interval intervalTwo) {
        return intervalOne.getStart() == intervalTwo.getEnd();
    }

    private boolean isAdjentOnEnd(Interval interval1, Interval interval2) {
        return interval1.getEnd() == interval2.getStart();
    }

    private boolean isSameIntervals(Interval interval1, Interval interval2) {
        return interval1.getStart() == interval2.getStart() && interval1.getEnd() == interval2.getEnd();
    }

}
