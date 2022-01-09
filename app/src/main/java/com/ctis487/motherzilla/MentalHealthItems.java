package com.ctis487.motherzilla;

public class MentalHealthItems {
    private int id;
    private int age;
    private String name;
    private String description;
    private String speciality;
    private byte[] img;

    public MentalHealthItems(int id, int age, String name, String description, String speciality, byte[] img) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.description = description;
        this.speciality = speciality;
        this.img = img;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
