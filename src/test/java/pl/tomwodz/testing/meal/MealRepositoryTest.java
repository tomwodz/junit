package pl.tomwodz.testing.meal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class MealRepositoryTest {

    MealRepository mealRepository = new MealRepository();

    @BeforeEach
    void cleanUp(){
        mealRepository.getAllMeals().clear();
    }

    @Test
    void shouldBrAbleToAddMealToRepository(){

        //given
        Meal meal = new Meal(20,"Pizza");

        //when
        mealRepository.add(meal);

        //then
        assertThat(mealRepository.getAllMeals().get(0), is(meal));

    }

    @Test
    void shouldBeAbleToRemoveFromRepository() {

        //given
        Meal meal = new Meal(20,"Pizza");
        mealRepository.add(meal);

        //when
        mealRepository.delete(meal);

        //then
        assertThat(mealRepository.getAllMeals(), not(contains(meal)));

    }

    @Test
    void shouldBeAbleToFindByExactName(){

        //given
        Meal meal = new Meal(20,"Pizza");
        Meal meal2 = new Meal(20,"Pi");
        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.findByName("Pizza", true);

        //then
        assertThat(results.size(), is(1));
    }

    @Test
    void shouldBeAbleToFineMealByStartingLetters(){
        //given
        Meal meal = new Meal(20,"Pizza");
        Meal meal2 = new Meal(20,"Pi");
        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.findByName("P", false);

        //then
        assertThat(results.size(), is(2));
    }

    @Test
    void shouldBeAbleToFindMealByExactPrice(){
        //given
        Meal meal = new Meal(20,"Pizza");
        Meal meal2 = new Meal(15,"Kebab");
        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.findByPrice(20, SearchType.EXACT);

        //then
        assertThat(results.size(), is(1));
        assertThat(results.get(0), is(meal));
    }

    @Test
    void shouldBeAbleToFindMealByLessPrice(){
        //given
        Meal meal = new Meal(20,"Pizza");
        Meal meal2 = new Meal(15,"Kebab");
        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.findByPrice(20, SearchType.LESS);

        //then
        assertThat(results.size(), is(1));
        assertThat(results.get(0), is(meal2));

    }

    @Test
    void shouldBeAbleToFindMealByHigherPrice(){
        //given
        Meal meal = new Meal(20,"Pizza");
        Meal meal2 = new Meal(15,"Kebab");
        Meal meal3 = new Meal(25,"Zapiekanka");
        mealRepository.add(meal);
        mealRepository.add(meal2);
        mealRepository.add(meal3);

        //when
        List<Meal> results = mealRepository.findByPrice(20, SearchType.MORE);

        //then
        assertThat(results.size(), is(1));
        assertThat(results.get(0), is(meal3));

    }

    @Test
    void shouldFindByExactNameAndExactPrice(){
        //given
        Meal meal = new Meal(20,"Pizza");
        Meal meal2 = new Meal(15,"Kebab");
        Meal meal3 = new Meal(25,"Zapiekanka");
        mealRepository.add(meal);
        mealRepository.add(meal2);
        mealRepository.add(meal3);

        //when
        List<Meal> results = mealRepository.find("Pizza", true, 20, SearchType.EXACT);

        //then
        assertThat(results.size(), is(1));
        assertThat(results.get(0), is(meal));

    }

    @Test
    void shouldFindByFirstLetterAndExactPrice(){
        //given
        Meal meal = new Meal(20,"Pizza");
        Meal meal2 = new Meal(15,"Kebab");
        Meal meal3 = new Meal(25,"Zapiekanka");
        mealRepository.add(meal);
        mealRepository.add(meal2);
        mealRepository.add(meal3);

        //when
        List<Meal> results = mealRepository.find("Pi", false, 20, SearchType.EXACT);

        //then
        assertThat(results.size(), is(1));
        assertThat(results.get(0), is(meal));

    }

    @Test
    void shouldFindByExactNameAndLowerPrice(){
        //given
        Meal meal = new Meal(20,"Pizza");
        Meal meal2 = new Meal(15,"Kebab");
        Meal meal3 = new Meal(25,"Zapiekanka");
        mealRepository.add(meal);
        mealRepository.add(meal2);
        mealRepository.add(meal3);

        //when
        List<Meal> results = mealRepository.find("Pizza", true, 20, SearchType.EXACT);

        //then
        assertThat(results.size(), is(1));
        assertThat(results.get(0), is(meal));

    }

    @Test
    void shouldFindByExactNameAndHigherPrice(){
        //given
        Meal meal = new Meal(20,"Pizza");
        Meal meal2 = new Meal(15,"Kebab");
        Meal meal3 = new Meal(25,"Zapiekanka");
        mealRepository.add(meal);
        mealRepository.add(meal2);
        mealRepository.add(meal3);

        //when
        List<Meal> results = mealRepository.find("Pizza", true, 19, SearchType.MORE);

        //then
        assertThat(results.size(), is(1));
        assertThat(results.get(0), is(meal));

    }

    @Test
    void shouldFindByFirstLetterAndLowerPrice(){
        //given
        Meal meal = new Meal(20,"Pizza");
        Meal meal2 = new Meal(15,"Kebab");
        Meal meal3 = new Meal(25,"Zapiekanka");
        mealRepository.add(meal);
        mealRepository.add(meal2);
        mealRepository.add(meal3);

        //when
        List<Meal> results = mealRepository.find("Ke", false, 20, SearchType.LESS);

        //then
        assertThat(results.size(), is(1));
        assertThat(results.get(0), is(meal2));

    }

    @Test
    void shouldFindByFirstLetterAndHigherPrice(){
        //given
        Meal meal = new Meal(20,"Pizza");
        Meal meal2 = new Meal(15,"Kebab");
        Meal meal3 = new Meal(25,"Zapiekanka");
        mealRepository.add(meal);
        mealRepository.add(meal2);
        mealRepository.add(meal3);

        //when
        List<Meal> results = mealRepository.find("Ke", false, 14, SearchType.MORE);

        //then
        assertThat(results.size(), is(1));
        assertThat(results.get(0), is(meal2));

    }



}
