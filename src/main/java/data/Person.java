package data;

import java.time.LocalDateTime;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.time.LocalDateTime birthday; //Поле не может быть null
    private Color eyeColor; //Поле может быть null
    private Country nationality; //Поле может быть null
    private Location location; //Поле не может быть null

    public Person(String name, LocalDateTime birthday, Color eyeColor, Country nationality, Location location) {
        this.name = name;
        this.birthday = birthday;
        this.eyeColor = eyeColor;
        this.nationality = nationality;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public Color getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public Location getLocation() {
        return location;
    }

    public void setXLocation(Long x) {
        this.location.setX(x);
    }

    public void setYLocation(Integer y) {
        this.location.setY(y);
    }

    public void setZLocation(int z) {
        this.location.setZ(z);
    }

    public void setLocationName(String name) {
        this.location.setName(name);
    }

    public String getLocationName(){
        return this.location.getName();
    }
}