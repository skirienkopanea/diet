import java.util.List;

public class Fish extends Food{
    private boolean isSustainable;

    public Fish(String name, Macronutrients macros, List<Serving> servings, boolean hasGluten) {
        super(name, macros, servings);
        this.isSustainable = hasGluten;
    }

    @Override
    public String getType() {
        return "fish";
    }

    public boolean isSustainable() {
        return isSustainable;
    }

    public void setSustainable(boolean sustainable) {
        isSustainable = sustainable;
    }

    public String toCsv(){
        return super.toCsv() + isSustainable + "\r\n";
    }
}
