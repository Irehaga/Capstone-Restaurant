package com.tamnguyen.restaurant.utility;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Tam Nguyen
 */

@Component
public class GenerateOrderNumber {


    private static final String ORDER_NUMBER = "Order Number";
    private static final AtomicInteger counter = new AtomicInteger(1);

    public String generateOrderNumber(){

        LocalDateTime now = LocalDateTime.now();
        String formatDateTime = now.format(DateTimeFormatter.ofPattern("yyyyMMDDHHmmss"));
        int orderSequence = counter.getAndIncrement();
        return ORDER_NUMBER + formatDateTime + orderSequence;

    }


}
