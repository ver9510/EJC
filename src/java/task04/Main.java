package task04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            main.getOddChars(reader);
            System.out.println(main.isPalindrome(reader));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*Exercise 9.43*/
    private void getOddChars(BufferedReader reader) throws IOException {
        String inputString = reader.readLine();
        StringBuilder outputString = new StringBuilder();
        for (int i = 0; i < inputString.length(); i++) {
            if (i % 2 != 0) {
                outputString.append(inputString.charAt(i));
            }
        }
        System.out.println(outputString.toString());
    }

    /*Exercise 9.116*/
    private boolean isPalindrome(BufferedReader reader) throws IOException {
        String inputString = reader.readLine();
        inputString = inputString.replace(" ", "");
        StringBuilder reversedLine = new StringBuilder();
        for (int i = inputString.length() - 1; i >= 0; i--) {
            reversedLine.append(inputString.charAt(i));
        }
        return inputString.contentEquals(reversedLine);
    }
}
