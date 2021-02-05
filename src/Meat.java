import java.util.List;

public class Meat extends Food{
    private int lifeQuality;

    public Meat(String name, Macronutrients macros, List<Serving> servings, int hasGluten) {
        super(name, macros, servings);
        this.lifeQuality = hasGluten;
    }

    @Override
    public Catalog.FoodType getEnum() {
        return Catalog.FoodType.MEATS;
    }

    public static String getCSVHeaders() {
        return Food.getCSVHeaders() + ",life quality\r\n";
    }

    public int getLifeQuality() {
        return lifeQuality;
    }

    public void setLifeQuality(int lifeQuality) {
        this.lifeQuality = lifeQuality;
    }

    public String toCsv(){
        return super.toCsv() + "," + lifeQuality + "\r\n";
    }
}
