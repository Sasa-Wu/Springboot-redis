package com.bookingmanager.springbootmicroservice.service;

import com.bookingmanager.springbootmicroservice.entity.Order;
import com.bookingmanager.springbootmicroservice.entity.Room;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaveOrderServiceTest {

     @Resource(name = "saveOrderService")
     private SaveOrderService saveOrderService;

    // There is a room available, and the guest has input his information correctly
    // The order(room1) can be saved
    @Test
    public void testSaveOrder1() {

        Order order1 = new Order();
        order1.setDate(20100114);
        order1.setGuestName("Amy");
        order1.setRoomNumber("1177");
        ArrayList<Room> result = saveOrderService.saveOrder(order1);
        assertTrue(result.size() == 1);
        // The orderDates have set
        assertTrue(result.get(0).getOrderDateFrom()==20100114);
        assertTrue(result.get(0).getOrderDateTo()==20100114);
        // The guestName has set
        assertEquals(result.get(0).getGuestName(),"Amy");
        assertEquals(result.get(0).getRoomNumber(),"1177");
    }

    // There is a room available, but the guest has input his information incorrectly
    // If the guest hasn't input his name(""), so the order(room1) can't be saved
    @Test
    public void testSaveOrder2() {
        Order order1 = new Order();
        order1.setDate(20100114);
        // GuestName is ""
        order1.setGuestName("");
        order1.setRoomNumber("1177");
        ArrayList<Room> result = saveOrderService.saveOrder(order1);
        // no room has booked
        assertTrue(result.size() == 0);
    }

    // There is a room available, but the hasn't guest input his information correctly
    // If the guest hasn't input his name(null), so the order(room1) can't be saved
    @Test
    public void testSaveOrder3() {
        Order order1 = new Order();
        order1.setDate(20100114);
        // GuestName is null
        order1.setGuestName(null);
        order1.setRoomNumber("1177");
        ArrayList<Room> result = saveOrderService.saveOrder(order1);
        // no room has booked
        assertTrue(result.size() == 0);
    }

    // There is a room available, but the guest input his information incorrectly
    // If the guest didn't input his orderDate, so the order(room1) can't be saved
    @Test
    public void testSaveOrder4() {
        Order order1 = new Order();
        order1.setGuestName("Amy");
        order1.setRoomNumber("1177");
        order1.setDate(0);
        ArrayList<Room> result = saveOrderService.saveOrder(order1);
        assertTrue(result.size() == 0);
    }


    // There is a room available, but the guest input his information incorrectly
    // If the guest didn't input roomNumber(""), the order can't be saved
    @Test
    public void testSaveOrder5() {
        Order order1 = new Order();
        order1.setDate(20100114);
        order1.setGuestName("Amy");
        // roomNumber is ""
        order1.setRoomNumber("");
        ArrayList<Room> result = saveOrderService.saveOrder(order1);
        assertTrue(result.size() == 0);
    }

    // There is a room available, but the guest input his information incorrectly
    // If the guest hasn't input roomNumber(null), so the order can't be saved
    @Test
    public void testSaveOrder6() {
        Order order1 = new Order();
        order1.setDate(20100114);
        order1.setGuestName("Amy");
        // roomNumber is null
        order1.setRoomNumber(null);
        ArrayList<Room> result = saveOrderService.saveOrder(order1);
        // no room has booked
        assertTrue(result.size() == 0);
    }

    // If the guest input his information correctly, but there is no room available
    // The roomNumber doesn't match, so the order can't be saved
    @Test
    public void testSaveOrder7() {
        Order order1 = new Order();
        order1.setDate(20100114);
        order1.setGuestName("Amy");
        // set the order roomNumber as "1188"
        order1.setRoomNumber("1188");
        ArrayList<Room> result = saveOrderService.saveOrder(order1);
        // no room has booked
        assertTrue(result.size() == 0);
    }

    // The OrderDate < AvailableDateFrom, so no room has booked
    @Test
    public void testSaveOrder8() {
        Order order1 = new Order();
        // The Date(20100113) < AvailableDateFrom(20100114)
        order1.setDate(20100113);
        order1.setGuestName("Amy");
        // The guest would like to book room1
        order1.setRoomNumber("1177");
        ArrayList<Room> result = saveOrderService.saveOrder(order1);
        // room1 hasn't booked
        assertTrue(result.size() == 0);
    }

    // The Date > AvailableDateTo, no room has booked
    @Test
    public void testSaveOrder9() {
        Order order1 = new Order();
        // The Date(20100115) > AvailableDateTo(20100114)
        order1.setDate(20100115);
        order1.setGuestName("Amy");
        order1.setRoomNumber("1177");
        ArrayList<Room> result = saveOrderService.saveOrder(order1);
        // room1 hasn't booked
        assertTrue(result.size() == 0);
    }

    // The room(room9) with a name but without setting OrderDateFrom and OrderDateTo can't be booked
    @Test
    public void testSaveOrder10() {
        Order order1 = new Order();
        order1.setDate(20150114);
        order1.setGuestName("Amy");
        // The guest would like to order room9
        order1.setRoomNumber("9527");
        ArrayList<Room> result = saveOrderService.saveOrder(order1);
        // room9 hasn't booked by Amy
        assertTrue(result.size() == 0);
    }


    /* Assume there are 4 booking orders for
     the same room at the same time. Only the one guest can book the room. */
    @Test
    public void testSaveOrder11() throws Exception {
        Order order1 = new Order();
        order1.setDate(20100114);
        order1.setGuestName("Amy1");
        order1.setRoomNumber("1177");

        Order order2 = new Order();
        order2.setDate(20100114);
        order2.setGuestName("Amy2");
        order2.setRoomNumber("1177");

        Order order3 = new Order();
        order3.setDate(20100114);
        order3.setGuestName("Amy3");
        order3.setRoomNumber("1177");

        Order order4 = new Order();
        order4.setDate(20100114);
        order4.setGuestName("Amy4");
        order4.setRoomNumber("1177");


        Callable<List> callable1 = new Callable<List>() {
            @Override
            public List<Room> call() throws Exception {
                ArrayList<Room> result = new ArrayList<>();
                result = saveOrderService.saveOrder(order1);
                return result;
            }
        };

        Callable<List> callable2 = new Callable<List>() {
            @Override
            public List<Room> call() throws Exception {
                ArrayList<Room> result = new ArrayList<>();
                result = saveOrderService.saveOrder(order2);
                return result;
            }
        };

        Callable<List> callable3 = new Callable<List>() {
            @Override
            public List<Room> call() throws Exception {
                ArrayList<Room> result = new ArrayList<>();
                result = saveOrderService.saveOrder(order3);
                return result;
            }
        };

        Callable<List> callable4 = new Callable<List>() {
            @Override
            public List<Room> call() throws Exception {
                ArrayList<Room> result = new ArrayList<>();
                result = saveOrderService.saveOrder(order4);
                return result;
            }
        };

        FutureTask<List> futureTask1 = new FutureTask<>(callable1);
        FutureTask<List> futureTask2 = new FutureTask<>(callable2);
        FutureTask<List> futureTask3 = new FutureTask<>(callable3);
        FutureTask<List> futureTask4 = new FutureTask<>(callable4);

        Thread t1 = new Thread(futureTask1,"Amy1");
        Thread t2 = new Thread(futureTask2, "Amy2");
        Thread t3 = new Thread(futureTask3, "Amy3");
        Thread t4 = new Thread(futureTask4, "Amy4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        List list1 = futureTask1.get();
        System.out.println("Amy1 has booked " + list1.size() + " Room");

        List list2 = futureTask2.get();
        System.out.println("Amy2 has booked " + list2.size() + " Room");

        List list3 = futureTask3.get();
        System.out.println("Amy3 has booked " + list3.size() + " Room");

        List list4 = futureTask4.get();
        System.out.println("Amy4 has booked " + list4.size() + " Room");

        // To implement the thread safe function, I choose synchronized.
        // It's none-ReentrantLock. Although Amy1 is the first guest who requests the
        // the order, it doesn't mean she can book the room in the end.
        // The only certain thing is only one guest can book the room. So, orderCount should be 1
        Integer orderCount = list1.size() + list2.size() + list3.size() + list4.size();

        System.out.println("OrderCount: "+orderCount);

        // only one order is successfully made
        assertTrue(orderCount==1);

    }
}
