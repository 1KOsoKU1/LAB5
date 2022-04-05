package commands;

import data.StudyGroup;
import control.CollectionEditor;

import java.util.Scanner;

/**
 * command that adding new element to the collection
 */
public class CommandAdd implements Command {

    private Scanner scanner;
    private CollectionEditor collectionEditor;

    public CommandAdd(CollectionEditor collectionEditor, Scanner scanner) {
        this.collectionEditor = collectionEditor;
        this.scanner = scanner;
    }

    @Override
    public String execute() {
        StudyGroup studyGroup = new StudyGroup(getId(), System.out, scanner);
        this.collectionEditor.addElement(studyGroup);
        return "Новый элемент успешно добавлен в коллекцию";
    }

    private int getId() {
        if (collectionEditor.isCollectionEmpty()) {
            return 1;
        } else {
            return collectionEditor.getMaxIdOfCollection() + 1;
        }
    }

    @Override
    public String getDescription() {
        return "Создаёт эллемент и добавлет его в коллекцию!";
    }
}
