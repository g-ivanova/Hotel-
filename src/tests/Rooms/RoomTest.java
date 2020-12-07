package Rooms;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoomTest {

    private Room room;

    @Before
    public void setUp() throws Exception {
        room=new Room("222","2","1","100");
    }

    @Test
    public void setId_roomInvalid() {
        try {
            room.setId_room("");
            fail("empty value in setId_room() should trigger an exception");
        } catch (IllegalArgumentException e) {
            System.out.println("Properly caught empty value in setId_room(): "+e.getMessage());
        } catch (Exception ex) {
            fail("Wrong exception thrown for setId_room() with empty argument");
        }

    }
    @Test
    public void testsetId_room() {
       room.setId_room("11");
        String expresult = "11";
        assertEquals(expresult, room.getId_room());
    }

    @Test
    public void setType_roomInvalid() {
        try {
            room.setType_room("");
            fail("empty value in setType_room() should trigger an exception");
        } catch (IllegalArgumentException e) {
            System.out.println("Properly caught empty value in setType_room(): "+e.getMessage());
        } catch (Exception ex) {
            fail("Wrong exception thrown for setType_room() with empty argument");
        }
    }

    @Test
    public void testsetTyoe_room() {
        room.setType_room("3");
        String expresult = "3";
        assertEquals(expresult, room.getType_room());
    }

    @Test
    public void setRatingInvalid() {
        try {
            room.setRating("11");
            fail("empty value in setRating() should trigger an exception");
        } catch (IllegalArgumentException e) {
            System.out.println("Properly caught empty value in setRating(): "+e.getMessage());
        } catch (Exception ex) {
            fail("Wrong exception thrown for setRating() with empty argument");
        }
    }

    @Test
    public void testsetRating() {
        room.setRating("50");
        String expresult = "50";
        assertEquals(expresult, room.getRating());
    }
}
