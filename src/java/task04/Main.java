package task04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            main.doExercise9_43(reader);
            System.out.println(main.doExercise9_116(reader));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doExercise9_43(BufferedReader reader) throws IOException {
        String s1 = reader.readLine();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s1.length(); i++) {
            if (i % 2 != 0) {
                builder.append(s1.charAt(i));
            }
        }
        System.out.println(builder.toString());
    }

    private boolean doExercise9_116(BufferedReader reader) throws IOException {
        boolean res = false;
        String s1 = reader.readLine();
        s1 = s1.replace(" ", "");
        StringBuilder builder = new StringBuilder();
        for (int i = s1.length() - 1; i >= 0; i--) {
            builder.append(s1.charAt(i));
        }
        res = s1.contentEquals(builder);
        return res;
    }
}
