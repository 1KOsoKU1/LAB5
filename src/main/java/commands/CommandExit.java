package commands;

/**
 * ends the program
 */
public class CommandExit implements Command{

    @Override
    public String execute() {
        System.exit(0);
        return "this will never be written(((";
    }

    @Override
    public String getDescription() {
        return "Заканчивает выполнение программы.";
    }
}
