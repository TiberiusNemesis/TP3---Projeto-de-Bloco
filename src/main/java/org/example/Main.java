package org.example;
public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer(1, "John Smith", "john@example.com", "123 Flower Street");
        customer.checkOrRegister();
    }
}