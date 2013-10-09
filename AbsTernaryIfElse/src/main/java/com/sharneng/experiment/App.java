package com.sharneng.experiment;

/**
 * Hello world!
 * 
 */
public class App {
    public static void main(String[] args) throws Exception {
        Thread.sleep(5000);
        RunnerAbsInline.main(args);
        Thread.sleep(5000);
        RunnerAbsOverride.main(args);
        Thread.sleep(5000);
        RunnerOrderingInline.main(args);
        Thread.sleep(5000);
        RunnerOrderingOverride.main(args);
    }
}
