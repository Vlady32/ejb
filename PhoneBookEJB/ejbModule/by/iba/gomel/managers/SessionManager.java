package by.iba.gomel.managers;

import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class SessionManager {

    /**
     * 
     * @return current locale using in application.
     */
    public Locale getCurrentLocale() {
        final FacesContext context = FacesContext.getCurrentInstance();
        final Locale currentLocale = context.getExternalContext().getRequestLocale();
        return currentLocale;
    }

    /**
     * 
     * @param propertyMessage
     *            property name for getting value from property file.
     * @param tag
     *            html tag for setting message.
     */
    public static void addMessage(final String propertyMessage, final String tag) {
        final FacesContext context = FacesContext.getCurrentInstance();
        final Locale currentLocale = new SessionManager().getCurrentLocale();
        final MessageManager messageManager = new MessageManager(currentLocale);
        final String messageError = messageManager.getProperty(propertyMessage);
        context.addMessage(tag, new FacesMessage(messageError));
    }

    /**
     * 
     * @param name
     *            name attribute.
     * @param value
     *            value attribute.
     */
    public static void setAttributesSession(final String name, final Object value) {
        SessionManager.getSession().setAttribute(name, value);
    }

    /**
     * 
     * @return active session.
     */
    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
                .getSession(false);
    }

}
