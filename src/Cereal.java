import java.util.List;

public class Cereal extends Food{
    private boolean hasGluten;

    public Cereal(String name, Macronutrients macros, List<Serving> servings, boolean hasGluten) {
        super(name, macros, servings);
        this.hasGluten = hasGluten;
    }

    @Override
    public Catalog.FoodType getEnum() {
        return Catalog.FoodType.CEREALS;
    }

    public static String getCSVHeaders() {
        return Food.getCSVHeaders() + ",gluten\r\n";
    }

    public boolean isHasGluten() {
        return hasGluten;
    }

    public void setHasGluten(boolean hasGluten) {
        this.hasGluten = hasGluten;
    }

    public String toCsv(){
        return super.toCsv() + "," + hasGluten + "\r\n";
    }
}
