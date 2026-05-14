package com.transactions;

import java.util.ArrayList;
import java.util.List;

import com.transactions.model.Transaction;
import com.transactions.service.AnalyticsService;

public class Main {

    public static void main(String[] args) {

        List<Transaction> transactions = new ArrayList<>();

        transactions.add(new Transaction("T1", "U1", "P1", "iPhone", "Electronics", 1, 80000, System.currentTimeMillis()));
        transactions.add(new Transaction("T2", "U2", "P2", "Shoes", "Fashion", 2, 3000, System.currentTimeMillis()));
        transactions.add(new Transaction("T3", "U1", "P3", "Laptop", "Electronics", 1, 120000, System.currentTimeMillis()));
        transactions.add(new Transaction("T4", "U3", "P2", "Shoes", "Fashion", 1, 3000, System.currentTimeMillis()));
        transactions.add(new Transaction("T5", "U2", "P1", "iPhone", "Electronics", 1, 80000, System.currentTimeMillis()));
        transactions.add(new Transaction("T6", "U1", "P1", "iPhone", "Electronics", 1, 80000, System.currentTimeMillis()));

        AnalyticsService service = new AnalyticsService();

        System.out.println("Total Revenue: " + service.getTotalRevenue(transactions));
        System.out.println("Total Transactions: " + service.getTotalTransactions(transactions));
        System.out.println("Unique Users: " + service.getUniqueUserCount(transactions));

        System.out.println("Quantity per Product: " + service.getTotalQuantityPerProduct(transactions));
        System.out.println("Top Products: " + service.getTopKProductsByQuantity(transactions, 2));

        System.out.println("Revenue per Category: " + service.getRevenuePerCategory(transactions));

        System.out.println("Spending per User: " + service.getSpendingPerUser(transactions));
        System.out.println("Products per User: " + service.getProductsPerUser(transactions));

        System.out.println("Duplicate Buyers: " + service.getUsersWithDuplicatePurchases(transactions));

        System.out.println("Latest Transactions: " + service.getLatestNTransactions(transactions, 3));

        System.out.println("High Value Users: " + service.getHighValueUsers(transactions));
    }
}