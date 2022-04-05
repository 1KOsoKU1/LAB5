package commands;

import control.CollectionEditor;

public class CommandAddIfMax implements CommandWithArgument {
    private CollectionEditor collectionEditor;
    String numberEntered;
    Integer number;

    public CommandAddIfMax(CollectionEditor collectionEditor) {
        this.collectionEditor = collectionEditor;
    }

    private boolean isArgumentANumber(String argument) {
        try {
            number = Integer.parseInt(argument);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String execute() {
        if (isArgumentANumber(numberEntered)) {
            if (number > collectionEditor.getMaxStudentsCountInTheCollection()) {
                if (!collectionEditor.getCollection().isEmpty()) {
                    collectionEditor.addIfMax(number);
                    return "Элемент был успешно добавлен!";
                } else {
                    return "";
                }
            } else {
                return "Элмент меньше максимального!";
            }

        } else {
            return "Неверно введён аргумент для команды.";
        }
    }

    @Override
    public String getDescription() {

        return "Добовляет элемент в коллекцию если он больше макимального." ;
    }

    @Override
    public void getArgumentFromOutside(String argument) {
        numberEntered = argument;
    }

    @Override
    public String getSyntacticsExample() {
        return getName() + " 45";
    }
}
