import java.util.List;

public class Vegetable extends Food {
    private boolean jan;
    private boolean feb;
    private boolean mar;
    private boolean apr;
    private boolean may;
    private boolean jun;
    private boolean jul;
    private boolean aug;
    private boolean sep;
    private boolean oct;
    private boolean nov;
    private boolean dec;

    public Vegetable(String name, Macronutrients macros, List<Serving> servings, boolean jan, boolean feb, boolean mar, boolean apr, boolean may, boolean jun, boolean jul, boolean aug, boolean sep, boolean oct, boolean nov, boolean dec) {
        super(name, macros, servings);
        this.jan = jan;
        this.feb = feb;
        this.mar = mar;
        this.apr = apr;
        this.may = may;
        this.jun = jun;
        this.jul = jul;
        this.aug = aug;
        this.sep = sep;
        this.oct = oct;
        this.nov = nov;
        this.dec = dec;
    }

    public String toCsv() {
        return super.toCsv()
                + jan + ","
                + feb + ","
                + mar + ","
                + apr + ","
                + may + ","
                + jun + ","
                + jul + ","
                + aug + ","
                + sep + ","
                + oct + ","
                + nov + ","
                + dec + ","
                + "\r\n";
    }
}
