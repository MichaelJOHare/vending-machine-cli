package com.michaeljohare.view;

import com.michaeljohare.FeedMoney;

import java.text.NumberFormat;

public class PurchaseMenu {
    public void purchaseMenu(FeedMoney feedMoney, double placeholderPrice) {
        System.out.println();
        System.out.println("Current Balance: " +
                NumberFormat.getCurrencyInstance().format(feedMoney.getCurrentBalance(
                        feedMoney.getPreviousBalance(), feedMoney.getMoneyProvided()) - placeholderPrice));
    }
}
