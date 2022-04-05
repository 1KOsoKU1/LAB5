package commands;

import control.CollectionEditor;
import control.FileManager;
import exceptions.NoWritePermissionException;

import java.io.FileNotFoundException;

/**
 * saving collection in a file
 */
public class CommandSave implements Command {

    private FileManager fileManager;
    private CollectionEditor collectionEditor;

    /**
     *
     * @param collectionEditor for executing it function
     * @param fileManager for working with a file
     */
    public CommandSave(CollectionEditor collectionEditor, FileManager fileManager) {
        this.fileManager = fileManager;
        this.collectionEditor = collectionEditor;
    }

    /**
     * @return nothing or message about error if unable to save
     */
    public String execute() {
        try {
            fileManager.saveCollectionInFile(collectionEditor.getCollection());
            return "Коллекция была сохранена!";
        } catch (FileNotFoundException e){
            return "Сохранить файл невозможно из-зи отсутвиия файла!";
        } catch (NoWritePermissionException e){
            return "Сохранить файл невозможно так как нет прав для записи!";
        }
    }

    /**
     * @return description of a command
     */
    public String getDescription() {
        return "Сохроняет коллекцию в файл.";
    }
}
