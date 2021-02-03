import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Athlete {
    private String name;
    private int age;
    private boolean isMale;
    private int weight;
    private int activityLevel;
    private int calIntake;

    public Athlete(String name, int age, boolean isMale, int weight, int activityLevel, int calIntake) {
        this.name = name;
        this.age = age;
        this.isMale = isMale;
        this.weight = weight;
        this.activityLevel = activityLevel;
        this.calIntake = calIntake;
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

    public static void printColumnHeaders(){
        System.out.println("Name          Age     Sex Weight     Activity      Calories");
        System.out.println("-------------|-------|---|----------|-------------|--------");
    }
    @Override
    public String toString() {
        String displayName = name.substring(0, Math.min(name.length(), 10));
        if (name.length() < 4) {
            displayName += "\t\t\t";
        } else if (name.length() < 8) {
            displayName += "\t\t";
        } else {
            displayName += "\t";
        }
        String displayActivityLevel;
        switch (activityLevel){
            case 1:
                displayActivityLevel = "Sedentary  ";
                break;
            case 2:
                displayActivityLevel = "Moderate   ";
                break;
            case 3:
                displayActivityLevel = "Active     ";
                break;
            case 4:
                displayActivityLevel = "Very Active";
                break;
            default:
                displayActivityLevel = "Unknown    ";
        }
        return displayName + " | " + age + "\t | " + (isMale ? "M" : "F") + " | " + weight + "\tkg " + " | " + displayActivityLevel + " | " + calIntake;
    }
}

