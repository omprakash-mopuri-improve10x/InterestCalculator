package com.prakash.simpleinterest;

import junit.framework.TestCase;

public class SimpleInterestActivityTest extends TestCase {

    public void testGetDiffDate() {
        System.out.println(DateUtils.getDiffDate("10-5-2022", "9-5-2022"));
        System.out.println(DateUtils.getDiffDate("20-6-2022", "9-5-2022"));
        System.out.println(DateUtils.getDiffDate("10-5-2022", "9-5-2023"));
    }

    public void testGetDiffDays() {
        System.out.println(DateUtils.getDiffDays("10-5-2022", "9-5-2022"));
        System.out.println(DateUtils.getDiffDays("20-6-2022", "9-5-2022"));
        System.out.println(DateUtils.getDiffDays("10-5-2026", "10-5-2028"));
    }
}