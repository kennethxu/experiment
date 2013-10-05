package com.sharneng.experiment;

/**
 * Hello world!
 * 
 */
public class App {
    public static void main(String[] args) throws Exception {
        Thread.sleep(5000);
        AbsTernaryIfElseMinInline.main(args);
        Thread.sleep(5000);
        AbsTernaryIfElseMinOverride.main(args);
        Thread.sleep(5000);
        AbsTernaryIfElseSwapInline.main(args);
        Thread.sleep(5000);
        AbsTernaryIfElseSwapOverride.main(args);
    }
}
