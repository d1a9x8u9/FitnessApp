package com.romodaniel.fitness.data;

/**
 * Created by Welcome To Future on 7/25/2017.
 */

public class User
{
    String name;
    String sex;
    int lbs;
    int height;

    public User(String name, String sex, int lbs, int height) {
        this.name = name;
        this.sex = sex;
        this.lbs = lbs;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getLbs() {
        return lbs;
    }

    public void setLbs(int lbs) {
        this.lbs = lbs;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
