package com.example.oqulyk.Model3;

public class Oqulyk {
    private long kid;
    private int roll;
    private String name;
    private String status;

    public Oqulyk(long kid, int roll, String name) {
        this.kid=kid;
        this.roll = roll;
        this.name = name;
        status="";
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getKid() {
        return kid;
    }

    public void setKid(long kid) {
        this.kid = kid;
    }
}
