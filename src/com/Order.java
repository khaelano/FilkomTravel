package com;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

import com.car.*;
import com.user.*;

public class Order {
    private LocalDateTime invoiceDate;
    User renter;
    Car rentedCar;
    private LocalDateTime rentStartDate;
    private LocalDateTime rentEndDate;

    public Order(User renter, Car rentedCar) {
        this.renter = renter;
        this.rentedCar = rentedCar;

        this.invoiceDate = LocalDateTime.now();
    }

    public void setRentStartDate(String formattedDateAndTime) {
        // Unfinished
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime proposedDate = LocalDateTime.parse(formattedDateAndTime, formatter);
        LocalDateTime currentDate = LocalDateTime.now();

        if (proposedDate.isAfter(currentDate)) {
            this.rentStartDate = proposedDate;
        } else {
            System.out.println("Error! The rent start date can't be before current date!");
        }
    }

    public void setRentEndDate(String formattedDateAndTime) {
        // Unfinished
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime proposedDate = LocalDateTime.parse(formattedDateAndTime, formatter);

        if (proposedDate.isAfter(this.rentStartDate)) {
            this.rentEndDate = proposedDate;
        } else {
            System.out.println("Error! The rent end date can't be before rent start date!");
        }
    }

    public String getRentStartDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return this.rentStartDate.format(formatter);
    }
    
    public String getRentEndDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return this.rentEndDate.format(formatter);
    }

    public String getInvoiceDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return this.invoiceDate.format(formatter);
    }

    public int calculateDuration() {
        Duration duration = Duration.between(rentStartDate, rentEndDate);
        return (int) Math.ceil(duration.getSeconds() / 3600);
    }

    public int calculateTotalCharges() {
        int durationInHour = calculateDuration();
        return (int) (Math.ceil(durationInHour/6) * rentedCar.getRentFee() * (1 - this.renter.discount));
    }

    public void printBill() {
        Car car = this.rentedCar;

        System.out.println("################################################");
        System.out.println("########          Payment Bill          ########");
        System.out.println("################################################");
        System.out.println("Invoice date: " + getInvoiceDate());
        System.out.println("Renter Name: " + this.renter.name);
        System.out.println("------------------------------------------------");
        System.out.println("--------------- Rented Car Details -------------");
        System.out.printf("%-21s: %s\n", "Brand Name", car.brand);
        System.out.printf("%-21s: %s\n", "Model", car.model);
        System.out.printf("%-21s: %s\n", "Color", car.color);
        System.out.printf("%-21s: %s\n", "License Plate Number", car.licensePlateNum);
        System.out.printf("%-21s: %b\n", "include driver?", car.includeDriver);
        System.out.printf("%-21s: %s person\n", "Capacity", car.capacity);
        System.out.printf("%-21s: Rp. %s /6 hr\n", "Rental Fee", car.rentFee);
        System.out.println("------------------------------------------------");
        System.out.println("------------------ Rent Details ----------------");
        System.out.printf("%-20s: %s\n", "Start Date", getRentStartDate());
        System.out.printf("%-20s: %s\n", "End Date", getRentEndDate());
        System.out.printf("%-20s: %s hour\n", "Duration", Integer.toString(calculateDuration()));
        System.out.printf("%-30s: Rp. %s\n", "Total Charges", (int) (car.getRentFee() * Math.ceil(calculateDuration()/6)));
        System.out.printf("%-30s: Rp. %s\n", "Total Charges (after discount)", Integer.toString(calculateTotalCharges()));
        System.out.println("################################################");
        System.out.println("######  Thank you for using FilkomTravel!  #####");
        System.out.println("######    We hope to see you next time!    #####");
        System.out.println("################################################");
        System.out.println();
    }
}