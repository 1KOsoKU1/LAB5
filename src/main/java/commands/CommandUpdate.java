package commands;

import data.StudyGroup;
import exceptions.NoElementWithSuchIdException;
import control.CollectionEditor;
import java.util.Scanner;

/**
 * updating element with the specified id
 */
public class CommandUpdate implements CommandWithArgument {

    private CollectionEditor collectionEditor;
    private String userEnteredId;
    private int id;
    private Scanner scanner;

    public CommandUpdate(CollectionEditor collectionEditor, Scanner scanner) {
        this.collectionEditor = collectionEditor;
        this.scanner = scanner;
    }

    @Override
    public String execute() {
        if (!isArgumentANumber(userEnteredId)) {
            return "Неверный формат id \"" + userEnteredId + "\".";
        }
        try {
            StudyGroup oldStudyGroup = collectionEditor.getStudyGroupById(id);
            StudyGroup newStudyGroup = new StudyGroup(id, System.out, scanner);
            newStudyGroup.setCreationDate(oldStudyGroup.getCreationDate());
            collectionEditor.updateElement(newStudyGroup);
            return "Значения элемента были обновлены";
        } catch (NoElementWithSuchIdException e) {
            return "Невозможно найти элемент с id " + id;
        }

    }

    public void getArgumentFromOutside(String argument) {
        userEnteredId = argument;
    }

    @Override
    public String getSyntacticsExample() {
        return getName() + " 51";
    }

    private boolean isArgumentANumber(String argument) {
        try {
            id = Integer.parseInt(argument);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    @Override
    public String getDescription() {
        return "Обновляет элемент с указанным id.";
    }

}

