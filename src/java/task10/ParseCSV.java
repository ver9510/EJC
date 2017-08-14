package task10;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParseCSV {
    private static final int INDEX_OF_FIELD_URL = 1;
    private static final int INDEX_OF_FIELD_TIME = 2;
    private static final int INDEX_OF_FIELD_USER = 3;
    private static final String REPORT_FILENAME = "report.csv";
    private static String[] FILENAMES = {"test1.csv", "test2.csv", "test3.csv", "test4.csv", "test5.csv"};
    private static final int MAX_COUNT_OF_THREADS = 5;

    /**
     * Map, contains pair of String and another Map. Key - user, Value - map, which contains pairs of url and time
     */
    private volatile static Map<String, Map> userUsageOfUrl = new HashMap<>();

    /**
     * Runs launch of threads, print of report
     */
    public static void main(String[] args) {
        ParseCSV parser = new ParseCSV();
        parser.runThreads();
        parser.printReport();
        parser.printReportInFile(REPORT_FILENAME);
    }

    /**
     * Starts all threads using ExecutorService. For each file runs execute, but there will not be more threads,
     * then specified in constructor newFixedThreadPool. Shutdowns it and waits for end of work in threads.
     */
    private void runThreads() {
        ExecutorService executor = Executors.newFixedThreadPool(MAX_COUNT_OF_THREADS);
        for (int i = 0; i < FILENAMES.length; i++) {
            ThreadForParsing parseFile = new ThreadForParsing(FILENAMES[i]);
            executor.execute(parseFile);
        }
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates file stream for file, reads it line by line, split line in fields and calls method for next processing
     *
     * @param filename - name of file for parsing
     */
    public void parseFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while (reader.ready()) {
                String inputStringFromFile = reader.readLine();
                if (!inputStringFromFile.startsWith("id")) {
                    putTimeUrlAndUser(inputStringFromFile.split(";"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Splits string by delmiter (here for csv by ':'), and this fields are put in Map. If field 'user' is already in Map,
     * checks, is there url from field url. If it is, sum times, else add another pair url-time. If there is no such user
     * in Map, add new pair user-Map, where Map contains pair url-time.
     *
     * @param fields - fields from input string - id, url, time and user
     */
    private void putTimeUrlAndUser(String[] fields) {
        String user = fields[INDEX_OF_FIELD_USER];
        String url = fields[INDEX_OF_FIELD_URL];
        long time = Long.parseLong(fields[INDEX_OF_FIELD_TIME]);
        Map<String, Long> timesWithUrl;
        if (userUsageOfUrl.containsKey(user)) {
            timesWithUrl = userUsageOfUrl.get(user);
            if (timesWithUrl.containsKey(url)) {
                timesWithUrl.put(url, timesWithUrl.get(url) + time);
            } else {
                timesWithUrl.put(url, time);
            }
        } else {
            timesWithUrl = new HashMap<>();
            timesWithUrl.put(url, time);
        }
        userUsageOfUrl.put(user, timesWithUrl);
    }

    /**
     * Print report in console
     */
    private void printReport() {
        for (String user : userUsageOfUrl.keySet()) {
            Map<String, Long> spentTimeAtUrl = userUsageOfUrl.get(user);
            for (String url : spentTimeAtUrl.keySet()) {
                System.out.println(user + " " + url + " " + spentTimeAtUrl.get(url));
            }
        }
    }

    /**
     * Print report in specified file
     *
     * @param filename - name of the file, where report writes
     */
    private void printReportInFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String user : userUsageOfUrl.keySet()) {
                Map<String, Long> spentTimeAtUrl = userUsageOfUrl.get(user);
                for (String url : spentTimeAtUrl.keySet()) {
                    writer.append(user + ";" + url + ";" + spentTimeAtUrl.get(url) + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}