package me.bactoria;

public class Holoman {

    private String name;
    private int HowLong;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHowLong() {
        return HowLong;
    }

    public void setHowLong(int howLong) {
        HowLong = howLong;
    }

    @Override
    public String toString() {
        return "Holoman{" +
                "name='" + name + '\'' +
                ", HowLong=" + HowLong +
                '}';
    }

}
