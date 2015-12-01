/**
 * 
 */
package cs3340.project.runninggame.Data;

/**
 * Pairs a date and a time into one container. Also contains a comparable
 * for when an array of DateTimePair's need to be sorted.
 * @author Jesus Ramos
 * @version 2.0
 * @since Nov 8, 2015, JDK 8
 */
public class DateTimePair implements Comparable<DateTimePair>{
    private String date;
    private double time;
    public DateTimePair() {
        this.date = "";
        this.time = 0;
    }
    
    /*
     * Creates a pairing with both date and time set
     * @param date the date
     * @param time the time
     */
    public DateTimePair(String date, double time) {
        this.date = date;
        this.time = time;
    }

    /*
     * @return the date
     */
    public String getDate() {
        return this.date;
    }

    /*
     * @return the time
     */
    public double getTime() {
        return this.time;
    }

    /*
     * Purpose: Used for when printing out the List elements
     * in the main scroll pane.
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return date +": " + time;
    }

    /**
     * Compares two DateTimePair's time's for sorting purposes.
     * Sorts in ascending order.
     * @param comparedPair
     * @return
     */
    public int compareTo(DateTimePair comparedPair) {
        return Double.compare(this.time, comparedPair.getTime());
    }
}
