package commands;


import control.CollectionEditor;
import control.Console;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/**
 * reads and executes a script from the specified file
 */
public class CommandExecuteScript implements CommandWithArgument {
    private final CollectionEditor collectionEditor;
    private String userEnteredPath;
    private Collection<String> paths;


    public CommandExecuteScript(CollectionEditor collectionEditor) {
        this.collectionEditor = collectionEditor;
        this.paths = new ArrayList<>();
    }


    @Override
    public String execute() {
        if (!isFileExits(userEnteredPath)) {
            return "Указан неверный путь к файлу!";
        }


        try {
            Console console = new Console(new Scanner(Paths.get(userEnteredPath))) {
                @Override
                public void run() {
                    while (scanner.hasNext()) {
                        userCommand = scanner.nextLine();
                        if (!isACommand(userCommand)) {
                            outputSystem.showMessage("Нет такой команды \"" + userCommand +
                                    "\", напишите \"help\", чтобы увидеть доступные команды.");
                            continue;
                        }
                        if (isLineAScriptWithRecursion(userCommand)) {
                            outputSystem.showMessage("Произошла рекурсия и программа не вполнилась!");
                            continue;
                        }
                        if (isACommand(userCommand)) {
                            executeCommand(userCommand);
                        }
                    }

                }
            };
            console.run();
        } catch (IOException e) {

        }
        return "";
    }


    public void getArgumentFromOutside(String argument) {
        userEnteredPath = argument;
    }

    private boolean isFileExits(String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            reader.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }



    private boolean isThePathHaveRecursion(String path) {
        ArrayList<String> linesCollection = getLinesCollectionFromFile(path);
        if (isStringCollectionHaveSameElements(paths)) {
            return true;
        }

        for (String line : linesCollection) {//хотим проанализировать все строчки в файле
            if (isLineIsACommandExecuteScript(line)) {
                String[] words = line.split(" ");
                paths.add(words[1]);
                if (isThePathHaveRecursion(words[1])) {
                    return true;
                }
            }
        }
        return false;
    }


    private boolean isLineAScriptWithRecursion(String line) {
        String[] words = line.split(" ");
        if (words.length == 2) {
            boolean condition1 = words[0].equals("execute_script");
            boolean condition2 = isThePathHaveRecursion(words[1]);
            return condition1 & condition2;
        }
        return false;
    }

    private boolean isStringCollectionHaveSameElements(Collection<String> collection) {
        String[] mass = new String[1];
        String[] array = collection.toArray(mass);
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i].equals(array[j])) {
                    return true;
                }
            }
        }
        return false;
    }

    private ArrayList<String> getLinesCollectionFromFile(String path) {
        ArrayList<String> linesCollection = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            while (line != null) {
                linesCollection.add(line);
                line = reader.readLine();
            }
            reader.close();

        } catch (IOException e) {
            //this will never happen because I'm using this method only after checking is path valid
        }
        return linesCollection;
    }


    private boolean isLineIsACommandExecuteScript(String line) {
        String[] words = line.split(" ");

        return words.length == 2 &&
                words[0].equalsIgnoreCase("execute_script") &&
                isFileExits(words[1]);
    }

    @Override
    public String getDescription() {
        return "Читает и исполняет скрипт из указоного файла.";
    }

    @Override
    public String getSyntacticsExample() {
        return getName() + " ScriptFile.txt";
    }

}
