package com.bookingmanager.springbootmicroservice.entity;

import lombok.Data;

@Data
public class Room {
    private String roomNumber;
    private String guestName;
    private int availableDateFrom;
    private int availableDateTo;
    private int orderDateFrom;
    private int orderDateTo;
}
