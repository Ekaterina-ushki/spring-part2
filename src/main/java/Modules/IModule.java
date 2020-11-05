package Modules;

import java.io.File;

public interface IModule {
    boolean checkFileExtension(String fileExtension);
    void displayFunction();
    void doFunction(File file, int numberFunc) throws Exception;
}


