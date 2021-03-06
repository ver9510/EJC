package task_02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class DuckRace {
    private int userMoney = 500;

    public static void play() {
        DuckRace simulator = new DuckRace();
        Duck[] arrayOfDucks = new Duck[5];
        int[] arrayOfDuckFlightLength = new int[5];

        try (BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {
            String usersAnswerContinue;
            do {
                System.out.println("Choose the duck - type the number from 1 to 5");
                for (int i = 0; i < 5; i++) {
                    arrayOfDucks[i] = new MallardDuck();
                    if (new Random().nextInt(4) == 0) {
                        arrayOfDucks[i].setFlyBehavior(new FlyNoWay());
                    }
                    arrayOfDuckFlightLength[i] = arrayOfDucks[i].performFly();
                }

                int userDuckChoice = Integer.parseInt(userInput.readLine()) - 1;
                if (arrayOfDuckFlightLength[userDuckChoice] == 0) {
                    System.out.println("You lose! This duck can't fly!");
                    simulator.userMoney = 0;
                } else {
                    System.out.println("Starting race!");
                    if (arrayOfDuckFlightLength[userDuckChoice] == simulator.max(arrayOfDuckFlightLength)) {
                        System.out.println("You win!");
                        simulator.userMoney += 100;
                    } else {
                        System.out.println("You lose!");
                        simulator.userMoney -= 100;
                    }
                }

                System.out.println("Your money " + simulator.userMoney + "$");
                if (simulator.userMoney == 0) {
                    System.out.println("End of game!");
                    break;
                }
                System.out.println("Would you like to continue? y/n");
                usersAnswerContinue = userInput.readLine();
            } while (!usersAnswerContinue.equals("n"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int max(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (max < array[i]) max = array[i];
        }
        return max;
    }
}
