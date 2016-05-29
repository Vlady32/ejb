package by.iba.gomel;

/**
 * This class contains all constants which uses in this application.
 */
public class Constants {

    public static final int    DEFAULT_PAGE             = 1;
    public static final int    NUMBER_VIEW_PAGE         = 0;
    public static final int    NUMBER_ADD_PAGE          = 1;
    public static final int    NUMBER_EDIT_PAGE         = 2;
    public static final int    NUMBER_SEARCH_PAGE       = 3;
    public static final int    NUMBER_CONTROL_PAGE      = 4;

    public static final String ATTRIBUTE_NAME_LOGIN     = "login";
    public static final String ATTRIBUTE_NAME_TYPE      = "type";
    public static final String ATTRIBUTE_LINK_TYPE      = "link";
    public static final String ATTRIBUTE_PAGE_TYPE      = "page";

    public static final String DEFAULT_PATH_NO_IMAGE    = "C:\\photos\\no_picture.png";
    public static final String FILE_MESSAGES_PROPERTY   = "resources.messages";
    public static final String FILE_REQUESTS_PROPERTY   = "resources.requests_db2";
    public static final String FILE_CONFIG_PROPERTY     = "resources.config";
    public static final String RECORD_BEAN_NAME         = "recordBean";
    public static final String SUB_BEAN_NAME            = "subBean";
    public static final String USER_BEAN_NAME           = "userBean";

    public static final String PROPERTY_PATH_LOGIN_PAGE = "path.page.login";
    public static final String PROPERTY_PATH_MAIN_PAGE  = "path.page.main";

    public static final String RESULT_SUCCESS           = "success";
    public static final String RESULT_SUCCESS_PROFILE   = "successProfile";
    public static final String RESULT_FAIL              = "fail";
    public static final String RESULT_LOG_OUT           = "logout";

    public static final String MESSAGE_MENU_VIEWING     = "main_menu_viewing";
    public static final String MESSAGE_MENU_ADDING      = "main_menu_adding";
    public static final String MESSAGE_MENU_EDITING     = "main_menu_editing";
    public static final String MESSAGE_MENU_SEARCHING   = "main_menu_searching";
    public static final String MESSAGE_MENU_CONTROL     = "main_menu_control_users";
    public static final String MESSAGE_SEARCH_NOTHING   = "search_message_nothing";

    public static final String TYPE_USER                = "user";
    public static final String TYPE_GUEST               = "guest";
    public static final String TYPE_ADMIN               = "admin";
    public static final String TYPE_EMPTY               = "";

    public static final String PAGE_VIEW                = "/jsf/view.xhtml";
    public static final String PAGE_ADD                 = "/jsf/add.xhtml";
    public static final String PAGE_CONTROL             = "/jsf/control.xhtml";
    public static final String PAGE_EDIT                = "/jsf/edit.jsf";
    public static final String PAGE_SEARCH              = "/jsf/search.jsf";

}
