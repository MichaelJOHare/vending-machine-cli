package com.michaeljohare;



public class Item {
    private String name;
    private String message;
    private String price;
    private String location;


    public Item(String name, String message, String price, String location) {
        this.name = name;
        this.message = message;
        this.price = price;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        if (message.equals("Chip")) {
            message = "Crunch Crunch, Yum!";
        } else if (message.equals("Candy")) {
            message = "Munch Munch, Yum!";
        } else if (message.equals("Drink")) {
            message = "Glug Glug, Yum!";
        } else {
            message = "Chew Chew, Yum!";
        }
        return message;
    }

    public String getPrice() {
        return price;
    }

    public String getLocation() {
        return location;
    }
}
