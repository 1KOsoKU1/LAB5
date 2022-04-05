package commands;

import control.CollectionEditor;

public class CommandCountGreaterThanStudentsCount implements CommandWithArgument{
    CollectionEditor collectionEditor;
    String userEnteredCount;
    int count;

    public CommandCountGreaterThanStudentsCount(CollectionEditor collectionEditor) {
        this.collectionEditor = collectionEditor;
    }
    private boolean isArgumentANumber(String argument) {
        try {
            count = Integer.parseInt(argument);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void getArgumentFromOutside(String argument) {
        userEnteredCount = argument;
    }

    @Override
    public String execute() {
        if (isArgumentANumber(userEnteredCount)){
           return collectionEditor.getCountGraterThanStudentsCount(count);
        } else {
            return "Неверно введён аргумент для команды.";
        }
    }

    @Override
    public String getDescription() {
        return "Возвращает колличество элементов коллекции у которых поле studentsCount больше заданного.";
    }

    @Override
    public String getSyntacticsExample() {
        return getName() + " 13";
    }
}
