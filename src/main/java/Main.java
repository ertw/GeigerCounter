import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        final String fileName = "/Users/erikwilliamson/Documents/CSC142/GeigerCounter/src/main/java/7_14_2019.txt";
        final LinkedList<RadiationSample> radiationSamples = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String dtRegex = "^(\\d{1,2}/){1,2}\\d{4}";
            Pattern dtPattern = Pattern.compile(dtRegex);
            String countsRegex = ",(\\d+),";
            Pattern countsPattern = Pattern.compile(countsRegex);
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher dtMatcher = dtPattern.matcher(line);
                Matcher countsMatcher = countsPattern.matcher(line);
                if (dtMatcher.find()) {
                    if (countsMatcher.find()) {
                        radiationSamples.add(new RadiationSample(dtMatcher.group(), Integer.parseInt(countsMatcher.group(1))));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        radiationSamples.forEach(sample -> System.out.println("Date and Time : " + sample.getDateTime() + "\nCounts Per Minute: " + sample.getCountsPerMinute()));
    }
}
