import Model.User;
import Service.UserService.UserService;

public class test
{

    public static void main(String[] args)
    {

        UserService userService = new UserService();

        User user = userService.findUserByEmail("marcu2@gmail.com");

        if (user ==null) {
            System.out.println("User not found");
        } else
        {
            System.out.println(user.toString());
        }


    }
}