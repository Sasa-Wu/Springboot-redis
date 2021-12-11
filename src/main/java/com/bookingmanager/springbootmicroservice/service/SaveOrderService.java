package com.bookingmanager.springbootmicroservice.service;

import com.bookingmanager.springbootmicroservice.common.ConfigureRooms;
import com.bookingmanager.springbootmicroservice.common.ErrorMessage;
import com.bookingmanager.springbootmicroservice.entity.Order;
import com.bookingmanager.springbootmicroservice.entity.Room;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class SaveOrderService {
    private int a = 0;
    public synchronized ArrayList<Room> saveOrder(Order order) {

        ConfigureRooms configureRooms = new ConfigureRooms();
        ArrayList<Room> totalrooms = configureRooms.configureRooms();
        ArrayList<Room> orderRoom = new ArrayList<>();
        if (order.getDate() == 0) {
            ErrorMessage errorMessage = ErrorMessage.DATE_ERROR;
            System.out.println("状态码：" + errorMessage.code() +
                    " 状态信息：" + errorMessage.msg());
            return orderRoom;
        }
        if (order.getGuestName() == null || order.getGuestName().equals("")) {
            ErrorMessage errorMessage = ErrorMessage.NAME_ERROR;
            System.out.println("状态码：" + errorMessage.code() +
                    " 状态信息：" + errorMessage.msg());
            return orderRoom;
        }
        if (order.getRoomNumber() == null || order.getRoomNumber().equals("")) {
            ErrorMessage errorMessage = ErrorMessage.NUMBER_ERROR;
            System.out.println("状态码：" + errorMessage.code() +
                    " 状态信息：" + errorMessage.msg());
            return orderRoom;
        }
        if (!totalrooms.isEmpty()) {
            try {
                Thread.sleep(100);
                System.out.println("The hotel staff is helping " + Thread.currentThread().getName() + " checking the room"
                );
            } catch (InterruptedException ie) {

            }
            for (Room room : totalrooms) {
                if (order.getRoomNumber().equals(room.getRoomNumber()) &&
                        room.getAvailableDateFrom() <= order.getDate() &&
                        room.getAvailableDateTo() >= order.getDate() &&
                        room.getGuestName() == null && a ==0) {
                    room.setGuestName(order.getGuestName());
                    room.setOrderDateFrom(order.getDate());
                    room.setOrderDateTo(order.getDate());
                    orderRoom.add(room);
                    System.out.println("The room has been booked by " + Thread.currentThread().getName());
                    a += 1;
                    break;
                }
            }
        }
        return orderRoom;
    }
}
