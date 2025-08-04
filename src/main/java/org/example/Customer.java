package org.example;

import java.io.*;
import java.nio.file.*;

public class Customer {
    private int id;
    private String name;
    private String email;
    private String default_address;

    private static final String CSV_FILE = "customers.csv";

    public Customer(int id, String name, String email, String default_address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.default_address = default_address;
    }

    public boolean isRegistered() {
        Path path = Paths.get(CSV_FILE);

        if (!Files.exists(path)) {
            return false;
        }

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0 && Integer.parseInt(data[0].trim()) == this.id) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void registerCustomer() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE, true))) {
            writer.write(String.format("%d,%s,%s,%s%n", id, name, email, default_address));
            System.out.println("Customer successfully registered.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkOrRegister() {
        if (isRegistered()) {
            System.out.println("Customer is already registered.");
        } else {
            registerCustomer();
        }
    }
}

