package com.michaeljohare;

import com.michaeljohare.view.ProductDisplay;


import java.text.NumberFormat;
import java.util.Scanner;

public class SelectProduct {

    public Item selectProduct(ProductDisplay productDisplay) {
        Item returnedItem = null;
        Scanner userInput = new Scanner(System.in);
        System.out.println();
        System.out.print("Please enter selection >>> ");
        String userSelection = userInput.nextLine();
        try {
            for (Item item : productDisplay.getDisplayItems()) {
                if (userSelection.equalsIgnoreCase(item.getLocation().replace("[", "").replace(
                        "]", ""))) {
                    returnedItem = item;
                    System.out.println();
                    System.out.println("Your choice was: " + item.getName() + ". Price: " + item.getPrice() + ". " +
                            item.getMessage());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Please enter Valid selection");
            System.out.println(e.getMessage());
        }
        return returnedItem;
    }

    public double attemptPurchase(Item userSelection, FeedMoney feedMoney, ProductDisplay productDisplay,
                                  int[] quantity, double placeholderPrice) {

        LogToFile log = new LogToFile();
        try {
            if ((feedMoney.getCurrentBalance(feedMoney.getMoneyProvided(), feedMoney.getPreviousBalance()) -
                    (Double.parseDouble(userSelection.getPrice()) + placeholderPrice)) > 0) {
                int index = -1;
                for (int i = 0; i < productDisplay.getDisplayItems().size(); i++) {
                    if (productDisplay.getDisplayItems().get(i).getName().equals(userSelection.getName())) {
                        index = i;
                        break;
                    }
                }
                if (index < 0) {
                    throw new RuntimeException("Item selected was not found.");
                }
                if (quantity[index] == 0) {
                    System.out.println();
                    System.out.println("Item is sold out, please choose another option.");
                } else {
                    placeholderPrice += Double.parseDouble(userSelection.getPrice());
                    quantity[index]--;
                    log.logToFile(userSelection.getName() + " " + userSelection.getLocation() + " " +
                            NumberFormat.getCurrencyInstance().format(Double.parseDouble(userSelection.getPrice())) +
                            " ", placeholderPrice, feedMoney);
                }
            } else {
                System.out.println();
                System.out.println("You do not have enough money inserted for this product.  Please" +
                        " enter a different selection or insert more money.");
            }
        } catch (NullPointerException e) {
            System.out.println();
            System.out.println("The item you selected is not an available option.");
        }
        return placeholderPrice;
    }
}
