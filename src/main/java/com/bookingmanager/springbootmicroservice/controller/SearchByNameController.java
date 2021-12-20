package com.bookingmanager.springbootmicroservice.controller;

import com.bookingmanager.springbootmicroservice.entity.Room;
import com.bookingmanager.springbootmicroservice.service.SearchByNameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;

@RestController
@RequestMapping("/api")
@Slf4j
public class SearchByNameController {
    @Resource
    private SearchByNameService searchByNameService;

    @GetMapping( "/searchByName")
    public ArrayList<Room> searchByDate(@PathVariable String name){
        log.info("searchByDate method in controller");
        return searchByNameService.searchByName(name);
    }

}
