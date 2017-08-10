package task10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static final int INDEX_OF_FIELD_URL = 1;
    private static final int INDEX_OF_FIELD_TIME = 2;
    private static final int INDEX_OF_FIELD_USER = 3;
    static Map<String, Map> userUsageOfUrl = new HashMap<>();

    public static void main(String[] args) {
        Main main = new Main();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            for (int i = 0; i < 2; i++) {
                main.putTimeUrlAndUser(main.parseFile(reader));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        main.printReport();
    }

    private String[] parseFile(BufferedReader reader) throws IOException {
        String inputStringFromFile = reader.readLine();
        String[] fieldsFromInputString = inputStringFromFile.split(":");
        return fieldsFromInputString;
    }

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

    private void printReport() {
        for (String user : userUsageOfUrl.keySet()) {
            Map<String, Long> spentTimeAtUrl = userUsageOfUrl.get(user);
            for (String url : spentTimeAtUrl.keySet()) {
                System.out.println(user + " " + url + " " + spentTimeAtUrl.get(url));
            }
        }
    }
}