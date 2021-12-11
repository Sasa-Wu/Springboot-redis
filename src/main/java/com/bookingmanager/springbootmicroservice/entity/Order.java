package com.bookingmanager.springbootmicroservice.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class Order implements Serializable {

    private String guestName;
    private String roomNumber;
    private int date;
    private Room room;

}

