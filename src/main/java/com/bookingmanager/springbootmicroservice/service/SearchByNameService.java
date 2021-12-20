package com.bookingmanager.springbootmicroservice.service;

import com.bookingmanager.springbootmicroservice.common.ConfigureRooms;
import com.bookingmanager.springbootmicroservice.common.ErrorMessage;
import com.bookingmanager.springbootmicroservice.entity.Room;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * I don't think the SearchByNameService will be called as many as SearchByDateService
 * So, the database won't be visited so often. It's fine even if we don't set the
 * order data into redis.
 */

@Service
@Slf4j
public class SearchByNameService {
    public ArrayList<Room> searchByName(String guestName){
        ConfigureRooms configureRooms = new ConfigureRooms();
        ArrayList<Room> totalrooms = configureRooms.configureRooms();
        ArrayList<Room> orderedRooms = new ArrayList<>();
        if (guestName == null || guestName.equals("")){
            ErrorMessage errorMessage = ErrorMessage.NAME_ERROR;
            log.error("状态码：" + errorMessage.code() +
                    " 状态信息：" + errorMessage.msg());
            return orderedRooms;
        }
        if(!totalrooms.isEmpty()){
            for (Room room: totalrooms) {
                if (room.getGuestName()!=null && room.getGuestName().equals(guestName)){
                    System.out.println(room.getGuestName());
                    orderedRooms.add(room);
                }
            }
        }
        return orderedRooms;
    }
}
