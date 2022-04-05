package commands;

public interface CommandWithArgument extends Command {

    /**
     * get an argument for later use (for instance command "remove_by_id 36"
     * have argument id with value 36 (in this case)
     */
    void getArgumentFromOutside(String argument);

    /**
     * return SyntacticsExample
     */
    String getSyntacticsExample();
}