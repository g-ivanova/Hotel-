package Services;

import Rooms.Room;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceTest {

    private Service service;

    @Before
    public void setUp() throws Exception {
        service=new Service("Boat","200","1","100");
    }

    @Test
    public void setName_serviceInvalid() {
        try {
            service.setName_service("Ad");
            fail("not enough characters in setName_service() should trigger an exception");
        } catch (IllegalArgumentException e) {
            System.out.println("Properly caught invalid value in setName_service(): "+e.getMessage());
        } catch (Exception ex) {
            fail("Wrong exception thrown for setName_service() with invalid argument");
        }
    }

    @Test
    public void testsetName_service() {
        service.setName_service("Bungee");
        String expresult = "Bungee";
        assertEquals(expresult, service.getName_service());
    }

    @Test
    public void setPrice_serviceInvalid() {
        try {
            service.setPrice_service("");
            fail("empty value in setPrice_service() should trigger an exception");
        } catch (IllegalArgumentException e) {
            System.out.println("Properly caught empty value in setPrice_service(): "+e.getMessage());
        } catch (Exception ex) {
            fail("Wrong exception thrown for setPrice_service with empty argument");
        }
    }

    @Test
    public void testsetPrice_service() {
        service.setPrice_service("121");
        String expresult = "121";
        assertEquals(expresult, service.getPrice_service());
    }
}
