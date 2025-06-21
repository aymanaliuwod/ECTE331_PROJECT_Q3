package ECTE331_Project_Q3;

public class ThreadA extends Thread { // extends Thread 
                                   
    private Data sharedData;

    public ThreadA(Data sharedData) {
        this.sharedData = sharedData;
    }

    @Override
    public void run() {
        // FuncA1
        synchronized (sharedData) {
            sharedData.A1 = Utility.calculateSum(500);
            System.out.println("ThreadA: FuncA1 completed. A1 = " + sharedData.A1);
            sharedData.funcA1_completed = true;
            sharedData.notifyAll(); // Notify ThreadB that A1 is ready for B2
        }

        // FuncA2: Depends on FuncB2 completion
        synchronized (sharedData) {
            while (!sharedData.funcB2_completed) {
                try {
                    System.out.println("ThreadA: Waiting for FuncB2 to complete before FuncA2.");
                    sharedData.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("ThreadA interrupted while waiting for FuncB2.");
                }
            }
            sharedData.A2 = sharedData.B2 + Utility.calculateSum(300);
            System.out.println("ThreadA: FuncA2 completed. A2 = " + sharedData.A2);
            sharedData.funcA2_completed = true;
            sharedData.notifyAll(); // Notify ThreadB (for B3) and ThreadC
        }

        // FuncA3: Depends on FuncB3 completion
        synchronized (sharedData) {
            while (!sharedData.funcB3_completed) {
                try {
                    System.out.println("ThreadA: Waiting for FuncB3 to complete before FuncA3.");
                    sharedData.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("ThreadA interrupted while waiting for FuncB3.");
                }
            }
            sharedData.A3 = sharedData.B3 + Utility.calculateSum(400);
            System.out.println("ThreadA: FuncA3 completed. A3 = " + sharedData.A3);
       
        }
    }
}
