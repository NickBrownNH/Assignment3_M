package com.example.assignment3_master;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class PicrossPuzzlePool {

    boolean hasAlert = false;
    boolean hasFormatAlert = false;
    int hasNextCounter = 0;

    private PicrossPuzzle[] getFiles () {

        Path path = Paths.get(".\\Data");
        File[] files;
        File aFile;
        int fileCount = 0;
        int[][] array2D;

        //dump all the file contents of the path into the local File array
        //all files and folders in the Data folder are collected
        aFile = new File(path.toString());
        files = aFile.listFiles();
        int size = files.length;
        PicrossPuzzle[] puzzlePool = new PicrossPuzzle[size];


        //iterate over each file in files
        for (File file : files) {
            //check to make sure file name ends with .txt, skips files like desktop.ini
            String fileName = file.toString();
            int dotIndex = fileName.lastIndexOf ('.');
            if(dotIndex > 0) { //filename has an extension
                if (fileName.substring(dotIndex + 1).equals("txt")) {
                    //System.out.println("File #: " + fileCount + "\t" + file);
                    array2D = readFile(file);
                    puzzlePool[fileCount] = new PicrossPuzzle(array2D);
                    fileCount++;
                    //System.out.println(fileCount + "------------------------");
                }
            }
        }

        return puzzlePool;
    }

    /**
     * Reads one file and store the contents into a 5x5 int array
     * File content is expected to be integers
     * @param file the file to be read
     * @return the 5x5 int array read from the file
     */
    private int[][] readFile(File file)
    {
        int[][] data = new int[5][5];
        Path path = Paths.get(".\\Data");
        File[] files;
        File aFile;
        aFile = new File(path.toString());
        files = aFile.listFiles();

        try {
            Scanner readFrom = new Scanner(file);

            //read the 9 numbers from the file and store them into the 2D int array, data
            for (int i = 0; i < 5; i++)
                for (int j = 0; j < 5; j++) {
                    try {
                        data[i][j] = readFrom.nextInt();


                        if (readFrom.hasNextInt() == true) {
                            hasNextCounter++;
                            System.out.println(hasNextCounter);
                        } else if (hasNextCounter != 24) {
                            throw new IncorrectFormatException("File: " + file + " has an incorrect size (25 numbers expected)");
                        }

                        if (hasNextCounter > 24 && readFrom.hasNextInt() == true) {
                            throw new IncorrectFormatException("File: " + file + " has an incorrect size (25 numbers expected)");
                        }

                        if (data[i][j] != 0 && data[i][j] != 1) {
                            throw new IncorrectNumberException("One of the numbers in this file have a different number than expected (0 or 1) in this file" + file);
                        }

                    } catch(IncorrectFormatException ex) {
                        System.err.println("File: " + file + " has an incorrect size (25 numbers expected)");
                        hasFormatAlert = true;
                        i = 0;
                        j = 0;
                        hasNextCounter = 1;
                        readFrom.close();
                        readFrom = new Scanner(files[1]);
                        data[i][j] = readFrom.nextInt();
                    } catch(IncorrectNumberException ex) {
                        System.err.println("One of the numbers in this file have a different number than expected (0 or 1) in this file" + file);
                        hasAlert = true;
                        i = 0;
                        j = 0;
                        readFrom.close();
                        readFrom = new Scanner(files[1]);
                        data[i][j] = readFrom.nextInt();
                    }
                    //data array is now filled, this may be used to instantiate a PicrossPuzzle object, but change to 5x5

                }
            readFrom.close();


        } catch (IOException ex) {

        }
        finally {
            //print contents of array to System.out to verify that data was read
//            for (int i=0; i<5; i++) {
//                for (int j = 0; j < 5; j++)
//                    System.out.print(data[i][j] + " ");
//                System.out.println();
//            }
            hasNextCounter = 0;
            return data;
        }
    }


    public PicrossPuzzle getRandomPuzzle(){
        Path path = Paths.get(".\\Data");
        File[] files;
        File aFile;
        aFile = new File(path.toString());
        files = aFile.listFiles();

        int randomNum = (int)(Math.random()* files.length);
        return PicrossPuzzlePool(randomNum);
    }

    PicrossPuzzle PicrossPuzzlePool (int puzzleNum) {
        return getFiles()[puzzleNum];
    }

    PicrossPuzzle PicrossPuzzlePool (PicrossPuzzle puzzle) {
        return puzzle;
    }

    boolean CheckAlert() {
        return hasAlert;
    }

    boolean CheckFormatAlert() {
        return hasFormatAlert;
    }

}

