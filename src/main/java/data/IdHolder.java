package data;

public class IdHolder {
    private int id;//Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    public IdHolder(int id) {
     this.id = id;
    }

    public int getId() throws IllegalArgumentException {
        if (id > 0) {
            return id;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
