package task03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

public class SeaBattle {
    private static final char[] LETTERS = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'k'};
    private char[][] fieldToShow = new char[10][10];
    private byte[][] fieldWithShips = new byte[10][10];

    /**
     * Markes all cells of fieldToShow closed - fills it with 'x' and makes fieldWithShips empty -fills with 0.
     */
    private void fillFieldToShow() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                fieldToShow[i][j] = 'x';
                fieldWithShips[i][j] = 0;
            }
        }
    }

    /**
     * Checks whether random chosen place of the ship is empty
     *
     * @param start      - coordinate of beginning of the ship
     * @param end        - coordinate of end of the ship
     * @param fixedPoint - coordinate of ship by another axe
     * @param direction  - can take two values - 'l' - the ship will be checked by horizontal,
     *                   'd' - the place will be placed by vertical
     * @return true, if cell, that we are checking, is empty, and false otherwise.
     */
    private boolean isFuturePlaceEmpty(int start, int end, int fixedPoint, char direction) {
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

    /**
     * Places the ship with specified length on the field.
     *
     * @param sizeOfShip - length of the ship, count of cells
     */
    private void setShip(int sizeOfShip) {
        boolean setShip = false;
        Random rnd = new Random();
        do {
            int startOfShipX = rnd.nextInt(10);
            int startOfShipY = rnd.nextInt(10);
            if (startOfShipX + sizeOfShip <= 10 && startOfShipY + sizeOfShip <= 10) {
                if (rnd.nextBoolean() == true) {
                    if (isFuturePlaceEmpty(startOfShipX, startOfShipX + sizeOfShip, startOfShipY, 'l')) {
                        setShip = true;
                        for (int j = startOfShipX; j < startOfShipX + sizeOfShip; j++) {
                            fieldWithShips[startOfShipY][j] = 1;
                        }
                    }
                } else {
                    if (isFuturePlaceEmpty(startOfShipY, startOfShipY + sizeOfShip, startOfShipX, 'd')) {
                        setShip = true;
                        for (int i = startOfShipY; i < startOfShipY + sizeOfShip; i++) {
                            fieldWithShips[i][startOfShipX] = 1;
                        }
                    }
                }
            } else if (startOfShipX + sizeOfShip <= 10 && startOfShipY + sizeOfShip > 10) {
                if (isFuturePlaceEmpty(startOfShipX, startOfShipX + sizeOfShip, startOfShipY, 'l')) {
                    setShip = true;
                    for (int j = startOfShipX; j < startOfShipX + sizeOfShip; j++) {
                        fieldWithShips[startOfShipY][j] = 1;
                    }
                }
            } else if (startOfShipX + sizeOfShip > 10 && startOfShipY + sizeOfShip <= 10) {
                if (isFuturePlaceEmpty(startOfShipY, startOfShipY + sizeOfShip, startOfShipX, 'd')) {
                    setShip = true;
                    for (int i = startOfShipY; i < startOfShipY + sizeOfShip; i++) {
                        fieldWithShips[i][startOfShipX] = 1;
                    }
                }
            }
        } while (setShip != true);
    }

    /**
     * Sets specified amount of ships with specified length.
     *
     * @param size  - length of the ship
     * @param count - count of the ships with this length.
     */
    private void setNDeckShip(int size, int count) {
        for (int i = 0; i < count; i++) {
            setShip(size);
        }
    }

    /**
     * Places all ships on field
     */
    private void setShipsOnField() {
        setShip(4);
        setNDeckShip(3, 2);
        setNDeckShip(2, 3);
        setNDeckShip(1, 4);
    }

    /**
     * Prints game field with moves of user
     */
    private void printFieldToShow() {
        for (int i = -1; i < 10; i++) {
            for (int j = -1; j < 10; j++) {
                if (i == -1) {
                    if (j == -1) {
                        System.out.print("  ");
                    } else {
                        System.out.print(j);
                    }
                } else {
                    if (j == -1) {
                        System.out.print(LETTERS[i] + " ");
                    } else {
                        System.out.print(fieldToShow[i][j]);
                    }
                }
            }
            System.out.println();
        }
    }

    /**
     * Prints field with setted ships
     */
    private void printFieldWithShips() {
        for (int i = -1; i < 10; i++) {
            for (int j = -1; j < 10; j++) {
                if (i == -1) {
                    if (j == -1) {
                        System.out.print("  ");
                    } else {
                        System.out.print(j);
                    }
                } else {
                    if (j == -1) {
                        System.out.print(LETTERS[i] + " ");
                    } else {
                        System.out.print(fieldWithShips[i][j]);
                    }
                }
            }
            System.out.println();
        }
    }

    /**
     * Method with main logic of game. Operates with user's input, if user typed the coordinates of part of the ship -
     * it writes to variable fieldToShow.
     *
     * @param reader - BufferedReader for user's input
     */
    private void playGame(BufferedReader reader) throws IOException {
        int countPartsOfShips = 20;
        int countOfShots = 0;
        while (countPartsOfShips > 0 && countOfShots < 100) {
            System.out.println("Make next shot: type coordinates like this: a3");
            String response = reader.readLine();
            if (response.equals("exit")) {
                break;
            } else {
                if (response.matches("[a-i,k]+[0-9]+")) {
                    int i = Arrays.binarySearch(LETTERS, response.charAt(0));
                    int j = Integer.parseInt(response.substring(1));
                    if (i >= 0 && i < 10 && j >= 0 && j < 10) {
                        if (fieldToShow[i][j]=='x') {
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
                        printFieldToShow();
                    } else {
                        System.out.println("You didn't hit the field! Type one letter between a and k(except j) " +
                                "and one number between 0 and 9 like this d3");
                    }
                } else {
                    System.out.println("Wrong input! Type one letter between a and k(except j) " +
                            "and one number between 0 and 9 like this d3");
                }
            }
        }
        if (countOfShots >= 100) System.out.println("You lose!");
        if (countPartsOfShips == 0) System.out.println("You win!");
    }

    /**
     * Starts the game - makes fieldToShow empty(like "cells are closed"), sets the ships on the field,
     * prints the field, opens BufferedReader and runs method playGame().
     */
    public void start() {
        SeaBattle battle = new SeaBattle();
        String answer;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            do {
                battle.fillFieldToShow();
                battle.setShipsOnField();
                System.out.println("There are field 10x10 with one four-deck ships, two three-deck ships, " +
                        "three two-deck and four one-deck ships. \nVertical coordinates is named by LETTERS " +
                        "from a to k, except j. Horizontal coordinates is numerated from 0 to 9." +
                        "\nx - closed cells, 0 - part of a ship, empty space - water. " +
                        "\nYou have 100 shots. If you don't find all ships - you'll lose." +
                        "\nIf you get bored - type \"exit\".");
                battle.printFieldToShow();
                battle.playGame(reader);
                System.out.println("Ships were there:");
                battle.printFieldWithShips();
                System.out.println("Would you like to play again?");
                answer = reader.readLine();
            } while (!answer.equals("n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
