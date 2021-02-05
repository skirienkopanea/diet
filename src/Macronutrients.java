public class Macronutrients {
    private double calories;
    private double carbs;
    private double fats;
    private double proteins;

    public Macronutrients(double calories, double carbs, double fats, double proteins) {
        this.calories = calories;
        this.carbs = carbs;
        this.fats = fats;
        this.proteins = proteins;
    }

    public Macronutrients(double carbs, double fats, double proteins) {
        this.carbs = carbs;
        this.fats = fats;
        this.proteins = proteins;
        this.calories = 4 * carbs + 4 * proteins + 9 * fats;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    @Override
    public String toString() {
        return "Macronutrients{" +
                "calories=" + calories +
                ", carbs=" + carbs +
                ", fats=" + fats +
                ", proteins=" + proteins +
                '}';
    }
}
