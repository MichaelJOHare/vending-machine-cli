package com.michaeljohare;


import java.util.Scanner;

public class FeedMoney {
    private double moneyProvided = 0.0;
    private double previousBalance = 0.0;
    public double getCurrentBalance(double previousBalance, double moneyProvided) {
        return previousBalance + moneyProvided;
    }

    public void feedMoney(double placeholderPrice, FeedMoney feedMoney, String moneyProvidedString) {
        LogToFile logToFile = new LogToFile();
        previousBalance = getCurrentBalance(previousBalance, moneyProvided);

        boolean isValidInput = false;
        while (!isValidInput) {
           try {
               moneyProvided = Integer.parseInt(moneyProvidedString);
           } catch (NumberFormatException e) {
               System.out.println("Please enter a whole number.");
           }
           isValidInput = true;
           logToFile.logToFile("FEED MONEY", placeholderPrice, feedMoney);
       }
    }

    public String feedMoneyMessage() {
        Scanner userInput = new Scanner(System.in);
        System.out.println();
        System.out.print("Enter money in whole dollar amounts >>> ");

        return userInput.nextLine();
    }

    public void finishTransaction() {
        previousBalance = 0.0;
        moneyProvided = 0.0;
    }

    public double getMoneyProvided() {
        return moneyProvided;
    }
    public double getPreviousBalance() {
        return previousBalance;
    }
}
