package commands;

import control.CollectionEditor;

/**
 * gives print information about the collection
 */
public class CommandInfo implements Command {
    private CollectionEditor collectionEditor;

    public CommandInfo(CollectionEditor collectionEditor) {
        this.collectionEditor = collectionEditor;
    }

    @Override
    public String execute() {
        return collectionEditor.getInformationAboutCollection();
    }

    @Override
    public String getDescription() {
        return "Выводит информацию о коллекции " +
                "(тип, дату инициализации, кол-во элементов и т. д.)";
    }
}
