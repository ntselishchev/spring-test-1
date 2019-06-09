package com.ntselishchev.springapp01.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class InOutServiceImpl implements InOutService {

    public Scanner initScanner() {
        return new Scanner(System.in);
    }

    public void print(String message) {
        System.out.println(message);
    }

    public String getUserInputMessage(Scanner scanner) {
        return scanner.nextLine().trim();
    }
}
