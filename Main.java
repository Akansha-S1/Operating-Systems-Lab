/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
// Java implementation of worst - Fit algorithm
import java.util.Scanner;

public class Main {
    // Method to allocate memory to blocks as per worst fit algorithm
    static void worstFit(int blockSize[], int m, int processSize[], int n) {
        // Stores block id of the block allocated to a process
        int allocation[] = new int[n];

        // Initially no block is assigned to any process
        for (int i = 0; i < allocation.length; i++)
            allocation[i] = -1;

        // Pick each process and find suitable blocks according to its size and assign to it
        for (int i = 0; i < n; i++) {
            // Find the worst fit block for the current process
            int wstIdx = -1;
            for (int j = 0; j < m; j++) {
                if (blockSize[j] >= processSize[i]) {
                    if (wstIdx == -1)
                        wstIdx = j;
                    else if (blockSize[wstIdx] < blockSize[j])
                        wstIdx = j;
                }
            }

            // If we could find a block for the current process
            if (wstIdx != -1) {
                // Allocate block j to process p[i]
                allocation[i] = wstIdx;

                // Reduce available memory in this block.
                blockSize[wstIdx] -= processSize[i];
            }
        }

        System.out.println("\nProcess No.\tProcess Size\tBlock no.");
        for (int i = 0; i < n; i++) {
            System.out.print(" " + (i + 1) + "\t\t" + processSize[i] + "\t\t");
            if (allocation[i] != -1)
                System.out.print(allocation[i] + 1);
            else
                System.out.print("Not Allocated");
            System.out.println();
        }
    }

    // Driver Method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of blocks: ");
        int m = scanner.nextInt();
        int blockSize[] = new int[m];
        System.out.println("Enter the block sizes: ");
        for (int i = 0; i < m; i++) {
            blockSize[i] = scanner.nextInt();
        }

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();
        int processSize[] = new int[n];
        System.out.println("Enter the process sizes: ");
        for (int i = 0; i < n; i++) {
            processSize[i] = scanner.nextInt();
        }

        worstFit(blockSize, m, processSize, n);
        scanner.close();
    }
}

