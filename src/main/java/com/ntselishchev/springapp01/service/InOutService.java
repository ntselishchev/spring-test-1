package com.ntselishchev.springapp01.service;

import java.util.Scanner;

public interface InOutService {

    Scanner initScanner();

    void print(String message);

    String getUserInputMessage(Scanner scanner);

}
