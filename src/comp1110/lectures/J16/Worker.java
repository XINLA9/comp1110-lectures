package comp1110.lectures.J16;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Worker implements Runnable {
    private static final int NTHREADS = 4;
    private static final int NJOBS = 1000;
    private int nextJob = 0;
    private boolean[] results = new boolean[NJOBS];
    private Map<String, Integer> completed = new HashMap<>();

    public boolean isPrime(int n) {
        if (n == 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }
        for (var i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public synchronized int getNextJob() {
        var job = nextJob;
        nextJob = nextJob + 1;
        return job;
    }


    @Override
    public void run() {
        int job;
        int jobsCompleted = 0;
        while ((job = getNextJob()) < NJOBS) {
            results[job] = isPrime(job); // special case, as long as "job" is unique per thread, no data race
            jobsCompleted += 1;
        }
        synchronized (this) {
            completed.put(Thread.currentThread().getName(), jobsCompleted);
        }
        System.out.println("Thread \"" + Thread.currentThread().getName() + "\" completed " + jobsCompleted + " jobs");
    }

    public static void main(String[] args) {
        System.out.println("Start of main method");
        var worker = new Worker();
        var threads = new Thread[NTHREADS];
        for (var i = 0; i < threads.length; i++) {
            threads[i] = new Thread(worker, "Thread " + i);
            threads[i].start();
        }
        for (var i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Total completed: " + worker.completed.values().stream().reduce(0, (x, y) -> x + y));
        System.out.println(Arrays.toString(worker.results));
        System.out.println("End of main method");
    }
}
