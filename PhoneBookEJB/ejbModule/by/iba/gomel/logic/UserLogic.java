package by.iba.gomel.logic;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import by.iba.gomel.Constants;
import by.iba.gomel.factories.DatabaseDAOFactory;
import by.iba.gomel.interfaces.DatabaseDAO;
import by.iba.gomel.interfaces.IUserLogicRemote;
import by.iba.gomel.managers.SessionManager;
import by.iba.gomel.model.User;

@Stateless
@Local(IUserLogicRemote.class)
public class UserLogic implements IUserLogicRemote, Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext(name = Constants.PERSISTENT_NAME_DB2)
    private EntityManager     em;
    private DatabaseDAO       data;

    @PostConstruct
    public void init() {
        data = DatabaseDAOFactory.getInstance(em);
    }

    public UserLogic() {
        // Empty constructor.
    }

    /**
     * 
     * @param userName
     *            userName.
     * @param password
     *            password.
     * @return result of validating user.
     */
    @Override
    public boolean validateUser(final String userName, final String password) {
        final User user = data.checkUser(userName, password);
        if (user != null) {
            SessionManager.setAttributesSession(Constants.ATTRIBUTE_NAME_LOGIN, user.getUserName());
            SessionManager.setAttributesSession(Constants.ATTRIBUTE_NAME_TYPE, user.getType());
            return true;
        } else {
            SessionManager.addMessage(Constants.MESSAGE_LOGIN_ERROR,
                    Constants.TAG_ERROR_MESSAGE_PASSWORD);
            return false;
        }
    }

    /**
     * 
     * @param user
     *            this user will be registered in application.
     * @return result of registration.
     */
    @Override
    public boolean registerUser(final User user) {
        if (user.getPassword().equals(user.getConfirmedPassword())) {
            if (data.registerUser(user)) {
                return true;
            } else {
                SessionManager.addMessage(Constants.MESSAGE_REGISTRATION_LOGIN_ERROR,
                        Constants.TAG_ERROR_MESSAGE_REGISTR);
            }
        } else {
            SessionManager.addMessage(Constants.MESSAGE_REGISTRATION_PASSWORDS_ERROR,
                    Constants.TAG_ERROR_MESSAGE_REGISTR);
        }
        return false;
    }

    /**
     * 
     * @return result of user's output.
     */
    @Override
    public boolean logOutUser() {
        SessionManager.getSession().invalidate();
        return true;
    }

    /**
     * 
     * @return list of all users.
     */
    @Override
    public List<User> getAllUsers() {
        final List<User> listUsers = data.getUsers();
        if (listUsers == null) {
            SessionManager.addMessage(Constants.MESSAGE_WRONG_VIEW, null);
        }
        return listUsers;
    }

    /**
     * 
     * @param user
     *            this user will be deleted form database.
     */
    @Override
    public void deleteUserFromDB(final User user) {
        if (data.deleteUser(user)) {
            SessionManager.addMessage(Constants.MESSAGE_DELETE_USER_SUCCESS, null);
        } else {
            SessionManager.addMessage(Constants.MESSAGE_DELETE_USER_ERROR, null);
        }
    }

}
