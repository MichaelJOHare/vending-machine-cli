package com.michaeljohare;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogToFile {
    public void logToFile(String text, double placeholderPrice, FeedMoney feedMoney) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
        String formattedTime = LocalDateTime.now().format(formatter);

        try (PrintWriter log = new PrintWriter(new FileOutputStream("log.txt", true))) {
            if (text.equals("FEED MONEY")) {
                log.println(formattedTime + " " + text + ": " + NumberFormat.getCurrencyInstance().format(
                        feedMoney.getMoneyProvided())
                        + " " + NumberFormat.getCurrencyInstance().format(feedMoney.getCurrentBalance(
                                feedMoney.getMoneyProvided(), feedMoney.getPreviousBalance()) - placeholderPrice));
            } else if (text.equals("GIVE CHANGE")) {
                log.println(formattedTime + " " + text + ": " +
                        NumberFormat.getCurrencyInstance().format(feedMoney.getCurrentBalance(
                                feedMoney.getMoneyProvided(), feedMoney.getPreviousBalance()) - placeholderPrice) +
                        " $0.00");
            } else {
                log.println(formattedTime + " " + text +
                        NumberFormat.getCurrencyInstance().format(feedMoney.getCurrentBalance(
                                feedMoney.getMoneyProvided(), feedMoney.getPreviousBalance()) - placeholderPrice));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Log file missing. " + e.getMessage());
        }
    }
}
