package com.bookingmanager.springbootmicroservice.controller;

import com.bookingmanager.springbootmicroservice.entity.Room;
import com.bookingmanager.springbootmicroservice.service.SearchByDateService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/api")
public class SearchByDateController {
    @Resource
    private SearchByDateService searchByDateService;

    @GetMapping( "/searchByDate/{date}")
    public ArrayList<Room> searchByDate(@PathVariable int date){
        // Assume the hotel is a global hotel, there are about 500 guests
        // check the available rooms on the date of 20100115 at the same time
        // url (http://localhost:8080/api/searchByDate/20100115)
        ExecutorService es = Executors.newFixedThreadPool(500);
        for (int i= 0; i <500; i++ ){
            es.submit(new Runnable() {
                @Override
                public void run() {
                    searchByDateService.searchByDate(date);
                }
            });
        }
        return searchByDateService.searchByDate(date);
    }

}
