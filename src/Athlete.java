import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Athlete {
    private String name;
    private int age;
    private boolean isMale;
    private int weight;
    private int activityLevel;

    public Athlete(String name, int age, boolean isMale, int weight, int activityLevel) {
        this.name = name;
        this.age = age;
        this.isMale = isMale;
        this.weight = weight;
        this.activityLevel = activityLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(int activityLevel) {
        this.activityLevel = activityLevel;
    }
}

