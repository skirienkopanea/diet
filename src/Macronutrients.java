public class Macronutrients {
    private int calories;
    private int carbs;
    private int fats;
    private int proteins;

    public Macronutrients(int calories, int carbs, int fats, int proteins) {
        this.calories = calories;
        this.carbs = carbs;
        this.fats = fats;
        this.proteins = proteins;
    }

    public Macronutrients(int carbs, int fats, int proteins) {
        this.carbs = carbs;
        this.fats = fats;
        this.proteins = proteins;
        this.calories = 4 * carbs + 4 * proteins + 9 * fats;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public int getFats() {
        return fats;
    }

    public void setFats(int fats) {
        this.fats = fats;
    }

    public int getProteins() {
        return proteins;
    }

    public void setProteins(int proteins) {
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
