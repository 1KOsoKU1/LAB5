package data;

public class Location {
    private Long x; //Поле не может быть null
    private Integer y; //Поле не может быть null
    private int z;
    private String name; //Строка не может быть пустой, Поле может быть null (условия задания противоречат друг другу)

    public Location(Long x, Integer y, int z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation(){
        return new String(this.getX() + " " + this.getY() + " " + this.getZ() + this.getName());
    }
}