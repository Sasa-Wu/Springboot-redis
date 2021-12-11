package com.bookingmanager.springbootmicroservice.controller;

import com.bookingmanager.springbootmicroservice.entity.Room;
import com.bookingmanager.springbootmicroservice.service.SearchByNameService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class SearchByNameController {
    @Resource
    private SearchByNameService searchByNameService;

    @GetMapping( "/searchByName")
    public ArrayList<Room> searchByDate(@PathVariable String name){

        return searchByNameService.searchByName(name);
    }

}
