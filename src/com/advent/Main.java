package com.advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	// write your code here
        int lineNumber = 200;
        Scanner input = new Scanner(new File("C:\\Repo\\Advent\\src\\com\\advent\\input.txt"));
        String line = new String();
        String[][] samples = new String[lineNumber][10];
        String[][] digits = new String[lineNumber][4];
        String[] decodedValues = new String[10];
        int sum = 0;
        int value = 0;
        int tmp = 0;

        while(input.hasNextLine()){
            line = input.nextLine();
            samples[tmp] = line.split(" \\| ")[0].split(" ");
            digits[tmp] = line.split(" \\| ")[1].split(" ");
            tmp++;
        }

        for (int i = 0; i < lineNumber; i++){
            decodedValues = decodeSample(samples[i]);
            for (int j = 0; j < 10; j++){
                System.out.print(decodedValues[j] + " ");
            }
            value = decodeValue(decodedValues, digits[i][0]) * 1000 + decodeValue(decodedValues, digits[i][1]) * 100 + decodeValue(decodedValues, digits[i][2]) * 10 + decodeValue(decodedValues, digits[i][3]);
            System.out.print(" value = " + value);
            sum = sum + value;
            System.out.println();
        }
        System.out.println(sum);
    }

    public static String[] decodeSample(String[] sample){
        String[] decodedValues = new String[10];
        String top = "";
        String topLeft = "";
        String topRight = "";
        String middle = "";
        String botLeft = "";
        String botRight = "";
        String bottom = "";

        //find 8, 4, 1, 7
        for(int i = 0; i < 10; i++){
            if (sample[i].length() == 7){
                decodedValues[8] = sample[i];
            }
            if (sample[i].length() == 4){
                decodedValues[4] = sample[i];
            }
            if (sample[i].length() == 2){
                decodedValues[1] = sample[i];
                for (int j = 0; j < 10; j++){
                    if (sample[j].length() == 3){
                        decodedValues[7] = sample[j];
                        top = sample[j].replace(String.valueOf(sample[i].charAt(0)), "").replace(String.valueOf(sample[i].charAt(1)), "");
                    }
                }
            }
        }

        //find 9
        for (int i = 0; i < 10; i++){
            if (sample[i].length() == 6){
                if (sample[i].indexOf(decodedValues[4].charAt(0)) != -1 && sample[i].indexOf(decodedValues[4].charAt(1)) != -1 && sample[i].indexOf(decodedValues[4].charAt(2)) != -1 && sample[i].indexOf(decodedValues[4].charAt(3)) != -1){
                    decodedValues[9] = sample[i];
                    for (int j = 0; j < 7; j++){
                        if (sample[i].indexOf(decodedValues[8].charAt(j)) == -1){
                            botLeft = String.valueOf(decodedValues[8].charAt(j));
                        }
                    }
                }
            }
        }

        //find 0
        for (int i = 0; i < 10; i++){
            if (sample[i].length() == 6){
                if (sample[i] != decodedValues[9]){
                    if (sample[i].indexOf(decodedValues[1].charAt(0)) != -1 && sample[i].indexOf(decodedValues[1].charAt(1)) != -1){
                        decodedValues[0] = sample[i];
                        for (int j = 0; j < 7; j++){
                            if (sample[i].indexOf(decodedValues[8].charAt(j)) == -1){
                                middle = String.valueOf(decodedValues[8].charAt(j));
                            }
                        }
                    }
                }
            }
        }

        //find 6
        for (int i = 0; i < 10; i++){
            if (sample[i].length() == 6){
                if (sample[i] != decodedValues[9] && sample[i] != decodedValues[0]){
                    decodedValues[6] = sample[i];
                    for (int j = 0; j < 7; j++){
                        if (sample[i].indexOf(decodedValues[8].charAt(j)) == -1){
                            topRight = String.valueOf(decodedValues[8].charAt(j));
                        }
                    }
                }
            }
        }

        //find 2
        for (int i = 0; i < 10; i++){
            if (sample[i].length() == 5){
                if (sample[i].indexOf(botLeft) != -1){
                    decodedValues[2] = sample[i];
                }
            }
        }

        //find 3
        for (int i = 0; i < 10; i++){
            if (sample[i].length() == 5){
                if (sample[i].indexOf(decodedValues[1].charAt(0)) != -1 && sample[i].indexOf(decodedValues[1].charAt(1)) != -1){
                    decodedValues[3] = sample[i];
                }
            }
        }

        //find 5
        for (int i = 0; i < 10; i++){
            if (sample[i].length() == 5){
                if (sample[i] != decodedValues[2] && sample[i] != decodedValues[3]){
                    decodedValues[5] = sample[i];
                }
            }
        }

        //for (int i = 0; i < 10; i++){
        //    System.out.print(decodedValues[i] + " ");
        //}
        //System.out.println();

        return decodedValues;
    }

    public static Integer decodeValue(String[] decodedValues, String value){
        boolean isValue;
        for (int i = 0; i < 10; i++){
            if (decodedValues[i].length() == value.length()) {
                isValue = true;
                for (int j = 0; j < decodedValues[i].length(); j++){
                    if (value.indexOf(decodedValues[i].charAt(j)) == -1){
                        isValue = false;
                    }
                }
                if (isValue){
                    System.out.print(value + " = " + i + " ");
                    return i;
                }
            }
        }
        return 0;
    }
}
