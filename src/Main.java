import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Catalog catalog = new Catalog();
        catalog.readAthletes();
        catalog.readFoods();
        catalog.readMeals(); //i.e. (vegan breakfast, vegan lunch...)(summer season)
        catalog.readDietPlans(); //i.e. (normal, pescatarian, vegetarian, vegan, gluten-free, lactose-free)(bulk, cut, maintain).

        Scanner input = new Scanner(System.in);
        input.useDelimiter(",|\n");
        String choice;
        do {
            printMainMenu();
            choice = input.next();
            switch (choice) {
                case "1":
                    catalog.showAthletes();
                    catalog.athleteMenu(input);
                    break;

                case "2":
                    catalog.showDietPlans();
                    //catalog.dietPlanMenu(input);
                    break;

                case "3":
                    catalog.showMeals();
                    //catalog.mealMenu(input);
                    break;

                case "4":
                    catalog.showFoods();
                    catalog.foodMenu(input);
                    break;

                case "5":
                    if (catalog.save(input)) {
                        return; //saved files and close or not saved and close without saving
                    } else {
                        break; //not saved and do not close
                    }
                default:
                    System.out.println("Option unknown");
            }
        } while (true);
    }

    public static void printMainMenu() {
        System.out.println("""
                Please make your choice:
                1 – Show athletes
                2 – Show diet plans
                3 – Show meals
                4 – Show foods
                5 – Stop program""");
    }
}
