package com.romodaniel.fitness;

import android.widget.EditText;

/**
 * Created by Welcome To Future on 7/25/2017.
 */

public class Contact
{
    //int id;
    String name;
    String uname;
    String password;
    int pn;
    float cw;
    float gw;
    int age;
    public Contact(String name, String uname, String password, int pn, float cw, float gw, int age) {
        // this.id = id;
        this.name = name;
        this.uname = uname;
        this.password = password;
        this.pn = pn;
        this.cw = cw;
        this.gw = gw;
        this.age = age;
    }

    /*public void setId(int id) {
        this.id = id;

    }
*/
    public void setName(String name) {
        this.name = name;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPn(int pn) {
        this.pn = pn;
    }

    public void setCw(float cw) {
        this.cw = cw;
    }

    public void setGw(float gw) {
        this.gw = gw;
    }

    public void setAge(int age) {
        this.age = age;
    }


/*

    public int getId() {
        return id;
    }
*/

    public String getName() {
        return name;
    }

    public String getUname() {
        return uname;
    }

    public String getPassword() {
        return password;
    }

    public int getPn() {
        return pn;
    }

    public float getCw() {
        return cw;
    }

    public float getGw() {
        return gw;
    }

    public int getAge() {
        return age;
    }


}
