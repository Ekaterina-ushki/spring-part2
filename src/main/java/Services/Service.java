package Services;

import Modules.IModule;

import java.io.File;
import java.util.Scanner;

public class Service {

    private static int command;

    public void chooseOperation(IModule module, File file) throws Exception {

        while (chooseCommand(module) != 0) {
            try {
                processСommand(command, module, file);
            } catch (NoSuchMethodException e) {
                System.out.println("Unknown command");
            }
        }
    }

    private static int chooseCommand(IModule module) {
        System.out.println();
        System.out.println("0 for exit");
        module.displayFunction();
        Scanner scanner = new Scanner(System.in);
        return command = scanner.nextInt();
    }

    private static void processСommand(int command, IModule module, File file) throws Exception {
        module.doFunction(file, command);
    }
}

