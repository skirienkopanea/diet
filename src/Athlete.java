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

    public int getCalIntake() {
        return calIntake;
    }

    public void setCalIntake(int calIntake) {
        this.calIntake = calIntake;
    }

    //Reason I have it here in the class as static and not in catalog is because I want to keep
    //the column headers close to the actual attributes of the class and their toString method
    public static void printColumnHeaders(){
        System.out.println("Name          Age     Sex Weight     Activity      Calories");
        System.out.println("-------------|-------|---|----------|-------------|--------");
    }
    @Override
    public String toString() {
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
        return Catalog.displayName(name) + " | " + age + "\t | " + (isMale ? "M" : "F") + " | " + weight + "\tkg " + " | " + displayActivityLevel + " | " + calIntake;
    }

    public String toCsv(){
        return name + "," + age + "," + (isMale ? "M" : "F") + "," + weight + "," + activityLevel + "," + calIntake + "\r\n";

    }
}

