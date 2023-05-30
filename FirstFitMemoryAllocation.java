/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class MemoryBlock {
    private int id;
    private int size;
    private boolean allocated;

    public MemoryBlock(int id, int size) {
        this.id = id;
        this.size = size;
        this.allocated = false;
    }

    public int getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public boolean isAllocated() {
        return allocated;
    }

    public void allocate() {
        this.allocated = true;
    }

    public void deallocate() {
        this.allocated = false;
    }
}

class Process {
    private int id;
    private int size;

    public Process(int id, int size) {
        this.id = id;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public int getSize() {
        return size;
    }
}

public class FirstFitMemoryAllocation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of memory blocks:");
        int numBlocks = sc.nextInt();
        List<MemoryBlock> memoryBlocks = new ArrayList<>();

        for (int i = 0; i < numBlocks; i++) {
            System.out.println("Enter the size of memory block " + (i + 1) + ":");
            int size = sc.nextInt();
            memoryBlocks.add(new MemoryBlock(i + 1, size));
        }

        System.out.println("Enter the number of processes:");
        int numProcesses = sc.nextInt();
        List<Process> processes = new ArrayList<>();

        for (int i = 0; i < numProcesses; i++) {
            System.out.println("Enter the size of process " + (i + 1) + ":");
            int size = sc.nextInt();
            processes.add(new Process(i + 1, size));
        }

        allocateMemory(memoryBlocks, processes);
        printMemoryAllocation(memoryBlocks);
    }

    private static void allocateMemory(List<MemoryBlock> memoryBlocks, List<Process> processes) {
        for (Process process : processes) {
            boolean allocated = false;

            for (MemoryBlock block : memoryBlocks) {
                if (!block.isAllocated() && block.getSize() >= process.getSize()) {
                    block.allocate();
                    allocated = true;
                    System.out.println("Allocated Process " + process.getId() + " to Memory Block " + block.getId());
                    break;
                }
            }

            if (!allocated) {
                System.out.println("Unable to allocate Process " + process.getId());
            }
        }
    }

    private static void printMemoryAllocation(List<MemoryBlock> memoryBlocks) {
        System.out.println("\nMemory Allocation:");
        System.out.println("Memory Block\tSize\tStatus");

        for (MemoryBlock block : memoryBlocks) {
            String status = block.isAllocated() ? "Allocated" : "Free";
            System.out.println(block.getId() + "\t\t" + block.getSize() + "\t" + status);
        }
    }
}

