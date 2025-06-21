package ECTE331_Project_Q3;

public class Question3 {

    public static void main(String[] args) {
        int num_iterations = 1; // Start with 1 for initial testing, extend later for high number 

        for (int i = 0; i < num_iterations; i++) {
            System.out.println("\n--- Iteration " + (i + 1) + " ---");
            Data my_data = new Data();

            ThreadA threadA = new ThreadA(my_data);
            ThreadB threadB = new ThreadB(my_data);
            ThreadC threadC = new ThreadC(my_data);

            threadA.start();
            threadB.start();
            threadC.start();

            try {
                // Wait for all threads to complete their execution
                threadA.join();
                threadB.join();
                threadC.join();
            } catch (InterruptedException e) {
                System.err.println("Main thread interrupted while waiting for child threads.");
                Thread.currentThread().interrupt();
            }

            // After all threads have completed, print the final values and verify
            System.out.println("\n--- Final Shared Variable Values (Iteration " + (i + 1) + ") ---");
            System.out.println("A1: " + my_data.A1);
            System.out.println("A2: " + my_data.A2);
            System.out.println("A3: " + my_data.A3);
            System.out.println("B1: " + my_data.B1);
            System.out.println("B2: " + my_data.B2);
            System.out.println("B3: " + my_data.B3);
            System.out.println("ThreadC Result (A2 + B3): " + threadC.getResultC());

            // Validate A2+B3 for Thread C
            // You can add an assertion here for programmatic verification
            int expected_A2_plus_B3 = my_data.A2 + my_data.B3;
            if (threadC.getResultC() == expected_A2_plus_B3) {
                System.out.println("Validation successful: ThreadC's result matches A2+B3.");
            } else {
                System.err.println("Validation FAILED: ThreadC's result does NOT match A2+B3.");
            }

            // TODO: For part (a), calculate the expected final correct values manually and compare.
            // This is where you'd put the manual calculation results.
        }

        // To run for a high number of iterations (part d)
        // Change num_iterations to a higher value, e.g., 1000 or 10000.
        // The synchronization should ensure correctness regardless of scheduling.
        // The output might be very long for high iterations, so you might want to suppress some print statements inside threads for high iteration runs.
    }
}
