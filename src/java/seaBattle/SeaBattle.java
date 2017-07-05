package seaBattle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class SeaBattle {
    char[][] fieldToShow = new char[10][10];
    byte[][] fieldWithShips = new byte[10][10];

    private void fillFieldToShow() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                fieldToShow[i][j] = 'x';
            }
        }
    }

    private boolean checkFuturePlace(int start, int end, int fixedPoint, char direction) {
        boolean emptyCell = true;
        if (direction == 'l') {
            for (int j = (start > 0 ? start - 1 : start); j < (end < 9 ? end + 1 : end); j++) {
                if (fixedPoint > 0) {
                    if (fieldWithShips[fixedPoint - 1][j] == 1) emptyCell = false;
                }
                if (fieldWithShips[fixedPoint][j] == 1) emptyCell = false;
                if (fixedPoint + 1 < 10) {
                    if (fieldWithShips[fixedPoint + 1][j] == 1) emptyCell = false;
                }
            }
        } else {
            for (int i = (start > 0 ? start - 1 : start); i < (end < 9 ? end + 1 : end); i++) {
                if (fixedPoint > 0) {
                    if (fieldWithShips[i][fixedPoint - 1] == 1) emptyCell = false;
                }
                if (fieldWithShips[i][fixedPoint] == 1) emptyCell = false;
                if (fixedPoint + 1 < 10) {
                    if (fieldWithShips[i][fixedPoint + 1] == 1) emptyCell = false;
                }
            }
        }
        return emptyCell;
    }

    private void setShip(int sizeOfShip) {
        boolean setShip = false;
        Random rnd = new Random();
        do {
            int startOf4DeckShipX = rnd.nextInt(10);
            int startOf4DeckShipY = rnd.nextInt(10);
            if (startOf4DeckShipX + 4 <= 10 && startOf4DeckShipY + 4 <= 10) {

                if (rnd.nextBoolean() == true) {
                    if (checkFuturePlace(startOf4DeckShipX, startOf4DeckShipX + sizeOfShip, startOf4DeckShipY, 'l')) {
                        setShip = true;
                        for (int j = startOf4DeckShipX; j < startOf4DeckShipX + sizeOfShip; j++) {
                            fieldWithShips[startOf4DeckShipY][j] = 1;
                        }
                    }
                } else {
                    if (checkFuturePlace(startOf4DeckShipY, startOf4DeckShipY + sizeOfShip, startOf4DeckShipX, 'd')) {
                        setShip = true;
                        for (int i = startOf4DeckShipY; i < startOf4DeckShipY + sizeOfShip; i++) {
                            fieldWithShips[i][startOf4DeckShipX] = 1;
                        }
                    }
                }
            } else if (startOf4DeckShipX + sizeOfShip <= 10 && startOf4DeckShipY + sizeOfShip > 10) {
                if (checkFuturePlace(startOf4DeckShipX, startOf4DeckShipX + sizeOfShip, startOf4DeckShipY, 'l')) {
                    setShip = true;
                    for (int j = startOf4DeckShipX; j < startOf4DeckShipX + sizeOfShip; j++) {
                        fieldWithShips[startOf4DeckShipY][j] = 1;
                    }
                }
            } else if (startOf4DeckShipX + sizeOfShip > 10 && startOf4DeckShipY + sizeOfShip <= 10) {
                if (checkFuturePlace(startOf4DeckShipY, startOf4DeckShipY + sizeOfShip, startOf4DeckShipX, 'd')) {
                    setShip = true;
                    for (int i = startOf4DeckShipY; i < startOf4DeckShipY + sizeOfShip; i++) {
                        fieldWithShips[i][startOf4DeckShipX] = 1;
                    }
                }
            }
        } while (setShip != true);
    }

    private void setShipsOnField() {
        byte sizeOfShip;
        setShip(4);
        setShip(3);
        setShip(3);
        setShip(2);
        setShip(2);
        setShip(2);
        setShip(1);
        setShip(1);
        setShip(1);
        setShip(1);
    }

    private void printField(Boolean choiceOfField) {
        if (choiceOfField == true) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    System.out.print(fieldToShow[i][j]);
                }
                System.out.println();
            }
        } else {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    System.out.print(fieldWithShips[i][j]);
                }
                System.out.println();
            }
        }
    }
    private void playGame(){
        //System.out.println("Make your shot: type coordinates i and j like this: 2:3");
        int countPartsOfShips=20;
        int countOfShots=0;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while(countPartsOfShips>0 && countOfShots<100) {
                System.out.println("Make next shot: type coordinates i and j like this: 2:3");
                String response = reader.readLine();
                String[] shotCoordinates = response.split(":");
                try {
                    int i = Integer.parseInt(shotCoordinates[0]);
                    int j = Integer.parseInt(shotCoordinates[1]);
                    if (fieldToShow[i][j] != '0') {
                        countOfShots++;
                        if (fieldWithShips[i][j] == 0) {
                            fieldToShow[i][j] = ' ';
                            System.out.println("You missed");
                        } else {
                            fieldToShow[i][j] = '0';
                            System.out.println("You shot the ship!");
                            countPartsOfShips--;
                        }
                    } else System.out.println("You have already shot here!");
                    printField(true);
                }
                catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("You didn't hit the field! Type numbers between 0 and 9");
                }
            }
            if(countOfShots>=100) System.out.println("You lose!");
            if(countPartsOfShips==0) System.out.println("You win!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        SeaBattle battle = new SeaBattle();
        //battle.printField(false);
        battle.fillFieldToShow();
        battle.setShipsOnField();
        battle.printField(false);
        battle.printField(true);
        //do{
        battle.playGame();
            System.out.println("Would you like to play again?");
        //}while(true);
        //battle.printField(true);

    }
}
