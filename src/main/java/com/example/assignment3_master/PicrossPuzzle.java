package com.example.assignment3_master;

import java.util.Random;

/**
 * Assignment 1 CS115
 * Class holds an array of 0s and 1s representing a Picross puzzle
 * @author basmalahasad, edited by CPR
 * @version 02/10/2022
 */

public class PicrossPuzzle {
    private int[][] solution;

    /**
     * Constructor for PicrossPuzzle with a default size of 5x5
     */
    public PicrossPuzzle()
    {
        this(5);
    }

    /**
     * Constructor for PicrossPuzzle with arguments, fills array with random 0s and 1s
     * @param size the size of the 2-D array
     */
    public PicrossPuzzle(int size)
    {
        Random rand = new Random();
        solution = new int[size][size];
        for (int i = 0; i < solution.length; i++)
            for (int j = 0; j < solution[i].length; j++)
                solution[i][j] = rand.nextInt(2);
    }

    /**
     * Constructor for PicrossPuzzle to initialize solution with the array passed
     * @param solution solution array
     */
    public PicrossPuzzle(int[][] solution)
    {
        this.solution = new int[solution.length][solution.length];
        for (int i = 0; i < solution.length; i++)
            for (int j = 0; j < solution[i].length; j++)
                this.solution[i][j] = solution[i][j];
    }

    /**
     * Returns the string representation of the data array
     * @return the string representation of the data array
     */
    public String toString()
    {
        String result = "";
        for (int i = 0; i < solution.length; i++) {
            for (int j = 0; j < solution[i].length; j++) {
                result += solution[i][j] + "\t";
            }
            result += "\n";
        }
        return result;
    }

    /**
     * Gets the value at a specified row and column
     * @param indrow the row index
     * @param indcol the column index
     * @return the array's value at the specified row and column
     */
    public int getValue(int indrow, int indcol)
    {
        return solution[indrow][indcol];
    }

    /**
     * Returns the row clues of the puzzle solution
     * @return the row clues of the puzzle solution
     */
    public String[] getRowClues() {
        int rows = solution.length;
        int cols = solution[0].length;
        String[] clues = new String[rows];
        int count;
        int j;

        for (int i = 0; i<rows; i++) {
            clues[i] = "";
            j = 0;
            count = 0;
            while (j < cols) {
                while (j < cols && solution[i][j] == 0)
                    j++;
                while (j < cols && solution[i][j] == 1) {
                    j++;
                    count++;
                }
                clues[i] += (count>0) ? count+" " : "";
                count = 0;
            }
        }
        return clues;
    }

    /**
     * Returns the column clues of the puzzle solution
     * @return the column clues of the puzzle solution
     */
    public String[] getColumnClues() {
        int rows = solution.length;
        int cols = solution[0].length;
        String[] clues = new String[cols];
        int count;
        int i;

        for (int j = 0; j<cols; j++) {
            clues[j] = "";
            i = 0;
            count = 0;
            while (i < rows) {
                while (i < rows && solution[i][j] == 0)
                    i++;
                while (i < rows && solution[i][j] == 1) {
                    i++;
                    count++;
                }
                clues[j] += (count>0) ? count+"\n" : "";  //adding endlines in between to make label appear vertical
                count = 0;
            }
        }
        return clues;
    }
}
