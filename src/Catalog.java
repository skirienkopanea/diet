import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
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
        Athlete.printColumnHeaders();
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
                    Choose what to do next:
                    1 – Add an athlete
                    2 – Edit an athlete
                    3 – Remove an athlete
                    4 – Back to main menu
                    """);
            choice = input.next();
            switch (choice) {
                case "1":
                    addAthlete(input);
                    return;

                case "2":
                    editAthlete(input);
                    return;

                case "3":
                    removeAthlete(input);
                    return;

                case "4":
                    return;
                default:
                    System.out.println("Option unknown");
            }
        } while (true);
    }

    public void readAthletes() {
        Scanner sc;
        try {
            sc = new Scanner(new File("src/athletes.csv"));
            sc.useDelimiter(",|\r\n"); //Java new line regex for windows csv...
            sc.nextLine(); //skip column names
            while (sc.hasNextLine()) {
                if (sc.hasNext()) { //excel automatically creates an empty line at the end
                    String name = sc.next();
                    int age = Integer.parseInt(sc.next());
                    boolean isMale = sc.next().equals("M");
                    int weight = Integer.parseInt(sc.next());
                    int activityLevel = Integer.parseInt(sc.next());
                    int calIntake = Integer.parseInt(sc.next());
                    athletes.add(new Athlete(name, age, isMale, weight, activityLevel, calIntake));
                } else {
                    break;
                }
            }
            System.out.println(athletes.size() + " athlete profiles loaded");

        } catch (FileNotFoundException e) {
            System.out.println("Athletes file not found, 0 athletes loaded." +
                    "\nA new file athletes.csv will be saved before closing the program");
        }
    }

    public void addAthlete(Scanner input) {
        System.out.println("Enter athlete name");
        String name = input.next();

        System.out.println("Enter athlete age");
        int age = input.nextInt();

        System.out.println("Enter athlete sex (M/F)");
        boolean isMale = (input.next().toUpperCase().equals("M"));

        System.out.println("Enter athlete weight in kg");
        int weight = input.nextInt();

        System.out.println("Enter athlete activity level number (1 = Sedentary, 2 = Moderate, 3 = Active, 4 = Very Active)");
        int activityLevel = input.nextInt();

        System.out.println("Enter athlete caloric intake");
        int calIntake = input.nextInt();

        athletes.add(new Athlete(name, age, isMale, weight, activityLevel, calIntake));

        Athlete.printColumnHeaders();
        System.out.println(athletes.get(athletes.size() - 1) + "\nSuccessfully added");
    }

    public void editAthlete(Scanner input) {
        System.out.println("Enter athlete name");
        String name = input.next();

        int athleteID = -1;
        int i = 0;
        for (Athlete athlete : athletes) {
            if (athlete.getName().equals(name)) {
                athleteID = i;
            }
            i++;
        }

        if (athleteID >= 0) {
            Athlete.printColumnHeaders();
            System.out.println(athletes.get(athleteID));

            System.out.println("Enter attribute to edit as specified by the column header");

            String selection = input.next();
            boolean edited = false;

            switch (selection) {
                case "Name":
                    System.out.println("Enter new name");
                    athletes.get(athleteID).setName(input.next());
                    edited = true;
                default:
                    if (edited) {
                        Athlete.printColumnHeaders();
                        System.out.println(athletes.get(athleteID));
                        System.out.println("Successfully edited");
                    } else {
                        System.out.println("Attribute not fond");
                    }
            }
        } else {
            System.out.println("Athlete not found");
        }
    }

    public void removeAthlete(Scanner input) {
        System.out.println("Enter athlete name");
        String name = input.next();

        int athleteID = -1;
        int i = 0;
        for (Athlete athlete : athletes) {
            if (athlete.getName().equals(name)) {
                athleteID = i;
            }
            i++;
        }

        if (athleteID >= 0) {
            Athlete.printColumnHeaders();
            System.out.println(athletes.get(athleteID) + "\nConfirm removal (Y/N)?");

            if (input.next().equalsIgnoreCase("Y")) {
                athletes.remove(athleteID);
                System.out.println("Athlete removed");
            }
            return;
        } else {
            System.out.println("Athlete not found");
        }
    }

    public void readDietPlans() {

    }

    public void readMeals() {

    }

    public void readFoods() {

    }

    public void save() {

    }
}

