package Service.UserService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserServiceTest {

    @Test
    //happy flow
    void addUserTest()
    {
        String firstName = "Dummy";
        String lastName = "Dummy";
        String email = "dummy@gmail.com";
        String password = "password";

        String messageReceived = new UserService().addUser(firstName, lastName, email,password);
        String expectedMsg = "User Created Successfully: Dummy Dummy - dummy@gmail.com";

        Assertions.assertTrue(expectedMsg.equals(messageReceived));
    }

    @Test
    //checking firstName length
    void addUserFailOnFirstName()
    {
        String firstName = "Dm";
        String lastName = "Dummy";
        String email = "dummy@gmail.com";
        String password = "password";

        String messageReceived = new UserService().addUser(firstName, lastName, email,password);
        String expectedMsg = "First name must have at least 3 characters";

        Assertions.assertTrue(expectedMsg.equals(messageReceived));
    }

    @Test
    //checking lastName length
    public void addUserFailOnLastName()
    {
        String firstName = "Dummy";
        String lastName = "Dum";
        String email = "dummy@gmail.com";
        String password = "password";

        String messageReceived = new UserService().addUser(firstName, lastName, email,password);
        String expectedMsg = "Last name must have at least 4 characters";

        Assertions.assertTrue(expectedMsg.equals(messageReceived));
    }

    @Test
    //checking email format
    public void addUserFailOnEmailFormat()
    {
        String firstName = "Dummy";
        String lastName = "Dummy";
        String email = "dummy.com";
        String password = "password";

        String messageReceived = new UserService().addUser(firstName, lastName, email,password);
        String expectedMsg = "Wrong email format!";

        Assertions.assertTrue(expectedMsg.equals(messageReceived));
    }

    @Test
    //checking password
    public void addUserFailOnPasswordLength()
    {
        String firstName = "Dummy";
        String lastName = "Dum";
        String email = "dummy@gmail.com";
        String password = "ps";

        String messageReceived = new UserService().addUser(firstName, lastName, email,password);
        String expectedMsg = "Password too short!";

        Assertions.assertTrue(expectedMsg.equals(messageReceived));
    }
}