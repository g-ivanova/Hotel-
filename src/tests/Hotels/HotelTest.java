package Hotels;

import Clients.Client;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HotelTest {

    private Hotel hotel;

    @Before
    public void setUp() throws Exception {
        hotel = new Hotel(7, "Beach");
    }

    @Test
    public void set_id_hotelInvalid() {
        try {
            hotel.set_id_hotel(-1);
            fail("negative id in set_id_hotel() should trigger an exception");
        } catch (IllegalArgumentException e) {
            System.out.println("Properly caught negative value in set_id_hotel():" + e.getMessage());
        } catch (Exception ex) {
            fail("Wrong exception thrown for set_id_hotel() with negative argument");
        }
    }

    @Test
    public void testset_id_hotel() {
        hotel.set_id_hotel(6);
        int expresult = 6;
        assertEquals(expresult, hotel.get_ID_hotel());
    }

    @Test
    public void set_name_hotelInvalid() {
        try {
            hotel.set_name_hotel("Ac");
            fail("Not enough characters in set_name_hotel() should trigger an exception");
        } catch (IllegalArgumentException e) {
            System.out.println("Properly caught not enough characters in set_name_hotel():" + e.getMessage());
        } catch (Exception ex) {
            fail("Wrong exception thrown for set_name_hotel() with not enough characters");
        }
    }

    @Test
    public void testset_name_client() {
        hotel.set_name_hotel("Ocean");
        String expresult="Ocean";
        assertEquals(expresult,hotel.get_name_hotel());
    }


}
