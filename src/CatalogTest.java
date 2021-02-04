import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CatalogTest {
    String servings;
    @BeforeEach
    void setUp() {
    servings = "1 glass&250;1 bottle&1000";
    }

    @Test
    void getServings() {

        Serving serving = new Serving("1 glass",250);
        assertEquals("1 glass&250;1 bottle&1000",servings);
    }

    @Test
    void displayName() {
    }

    @Test
    void showAthletes() {
    }

    @Test
    void showDietPlans() {
    }

    @Test
    void showMeals() {
    }

    @Test
    void showFoods() {
    }

    @Test
    void athleteMenu() {
    }

    @Test
    void readAthletes() {
    }

    @Test
    void addAthlete() {
    }

    @Test
    void editAthlete() {
    }

    @Test
    void removeAthlete() {
    }

    @Test
    void writeAthletes() {
    }

    @Test
    void readDietPlans() {
    }

    @Test
    void readMeals() {
    }

    @Test
    void readFoods() {
    }

    @Test
    void save() {
    }
}