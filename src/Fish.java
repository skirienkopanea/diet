import java.util.List;

public class Fish extends Food{
    private boolean isSustainable;

    public Fish(String name, Macronutrients macros, List<Serving> servings, boolean hasGluten) {
        super(name, macros, servings);
        this.isSustainable = hasGluten;
    }

    public String toCsv(){
        return super.toCsv() + isSustainable + "\r\n";
    }
}
