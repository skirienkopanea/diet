import java.util.List;

public class Diary extends Food{
    private boolean hasLactose;

    public Diary(String name, Macronutrients macros, List<Serving> servings, boolean hasLactose) {
        super(name, macros, servings);
        this.hasLactose = hasLactose;
    }

    @Override
    public Catalog.FoodType getEnum() {
        return Catalog.FoodType.DIARY;
    }

    public static String getCSVHeaders() {
        return Food.getCSVHeaders() + ",lactose\r\n";
    }

    public boolean isHasLactose() {
        return hasLactose;
    }

    public void setHasLactose(boolean hasLactose) {
        this.hasLactose = hasLactose;
    }

    public String toCsv(){
        return super.toCsv() + "," + hasLactose + "\r\n";
    }
}
