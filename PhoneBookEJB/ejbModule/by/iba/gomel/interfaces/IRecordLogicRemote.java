package by.iba.gomel.interfaces;

import java.util.List;

import javax.ejb.Local;

import org.richfaces.event.FileUploadEvent;

import by.iba.gomel.model.Record;

/**
 * This interface defines all methods for working with record logic.
 */
@Local
public interface IRecordLogicRemote {

    /**
     * 
     * @param currentPage
     *            current opened page.
     * @return list of records.
     */
    public List<Record> getAllRecords(int currentPage);

    /**
     * 
     * @return quality pages for records.
     */
    public int getQualityPages();

    /**
     * 
     * @param record
     *            record for adding in database.
     * @return result of adding.
     */
    public boolean addRecordInDB(final Record record);

    /**
     * 
     * @param record
     *            record in database will be changed this one.
     */
    public void changeRecordInDB(final Record record);

    /**
     * 
     * @param record
     *            this record will be deleted from database.
     */
    public void deleteRecordInDB(final Record record);

    /**
     * 
     * @param searchPhrase
     *            phrase for searching.
     * @return list of found records.
     */
    public List<Record> searchRecord(final String searchPhrase);

    /**
     * 
     * @param pathToFile
     *            path to image file.
     * @return array of byte.
     */
    public String getByteFile(final String pathToFile);

    /**
     * 
     * @param event
     *            file upload event.
     * @return path to file.
     */
    public String getPathFile(final FileUploadEvent event);
}
