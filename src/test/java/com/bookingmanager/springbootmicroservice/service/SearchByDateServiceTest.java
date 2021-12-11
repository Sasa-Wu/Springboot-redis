package com.bookingmanager.springbootmicroservice.service;
import com.bookingmanager.springbootmicroservice.entity.Room;
import javafx.application.Application;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchByDateServiceTest {

    @Resource(name = "searchByDateService")
    private SearchByDateService searchByDateService;

    // correct input information, one room is found
    @Test
    public void searchByDate1() {
        ArrayList<Room> result = searchByDateService.searchByDate(20100115);
        assertTrue(result.size() == 1);
        assertTrue(result.get(0).getAvailableDateFrom()==20100115);
        assertTrue(result.get(0).getAvailableDateTo()==20100115);
        assertTrue(result.get(0).getOrderDateFrom()==20100114);
        assertTrue(result.get(0).getOrderDateTo()==20100114);
        assertEquals(result.get(0).getGuestName(),"Jeff");
        assertEquals(result.get(0).getRoomNumber(),"1166");

    }

    // No error in input information, and three rooms are found
    @Test
    public void searchByDate2() {
        ArrayList<Room> result = searchByDateService.searchByDate(20100116);
        // 3 rooms has found, so the list's size is 3
        assertTrue(result.size() == 3);
        // room3
        assertTrue(result.get(0).getAvailableDateFrom()==20100116);
        assertTrue(result.get(0).getAvailableDateTo()==20100116);
        assertEquals(result.get(0).getGuestName(),"Kate");
        assertEquals(result.get(0).getRoomNumber(),"1144");
        // room4
        assertTrue(result.get(1).getAvailableDateFrom()==20100116);
        assertTrue(result.get(1).getAvailableDateTo()==20100116);
        assertEquals(result.get(1).getGuestName(),"May");
        assertEquals(result.get(1).getRoomNumber(),"1133");
        // room5
        assertTrue(result.get(2).getAvailableDateFrom()==20100116);
        assertTrue(result.get(2).getAvailableDateTo()==20100116);
        assertEquals(result.get(2).getGuestName(),"Laura");
        assertEquals(result.get(2).getRoomNumber(),"1122");
    }

    // invalid queryDate is input, then no available room is found
    @Test
    public void searchByDate3() {
        ArrayList<Room> result = searchByDateService.searchByDate(0);
        assertTrue(result.size() == 0);
    }

    // correct input information, no room is found
    @Test
    public void searchByDate4() {
        // set the query date is 20170115
        ArrayList<Room> result = searchByDateService.searchByDate(20170115);
        assertTrue(result.size() == 0);
    }

}
