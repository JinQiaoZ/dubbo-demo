package com.mirana.provider;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 创建线程池
 * @date 2017年2月20日
 * @author qiao
 *
 */

public class ThreadPoolUtils {
	private static final int COREPOOLSIZE = 1;
	private static final int MAXIMUMPOOLSIZE = 2;
	private static final int KEEPALIVETIME = 2;
	private static ThreadPoolExecutor threadPoolExecutor;

	/**
	 * 
	 * @Description: 创建线程池
	 */
	public static ThreadPoolExecutor getExecutorService() {
		if (null == threadPoolExecutor) {
			threadPoolExecutor = new ThreadPoolExecutor(COREPOOLSIZE,
					MAXIMUMPOOLSIZE, KEEPALIVETIME, TimeUnit.MINUTES,
					new ArrayBlockingQueue<Runnable>(10));
		}
		return threadPoolExecutor;
	}

	/**
	 * 
	 *  
	 * @Description: 开启线程池,带返回结果的
	 */
	public static  Future<Object> execute(Callable<Object> rCallable) {
		Future<Object> future  = threadPoolExecutor.submit(rCallable);
		return future;
	}

	/**
	 * 
	 * @Description: 开启线程池,不带返回结果的
	 */
	public static void execute(Runnable runnable) {
		threadPoolExecutor.execute(runnable);
	}

	/**
	 * 
	 * @Description: 关闭线程池
	 */
	public static void shutdown() {
		ThreadPoolExecutor executorService = getExecutorService();
		if (!executorService.isShutdown()) {
			threadPoolExecutor.shutdown();
		}
	}

}
