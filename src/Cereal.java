import java.util.List;

public class Cereal extends Food{
    private boolean hasGluten;

    public Cereal(String name, Macronutrients macros, List<Serving> servings, boolean hasGluten) {
        super(name, macros, servings);
        this.hasGluten = hasGluten;
    }

    public String toCsv(){
        return super.toCsv() + hasGluten + "\r\n";
    }
}
