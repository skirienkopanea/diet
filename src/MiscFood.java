import java.util.List;

public class MiscFood extends Food{

    public MiscFood(String name, Macronutrients macros, List<Serving> servings) {
        super(name, macros, servings);
    }

    @Override
    public Catalog.FoodType getEnum() {
        return Catalog.FoodType.OTHER;
    }

    public static String getCSVHeaders() {
        return Food.getCSVHeaders() + "\r\n";
    }

    public String toCsv(){
        return super.toCsv() + "\r\n";
    }
}
