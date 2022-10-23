package comp1110.lectures.J16;

public class Workers implements Runnable {
    int workitem = 0;
    int squares_found = 0;

    static final int WORK_ITEMS = 1000;
    static final int THREADS = 20;

    private void pretendtoworkhard(int workitems) {
		try {
	    	Thread.sleep(1); // 1 millisecond
		} catch (InterruptedException e) {
	    	e.printStackTrace();
		}
    }

    private void workhard(int workitems) {
		for (int i = 0; i < workitems/2; i++)
	    	if (i * i == workitems)
				squares_found += 1;
    }

    synchronized private int incWorkitem() {
		return workitem++;
    }

    @Override
    public void run() {
		int mytasks = 0;

		int localworkitem;
		while ((localworkitem = incWorkitem()) < WORK_ITEMS) {
	    	//workhard(localworkitem);
	    	pretendtoworkhard(localworkitem);
	    	mytasks++;
	    	//Thread.currentThread().yield();
		}

		System.out.println(Thread.currentThread().getName() + " did " + mytasks);
    }

    public static void main(String[] args) {
		Workers workers = new Workers();
		Thread[] threads = new Thread[THREADS];
		for (int i = 0; i < THREADS; i++)
	    	threads[i] = new Thread(workers, "Worker " + i);

		// one thread running now (us)
		for (int i = 0; i < THREADS; i++)
	    	threads[i].start(); // start worker thread

		System.out.println(Thread.currentThread().getName() + " is twiddling its thumbs...");

		// THREADS + 1 threads running now (us + THREADS worker threads)
		try {
	    	for (int i = 0; i < THREADS; i++)
				threads[i].join(); // wait for the worker thread
		} catch (InterruptedException e) {
	    	e.printStackTrace();
		}
		// one thread running now (us)
	    
		System.out.println("All done!");
    }
}
