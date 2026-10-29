package com.pluralsight;

import java.util.Scanner;

public class PizzaOrderSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Welcome to Tony's Pizza Palace ===");
        System.out.printf("Enter your name: ");
        String customerName = scanner.nextLine();

        // Display pizza options
        displayPizzaMenu();

        // Get pizza choice
        System.out.print("Enter your choice (1-3): ");
        int pizzaChoice = scanner.nextInt();

        // Get number of toppings
        System.out.print("How many additional toppings? $1.50 each: ");
        int numToppings = scanner.nextInt();

        // Get customer age
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();

        if (age < 1 || age > 120) {
            System.out.println("Invalid age. Please enter an age between 1 and 120.");
            return;
        }

        // Calculate order
        double pizzaPrice = getPizzaPrice(pizzaChoice);
        double subtotal = calculateSubtotal(pizzaPrice, numToppings);
        double discount = calculateDiscount(subtotal, age);
        double taxAmount = calculateTax(subtotal - discount);
        double total =Math.round((subtotal - discount + taxAmount) * 100.0) /100.0 ;

        // Display order summary
        displayOrderSummary(customerName, pizzaChoice, numToppings, pizzaPrice,
                subtotal, discount, taxAmount, total);

        scanner.close();
    }

    public static void displayPizzaMenu() {
        System.out.printf("Pizza Sizes:\n" +
                "1. Small Pizza - $8.99\n" +
                "2. Medium Pizza - $12.99\n" +
                "3. Large Pizza - $16.99\n");
    }

    public static double getPizzaPrice(int pizzaChoice) {
        if (pizzaChoice == 1) {
            return 8.99;
        } else if (pizzaChoice == 2) {
            return 12.99;
        } else if (pizzaChoice == 3) {
            return 16.99;
        }else {
            return 0.0;
        }
    }

    public static double calculateSubtotal(double pizzaPrice, int toppings) {
        return pizzaPrice + (toppings * 1.50);
    }

    public static double calculateDiscount(double subtotal, int age) {
        double discountRate = 0.0;
        if (age <= 17) {
            discountRate = 0.10; 
        } else if (age >= 65) {
            discountRate = 0.15; 
        }
        return subtotal * discountRate;
    }

    public static double calculateTax(double taxableAmount) {
        
        double TAX_RATE = 0.0825;  // 8.25% tax
        double tax = 0;
        if (taxableAmount > 0) {
            tax = taxableAmount * TAX_RATE;
        }
        return tax; 
    }

    public static void displayOrderSummary(String name, int pizzaChoice, int toppings,
                                           double pizzaPrice, double subtotal, double discount,
                                           double taxAmount, double total) {

        String pizzaSize = getPizzaSizeName(pizzaChoice);

        System.out.printf("\n=== Order Summary for " + name + " ===");
        System.out.printf("\n%s Pizza: $%.2f\n",pizzaSize, pizzaPrice);
        System.out.println("Total items: " + (1 + toppings));

        if (toppings > 0) {
            System.out.printf("Additional Toppings (%d): $%.2f" ,toppings, toppings * 1.50);
        }

        System.out.printf("\nSubtotal: $%.2f", subtotal);

        if (discount > 0) {
            String discountType = getDiscountType(discount, subtotal);

            System.out.printf("\n%s: -$%.2f" ,discountType, discount);
        }

        System.out.printf("\nTax (8.25%%): $%.2f", taxAmount);
        System.out.printf("\nTotal: $%.2f", total);

        System.out.println("\nThank you for your order!");
    }

    public static String getPizzaSizeName(int pizzaChoice) {
        switch (pizzaChoice) {
            case 1: return "Small";
            case 2: return "Medium";
            case 3: return "Large";
            default: return "Unknown";
        }
    }

    public static String getDiscountType(double discount, double subtotal) {
        double discountRate = discount / subtotal;

        if (discountRate >= 0.09 && discountRate <= 0.11) {  // Around 10%
            return "Student Discount (10%)";
        } else if (discountRate >= 0.14 && discountRate <= 0.16) {  // Around 15%
            return "Senior Discount (15%)";
        }
        return "Discount";
    }
}
