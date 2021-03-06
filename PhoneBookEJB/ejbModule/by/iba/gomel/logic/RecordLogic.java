package by.iba.gomel.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.iba.gomel.Constants;
import by.iba.gomel.factories.DatabaseDAOFactory;
import by.iba.gomel.interfaces.DatabaseDAO;
import by.iba.gomel.interfaces.IRecordLogicRemote;
import by.iba.gomel.managers.SessionManager;
import by.iba.gomel.model.Record;

import com.ibm.ws.util.Base64;

/**
 * Session Bean implementation class RecordLogic
 */
@Stateless
@Local(IRecordLogicRemote.class)
public class RecordLogic implements IRecordLogicRemote, Serializable {

    private static final long   serialVersionUID = 1L;
    private static final Logger LOGGER           = LoggerFactory.getLogger(RecordLogic.class);

    @PersistenceContext(name = Constants.PERSISTENT_NAME_DB2)
    private EntityManager       em;
    private DatabaseDAO         data;

    @PostConstruct
    public void init() {
        data = DatabaseDAOFactory.getInstance(em);
    }

    public RecordLogic() {
    }

    /**
     * 
     * @param currentPage
     *            current opened page.
     * @return list of records.
     */
    @Override
    public List<Record> getAllRecords(final int currentPage) {
        final int start = (int) ((currentPage - Constants.NUMBER_ONE) * Constants.DEFAULT_QUALITY_RECORDS_AND_USERS_ON_PAGE);
        final List<Record> listRecords = data.getRecords(start);
        if (listRecords == null) {
            SessionManager.addMessage(Constants.MESSAGE_WRONG_VIEW, null);
        }

        return listRecords;
    }

    /**
     * 
     * @return quality pages for records.
     */
    @Override
    public int getQualityPages() {
        final long qualityRecords = data.getQualityRecords();
        final int qualityPages = (int) Math
                .ceil((qualityRecords / Constants.DEFAULT_QUALITY_RECORDS_AND_USERS_ON_PAGE));
        return qualityPages;
    }

    /**
     * 
     * @param record
     *            record for adding in database.
     * @return result of adding.
     */
    @Override
    public boolean addRecordInDB(final Record record) {
        if (data.checkExistRecord(record) == null) {
            data.addRecord(record);
            SessionManager.addMessage(Constants.MESSAGE_ADD_RECORD_SUCCESS,
                    Constants.TAG_ERROR_ADD_FORM);
            return true;
        } else {
            SessionManager.addMessage(Constants.MESSAGE_ADD_RECORD_ERROR,
                    Constants.TAG_ERROR_ADD_FORM);
            return false;
        }
    }

    /**
     * 
     * @param record
     *            record in database will be changed this one.
     */
    @Override
    public void changeRecordInDB(final Record record) {
        if (data.changeRecord(record)) {
            SessionManager.addMessage(Constants.MESSAGE_CHANGE_RECORD_SUCCESS, null);
        } else {
            SessionManager.addMessage(Constants.MESSAGE_CHANGE_RECORD_ERROR, null);
        }
    }

    /**
     * 
     * @param record
     *            this record will be deleted from database.
     */
    @Override
    public void deleteRecordInDB(final Record record) {
        if (data.deleteRecord(record)) {
            SessionManager.addMessage(Constants.MESSAGE_DELETE_RECORD_SUCCESS, null);
        } else {
            SessionManager.addMessage(Constants.MESSAGE_DELETE_RECORD_ERROR, null);
        }
    }

    /**
     * 
     * @param searchPhrase
     *            phrase for searching.
     * @return list of found records.
     */
    @Override
    public List<Record> searchRecord(final String searchPhrase) {
        final List<Record> foundRecords = data.searchRecord(searchPhrase.toLowerCase());
        if ((foundRecords == null) || !(foundRecords.size() > 0)) {
            SessionManager.addMessage(Constants.MESSAGE_SEARCH_NOTHING, null);
        }
        return foundRecords;
    }

    /**
     * 
     * @param pathToFile
     *            path to image file.
     * @return array of byte.
     */
    @Override
    public String getByteFile(final String pathToFile) {
        FileInputStream fileInputStream = null;
        final File file = new File(pathToFile);
        final byte[] bFile = new byte[(int) file.length()];
        try {
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();
        } catch (final IOException e) {
            RecordLogic.LOGGER.error(Constants.IO_EXCEPTION, e);
        }
        return Base64.encode(bFile);
    }

    /**
     * 
     * @param event
     *            file upload event.
     * @return path to file.
     */
    @Override
    public String getPathFile(final FileUploadEvent event) {
        final UploadedFile uploadedFile = event.getUploadedFile();
        final String path = Constants.PATH_VALUE_PHOTOS + File.separator + new Date().getTime()
                + uploadedFile.getName();
        final File file = new File(path);
        InputStream in = null;
        OutputStream out = null;
        try {
            in = uploadedFile.getInputStream();
            out = new FileOutputStream(file);
            int read = 0;
            final byte[] bytes = new byte[Constants.ONE_KB];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
        } catch (final FileNotFoundException e) {
            RecordLogic.LOGGER.error(Constants.FILE_NOT_FOUND_EXCEPTION, e);
        } catch (final IOException e) {
            RecordLogic.LOGGER.error(Constants.IO_EXCEPTION, e);
        } finally {
            try {
                in.close();
                out.flush();
                out.close();
            } catch (final IOException e) {
                RecordLogic.LOGGER.error(Constants.IO_EXCEPTION, e);
            }
        }
        return path;
    }

}
