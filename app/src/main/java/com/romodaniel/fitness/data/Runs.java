package com.romodaniel.fitness.data;

/**
 * Created by drdan on 7/26/2017.
 */

public class Runs {
    private int cal;
    private double miles;
    private int steps;
    private int minutes;
    private int seconds;


    public Runs(int cal, double miles, int steps, int minutes, int seconds) {
        this.cal = cal;
        this.miles = miles;
        this.steps = steps;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int getCal() {
        return cal;
    }

    public void setCal(int cal) {
        this.cal = cal;
    }

    public double getMiles() {
        return miles;
    }

    public void setMiles(double miles) {
        this.miles = miles;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}
