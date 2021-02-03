import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Catalog {
    private List<Athlete> athletes;
    private List<DietPlan> dietPlans;
    private List<Meal> meals;
    private List<Food> foods;

    public Catalog() {
        this.athletes = new ArrayList<>();
        this.dietPlans = new ArrayList<>();
        this.meals = new ArrayList<>();
        this.foods = new ArrayList<>();
    }

    public void showAthletes() {
        System.out.println("     Name     Age  Sex   Weight   Activity");
        System.out.println("-------------|----|---|----------|--------");
        for (Athlete athlete : athletes) {
            System.out.println(athlete);
        }
    }

    public void showDietPlans() {
        for (DietPlan dietPlan : dietPlans) {
            System.out.println(dietPlan);
        }
    }

    public void showMeals() {
        for (Meal meal : meals) {
            System.out.println(meal);
        }
    }

    public void showFoods() {
        for (Food food : foods) {
            System.out.println(food);
        }
    }

    public void athleteMenu(Scanner input) {
        String choice;
        do {
            System.out.println("""
                    1 – Add an athlete
                    2 – Edit an athlete
                    3 – Remove an athlete
                    4 – Back to main menu
                    """);
            choice = input.next();
            switch (choice) {
                case "1":
                    System.out.println(1);
                    return;

                case "2":
                    System.out.println(2);
                    return;

                case "3":
                    System.out.println(3);
                    return;

                case "4":
                    return;
                default:
                    System.out.println("Option unknown");
            }
        } while (true);
    }

    public void readAthletes(){
        Scanner sc;
        try {
            sc = new Scanner(new File("src/athletes.csv"));
            sc.useDelimiter(",|\r\n"); //Java new line regex for windows csv...
            sc.nextLine(); //skip column names
            while (sc.hasNextLine()) {
                String name = sc.next();
                int age = Integer.parseInt(sc.next());
                boolean isMale = sc.next().equals("M");
                int weight = Integer.parseInt(sc.next());
                int activityLevel = Integer.parseInt(sc.next());

                this.athletes.add(new Athlete(name, age, isMale, weight, activityLevel));
            }
            System.out.println(athletes.size() + " athlete profiles loaded");

        } catch (FileNotFoundException e) {
            System.out.println("Athletes file not found, 0 athletes loaded." +
                    "\nA new file athletes.csv will be saved before closing the program");
        }

    }


    public void readDietPlans(){

    }
    public void readMeals(){

    }
    public void readFoods(){

    }

    public void save() {

    }
}

