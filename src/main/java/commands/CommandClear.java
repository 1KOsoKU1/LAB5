package commands;

import control.CollectionEditor;

/**
 * deleting all elements from the collection
 */
public class CommandClear implements Command {

    private CollectionEditor collectionEditor;

    public CommandClear(CollectionEditor collectionEditor) {
        this.collectionEditor = collectionEditor;
    }

    @Override
    public String execute() {
        if (collectionEditor.isCollectionEmpty()) {
            return "Коллекция уже пуста!";
        } else {
            collectionEditor.cleanCollection();
            return "Коллекция успешно очищенна!";
        }
    }

    @Override
    public String getDescription() {
        return "Удаляет все элементы из коллекции.";
    }
}
