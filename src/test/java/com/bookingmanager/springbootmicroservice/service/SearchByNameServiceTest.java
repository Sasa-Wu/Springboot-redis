package com.bookingmanager.springbootmicroservice.service;

import com.bookingmanager.springbootmicroservice.entity.Room;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchByNameServiceTest {

    @Resource(name = "searchByNameService" )
    private SearchByNameService searchByNameService;

    // correct input information, one booked room is found
    @Test
    public void searchByName1() {
        ArrayList<Room> result = searchByNameService.searchByName("Jeff");
        // room2 is found
        assertTrue(result.size() == 1);
        assertTrue(result.get(0).getOrderDateFrom()==20100114);
        assertTrue(result.get(0).getOrderDateTo()==20100114);
        assertEquals(result.get(0).getGuestName(),"Jeff");
        assertEquals(result.get(0).getRoomNumber(),"1166");
    }

    // correct input information, three booked rooms are found
    @Test
    public void searchByName2() {
        ArrayList<Room> result = searchByNameService.searchByName("Sasa");
        System.out.println(result.size());
        assertTrue(result.size() == 3);
        // roo6
        assertTrue(result.get(0).getOrderDateFrom()==20100111);
        assertTrue(result.get(0).getOrderDateTo()==20100111);
        assertEquals(result.get(0).getGuestName(),"Sasa");
        assertEquals(result.get(0).getRoomNumber(),"1111");
        // room7
        assertTrue(result.get(1).getOrderDateFrom()==20100112);
        assertTrue(result.get(1).getOrderDateTo()==20100112);
        assertEquals(result.get(1).getGuestName(),"Sasa");
        assertEquals(result.get(1).getRoomNumber(),"1100");
        // room8
        assertTrue(result.get(2).getOrderDateFrom()==20100113);
        assertTrue(result.get(2).getOrderDateTo()==20100113);
        assertEquals(result.get(2).getGuestName(),"Sasa");
        assertEquals(result.get(2).getRoomNumber(),"11AA");
    }

    // correct input information, one booked room is found
    @Test
    public void searchByName3() {
        // no guest named Kafa has booked any room
        ArrayList<Room> result = searchByNameService.searchByName("kafa");
        // no room is found
        assertTrue(result.size() == 0);
    }

    // invalid input name
    @Test
    public void searchByName4() {
        // guestName is ""
        ArrayList<Room> result = searchByNameService.searchByName("");
        // no room is found
        assertTrue(result.size() == 0);
    }

    // invalid input name
    @DisplayName("Test searchByNameService.searchByName5()")
    @Test
    public void searchByName5() {
        // guestName is null
        ArrayList<Room> result = searchByNameService.searchByName(null);
        assertTrue(result.size() == 0);
    }
}
