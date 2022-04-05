package commands;


import control.CollectionEditor;

/**
 * class for command "show" realization
 */
public class CommandShow implements Command {

    private CollectionEditor collectionEditor;

    public CommandShow(CollectionEditor collectionEditor) {
        this.collectionEditor = collectionEditor;
    }

    /**
     * @return information about all elements in the collection or says that collection is empty if it is
     */
    @Override
    public String execute() {
        return collectionEditor.getStringInterpretationOfCollection();
    }

    /**
     * @return description of a command
     */
    @Override
    public String getDescription() {
        return "Выводит элементы в стороковом представлении.";
    }
}
