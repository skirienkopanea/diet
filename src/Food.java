import java.util.List;
import java.util.stream.Collectors;

public abstract class Food {
    private String name;
    private Macronutrients macros;
    //private Micronutrients micros;
    private List<Serving> servings;

    public Food(String name, Macronutrients macros, List<Serving> servings) {
        this.name = name;
        this.macros = macros;
        this.servings = servings;
    }

    public abstract String getType(); //enforce implementation at child level

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Macronutrients getMacros() {
        return macros;
    }

    public void setMacros(Macronutrients macros) {
        this.macros = macros;
    }

    public List<Serving> getServings() {
        return servings;
    }

    public void setServings(List<Serving> servings) {
        this.servings = servings;
    }

    //Reason I have it here in the class as static and not in catalog is because I want to keep
    //the column headers close to the actual attributes of the class and their toString method
    public static void printColumnHeaders(){
        System.out.println("Name              Cals    Carbs   Fats    Proteins");
        System.out.println("-----------------|-------|-------|-------|--------");
    }

    @Override
    public String toString() {
        return Catalog.displayName(name) + " | "
                + Math.round(macros.getCalories()) + "\t | "
                + Math.round(macros.getCarbs()) + "\t | "
                + Math.round(macros.getFats()) + "\t | "
                + Math.round(macros.getProteins()) + "\t | ";
    }

    public String toCsv(){
        return name + "," + macros.getCalories() + "," + macros.getCarbs() + "," + macros.getFats() + "," + macros.getProteins() + ","
                + servings.stream().map(serving -> serving.toCSV())
                .collect(Collectors.joining(";"));
    }
}
