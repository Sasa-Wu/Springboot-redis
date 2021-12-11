package com.bookingmanager.springbootmicroservice.common;

import com.bookingmanager.springbootmicroservice.entity.Room;

import java.util.ArrayList;

public class ConfigureRooms {
    public ArrayList<Room> configureRooms() {
        ArrayList<Room> totalrooms = new ArrayList<>();
        Room room1 = new Room();
        room1.setAvailableDateFrom(20100114);
        room1.setAvailableDateTo(20100114);
        room1.setRoomNumber("1177");
        room1.setGuestName(null);
        room1.setOrderDateFrom(0);
        room1.setOrderDateTo(0);

        Room room2 = new Room();
        room2.setAvailableDateFrom(20100115);
        room2.setAvailableDateTo(20100115);
        room2.setRoomNumber("1166");
        room2.setGuestName("Jeff");
        room2.setOrderDateFrom(20100114);
        room2.setOrderDateTo(20100114);

        Room room3 = new Room();
        room3.setAvailableDateFrom(20100116);
        room3.setAvailableDateTo(20100116);
        room3.setRoomNumber("1144");
        room3.setGuestName("Kate");
        room3.setOrderDateFrom(0);
        room3.setOrderDateTo(0);

        Room room4 = new Room();
        room4.setAvailableDateFrom(20100116);
        room4.setAvailableDateTo(20100116);
        room4.setRoomNumber("1133");
        room4.setGuestName("May");
        room4.setOrderDateFrom(0);
        room4.setOrderDateTo(0);

        Room room5 = new Room();
        room5.setAvailableDateFrom(20100116);
        room5.setAvailableDateTo(20100116);
        room5.setRoomNumber("1122");
        room5.setGuestName("Laura");
        room5.setOrderDateFrom(0);
        room5.setOrderDateTo(0);

        Room room6 = new Room();
        room6.setAvailableDateFrom(20130116);
        room6.setAvailableDateTo(20130116);
        room6.setRoomNumber("1111");
        room6.setGuestName("Sasa");
        room6.setOrderDateFrom(20100111);
        room6.setOrderDateTo(20100111);

        Room room7 = new Room();
        room7.setAvailableDateFrom(20130116);
        room7.setAvailableDateTo(20130116);
        room7.setRoomNumber("1100");
        room7.setGuestName("Sasa");
        room7.setOrderDateFrom(20100112);
        room7.setOrderDateTo(20100112);

        Room room8 = new Room();
        room8.setAvailableDateFrom(20130116);
        room8.setAvailableDateTo(20130116);
        room8.setRoomNumber("11AA");
        room8.setGuestName("Sasa");
        room8.setOrderDateFrom(20100113);
        room8.setOrderDateTo(20100113);

        Room room9 = new Room();
        room9.setAvailableDateFrom(20150114);
        room9.setAvailableDateTo(20150115);
        room9.setRoomNumber("9527");
        room9.setGuestName("Karen");
        room9.setOrderDateFrom(0);
        room9.setOrderDateTo(0);


        totalrooms.add(room1);
        totalrooms.add(room2);
        totalrooms.add(room3);
        totalrooms.add(room4);
        totalrooms.add(room5);
        totalrooms.add(room6);
        totalrooms.add(room7);
        totalrooms.add(room8);
        totalrooms.add(room9);

        return totalrooms;
    }
}
