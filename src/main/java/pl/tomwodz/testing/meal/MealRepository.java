package pl.tomwodz.testing.meal;

import java.util.ArrayList;
import java.util.List;

public class MealRepository {

    private List<Meal> meals = new ArrayList<>();

    public void add(Meal meal) {
        this.meals.add(meal);
    }

    public List<Meal> getAllMeals() {
        return meals;
    }

    public void delete(Meal meal) {
        this.meals.remove(meal);
    }

    public List<Meal> findByName(String mealName, boolean exactMatch) {

        List<Meal> result;
        if(exactMatch){
            result = this.meals.stream()
                    .filter(meal -> meal.getName().equals(mealName))
                    .toList();
        } else {
            result = this.meals.stream()
                    .filter(meal -> meal.getName().startsWith(mealName))
                    .toList();
        }
        return result;
    }

    public List<Meal> findByPrice(int price, SearchType type) {
        return findByPriceWithInitialData(price,type, meals);
    }

    public List<Meal> find(String name, boolean exactName, int price, SearchType searchTypePrice) {

        List<Meal> nameMatches = findByName(name, exactName);

        List<Meal> result = findByPriceWithInitialData(price, searchTypePrice, nameMatches);

        return result;
    }

    private List<Meal> findByPriceWithInitialData(int price, SearchType type, List<Meal> meals) {

        List<Meal> result = new ArrayList<>();

        switch (type) {
            case EXACT:
                result = meals.stream()
                        .filter(meal -> meal.getPrice() == price)
                        .toList();
                break;
            case LESS:
                result = meals.stream()
                        .filter(meal -> meal.getPrice() < price)
                        .toList();
                break;
            case MORE:
                result = meals.stream()
                        .filter(meal -> meal.getPrice() > price)
                        .toList();
                break;
        }

        return result;
    }


}
