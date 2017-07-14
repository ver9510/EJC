package task05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println(main.countOfDifferentLetters(reader));
            System.out.println(main.getMaxSequenceOfLetters(reader));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Exercise 9.154
    private int countOfDifferentLetters(BufferedReader reader) throws IOException {
        Set charSet = new HashSet();
        String inputWord = reader.readLine();
        for (char letter : inputWord.toCharArray()) {
            charSet.add(letter);
        }
        return charSet.size();
    }

    //Exercise 9.153
    private int getMaxSequenceOfLetters(BufferedReader reader) throws IOException {
        String inputText = reader.readLine();
        int maxSequenceCount=0;
        char[] charArray=inputText.toCharArray();
        int currentSequenceCount=1;
        for (int i = 0; i < inputText.length()-1; i++) {
            if(charArray[i]==charArray[i+1])
                currentSequenceCount++;
            else {
                maxSequenceCount=(maxSequenceCount<currentSequenceCount)?currentSequenceCount:maxSequenceCount;
                currentSequenceCount=0;
            }
        }
        return maxSequenceCount;
    }
}
