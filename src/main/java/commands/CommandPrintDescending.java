package commands;

import control.CollectionEditor;

public class CommandPrintDescending implements Command{
    private CollectionEditor collectionEditor;

    public CommandPrintDescending(CollectionEditor collectionEditor) {
        this.collectionEditor = collectionEditor;
    }

    @Override
    public String execute() {
        return collectionEditor.getCollectionInDescendingOrder();
    }

    @Override
    public String getDescription() {
        return "Выводит список элемнтов в обратном порядке.";
    }
}
