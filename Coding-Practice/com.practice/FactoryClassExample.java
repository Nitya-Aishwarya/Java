package com.practice;

interface Payment {
    void pay();
}

class CreditCardPayment implements Payment {

    public void pay() {
        System.out.println("Payment done using Credit Card");
    }
}

class UPIPayment implements Payment {

    public void pay() {
        System.out.println("Payment done using UPI");
    }
}

class PayPalPayment implements Payment {

    public void pay() {
        System.out.println("Payment done using PayPal");
    }
}

class PaymentFactory {

    public Payment createPayment(String type) {

        if (type == null) {
            return null;
        }

        if (type.equalsIgnoreCase("CREDIT")) {
            return new CreditCardPayment();
        }

        else if (type.equalsIgnoreCase("UPI")) {
            return new UPIPayment();
        }

        else if (type.equalsIgnoreCase("PAYPAL")) {
            return new PayPalPayment();
        }

        return null;
    }
}

public class FactoryClassExample {
	public static void main(String[] args) {
		
		PaymentFactory factory = new PaymentFactory();
		
		Payment payment = factory.createPayment("UPI");
		
		payment.pay();
	}

}
