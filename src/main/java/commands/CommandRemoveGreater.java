package commands;

import control.CollectionEditor;

/**
 * remove all elements with grater id
 */
public class CommandRemoveGreater implements CommandWithArgument {
    private CollectionEditor collectionEditor;
    private String userEnteredStudentsCount;
    private int studentsCount;

    public CommandRemoveGreater(CollectionEditor collectionEditor) {
        this.collectionEditor = collectionEditor;
    }

    @Override
    public String execute() {
        if (isArgumentANumber(userEnteredStudentsCount)) {
            if (collectionEditor.removeElementsWithGreaterStudentsCount(studentsCount)) {
                return "Все элементы больше чем " + userEnteredStudentsCount + ", были успешно удалены.";
            } else {
                return "Нет элемнтов больше чем заданный " + userEnteredStudentsCount;
            }
        } else {
            return "неверный формат числа \"" + userEnteredStudentsCount + "\"";
        }

    }


    @Override
    public String getDescription() {
        return "Удаляет все элементы больше заданного";
    }

    private boolean isArgumentANumber(String argument) {
        try {
            studentsCount = (int) Double.parseDouble(argument);

            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public void getArgumentFromOutside(String argument) {
        userEnteredStudentsCount = argument;
    }

    @Override
    public String getSyntacticsExample(){
        return getName() + " 11";
    }

}

