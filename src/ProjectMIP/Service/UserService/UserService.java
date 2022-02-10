package Service.UserService;

import Model.User;
import Repository.UserRepository.UserRepository;
import Service.UserService.Exceptions.UserExceptions;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserService
{
    private final UserRepository userRepository = new UserRepository();
    private final UserExceptions userExceptions = new UserExceptions();

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("connetionDB");

    public String addUser(String firstName, String lastName, String email, String password) {

        String errorMessage = "User Created Successfully: "+firstName+" "+lastName+" - "+email;

        switch (userExceptions.checkUser(firstName,lastName,email,password))
        {
            case firstNameTooShort: {errorMessage = "First name must have at least 3 characters"; break;}
            case lastnameTooShort: {errorMessage = "Last name must have at least 4 characters"; break;}
            case invalidEmailFormat: {errorMessage = "Wrong email format!"; break;}
            case emailAlreadyExists: {errorMessage = "Email already exists!"; break;}
            case success:
            {
                User user = new User();
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setPassword(password);
                userRepository.addUserToDatabase(user);
            }
        }
        return errorMessage;
    }

    public User findUserByEmail(String email)
    {
        return userRepository.findUser(email);
    }

}
