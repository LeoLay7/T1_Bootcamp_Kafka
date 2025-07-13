package org.homework;

public class TaskScheduler {
    public void schedule(Runnable task, int intervalMs) {
        while (true) {
            try {
                task.run();
                Thread.sleep(intervalMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
