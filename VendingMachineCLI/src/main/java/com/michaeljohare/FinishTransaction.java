package com.michaeljohare;

import java.math.BigDecimal;
import java.math.MathContext;

public class FinishTransaction {
    private final BigDecimal QUARTER = BigDecimal.valueOf(0.25);
    private final BigDecimal DIME = BigDecimal.valueOf(0.10);
    private final BigDecimal NICKEL = BigDecimal.valueOf(0.05);
   private int quarterCounter;
   private int dimeCounter;
   private int nickelCounter;

    public void finishTransaction(double placeholderPrice, FeedMoney feedmoney) {

        MathContext precision = new MathContext(4);

        quarterCounter = 0;
        dimeCounter = 0;
        nickelCounter = 0;

        BigDecimal bigPlaceHolderPrice = BigDecimal.valueOf(placeholderPrice).round(precision);
        BigDecimal bigCurrentBalance = BigDecimal.valueOf(feedmoney.getCurrentBalance(feedmoney.getMoneyProvided(),
                feedmoney.getPreviousBalance()));

        BigDecimal remainingBalance = bigCurrentBalance.subtract(bigPlaceHolderPrice);

        while (remainingBalance.compareTo(BigDecimal.ZERO)> 0) {
            if (remainingBalance.compareTo(QUARTER) >= 0) {
                remainingBalance = remainingBalance.subtract(QUARTER);
                this.quarterCounter+=1;
            } else if (remainingBalance.compareTo(DIME) >= 0) {
                remainingBalance = remainingBalance.subtract(DIME);
                this.dimeCounter+=1;
            } else if (remainingBalance.compareTo(NICKEL) >= 0) {
                remainingBalance = remainingBalance.subtract(NICKEL);
                this.nickelCounter+=1;
            }
        }
    }

    public void getMessage(double placeholderPrice, FeedMoney feedmoney) {
        LogToFile logToFile = new LogToFile();
        System.out.println();
        System.out.println("Your change is: " + this.quarterCounter + " Quarter(s), " + this.dimeCounter + " Dime(s), and "
                + this.nickelCounter + " Nickel(s).");
        System.out.println();
        System.out.println("Current Balance: $0.00");
        logToFile.logToFile("GIVE CHANGE", placeholderPrice, feedmoney);
    }
}
