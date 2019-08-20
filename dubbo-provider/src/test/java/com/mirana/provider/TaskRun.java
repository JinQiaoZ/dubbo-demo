package com.mirana.provider;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;

public class TaskRun {

    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(DownLoadFile.getUrls().size());
        ExecutorService pool = Executors.newFixedThreadPool(10);

    }


}
