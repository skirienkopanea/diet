import java.util.List;

public class MiscFood extends Food{

    public MiscFood(String name, Macronutrients macros, List<Serving> servings) {
        super(name, macros, servings);
    }

    public String toCsv(){
        return super.toCsv() + "\r\n";
    }
}
