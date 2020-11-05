package Modules;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.awt.*;

public class TextModule implements IModule {

    public boolean checkFileExtension(String fileExtension) {
        return fileExtension.equals("txt");
    }

    public void displayFunction() {
        System.out.println("1 - Get count of lines");
        System.out.println("2 - Get frequency of occurrence of each character");
        System.out.println("3 - Open file");
    }

    public void doFunction(File file, int numberFunc) throws Exception {
        TextModule.class.getMethod("doFunction" + numberFunc, File.class).invoke(this, file);
    }

    //Get count of lines
    public void doFunction1(File file) throws FileNotFoundException {
        int linesCount = 0;
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            scanner.nextLine();
            linesCount++;
        }
        System.out.println("Lines count: " + linesCount);
    }

    //Get frequency of occurrence of each character
    public void doFunction2(File file) {
        int totalCharacters = 0;
        Scanner scanner = new Scanner(file.getPath());
        HashMap<Character, ArrayList<Character>> dictionary = new HashMap<>();

        while (scanner.hasNext()) {
            char[] str = scanner.next().toLowerCase().toCharArray();
            for (char symbol : str) {
                if (!dictionary.containsKey(symbol))
                    dictionary.put(symbol, new ArrayList<>());
                ArrayList<Character> list = dictionary.get(symbol);
                list.add(symbol);
                totalCharacters++;
            }
        }

        System.out.println("Total symbols count - " + totalCharacters);

        for (ArrayList<Character> list : dictionary.values()) {
            int count = list.size();
            System.out.println("Count of each symbol - '" + list.get(0) + "' = " + count);
        }
    }

    //Open file
    public void doFunction3(File file) throws IOException {
        Desktop.getDesktop().open(file);
    }
}
