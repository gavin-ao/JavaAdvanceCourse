package javaAdvance.multiThread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by aoyonggang on 2022/3/26.
 */


public class GetResultOfThreads {

    //declare class just for return value
    static class ReturnValue implements Runnable {
        private Integer result = null;

        public Integer getValue() {
            return result;
        }

        @Override
        public void run() {
            result = new Integer(0);
            result++;
            result++;
            result++;
        }
    }

    ;

    public static void main(String[] args) throws Exception {
        //case 1:by callable
        Integer result1 = null;
        ExecutorService executor = Executors.newSingleThreadExecutor();
        result1 = executor.submit(() -> {
            return 3;
        }).get();
        executor.shutdown();
        System.out.println("case 1: by callable: " + result1);


        //case 2: by object + join
        ReturnValue resultobject = new ReturnValue();
        Thread thread1 = new Thread(resultobject);
        thread1.start();
        thread1.join();
        result1 = resultobject.getValue();
        System.out.println("case 2: by object + join: " + result1);

        //case 3: by countdownlatch
        final int[] result2 = new int[1];
        CountDownLatch latch = new CountDownLatch(1);
        Thread thread2 = new Thread(() -> {
            result2[0] = 3;
            latch.countDown();
        });
        thread2.start();
        latch.await();
        result1 = result2[0];
        System.out.println("case 3: by countdownlatch: " + result1);

        //case 4:by completablefuture
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            return 3;
        });
        result1 = completableFuture.get();
        System.out.println("case 4: by completablefuture: " + result1);

        //case 5: by cyclicbarrier
        final int[] result3 = new int[1];
        CyclicBarrier barrier = new CyclicBarrier(2, () -> {
            System.out.println("barrier is reached!This thread's name is: " + Thread.currentThread().getName());
        });
        Thread thread3 = new Thread(() -> {
            result3[0] = 3;
            try {
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        thread3.start();
        barrier.await();
        result1 = result3[0];
        System.out.println("case 5: by cyclibarrier: " + result1);


        //case 6：by infinite loop
        ReturnValue retValue = new ReturnValue();
        Thread thread5 = new Thread(retValue);
        thread5.start();

        while (retValue.getValue() == null) {
            Thread.sleep(1);
        }
        result1 = retValue.getValue();
        System.out.println("case 6: by infinite loop: " + result1);


        //case 7: by semphore
        Semaphore semaphore = new Semaphore(1);
        result1 = 0;
        final int[] result4 = new int[1];
        result4[0] = 0;
        Object sync = new Object();
        Thread thread7 = new Thread(() -> {
            try {
                //semaphore.acquire();
                result4[0]++;
                result4[0]++;
                result4[0]++;
                //semaphore.release();

                //Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }

        });
        semaphore.acquire();
        thread7.start();
        semaphore.acquire();

        System.out.println("case 7: by semphore: " + result4[0]);
        semaphore.release();


    }

}
