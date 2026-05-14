  package com.transactions.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.transactions.model.Transaction;

public class AnalyticsService {
	
    // Task 1
    public double getTotalRevenue(List<Transaction> transactions) {
        return transactions.stream()
        				.mapToDouble(Transaction::getTotalPrice)
        				.sum();
    }

    public int getTotalTransactions(List<Transaction> transactions) {
        return transactions.size();
    }

   
    public long getUniqueUserCount(List<Transaction> transactions) {
        return transactions.stream()
                .map(Transaction::getUserId)
                .distinct()
                .count();
    }
    // Task 2
    public Map<String, Integer> getTotalQuantityPerProduct(List<Transaction> transactions) {
    	
    	return transactions.stream().collect(Collectors.groupingBy(Transaction::getProductName,
    			Collectors.summingInt(Transaction::getQuantity)));

    }

    public List<String> getTopKProductsByQuantity(List<Transaction> transactions, int k) {
        return transactions.stream()
                .collect(Collectors.groupingBy(
                        Transaction::getProductName,
                        Collectors.summingInt(Transaction::getQuantity)
                ))
                .entrySet()
                .stream()
                .sorted(Comparator.comparingInt(Map.Entry<String,Integer>::getValue).reversed())
                .limit(k)
                .map(Map.Entry::getKey)
                .toList();
    }

    // Task 3
    public Map<String, Double> getRevenuePerCategory(List<Transaction> transactions) {
    	
    	return transactions.stream().collect(
    			Collectors.groupingBy(Transaction::getCategory,
    					Collectors.summingDouble(Transaction::getTotalPrice)));
//        return new HashMap<>();
    }

    // Task 4
    public Map<String, Double> getSpendingPerUser(List<Transaction> transactions) {
    	return transactions.stream().collect(Collectors.groupingBy(Transaction::getUserId,
    			Collectors.summingDouble(Transaction::getTotalPrice)));
//        return new HashMap<>();
    }

    public Map<String, Set<String>> getProductsPerUser(List<Transaction> transactions) {
    	return transactions.stream().collect(Collectors.groupingBy(Transaction::getUserId,
    			Collectors.mapping(Transaction::getProductName, Collectors.toSet())));
//        return new HashMap<>();
    }

    // Task 5
    public Set<String> getUsersWithDuplicatePurchases(List<Transaction> transactions) {

        Map<String, Map<String, Long>> map =
                transactions.stream()
                        .collect(Collectors.groupingBy(
                                Transaction::getUserId,
                                Collectors.groupingBy(
                                        Transaction::getProductName,
                                        Collectors.counting()
                                )
                        ));

        return map.entrySet().stream()
                .filter(entry -> entry.getValue().values().stream().anyMatch(count -> count > 1))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    // Task 6
    public List<Transaction> getLatestNTransactions(List<Transaction> transactions, int n) {
    	return transactions.stream().sorted(Comparator.comparingLong(Transaction::getTimestamp).reversed() )
    					.limit(n)
    					.toList();
//        return new ArrayList<>();
    }

    // Task 7
    public List<String> getHighValueUsers(List<Transaction> transactions) {
    	
    return 	transactions.stream()
    				.collect(Collectors.groupingBy(
    						Transaction::getUserId,
    						Collectors.summingDouble(Transaction::getTotalPrice)
    						))
    				.entrySet()
    				.stream()
    				.filter(entry->entry.getValue()>10000)
    				.map(Map.Entry::getKey)
    				.toList();

    }
}
