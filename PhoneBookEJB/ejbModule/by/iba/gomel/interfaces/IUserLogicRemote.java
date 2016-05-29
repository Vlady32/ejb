package by.iba.gomel.interfaces;

import java.util.List;

import javax.ejb.Local;

import by.iba.gomel.model.User;

/**
 * This interface defines all methods for working with user logic.
 */
@Local
public interface IUserLogicRemote {

    /**
     * 
     * @param userName
     *            userName.
     * @param password
     *            password.
     * @return result of validating user.
     */
    public boolean validateUser(final String userName, final String password);

    /**
     * 
     * @param user
     *            this user will be registered in application.
     * @return result of registration.
     */
    public boolean registerUser(final User user);

    /**
     * 
     * @return result of user's output.
     */
    public boolean logOutUser();

    /**
     * 
     * @return list of all users.
     */
    public List<User> getAllUsers();

    /**
     * 
     * @param user
     *            this user will be deleted form database.
     */
    public void deleteUserFromDB(final User user);
}
