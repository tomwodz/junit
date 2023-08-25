package pl.tomwodz.testing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@ExtendWith(BeforeAfterExtension.class)
class OrderTest {

    private Order order;
    @BeforeEach
    void initializerOrder(){
        System.out.println("Inside @BeforeEach method");
        order = new Order();
    }

    @AfterEach
    void cleanUp(){
        System.out.println("Inside @AfterEach method");
        order.cancel();
    }

    @Test
    void testAssertArrayEquals(){

        //given
        int[] ints1 = {1,2,3};
        int[] ints2 = {1,2,3};

        //then
        assertArrayEquals(ints1, ints2);

    }

    @Test
    void mealListShouldBeEmptyAfterCreationOfOrder(){

        //given

        //then
        assertThat(order.getMeals(), empty());
        assertThat(order.getMeals().size(), equalTo(0));
        assertThat(order.getMeals(), hasSize(0));
        assertThat(order.getMeals(), emptyCollectionOf(Meal.class));

    }

    @Test
    void addingMealToOrderShouldIncreaseOrderSize(){

        //given
        Meal meal = new Meal(15, "Burger");
        Meal meal2 = new Meal(5, "Kanapka");

        //when
        order.addMealToOrder(meal);

        //then
        assertThat(order.getMeals(), hasSize(1));
        assertThat(order.getMeals(), contains(meal));
        assertThat(order.getMeals(), hasItem(meal));
        assertThat(order.getMeals().get(0).getPrice(), equalTo(15));
    }

    @Test
    void removingMealFromOrderShouldDecreaseOrderSize(){
        //given
        Meal meal = new Meal(15, "Burger");

        //when
        order.addMealToOrder(meal);
        order.removeMealFromOrder(meal);

        //then
        assertThat(order.getMeals(),hasSize(0));
        assertThat(order.getMeals(),not(contains(meal)));
    }

    @Test
    void mealsShouldBeIncorrectOrderAfterAddingThemToOrder(){

        //given
        Meal meal1 = new Meal(15, "Burger");
        Meal meal2 = new Meal(5, "Kanapka");

        //when
        order.addMealToOrder(meal1);
        order.addMealToOrder(meal2);

        //then
        assertThat(order.getMeals(),contains(meal1,meal2));
        assertThat(order.getMeals(), containsInAnyOrder(meal1, meal2));

    }

    @Test
    void testIfTwoMealListsAreTheSame(){

        //given
        Meal meal1 = new Meal(15, "Burger");
        Meal meal2 = new Meal(5, "Kanapka");
        Meal meal3 = new Meal(20, "Kebab");

        List<Meal> meals1 = Arrays.asList(meal1, meal2);
        List<Meal> meals2 = Arrays.asList(meal1, meal2);

        //then
        assertThat(meals1, is(meals2));

    }

}