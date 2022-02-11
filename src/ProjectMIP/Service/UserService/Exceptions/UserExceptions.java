package Service.UserService.Exceptions;

import Model.User;
import Repository.UserRepository.UserRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserExceptions
{
    private final UserRepository userRepository = new UserRepository();



    public UserExceptionsEnum checkUser(String firstName, String lastName, String email, String password)
    {

       if (checkFirstName(firstName)==false)
       {
           return UserExceptionsEnum.firstNameTooShort;
       }

       if (checkLastName(lastName)==false)
       {
           return UserExceptionsEnum.lastnameTooShort;
       }

       if (checkEmailFormat(email)==false)
       {
           return UserExceptionsEnum.invalidEmailFormat;
       }

       if (checkEmailExists(email)==true)
       {
           return UserExceptionsEnum.emailAlreadyExists;
       }

       if (checkPassword(password)==false)
       {
           return UserExceptionsEnum.invalidPassword;
       }

       return UserExceptionsEnum.success;
    }

    //firstName must be greater than 3
    public boolean checkFirstName(String firstName)
    {
        return firstName.length()>=3;
    }

    //firstName must be greater than 4
    public boolean checkLastName(String lastName)
    {
        return lastName.length()>=4;
    }

    //check email format email format
    public boolean checkEmailFormat(String email)
    {
        String regex = "^(.+)@(.+)$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    //check email if already exists in user database
    public boolean checkEmailExists(String email)
    {
        User user = userRepository.findUser(email);
        if (user ==null) {
            return false;
        } else
        {
            return true;
        }
    }

    public boolean checkPassword(String password)
    {
        if (password.length()<4)
        {
         return false;
        }
        return true;
    }


}
