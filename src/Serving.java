public class Serving<Serving,Gram> {
    private Serving servingSize;
    private Gram grams;

    public Serving(Serving servingSize, Gram grams) {
        this.servingSize = servingSize;
        this.grams = grams;
    }

    public String getServingSize() {
        return (String) servingSize;
    }

    public void setServingSize(Serving servingSize) {
        this.servingSize = servingSize;
    }

    public Integer getGrams() {
        return (int) grams;
    }

    public void setGrams(Gram grams) {
        this.grams = grams;
    }

    @Override
    public String toString() {
        return servingSize + " (" + grams + "g)";
    }

    public String toCSV(){
        return servingSize + "&" + grams;
    }
}
