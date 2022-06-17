package services;

import models.User;
import repositories.UserRepository;

import java.util.List;

public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Boolean isLogin(String userName, String userPassword) {
        return userRepository.userAndPasswordMatch(userName, userPassword);
    }

    public User getLoggedInUser(String userName, String userPassword)
    {
        if(isLogin(userName, userPassword))
        {
            return userRepository.getUserByUserName(userName);
        }
        else
        {
            //if we try to get user object with wrong credentials
            return null;
        }
    }

    public User getUserById(Long userId) {
        return userRepository.getUserById(userId);
    }


    public Boolean updateUser(Long userId, String userName, String userPassword, Boolean isAdmin)
    {
        User user = getUserById(userId);
        if(user!=null)
        {
            user.setUserName(userName);
            user.setUserPassword(userPassword);
            user.setAdmin(isAdmin);

            return userRepository.updateUser(user, userId);

        } else {
            return false;
        }
    }

}