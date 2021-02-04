# Diet plan manager

Java command line app to manage a catalog of athletes and their diet plans (stored locally as csv files). The main purpose of this app was just for me
to apply object-oriented programming principles and good practices.

## Athlete

An athlete class comprises the following private attributes:

* name
* age
* gender
* weight
* activity level
* daily caloric intake (calculated/manual)
* List of allergies
* diet plan

## Diet Plan

A diet plan is composed of:

* name
* List of daily meals
* Macronutrients and calories (calculated)
* Micronutrients (calculated)

### Macro/Micro nutrients

Macro and micro nutrients are -nested? (forgot the specific term)- classes, like using a Date class to store days,
months, years. I make use of a macro and micro class to avoid duplication and not define protiens, fats, carbs, etc. in
the classes "Food", "Meal", "DietPlan", etc. Respectively, they have the private attributes for the macro and
micro nutrients (limited to just the ones available in 'my fitness pal' food database). Sample food data is taken either
from usda.gov or myfitnesspal.com. The values correspond per 100g

## Daily meal

Daily meal class contains:

* Day number
* Breakfast meal
* Lunch meal
* Dinner meal
* Snack meal
* Macronutrients and calories (calculated)
* Micronutrients (calculated)

## Meal

A single meal contains:
* List of food and serving size pairs
* Macronutrients and calories (calculated)
* Micronutrients (calculated)

## Food (abstract and parent class)
Food is parent class for the food types (i.e. fruit) and is abstract as a non-classified food cannot exist.
A food object contains:
* Name
* Macronutrients and calories (calculated)
* Micronutrients (calculated)
* List of serving size and grams pairs

*Optinal fields*

* Organic boolean
* Price
* Country of origin
* Suppliers
* CO2 footprint
* Extended list of allergies

### Fruit and veggies
A fruit/veggie is a child class that inherits from Food, and offers the additional field:
* Season (boolean for each month)

### Cereals
Similarly, they contain:
* Gluten boolean

### Diary and beverages
Child of Food, also contains (respectively):
* Lactose boolean
* Caffeine

### Fish
Also contains:
* MSC boolean (sustainable fishing)

### Meat
* Life quality label (beter leven keurmerk/life quality of animal) 1 to 3

### Other
Does not contain extra fields but allows you to initialise a new food object.

The methods of the classes are mostly getters, setters, equals, to string, simple totals calculations, and input validations. Needless to say, class atributes are almost always private and the methods public (some are static, which regard the whole class rather than a specific object).
* The app allows you to see, add, edit and remove athletes from a csv file.
* Same operations for diet plans, meals, and foods.

## Improvement points
* Make a GUI
* Replace csv files for a database
