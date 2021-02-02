import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Catalog catalog = new Catalog();
        catalog.readAthletes();
        catalog.readAthletes();
        catalog.readFoods();
        catalog.readMeals();

        Scanner input = new Scanner(System.in);
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
                    //catalog.foodMenu(input);
                    break;

                case "5":
                    catalog.save(); //update files before closing app
                    return;
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
                5 – Stop program
                """);
    }
}
