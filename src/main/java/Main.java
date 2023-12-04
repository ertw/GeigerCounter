/**
 * Parses and outputs radiation samples from a file.
 *
 * @Author: Erik Williamson
 * @version: 1
 * <p>
 * I estimate that a camping trip to a higher elevation / radiation zone was taken
 * from 5/31/2019 - 6/1/2019. I think this is the case because this date range
 * has the highest frequency of radiation counts within 5 of the maximum count
 * (which I found to be 38).
 * <p>
 * I initially sorted my output by count, but this wasn't too useful for
 * finding the date range. I then sorted by date, which made it easy to find.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final String FILE_NAME = "path/to/file.txt";

    private static void printSample(RadiationSample sample) {
        System.out.printf("%-12s%-10d%n", sample.getDateTime(), sample.getCountsPerMinute());
    }

    private static LinkedList<RadiationSample> parseRadiationSamples(String fileName) {
        final LinkedList<RadiationSample> radiationSamples = new LinkedList<>();
        final String dtRegex = "^(\\d{1,2}/){1,2}\\d{4}";
        final Pattern dtPattern = Pattern.compile(dtRegex);
        final String countsRegex = ",(\\d+),";
        final Pattern countsPattern = Pattern.compile(countsRegex);
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher dtMatcher = dtPattern.matcher(line);
                Matcher countsMatcher = countsPattern.matcher(line);
                if (dtMatcher.find() && countsMatcher.find()) {
                    radiationSamples.add(new RadiationSample(dtMatcher.group(), Integer.parseInt(countsMatcher.group(1))));
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return radiationSamples;
    }

    private static int compareRadiationSamplesByDateTime(RadiationSample a, RadiationSample b) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        return LocalDate.parse(a.getDateTime(), formatter).isBefore(LocalDate.parse(b.getDateTime(), formatter)) ? -1 : 1;
    }

    public static void main(String[] args) {
        final String fileName = args.length > 0 ? args[0] : FILE_NAME;
        final LinkedList<RadiationSample> radiationSamples = parseRadiationSamples(fileName);
        radiationSamples.sort(Main::compareRadiationSamplesByDateTime);
        RadiationSample maxCount = radiationSamples.stream().max((a, b) -> a.getCountsPerMinute() - b.getCountsPerMinute()).orElse(null);
        System.out.printf("%-12s%-10s%n", "Date", "Counts per minute");
        System.out.printf("%-12s%-10s%n", "----------  ", "-----------------");
        if (maxCount != null) {
            for (RadiationSample sample : radiationSamples) {
                if (sample.getCountsPerMinute() > maxCount.getCountsPerMinute() - 5) {
                    printSample(sample);
                }
            }
        }
    }
}
