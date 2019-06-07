package com.example.rockn.connectdatabase;

public class Member {
    private String name;
    private String pass;
    private String type;
    private int sub1;
    private int sub2;

    public int getSub1() {
        return sub1;
    }

    public void setSub1(int sub1) {
        this.sub1 = sub1;
    }

    public int getSub2() {
        return sub2;
    }

    public void setSub2(int sub2) {
        this.sub2 = sub2;
    }

    public Member() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
