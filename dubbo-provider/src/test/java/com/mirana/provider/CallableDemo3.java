package com.mirana.provider;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

public class CallableDemo3 {
    private static  String url="http://10.161.16.172/timesadmin/user/photo";
    final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args)
    {

        // 请求总数
        final List<String> filePathsList = new ArrayList<String>();
        List<String> urls=new ArrayList<String>();
        for (int i=0;i<10000;i++){
            urls.add(url);
        }
        System.out.println("urls-----------------------------------:"+urls.size());
        for (String s : urls) {
            filePathsList.add(s);
        }
        CountDownLatch latch = new CountDownLatch(filePathsList.size());
        ExecutorService pool = Executors.newFixedThreadPool(10);

        BlockingQueue<Future<Map<String, String>>> queue =
                new ArrayBlockingQueue<Future<Map<String, String>>>(100);

        System.out.println("-------------文件读、写任务开始时间：" + sdf.format(new Date()));
        for (int i = 0; i < filePathsList.size(); i++) {
            String temp = filePathsList.get(i);
            Future<Map<String, String>> future = pool.submit(new MyCallableProducer(latch, temp));
            queue.add(future);

            //pool.execute(new MyCallableConsumer(queue));
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-------------文件读、写任务结束时间：" + sdf.format(new Date()));
        pool.shutdownNow();
    }


    // 文件读线程
    static class MyCallableProducer implements Callable<Map<String, String>>
    {
        private CountDownLatch latch;
        private String json=null;
        private String fis = null;
        private Map<String, String> fileMap = new HashMap<String, String>();

        public MyCallableProducer(CountDownLatch latch, String json)
        {
            this.latch = latch;
            this.json = json;
        }

        @Override
        public Map<String, String> call() throws Exception
        {
            System.out.println(Thread.currentThread().getName() + " 线程开始请求 ,时间为 "+ sdf.format(new Date()));
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("mobile", "13366564333");
            fis =HttpClientUtil.doGet(json,params,false) ;
            fileMap.put(fis, fis);

            doWork();
            System.out.println(Thread.currentThread().getName() + " 线程读取文件  +json:"+fileMap.get(fis)+",时间为 "+ sdf.format(new Date()));
            latch.countDown();
            return fileMap;
        }

        private void doWork()
        {
           //此方法可以添加一些业务逻辑，比如包装pojo等等操作，返回的值可以是任何类型
            Random rand = new Random();
            int time = rand.nextInt(10) * 1000;
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    // 文件写线程
    /*static class MyCallableConsumer implements Runnable
    {
        private String fileName = "";
        private BlockingQueue<Future<Map<String, FileInputStream>>> queue;
        private FileInputStream fis = null;
        private File dirFile = null;

        private BufferedReader br = null;
        private InputStreamReader isr = null;
        private FileWriter fw = null;
        private BufferedWriter bw = null;

        public MyCallableConsumer(BlockingQueue<Future<Map<String, FileInputStream>>> queue2)
        {
            this.queue = queue2;
        }

        @Override
        public void run()
        {
            try {
                Future<Map<String, FileInputStream>> future = queue.take();
                Map<String, FileInputStream> map = future.get();

                Set<String> set = map.keySet();
                for (Iterator<String> iter = set.iterator(); iter.hasNext();) {

                    fileName = iter.next().toString();
                    fis = map.get(fileName);

                    System.out.println(Thread.currentThread().getName() + " 线程开始写文件 ：" + fileName  + " ,时间为 "+ sdf.format(new Date()));
                    try {
                        isr = new InputStreamReader(fis, "utf-8");
                        br = new BufferedReader(isr);

                        dirFile = new File("d:" + File.separator + "gc3" + File.separator + fileName);
                        fw = new FileWriter(dirFile);
                        bw = new BufferedWriter(fw);

                        String data = "";
                        bw.write("+++++++++++++" + Thread.currentThread().getName() + " 线程开始写文件++++++++++++");
                        while ((data = br.readLine()) != null) {
                            bw.write(data + "\r");
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            bw.close();
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }*/
}
