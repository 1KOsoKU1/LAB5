package commands;

import control.CollectionEditor;

/**
 * returns fits element of the collection
 */
public class CommandHead implements Command{
    private CollectionEditor collectionEditor;

    public CommandHead(CollectionEditor collectionEditor){
        this.collectionEditor = collectionEditor;
    }

    @Override
    public String execute() {
        return collectionEditor.getFirstElementOfCollection();
    }

    @Override
    public String getDescription() {
        return "Выводит инфрмацию о первом элементе коллекции.";
    }
}
