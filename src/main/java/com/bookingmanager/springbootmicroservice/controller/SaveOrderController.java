package com.bookingmanager.springbootmicroservice.controller;

import com.bookingmanager.springbootmicroservice.entity.Order;
import com.bookingmanager.springbootmicroservice.entity.Room;
import com.bookingmanager.springbootmicroservice.service.SaveOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

@RestController
@RequestMapping("/api")
@Slf4j
public class SaveOrderController {
    @Resource
    private SaveOrderService saveOrderService;

    @PostMapping( "/saveorder")
    public ArrayList<Room> saveOrder(@RequestBody Order order){
        log.info("saveOrder method in controller");
        return saveOrderService.saveOrder(order);
    }

}
