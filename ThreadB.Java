package ECTE331_Project_Q3;

public class ThreadB extends Thread {
    private Data sharedData;

    public ThreadB(Data sharedData) {
        this.sharedData = sharedData;
    }

    @Override
    public void run() {
        // FuncB1
        synchronized (sharedData) {
            sharedData.B1 = Utility.calculateSum(250);
            System.out.println("ThreadB: FuncB1 completed. B1 = " + sharedData.B1);
            sharedData.funcB1_completed = true;
            sharedData.notifyAll(); // Notify anyone who might depend on B1 (though none explicitly shown in diagram for calculation)
        }

        // FuncB2: Depends on FuncA1 completion
        synchronized (sharedData) {
            while (!sharedData.funcA1_completed) {
                try {
                    System.out.println("ThreadB: Waiting for FuncA1 to complete before FuncB2.");
                    sharedData.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("ThreadB interrupted while waiting for FuncA1.");
                }
            }
            sharedData.B2 = sharedData.A1 + Utility.calculateSum(200);
            System.out.println("ThreadB: FuncB2 completed. B2 = " + sharedData.B2);
            sharedData.funcB2_completed = true;
            sharedData.notifyAll(); // Notify ThreadA (for A2)
        }

        // FuncB3: Depends on FuncA2 completion
        synchronized (sharedData) {
            while (!sharedData.funcA2_completed) {
                try {
                    System.out.println("ThreadB: Waiting for FuncA2 to complete before FuncB3.");
                    sharedData.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("ThreadB interrupted while waiting for FuncA2.");
                }
            }
            sharedData.B3 = sharedData.A2 + Utility.calculateSum(400);
            System.out.println("ThreadB: FuncB3 completed. B3 = " + sharedData.B3);
            sharedData.funcB3_completed = true;
            sharedData.notifyAll(); // Notify ThreadA (for A3) and ThreadC
        }
    }
}
