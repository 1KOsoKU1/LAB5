package control;

import com.google.gson.*;
import exceptions.NoReadPermissionException;
import exceptions.NoWritePermissionException;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Operates the file for saving/reading collection.
 */
public class FileManager<T> {
    private String path;
    private Type typeToken;

    public FileManager(String envVariable, Type typeToken) {
        path = System.getenv(envVariable);
        this.typeToken = typeToken;
    }

    /**
     * saving collection in the file
     *
     * @param collection that will be saved in a file
     * @throws IOException if file for saving is missing or damaged
     */
    public void saveCollectionInFile(Collection<T> collection) throws FileNotFoundException, NoWritePermissionException {
        if (!isFileExist(path)) {
            throw new FileNotFoundException();
        }
        if (!isFileWritable(path)) {
            throw new NoWritePermissionException();
        }
        try {
            Gson gson = new Gson();
            FileOutputStream writer = new FileOutputStream(path);
            byte[] buffer = gson.toJson(collection).getBytes();
            writer.write(buffer);
            writer.close();
        } catch (IOException e) {
            //...
        }
    }

    /**
     * give LinkedList with collection reading from a file
     *
     * @return LinkedList read from a file
     * @throws IOException only if jsonToString method throw it
     */
    public LinkedList<T> readCollection() throws FileNotFoundException, NoReadPermissionException, NoWritePermissionException {
        LinkedList<T> list = new LinkedList<>();
        if (!isFileExist(path)) {
            throw new FileNotFoundException();
        }
        if (!isFileReadable(path)) {
            throw new NoReadPermissionException();
        }
        if (!isFileWritable(path)) {
            throw new NoWritePermissionException();
        }
        try {
            String stringInterpretationOfJson = this.jsonToString();
            Gson gson = new Gson();
            list = gson.fromJson(stringInterpretationOfJson, typeToken);
            //new TypeToken<LinkedList<StudyGroup>>(){}.getType()
        } catch (IOException e) {
            //..
        } catch (JsonSyntaxException e) {
            System.out.println("Файл с коллекцией был повреждён поэтому коллекцция пуста.");
        }
        return list;
    }

    /**
     * give String interpretation of json file
     *
     * @return String interpretation of json file
     * @throws IOException if BufferedInputStream object can't read
     */
    private String jsonToString() throws IOException {
        try {
            String jsonText = "";
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                jsonText += line;
            }
            reader.close();
            return jsonText;
        } catch (JsonSyntaxException e) {
           return "\n";
        }
    }


    /**
     * says whether the file can be read
     *
     * @param path to the file
     * @return boolean
     */
    private boolean isFileReadable(String path) {
        File file = new File(path);
        return file.canRead();
    }

    /**
     * says whether the file exist
     *
     * @param path to the file
     * @return boolean
     */
    private boolean isFileExist(String path) {
        try{
            File file = new File(path);
            return file.exists();
        } catch (NullPointerException e){//I have no idea why this is throwing
            return false;
        }

    }

    /**
     * says whether it is possible to write to a file
     *
     * @param path to the file
     * @return boolean
     */
    private boolean isFileWritable(String path) {
        try {
            File file = new File(path);
            return file.canWrite();
        } catch (NullPointerException e){//I have no idea why this is throwing
            return false;
        }
    }

    /**
     * return String representation of object
     *
     * @return String representation of object
     */
    @Override
    public String toString() {
        return "FileManager object for working with file " + path;
    }

}