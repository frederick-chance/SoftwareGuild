/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swg.flooring.view;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author frederick
 */
public class UserIOImpl implements UserIO {

    String invalidEntry = "Invalid Entry.\n";
    String notInRange = "Entry not in range.\n";
    Scanner scan = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.print(message);
    }

    @Override
    public void println(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        double number = 0;
        boolean isDouble = false;

        do {
            print(prompt);
            String input = scan.nextLine();

            try {
                number = Double.parseDouble(input);
                isDouble = true;
            } catch (NumberFormatException ex) {
                System.out.println(invalidEntry);
                isDouble = false;
            }

        } while (!isDouble);

        return number;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double number = 0;
        boolean isDouble = false;
        boolean isInRange = false;

        do {
            print(prompt);
            String input = scan.nextLine();

            try {
                number = Double.parseDouble(input);
                isInRange = number <= max && number >= min;
                isDouble = true;

                if (!isInRange && isDouble) {
                    System.out.println(notInRange);
                }

            } catch (NumberFormatException ex) {
                System.out.println(invalidEntry);
                isDouble = false;
            }

        } while (!isDouble || !isInRange);

        return number;
    }

    @Override
    public float readFloat(String prompt) {
        float number = 0;
        boolean isFloat = false;

        do {
            print(prompt);
            String input = scan.nextLine();

            try {
                number = Float.parseFloat(input);
                isFloat = true;
            } catch (NumberFormatException ex) {
                System.out.println(invalidEntry);
                isFloat = false;
            }

        } while (!isFloat);

        return number;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float number = 0;
        boolean isFloat = false;
        boolean isInRange = false;

        do {
            print(prompt);
            String input = scan.nextLine();

            try {
                number = Float.parseFloat(input);
                isInRange = number <= max && number >= min;
                isFloat = true;

                if (!isInRange && isFloat) {
                    System.out.println(notInRange);
                }

            } catch (NumberFormatException ex) {
                System.out.println(invalidEntry);
                isFloat = false;
            }

        } while (!isFloat || !isInRange);

        return number;
    }

    @Override
    public int readInt(String prompt) {
        int number = 0;
        boolean isInteger = false;

        do {
            print(prompt);
            String input = scan.nextLine();

            try {
                number = Integer.parseInt(input);
                isInteger = true;
            } catch (NumberFormatException ex) {
                System.out.println(invalidEntry);
                isInteger = false;
            }

        } while (!isInteger);

        return number;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int number = 0;
        boolean isInteger = false;
        boolean isInRange = false;

        do {
            print(prompt);
            String input = scan.nextLine();

            try {
                number = Integer.parseInt(input);
                isInRange = number <= max && number >= min;
                isInteger = true;

                if (!isInRange && isInteger) {
                    System.out.println(notInRange);
                }

            } catch (NumberFormatException ex) {
                System.out.println(invalidEntry);
                isInteger = false;
            }

        } while (!isInteger || !isInRange);

        return number;
    }

    @Override
    public long readLong(String prompt) {
        long number = 0;
        boolean isLong = false;

        do {
            print(prompt);
            String input = scan.nextLine();

            try {
                number = Long.parseLong(input);
                isLong = true;
            } catch (NumberFormatException ex) {
                System.out.println(invalidEntry);
                isLong = false;
            }

        } while (!isLong);

        return number;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long number = 0;
        boolean isLong = false;
        boolean isInRange = false;

        do {
            print(prompt);
            String input = scan.nextLine();

            try {
                number = Long.parseLong(input);
                isInRange = number <= max && number >= min;
                isLong = true;

                if (!isInRange && isLong) {
                    System.out.println(notInRange);
                }

            } catch (NumberFormatException ex) {
                System.out.println(invalidEntry);
                isLong = false;
            }

        } while (!isLong || !isInRange);

        return number;
    }

    @Override
    public String readString(String prompt) {
        String input;

        boolean isString = false;

        do {
            print(prompt);
            input = scan.nextLine();

            if (input.isEmpty()) {
                isString = false;
            } else {
                isString = true;
            }

        } while (!isString);

        return input;
    }
   

    @Override
    public String readAnyKey(String prompt) {
        String input;

        boolean isAnyKey = false;

        do {
            print(prompt);
            input = scan.nextLine();

            if (!input.isEmpty() || input.isEmpty()) {
                isAnyKey = true;
            } else {
                println("Unknown input, please enter text or press [Enter].");
            }

        } while (!isAnyKey);

        return input;
    }

    public LocalDate readDate(String prompt) {
        String input;
        Boolean isDate = false;
        LocalDate date = null;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        do {
            print(prompt);
            input = scan.nextLine();

            try {
                date = LocalDate.parse(input, format);
                isDate = true;
            } catch (DateTimeParseException e) {
                println("Please enter date as MM/DD/YYYY.");
                isDate = false;
            }

        } while (!isDate);

        return date;

    }

    public BigDecimal readBigDecimal(String prompt) {
        String input;
        boolean isBigDecimal = false;
        BigDecimal number = null;

        do {
            print(prompt);
            input = scan.nextLine();

            try {
                number = new BigDecimal(input);
                isBigDecimal = true;
            } catch (NumberFormatException e) {
                println("Please enter a number.");
                isBigDecimal = false;
            }

        } while (!isBigDecimal);

        return number;
    }
    
    public boolean readYesNo(String prompt) {
        String input;
        boolean isYesNo = false;
        boolean choice = false;
        
        do {            
            print(prompt);
            input = scan.nextLine().toUpperCase();
            
            switch (input) {
                case "Y":
                case "YES":
                    choice = true;
                    isYesNo = true;
                    break;
                case "N":
                case "NO":
                    choice = false;
                    isYesNo = true;
                    break;
                default:
                    println(invalidEntry);
                    isYesNo = false;
            }
        } while (!isYesNo);
        
        return choice;
    }
}
