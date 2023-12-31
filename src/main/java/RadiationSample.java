/* The RadiationSample class has 2 instance variables:
 *
 * dateTime - holds a string encoding the time and date.  It is currently the responsibility
 *            of the client to decode the string if fine grained access is required.
 *
 *  countsPerMinute - holds an int which is the number of counts per minute the detector
 *            received during the minute specified by dateTime
 *
 *
 *  The constructor is the only way to create a RadiationSample.  There are no mutator methods.
 *
 *  Do not modify this file!
 */

public class RadiationSample {

    private final String dateTime;
    private final int countsPerMinute;

    public RadiationSample(String dt, int counts) {
        dateTime = dt;
        countsPerMinute = counts;
    }

    public String getDateTime() {
        return dateTime;
    }

    public int getCountsPerMinute() {
        return countsPerMinute;
    }

}
