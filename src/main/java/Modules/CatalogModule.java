package Modules;

import java.io.File;
import java.util.*;

public class CatalogModule implements IModule {

    public boolean checkFileExtension(String fileExtension) {
        return fileExtension.equals("directory");
    }

    public void displayFunction() {
        System.out.println("1 - Get a list of files");
        System.out.println("2 - Get the size of all files");
        System.out.println("3 - Get the count of files for each extension");
    }

    public void doFunction(File file, int numberFunc) throws Exception {
        CatalogModule.class.getMethod("doFunction" + numberFunc, File.class).invoke(this, file);
    }

    //Get a list of files
    public void doFunction1(File path) {
        StringBuilder filenames = new StringBuilder();
        File[] files = path.listFiles();

        for (File file : files) {
            filenames.append(file.getName())
                    .append("\n");
        }
        System.out.println(filenames);
    }

    //Get the size of all files
    public void doFunction2(File path) {
        double size = 0;
        File[] files = path.listFiles();

        for (File file : files) {
            size += (double) file.length() / (1024*2); //в мегабайтах
        }
        System.out.println("Size of all files = " + String.format("%.5f", size) + " MB");
    }

    //Get the count of files for each extension
    public void doFunction3(File path) {
        File[] files = path.listFiles();
        HashMap<String, ArrayList<String>> dictionary = new HashMap<>();

        for (File file : files) {
            String extention = getExtention(file);
            if (!dictionary.containsKey(extention))
                dictionary.put(extention, new ArrayList<>());
            ArrayList<String> list = dictionary.get(extention);
            list.add(extention);
        }

        for (ArrayList<String> list : dictionary.values()) {
            int count = list.size();
            System.out.println("Count of files with '" + list.get(0) + "' = " + count);
        }
    }
    public String getExtention (File file){
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        return null;
    }
}

