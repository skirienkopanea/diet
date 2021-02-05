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

    @Override
    public Catalog.FoodType getEnum() {
        return Catalog.FoodType.VEGETABLES;
    }

    public static String getCSVHeaders() {
        return Food.getCSVHeaders() + ",jan,feb,mar,apr,may,jun,jul,aug,sep,oct,nov,dec\r\n";
    }


    public boolean isJan() {
        return jan;
    }

    public void setJan(boolean jan) {
        this.jan = jan;
    }

    public boolean isFeb() {
        return feb;
    }

    public void setFeb(boolean feb) {
        this.feb = feb;
    }

    public boolean isMar() {
        return mar;
    }

    public void setMar(boolean mar) {
        this.mar = mar;
    }

    public boolean isApr() {
        return apr;
    }

    public void setApr(boolean apr) {
        this.apr = apr;
    }

    public boolean isMay() {
        return may;
    }

    public void setMay(boolean may) {
        this.may = may;
    }

    public boolean isJun() {
        return jun;
    }

    public void setJun(boolean jun) {
        this.jun = jun;
    }

    public boolean isJul() {
        return jul;
    }

    public void setJul(boolean jul) {
        this.jul = jul;
    }

    public boolean isAug() {
        return aug;
    }

    public void setAug(boolean aug) {
        this.aug = aug;
    }

    public boolean isSep() {
        return sep;
    }

    public void setSep(boolean sep) {
        this.sep = sep;
    }

    public boolean isOct() {
        return oct;
    }

    public void setOct(boolean oct) {
        this.oct = oct;
    }

    public boolean isNov() {
        return nov;
    }

    public void setNov(boolean nov) {
        this.nov = nov;
    }

    public boolean isDec() {
        return dec;
    }

    public void setDec(boolean dec) {
        this.dec = dec;
    }

    public String toCsv() {
        return super.toCsv() + ","
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
                + dec + "\r\n";
    }

    public String displaySeasons() {
        String displaySeasons = "   "
                + (isJan()? "jan, " : "")
                + (isFeb()? "feb, " : "")
                + (isMar()? "mar, " : "")
                + (isApr()? "apr, " : "")
                + (isMay()? "may, " : "")
                + (isJun()? "jun, " : "")
                + (isJul()? "jul, " : "")
                + (isAug()? "aug, " : "")
                + (isSep()? "sep, " : "")
                + (isOct()? "oct, " : "")
                + (isNov()? "nov, " : "")
                + (isDec()? "dec, " : "");
        return displaySeasons.substring(0,displaySeasons.length()-2);
    }
}
