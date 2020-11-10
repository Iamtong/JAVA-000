package java0.conc0303;

import java.util.concurrent.*;

/**
 *
 *
 * 作业完成
 *
 */
public class Homework03 {
    
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        /**
         * 0、示例为同步的方法
         */
        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        int result = sum(); //这是得到的返回值
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        // 然后退出main线程

        /**
         * 1、单独起线程的方式，但同步获取不到值，使用等待1秒的形式拿到值
         */
        long start1=System.currentTimeMillis();
        final int[] result1 = new int[1];
        new Thread(new Runnable() {
            @Override
            public void run() {
                result1[0] = sum();
            }
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("1异步计算结果为："+result1[0]);
        System.out.println("1使用时间："+ (System.currentTimeMillis()-start1) + " ms");

        /**
         * 2、接下来是几个线程池的方法 newSingleThreadExecutor newFixedThreadPool newCachedThreadPool newScheduledThreadPool
         */
        long start2=System.currentTimeMillis();
        ExecutorService executorService = Executors.newSingleThreadExecutor();//同理newFixedThreadPool newCachedThreadPool newScheduledThreadPool
        //ExecutorService executorService = Executors.newFixedThreadPool(1);
        //ExecutorService executorService = Executors.newCachedThreadPool();
        //ExecutorService executorService = Executors.newScheduledThreadPool(1);
        Integer result2 = executorService.submit(new Callable<Integer>(){

            @Override
            public Integer call() throws Exception {
                return sum();
            }
        }).get();
        executorService.shutdown();//种植线程
        System.out.println("1异步计算结果为："+result2);
        System.out.println("1使用时间："+ (System.currentTimeMillis()-start2) + " ms");




        /**
         * 3 FutureTask
         */
        long start3=System.currentTimeMillis();
        FutureTask<Integer> task = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return sum();
            }
        });
        new Thread(task).start();
        System.out.println("3异步计算结果为："+task.get());
        System.out.println("3使用时间："+ (System.currentTimeMillis()-start3) + " ms");



        /**
         * 4、CompletableFuture
         */
        long start4=System.currentTimeMillis();
        Integer result4 = CompletableFuture.supplyAsync(()->{return sum();}).join();
        System.out.println("4异步计算结果为："+result4);
        System.out.println("4使用时间："+ (System.currentTimeMillis()-start4) + " ms");


    }

    private static int sum() {
        return fibo(36);
    }

    /**
     * 优化递归
     * @param a
     * @return
     */
    private static int fibo(int a) {
//        if ( a < 2)
//            return 1;
//        return fibo(a-1) + fibo(a-2);
        int re = 0;
        int[] f = new int[a+1];
        f[0] = 1;
        f[1] = 1;
        for (int i=2;i<=a;i++){
            f[i] = f[i-1]+f[i-2];
        }
        return f[a];
    }
}
