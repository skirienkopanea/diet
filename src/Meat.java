import java.util.List;

public class Meat extends Food{
    private int lifeQuality;

    public Meat(String name, Macronutrients macros, List<Serving> servings, int hasGluten) {
        super(name, macros, servings);
        this.lifeQuality = hasGluten;
    }

    public String toCsv(){
        return super.toCsv() + lifeQuality + "\r\n";
    }
}
