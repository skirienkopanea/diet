import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
        String displayName = name.substring(0, Math.min(name.length(), 16));
        if (name.length() < 4) {
            displayName += "\t\t\t\t";
        } else if (name.length() < 8) {
            displayName += "\t\t\t";
        } else if (name.length() < 12) {
            displayName += "\t\t";
        } else if (name.length() < 16) {
            displayName += "\t";
        }
        return displayName;
    }

    //print tables
    public void showAthletes() {
        Athlete.printColumnHeaders();
        for (Athlete athlete : athletes) {
            System.out.println(athlete);
        }
    }

    public void showFoods() {
        Food.printColumnHeaders();
        for (Food food : foods) {
            System.out.println(food);
        }
    }

    public void showMeals() {
        for (Meal meal : meals) {
            System.out.println(meal);
        }
    }

    public void showDietPlans() {
        for (DietPlan dietPlan : dietPlans) {
            System.out.println(dietPlan);
        }
    }

    //print menus
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

    public void foodMenu(Scanner input) {
        String choice;
        do {
            System.out.println("""
                    Choose what to do next:
                    1 – Add food
                    2 – Edit food
                    3 – Remove food
                    4 – Back to main menu""");
            choice = input.next();
            switch (choice) {
                case "1":
                    addFood(input);
                    return;

                case "2":
                    editFood(input);
                    return;

                case "3":
                    removeFood(input);
                    return;

                case "4":
                    return;
                default:
                    System.out.println("Option unknown");
            }
        } while (true);
    }

    //read files
    public void readAthletes() {
        Scanner sc;
        try {
            sc = new Scanner(new File("src/data/athletes.csv"));
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
            System.out.println("Error loading data/athletes.csv (" + e.getStackTrace() +
                    ")\n0 athletes loaded");
        }
    }

    public void readFoods(String foodType) {
        Scanner sc;
        try {
            sc = new Scanner(new File("src/data/" + foodType + ".csv"));
            sc.useDelimiter(",|\r\n"); //Java new line regex for windows csv...
            sc.nextLine(); //skip column names
            while (sc.hasNextLine()) {
                if (sc.hasNext()) { //excel automatically creates an empty line at the end

                    String name = sc.next();
                    double calories = Double.parseDouble(sc.next());
                    double carbs = Double.parseDouble(sc.next());
                    double fats = Double.parseDouble(sc.next());
                    double proteins = Double.parseDouble(sc.next());
                    String servings = sc.next();

                    Boolean hasProperty = false;
                    int extraProperty = -1;

                    //Optional properties
                    if (sc.hasNextBoolean()) { //boolean property goes first
                        hasProperty = sc.nextBoolean();
                    }
                    if (sc.hasNextInt()) {
                        extraProperty = Integer.parseInt(sc.next());
                    }

                    Macronutrients macros = new Macronutrients(calories, carbs, fats, proteins);
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
                            //january is already retrieved as hasProperty
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
                            System.out.println("Unknown food type");
                    }
                } else {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading data/" + foodType + ".csv (" + e.getStackTrace() + ")");
        }
    }

    public void readMeals() {

    }

    public void readDietPlans() {

    }

    //add to lists
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

    public void addFood(Scanner input) {
        System.out.println("Enter food type (diary, beverages, cereals, vegetables, meats, fish, other)");
        String foodType = input.next();

        System.out.println("Enter food name");
        String name = input.next();

        System.out.println("Enter food calories per 100g");
        double calories = input.nextDouble();

        System.out.println("Enter food carbohydrates per 100g");
        double carbs = input.nextDouble();

        System.out.println("Enter the food fats per 100g");
        double fats = input.nextDouble();

        System.out.println("Enter the food proteins per 100g");
        double proteins = input.nextDouble();

        System.out.println("Enter the serving sizes in the format \"100g&100;1 glass&250\"");
        String servings = input.next();

        Macronutrients macros = new Macronutrients(calories, carbs, fats, proteins);

        switch (foodType) {
            case "diary":
                System.out.println("Does it have lactose (Y/N)?");
                foods.add(new Diary(name, macros, getServings(servings), input.next().equalsIgnoreCase("y")));
                break;
            case "beverages":
                System.out.println("Does it have lactose(Y/N)?");
                boolean hasProperty = input.next().equalsIgnoreCase("y");
                System.out.println("Enter caffeine per 100g");
                int extraProperty = input.nextInt();
                foods.add(new Beverage(name, macros, getServings(servings), hasProperty, extraProperty));
                break;
            case "cereals":
                System.out.println("Does it have gluten (Y/N)?");
                foods.add(new Cereal(name, macros, getServings(servings), input.next().equalsIgnoreCase("y")));
                break;
            case "meats":
                System.out.println("Enter the life quality of the animal 1-3 (worst to best).");
                foods.add(new Meat(name, macros, getServings(servings), input.nextInt()));
                break;
            case "fish":
                System.out.println("Does it have the sustainable fishing MSC label (Y/N)?");
                foods.add(new Fish(name, macros, getServings(servings), input.next().equalsIgnoreCase("y")));
                break;
            case "vegetables":
                System.out.println("Enter Y/N for each month from January to December to confirm the harvesting of the fruit/vegetable");
                boolean jan = input.next().equalsIgnoreCase("y");
                boolean feb = input.next().equalsIgnoreCase("y");
                boolean mar = input.next().equalsIgnoreCase("y");
                boolean apr = input.next().equalsIgnoreCase("y");
                boolean may = input.next().equalsIgnoreCase("y");
                boolean jun = input.next().equalsIgnoreCase("y");
                boolean jul = input.next().equalsIgnoreCase("y");
                boolean aug = input.next().equalsIgnoreCase("y");
                boolean sep = input.next().equalsIgnoreCase("y");
                boolean oct = input.next().equalsIgnoreCase("y");
                boolean nov = input.next().equalsIgnoreCase("y");
                boolean dec = input.next().equalsIgnoreCase("y");
                foods.add(new Vegetable(name, macros, getServings(servings), jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec));
                break;
            case "other":
                foods.add(new MiscFood(name, macros, getServings(servings)));
                break;
            default:
                System.out.println("Unknown food type, use \"other\" if the food does not belong to the standard classes." +
                        " Operation aborted.");
                return;
        }
        Food.printColumnHeaders();
        System.out.println(foods.get(foods.size() - 1) + "\nSuccessfully added");
    }

    //edit entries
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
            athleteMenu(input);
        }
    }

    public void editFood(Scanner input) {
        System.out.println("Enter food name");
        String name = input.next();

        int foodID = -1;
        int i = 0;
        for (Food food : foods) {
            if (food.getName().equals(name)) {
                foodID = i;
            }
            i++;
        }

        if (foodID >= 0) {
            Food.printColumnHeaders();
            System.out.println(foods.get(foodID));

            System.out.println("Enter attribute to edit as specified by the column header."
                    + "\nType \"Extra\" to edit the extra attributes not shown in the table."
                    + "\nType \"Servings\" to edit the serving sizes.");

            String selection = input.next();
            boolean edited = false;

            switch (selection) {
                case "Name":
                    System.out.println("Enter new name");
                    foods.get(foodID).setName(input.next());
                    edited = true;
                    break;
                case "Cals":
                    System.out.println("Enter new calories per 100g");
                    foods.get(foodID).getMacros().setCalories(input.nextDouble());
                    edited = true;
                    break;
                case "Carbs":
                    System.out.println("Enter new carbohydrates per 100g");
                    foods.get(foodID).getMacros().setCarbs(input.nextDouble());
                    edited = true;
                    break;
                case "Fats":
                    System.out.println("Enter new fats per 100g");
                    foods.get(foodID).getMacros().setFats(input.nextDouble());
                    edited = true;
                    break;
                case "Proteins":
                    System.out.println("Enter new proteins per 100g");
                    foods.get(foodID).getMacros().setProteins(input.nextDouble());
                    edited = true;
                    break;
                case "Extra":
                    boolean hasProperty;
                    switch (foods.get(foodID).getType()) {
                        case "diary":
                            System.out.println("Current lactose value: " + ((Diary) foods.get(foodID)).isHasLactose());
                            System.out.println("Does it have lactose (Y/N)?");
                            hasProperty = input.next().equalsIgnoreCase("y");
                            ((Diary) foods.get(foodID)).setHasLactose(hasProperty);
                            System.out.println("Lactose updated with: " + hasProperty);
                            return;
                        case "beverages":
                            System.out.println("Current lactose value: " + ((Beverage) foods.get(foodID)).isHasLactose());
                            System.out.println("Does it have lactose (Y/N)?");
                            hasProperty = input.next().equalsIgnoreCase("y");
                            ((Beverage) foods.get(foodID)).setHasLactose(hasProperty);
                            System.out.println("Lactose updated with: " + hasProperty);

                            System.out.println("Current caffeine value: " + ((Beverage) foods.get(foodID)).getCaffeine());
                            System.out.println("Enter new caffeine per 100g (int only)");
                            ((Beverage) foods.get(foodID)).setCaffeine(input.nextInt());
                            System.out.println("Caffeine updated with: " + ((Beverage) foods.get(foodID)).getCaffeine());
                            return;
                        case "cereals":
                            System.out.println("Current gluten value: " + ((Cereal) foods.get(foodID)).isHasGluten());
                            System.out.println("Does it have gluten (Y/N)?");
                            hasProperty = input.next().equalsIgnoreCase("y");
                            ((Cereal) foods.get(foodID)).setHasGluten(hasProperty);
                            System.out.println("Gluten updated with: " + hasProperty);
                            return;
                        case "meats":
                            System.out.println("Current life quality value: " + ((Meat) foods.get(foodID)).getLifeQuality());
                            System.out.println("Enter new life quality value 1-3 (worst to best)");
                            ((Meat) foods.get(foodID)).setLifeQuality(input.nextInt());
                            System.out.println("Life quality updated with: " + ((Meat) foods.get(foodID)).getLifeQuality());
                            return;
                        case "fish":
                            System.out.println("Current MSC label value: " + ((Fish) foods.get(foodID)).isSustainable());
                            System.out.println("Does it have the MSC label(Y/N)?");
                            hasProperty = input.next().equalsIgnoreCase("y");
                            ((Fish) foods.get(foodID)).setSustainable(hasProperty);
                            System.out.println("MSC label updated with: " + hasProperty);
                            return;
                        case "vegetables":
                            System.out.println("Enter Y/N for each month from January to December to confirm the harvesting of the fruit/vegetable");

                            Vegetable v = ((Vegetable) foods.get(foodID));

                            System.out.println("Current seasonality: " + v.displaySeasons());
                            System.out.println("Give a space separated of the 3 letter month code in which the food is on season");
                            String monthList = input.next();

                            v.setJan((monthList.contains("jan")));
                            v.setFeb((monthList.contains("feb")));
                            v.setMar((monthList.contains("mar")));
                            v.setApr((monthList.contains("apr")));
                            v.setMay((monthList.contains("may")));
                            v.setJun((monthList.contains("jun")));
                            v.setJul((monthList.contains("jul")));
                            v.setAug((monthList.contains("aug")));
                            v.setSep((monthList.contains("sep")));
                            v.setOct((monthList.contains("oct")));
                            v.setNov((monthList.contains("nov")));
                            v.setDec((monthList.contains("dec")));

                            System.out.println("Updated seasonality: " + v.displaySeasons());
                            return;
                        case "other":
                            System.out.println("There are no extra properties to edit");
                            return;
                    }
                case "Servings":
                    System.out.println("Current serving sizes:");
                    foods.get(foodID).getServings().stream().forEach(System.out::println);
                    System.out.println("Follow the string syntax and add/edit/remove servings at your will.");
                    System.out.println("The current string is: " +
                            foods.get(foodID).getServings()
                                    .stream().map(Serving::toCSV)
                                    .collect(Collectors.joining(";"))
                    );
                    String servings = input.next();
                    foods.get(foodID).setServings(getServings(servings));
                    System.out.println("Updated serving sizes with:");
                    foods.get(foodID).getServings().stream().forEach(System.out::println);
                    break;
                default:
                    System.out.println("Attribute not fond");
            }

            if (edited) {
                System.out.println("Food data updated:");
                Food.printColumnHeaders();
                System.out.println(foods.get(foodID));
            }

        } else {
            System.out.println("Food not found");
            foodMenu(input);
        }
    }

    //remove entries
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

    public void removeFood(Scanner input) {
        System.out.println("Enter food name");
        String name = input.next();

        int foodID = -1;
        int i = 0;
        for (Food food : foods) {
            if (food.getName().equals(name)) {
                foodID = i;
            }
            i++;
        }

        if (foodID >= 0) {
            Food.printColumnHeaders();
            System.out.println(foods.get(foodID) + "\nConfirm removal (Y/N)?");

            if (input.next().equalsIgnoreCase("Y")) {
                foods.remove(foodID);
                System.out.println("Food removed");
            }
            return;
        } else {
            System.out.println("Food not found");
        }
    }

    //write to files
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


    public List<Serving> getServings(String text) {
        List<Serving> servings = new ArrayList<>();
        Scanner sc = new Scanner(text);
        sc.useDelimiter(";");
        while (sc.hasNext()) {
            String pair = sc.next();
            String servingSize = pair.substring(0, pair.indexOf("&"));
            int grams = Integer.parseInt(pair.substring(pair.indexOf("&") + 1));
            servings.add(new Serving(servingSize, grams));
        }
        return servings;
    }

    public boolean save(Scanner input) {
        return writeAthletes(input); //AND AND AND...
    }
}

