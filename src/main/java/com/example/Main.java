package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Processor processor = new Processor(scanner);

        processor.run();
    }
}
