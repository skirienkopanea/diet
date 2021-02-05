import java.util.List;

public class Cereal extends Food{
    private boolean hasGluten;

    public Cereal(String name, Macronutrients macros, List<Serving> servings, boolean hasGluten) {
        super(name, macros, servings);
        this.hasGluten = hasGluten;
    }

    @Override
    public String getType() {
        return "cereals";
    }

    public boolean isHasGluten() {
        return hasGluten;
    }

    public void setHasGluten(boolean hasGluten) {
        this.hasGluten = hasGluten;
    }

    public String toCsv(){
        return super.toCsv() + hasGluten + "\r\n";
    }
}
