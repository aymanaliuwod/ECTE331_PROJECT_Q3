package ECTE331_Project_Q3;

public class ThreadC extends Thread {
    private Data sharedData;
    private int resultC;

    public ThreadC(Data sharedData) {
        this.sharedData = sharedData;
    }

    @Override
    public void run() {
        // Thread C returns A2 + B3, which should be validated after FuncB3 completes
        synchronized (sharedData) {
            while (!sharedData.funcB3_completed) {
                try {
                    System.out.println("ThreadC: Waiting for FuncB3 to complete before calculating A2+B3.");
                    sharedData.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("ThreadC interrupted while waiting for FuncB3.");
                }
            }
            resultC = sharedData.A2 + sharedData.B3;
            System.out.println("ThreadC: Calculation completed. A2 + B3 = " + resultC);
            // No other thread explicitly depends on ThreadC's calculation for synchronization
        }
    }

    public int getResultC() {
        return resultC;
    }
}
