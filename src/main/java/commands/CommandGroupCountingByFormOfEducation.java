package commands;

import control.CollectionEditor;

public class CommandGroupCountingByFormOfEducation implements Command {
    private CollectionEditor collectionEditor;

    public CommandGroupCountingByFormOfEducation(CollectionEditor collectionEditor) {
        this.collectionEditor = collectionEditor;
    }

    @Override
    public String execute() {
       return collectionEditor.getAmountOfGroupsByFormOfEducation();
    }

    @Override
    public String getDescription() {
        return "Выводит количество элементов коллекции сгрупированных по форме обучения.";
    }
}
