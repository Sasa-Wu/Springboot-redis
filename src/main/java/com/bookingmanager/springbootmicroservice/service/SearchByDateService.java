package com.bookingmanager.springbootmicroservice.service;

import com.bookingmanager.springbootmicroservice.common.ConfigureRooms;
import com.bookingmanager.springbootmicroservice.common.ErrorMessage;
import com.bookingmanager.springbootmicroservice.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SearchByDateService {
    @Autowired
    private RedisTemplate redisTemplate;

    public ArrayList<Room> searchByDate(int queryDate) {
        ConfigureRooms configureRooms = new ConfigureRooms();
        ArrayList<Room> totalrooms = configureRooms.configureRooms();
        ArrayList<Room> availableRooms = new ArrayList<>();
        if (queryDate == 0) {
            ErrorMessage errorMessage = ErrorMessage.DATE_ERROR;
            System.out.println("状态码：" + errorMessage.code() +
                    " 状态信息：" + errorMessage.msg());
            return availableRooms;
        }
        String key = "Date_" + queryDate;
        // Try to get the data from cache first, if exits, return
        Object roomObj = redisTemplate.opsForValue().get(key);
        if (roomObj == null) {
            synchronized (this.getClass()) {
                // if it doesn't, then search from the database, and set into cache
                // before search from the Database, check the redis data again
                roomObj = redisTemplate.opsForValue().get(key);
                if(roomObj == null){
                    // if the data still not exits
                    System.out.println("Search from database");
                    // start search from the database
                    if (!totalrooms.isEmpty()) {
                        for (Room room : totalrooms) {
                            if (room.getAvailableDateFrom() <= queryDate &&
                                    room.getAvailableDateTo() >= queryDate) {
                                availableRooms.add(room);
                            }
                        }
                    }
                    // to reduce the load of database and reduce the searching time
                    // we can set the data that search from database into redis
                    // when you send the url (http://localhost:8080/api/searchByDate/20100115)
                    // from postman, and you can find the log-"Search from database" only once
                    // it means that, even there are 500 guests query the same url at the same time
                    // we need to visit the database only once.
                    redisTemplate.opsForValue().set(key, availableRooms);
                    return availableRooms;
                }else {
                    System.out.println("Search from cache--synchronized");
                    return availableRooms;
                }
            }
        } else {
            System.out.println("Search from cache");
        }
        return availableRooms;
    }
}

