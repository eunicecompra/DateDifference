package com.app.test;

import com.app.DateDifferenceRunner;
import org.junit.Before;
import org.junit.Test;

public class DateDifferenceTest {

    DateDifferenceRunner runner;

    @Before
    public void init() {
        runner = new DateDifferenceRunner();
    }

    @Test
    public void checkNullInput() {
        runner.calculateDifference(null);
    }
}
