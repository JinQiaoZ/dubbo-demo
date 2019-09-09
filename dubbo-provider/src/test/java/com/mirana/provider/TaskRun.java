package com.mirana.provider;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskRun {

    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(DownLoadFile.getUrls().size());
        ExecutorService pool = Executors.newFixedThreadPool(10);

    }


}
