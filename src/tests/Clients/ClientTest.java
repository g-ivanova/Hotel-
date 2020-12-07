package Clients;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClientTest {

    private Client client;

    @Before
    public void setUp() throws Exception {
        client=new Client(2,"Ivan Ivanov","Drujba 32","0879657890");
    }

    @Test
    public void setId_clientInvalid() {
        try {
            client.setId_client(-10);
            fail("negative id in setId_client() should trigger an exception");
        }
        catch(IllegalArgumentException e){
            System.out.println("Properly caught negative value in setId_client():"+e.getMessage());
        }
        catch(Exception ex){
            fail("Wrong exception thrown for setId_client() with negative argument");
        }
    }

    @Test
    public void testsetId_client(){
        client.setId_client(3);
        int expresult=3;
        assertEquals(expresult,client.getId_client());
    }

    @Test
    public void setName_clientInvalid(){
        try{
            client.setName_client("Ab");
            fail("Not enough characters in setName_client() should trigger an exception");
        }
        catch(IllegalArgumentException e){
            System.out.println("Properly caught not enough characters in setName_client():"+e.getMessage());
        }
        catch(Exception ex){
            fail("Wrong exception thrown for setName_client() with not enough characters");
        }
    }


    @Test
    public void setName_client() {
        client.setName_client("Boris Nikolov");
        String expresult="Boris Nikolov";
        assertEquals(expresult,client.getName_client());
    }

    @Test
    public void setAddress_clientInvalid(){
        try{
            client.setAddress_client("AC2");
            fail("Not enough characters in setAddress_client() should trigger an exception");
        }
        catch(IllegalArgumentException e){
            System.out.println("Properly caught not enough characters in setAddress_client():"+e.getMessage());
        }
        catch(Exception ex){
            fail("Wrong exception thrown for setAddress_client() with not enough characters");
        }

    }

    @Test
    public void setAddress_client() {
        client.setAddress_client("Dobrotica 20");
        String expresult="Dobrotica 20";
        assertEquals(expresult,client.getAddress_client());
    }


    @Test
    public void setPhone_clientInvalid(){
        try{
            client.setPhone_client("089976");
            fail("Not enough characters in setPhone_client() should trigger an exception");
        }
        catch(IllegalArgumentException e){
            System.out.println("Properly caught not enough characters in setPhone_client():"+e.getMessage());
        }
        catch(Exception ex){
            fail("Wrong exception thrown for setPhone_client() with not enough characters");
        }

    }

    @Test
    public void setPhone_client() {
        client.setPhone_client("0879765438");
        String expresult="0879765438";
        assertEquals(expresult,client.getPhone_client());
    }
}
