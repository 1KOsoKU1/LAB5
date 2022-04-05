package control;

import data.StudyGroup;
import com.google.gson.reflect.TypeToken;
import commands.*;
import exceptions.NoReadPermissionException;
import exceptions.NoWritePermissionException;

import java.lang.reflect.Type;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * class for constructing commands for later use
 */

public class FactoryOfCommands {

    private ArrayList<Command> commandsList;
    private FileManager<StudyGroup> fileManager;
    private Type typeToken = new TypeToken<LinkedList<StudyGroup>>(){}.getType();

    public FactoryOfCommands(){
        commandsList = new ArrayList<>();
        fileManager = new FileManager<StudyGroup>("LAB5", typeToken);
    }

    /**
     * creates and return list of commands,
     * also some commands needs scanner to be constructed, so we need to give it as a parameter
     *
     * @param scanner for some commands
     * @return list with all commands
     */
    public ArrayList<Command> getCommandList(Scanner scanner) {

        CollectionEditor collectionEditor = new CollectionEditor();
        Command help = new CommandHelp(commandsList);
        Command info = new CommandInfo(collectionEditor);
        Command show = new CommandShow(collectionEditor);
        Command add = new CommandAdd(collectionEditor, scanner);
        Command exit = new CommandExit();
        Command save = new CommandSave(collectionEditor, fileManager);
        Command update = new CommandUpdate(collectionEditor, scanner);
        Command clear = new CommandClear(collectionEditor);
        Command commandGroupCountingByFormOfEducation = new CommandGroupCountingByFormOfEducation(collectionEditor);
        Command head = new CommandHead(collectionEditor);
        Command printDescending = new CommandPrintDescending(collectionEditor);
        Command removeGreater = new CommandRemoveGreater(collectionEditor);
        Command countGraterThanStudentsCount = new CommandCountGreaterThanStudentsCount(collectionEditor);
        Command executeScript = new CommandExecuteScript(collectionEditor);
        Command addIfMax = new CommandAddIfMax(collectionEditor);
        commandsList.add(addIfMax);
        commandsList.add(executeScript);
        commandsList.add(countGraterThanStudentsCount);
        commandsList.add(commandGroupCountingByFormOfEducation);
        commandsList.add(head);
        commandsList.add(printDescending);
        commandsList.add(removeGreater);
        commandsList.add(clear);
        commandsList.add(info);
        commandsList.add(show);
        commandsList.add(add);
        commandsList.add(exit);
        commandsList.add(save);
        commandsList.add(update);
        return commandsList;
    }

    /**
     * checking is anything wrong with a file
     *
     * @note this line will also apear when you execute script (it's not a bug, it's a feature)
     */
    public void getStatusOfLoadFile() {
        FileManager<StudyGroup> fileManager = new FileManager<>("LAB5", new TypeToken<LinkedList<StudyGroup>>() {
        }.getType());
        try {
            fileManager.readCollection();
            System.out.println("Коллекция успешно загруженна!");
        } catch (NoReadPermissionException e) {
            System.out.println("Невозможно загрузить колекцию из-за отсутсвия прав доступа!");
            System.exit(0);
        } catch (FileNotFoundException e) {
            System.out.println("Файл отстствует!");
            System.exit(0);
        } catch (NoWritePermissionException e) {
            System.out.println("Коллекция загруженна, но нет прав доступа на запись в файл!");
            System.exit(0);
        }
    }

}
