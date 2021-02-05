import java.util.List;

public class Beverage extends Food {
    private boolean hasLactose;
    private int caffeine;

    public Beverage(String name, Macronutrients macros, List<Serving> servings, boolean hasLactose, int caffeine) {
        super(name, macros, servings);
        this.hasLactose = hasLactose;
        this.caffeine = caffeine;
    }

    @Override
    public Catalog.FoodType getEnum() {
        return Catalog.FoodType.BEVERAGES;
    }

    public static String getCSVHeaders() {
        return Food.getCSVHeaders() + ",lactose,caffeine\r\n";
    }

    public boolean isHasLactose() {
        return hasLactose;
    }

    public void setHasLactose(boolean hasLactose) {
        this.hasLactose = hasLactose;
    }

    public int getCaffeine() {
        return caffeine;
    }

    public void setCaffeine(int caffeine) {
        this.caffeine = caffeine;
    }

    public String toCsv() {
        return super.toCsv() + "," + hasLactose + "," + caffeine + "\r\n";
    }
}
