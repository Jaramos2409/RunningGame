package cs3340.project.runninggame.android.Database;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import cs3340.project.runninggame.Data.DateTimePair;
import cs3340.project.runninggame.Data.TimeDatabase;

/**
 * Contains the single database for the game and all it's basic
 * functions needed for the game such as saving a score and
 * loading all the scores from the database.
 * @author Jesus Ramos
 * @version 0.1
 * @since 11/30/2015
 */
public class AndroidTimeDatabase implements TimeDatabase {
    DBAdapter db;

    /**
     * Loads the database with one that was created at AndroidLauncher and
     * opens the database by giving the program access to read and write off the
     * database if it exists. Otherwise it will create a new one.
     * @param db the main database for the game
     */
    public AndroidTimeDatabase(DBAdapter db) {
        this.db = db;
        db.open();
    }

    /**
     * Saves a single time into the database. Generates a unique ID
     * and the current date that will go along with the time in the
     * database.
     * @param rFT the race's final time
     */
    @Override
    public void saveTime(double rFT) {
        UUID id = UUID.randomUUID();

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        Date date = new Date();
        String currentDate = dateFormat.format(date);

        String raceTime = Double.toString(rFT);
        db.insertTime(id.toString(), currentDate, raceTime);
    }

    /**
     * Calls DBAdapter's loadTimes method and returns what that method returns
     * @return an ArrayList of all the times paired with their respective dates from the database.
     */
    @Override
    public ArrayList<DateTimePair> loadTimes(){
        return db.loadTimes();
    }
}
