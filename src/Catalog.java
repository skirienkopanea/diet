import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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

    public List<Food> getFoods() {
        return foods;
    }

    public static String displayName(String name) {
        String displayName = name.substring(0, Math.min(name.length(), 10));
        if (name.length() < 4) {
            displayName += "\t\t\t";
        } else if (name.length() < 8) {
            displayName += "\t\t";
        } else {
            displayName += "\t";
        }
        return displayName;
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
        Food.printColumnHeaders();
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
                    4 – Back to main menu""");
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

        System.out.println("Enter athlete daily caloric intake");
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
                    break;
                case "Age":
                    System.out.println("Enter new age");
                    athletes.get(athleteID).setAge(input.nextInt());
                    edited = true;
                    break;
                case "Sex":
                    System.out.println("Is the athlete a male (Y/N)?");
                    athletes.get(athleteID).setMale(input.next().equalsIgnoreCase("Y"));
                    edited = true;
                    break;
                case "Weight":
                    System.out.println("Enter new weight in kg");
                    athletes.get(athleteID).setWeight(input.nextInt());
                    edited = true;
                    break;
                case "Activity":
                    System.out.println("Enter new activity level number (1 = Sedentary, 2 = Moderate, 3 = Active, 4 = Very Active)");
                    athletes.get(athleteID).setActivityLevel(input.nextInt());
                    edited = true;
                    break;
                case "Calories":
                    System.out.println("Enter new daily caloric intake");
                    athletes.get(athleteID).setCalIntake(input.nextInt());
                    edited = true;
                    break;
                default:
                    System.out.println("Attribute not fond");
            }
            if (edited) {
                System.out.println("Athlete data updated:");
                Athlete.printColumnHeaders();
                System.out.println(athletes.get(athleteID));
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

    public boolean writeAthletes(Scanner input) {
        try {
            PrintWriter writer = new PrintWriter("src/athletes.csv");
            StringBuilder file = new StringBuilder("name,age,sex,weight,activity,calories\r\n");
            athletes.stream().forEach(athlete -> file.append(athlete.toCsv()));
            writer.print(file);
            writer.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Oops... athletes file could not be saved. Close other applications using athletes.csv.");
            System.out.println("Do you want to exit without saving changes (Y/N)?");
            return input.next().equalsIgnoreCase("Y");
        }
    }

    public void readDietPlans() {

    }

    public void readMeals() {

    }

    public void readFoods(String foodType) {
        Scanner sc;
        try {
            sc = new Scanner(new File("src/" + foodType + ".csv"));
            sc.useDelimiter(",|\r\n"); //Java new line regex for windows csv...
            sc.nextLine(); //skip column names
            while (sc.hasNextLine()) {
                if (sc.hasNext()) { //excel automatically creates an empty line at the end

                    String name = sc.next();
                    int cals = Integer.parseInt(sc.next());
                    int carbs = Integer.parseInt(sc.next());
                    int fats = Integer.parseInt(sc.next());
                    int proteins = Integer.parseInt(sc.next());
                    String servings = sc.next();

                    Boolean hasProperty = false;
                    int extraProperty = -1;

                    //Optional properties
                    if (sc.hasNextBoolean()) { //boolean property goes first
                        hasProperty = sc.nextBoolean();
                    }
                    if (sc.hasNextInt()){
                        extraProperty = Integer.parseInt(sc.next());
                    }

                    Macronutrients macros = new Macronutrients(cals, carbs, fats, proteins);
                    switch (foodType) {
                        case "diary":
                            foods.add(new Diary(name, macros, getServings(servings), hasProperty));
                            break;
                        case "beverages":
                            foods.add(new Beverage(name, macros, getServings(servings), hasProperty, extraProperty));
                            break;
                        case "cereals":
                            foods.add(new Cereal(name, macros, getServings(servings), hasProperty));
                            break;
                        case "meats":
                            foods.add(new Meat(name, macros, getServings(servings), extraProperty));
                            break;
                        case "fish":
                            foods.add(new Fish(name, macros, getServings(servings), hasProperty));
                            break;
                        case "vegetables":
                            boolean feb = sc.nextBoolean();
                            boolean mar = sc.nextBoolean();
                            boolean apr = sc.nextBoolean();
                            boolean may = sc.nextBoolean();
                            boolean jun = sc.nextBoolean();
                            boolean jul = sc.nextBoolean();
                            boolean aug = sc.nextBoolean();
                            boolean sep = sc.nextBoolean();
                            boolean oct = sc.nextBoolean();
                            boolean nov = sc.nextBoolean();
                            boolean dec = sc.nextBoolean();

                            foods.add(new Vegetable(name, macros, getServings(servings), hasProperty, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec));
                            break;
                        case "other":
                            foods.add(new MiscFood(name, macros, getServings(servings)));
                            break;
                        default:
                            //it will be caught by the catch
                    }
                } else {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading foods: " + e.getStackTrace());
        }
    }

    public List<Serving> getServings(String text){
        List<Serving> servings = new ArrayList<>();
        Scanner sc = new Scanner(text);
        sc.useDelimiter(";");
        while(sc.hasNext()){
            String pair = sc.next();
            String servingSize = pair.substring(0,pair.indexOf("&"));
            int grams = Integer.parseInt(pair.substring(pair.indexOf("&")+1));
            servings.add(new Serving(servingSize,grams));
        }
        return servings;
    }

    public boolean save(Scanner input) {
        return writeAthletes(input); //AND AND AND...
    }
}

