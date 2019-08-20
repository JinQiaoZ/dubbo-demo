package com.mirana.provider;

import org.junit.Test;

public class TestExcption {




   @Test
    public void testThrowable() {
        try {
            int a = 1 / 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
    }

}
