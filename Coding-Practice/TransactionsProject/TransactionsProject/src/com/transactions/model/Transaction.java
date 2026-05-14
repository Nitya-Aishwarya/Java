package com.transactions.model;

import java.util.Objects;

public class Transaction {
    String transactionId;
    String userId;
    String productId;
    String productName;
    String category;
    int quantity;
    double pricePerUnit;
    long timestamp;

    public Transaction(String transactionId, String userId, String productId,
                       String productName, String category,
                       int quantity, double pricePerUnit, long timestamp) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.timestamp = timestamp;
    }

    public double getTotalPrice() {
        return quantity * pricePerUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(transactionId, that.transactionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId);
    }

    @Override
    public String toString() {
        return productName + " (" + quantity + ")";
    }

	public String getTransactionId() {
		return transactionId;
	}

	public String getUserId() {
		return userId;
	}

	public String getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public String getCategory() {
		return category;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}

	public long getTimestamp() {
		return timestamp;
	}
}