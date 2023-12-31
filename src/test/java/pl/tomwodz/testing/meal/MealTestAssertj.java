package pl.tomwodz.testing.meal;

import org.junit.jupiter.api.Test;
import pl.tomwodz.testing.meal.Meal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class MealTestAssertj {

    @Test
    void shouldReturnDiscountedPrice() {

        //given
        Meal meal = new Meal(100);

        //when
        int discountedPrice = meal.getDiscountedPrice(10);

        //then
        assertEquals(90,discountedPrice);
        assertThat(discountedPrice).isEqualTo(90);
    }

    @Test
    void referencesToTheSameObjectShouldBeEqual() {

        //given
        Meal meal1 = new Meal(10);
        Meal meal2 = meal1;

        //then
        assertSame(meal1, meal2);
        assertThat(meal1).isSameAs(meal2);
    }

    @Test
    void referencesToTheDifferentObjectShouldBeEqual() {

        //given
        Meal meal1 = new Meal(10);
        Meal meal2 = new Meal(20);

        //then
        assertNotSame(meal1, meal2);
        assertThat(meal1).isNotSameAs(meal2);
    }

    @Test
    void twoMealsShouldBeEqualWhenPriceAndNameAreTheSame(){

        //given
        Meal meal1 = new Meal(10, "Ciastko");
        Meal meal2 = new Meal(10, "Ciastko");

        //then
        assertEquals(meal1,meal2,"Checking if two meals are equal");
        assertThat(meal1).isEqualTo(meal2);
    }

    @Test
    void exceptionShouldBeThrownIfDiscountIsHigherThanThePrice(){

        //given
        Meal meal = new Meal(8,"Soup");

        //when
        //then
        assertThrows(IllegalArgumentException.class,() -> meal.getDiscountedPrice(100));
    }

}