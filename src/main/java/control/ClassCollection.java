package control;

import data.StudyGroup;
import com.google.gson.reflect.TypeToken;
import exceptions.NoReadPermissionException;
import exceptions.NoWritePermissionException;

import java.io.FileNotFoundException;
import java.util.LinkedList;

/**
 * class for giving the only instance of StudyGroup collection
 */
public class ClassCollection {

    private static LinkedList<StudyGroup> list;

    public static LinkedList<StudyGroup> getList() {
        if (list == null){
            FileManager<StudyGroup> fileManager = new FileManager<StudyGroup>("LAB5",new TypeToken<LinkedList<StudyGroup>>(){}.getType());
            try {
                list = new LinkedList<>();
                list = fileManager.readCollection();
            } catch (NoReadPermissionException | FileNotFoundException | NoWritePermissionException e) {
                //...
            }
        }
        return list;
    }

}
