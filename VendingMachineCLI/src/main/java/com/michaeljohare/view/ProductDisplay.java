package com.michaeljohare.view;

import com.michaeljohare.Item;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductDisplay {

    private List<Item> displayItems = new ArrayList<>();

    public void setInventory(File inventory) {

        try (Scanner reader = new Scanner(inventory)) {
            while(reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] itemLine = line.split("\\|");
                Item item = new Item(itemLine[1], itemLine[3], itemLine[2], itemLine[0]);
                displayItems.add(item);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. " + e.getMessage());
        }
    }

    public void displayItems(int[] quantity){
        int placeholder = 0;
        for (int i = 0; i < displayItems.size()/4; i++) {
            System.out.println();
            System.out.println();
            for (int j = placeholder; j < 4 + placeholder; j++) {
                System.out.print("[" + displayItems.get(j).getLocation() + "] " + displayItems.get(j).getName()
                + " - $" + displayItems.get(j).getPrice() + (quantity[j] == 0 ? " SOLD OUT " : " quantity remaining: " +
                        quantity[j]) + " | ");
            }
            placeholder += 4;
        }
        System.out.println();
    }

    public List<Item> getDisplayItems() {
        return displayItems;
    }
}
