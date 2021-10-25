package net.jbw.playground.app;

import net.jbw.playground.util.Calculator;

public class Main {

    public static void main(String[] args) {
        System.out.println("Command: " + ProcessHandle.current().info().commandLine());
        System.out.println("Hello from Java Module: " + Main.class.getModule().getName());
        System.out.println("Required Java Module: " + Calculator.class.getModule().getName());
        System.out.println("Use module to calculate sum of '17' and '25': " + Calculator.add(17, 25));
    }
}
