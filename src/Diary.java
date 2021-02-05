import java.util.List;

public class Diary extends Food{
    private boolean hasLactose;

    public Diary(String name, Macronutrients macros, List<Serving> servings, boolean hasLactose) {
        super(name, macros, servings);
        this.hasLactose = hasLactose;
    }

    @Override
    public String getType() {
        return "diary";
    }

    public boolean isHasLactose() {
        return hasLactose;
    }

    public void setHasLactose(boolean hasLactose) {
        this.hasLactose = hasLactose;
    }

    public String toCsv(){
        return super.toCsv() + hasLactose + "\r\n";
    }
}
