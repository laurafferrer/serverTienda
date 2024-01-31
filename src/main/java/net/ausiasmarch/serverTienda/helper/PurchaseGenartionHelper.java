package net.ausiasmarch.serverTienda.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class PurchaseGenartionHelper {

    /*
     * Generate a random date from a week ago to the current date
     * 
     * @return A random date.
     */
    public static LocalDate getRandomDatePurchase() {
        // Calculate current date
        LocalDate oCurrentDate = LocalDate.now();

        // Calculate date a week ago
        LocalDate oWeekAgo = oCurrentDate.minusDays(7);

        // Calculate random date between a week ago and today
        long oRandomDate = ThreadLocalRandom.current().nextLong(oWeekAgo.toEpochDay(), oCurrentDate.toEpochDay());

        // Convert Random Date to LocalDate
        return LocalDate.ofEpochDay(oRandomDate);
    }


    /**
     * Generates a unique Long code for a bill based on the current date and a UUID.
     * 
     * @return A unique Long code for a bill.
     */
    public static Long getRandomNumBill() {
        // Format the current date in the pattern "yyyyMMdd"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        Long actualDate;

        try {
            // Attempt to parse the formatted date as a Long
            actualDate = Long.parseLong(LocalDate.now().format(formatter));
        } catch (NumberFormatException e) {
            // Handle the case where the formatted date is not a valid Long
            actualDate = 0L; // You can choose a default value or handle it differently
        }

        // Generate a UUID, remove hyphens, and take the first 4 characters
        Long uuid;

        try {
            // Attempt to parse the UUID as a Long
            uuid = Long.parseLong(UUID.randomUUID().toString().replace("-", "").substring(0, 4));
        } catch (NumberFormatException e) {
            // Handle the case where the UUID is not a valid Long
            uuid = 0L; // You can choose a default value or handle it differently
        }

        // Concatenate the formatted date and UUID to create the final Long code
        return actualDate * 10000 + uuid;
    }

    /*
     * Generate a random date from today to a week from now
     * 
     * @return A random date.
     */
    public static LocalDate getRandomDateBill() {
        // Calculate current date
        LocalDate oCurrentDate = LocalDate.now();

        // Calculate date a week from now
        LocalDate oWeekFromNow = oCurrentDate.plusDays(7);

        // Calculate random date between today and a week from now
        long oRandomDate = ThreadLocalRandom.current().nextLong(oCurrentDate.toEpochDay(), oWeekFromNow.toEpochDay());

        // Convert Random Date to LocalDate
        return LocalDate.ofEpochDay(oRandomDate);
    }
}
