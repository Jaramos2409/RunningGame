package cs3340.project.runninggame.Data;

import java.util.ArrayList;

/**
 * Used for when writing platform specific code for saving the data for the game.
 * In this project it is particularly used for activating the SQLite based
 * database and saving information from the game onto it.
 * @author Jesus Ramos
 * @version 0.1
 * @since 11/30/2015
 */
public interface TimeDatabase {
    public void saveTime(double raceFinalTime);
    public ArrayList<DateTimePair> loadTimes();
}
