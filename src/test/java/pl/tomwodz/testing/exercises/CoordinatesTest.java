package pl.tomwodz.testing.exercises;

import org.junit.jupiter.api.Test;
import pl.tomwodz.testing.exercises.Coordinates;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.MatcherAssert.assertThat;


class CoordinatesTest {

    @Test
    void constructorShouldFailIfXorYValueAbove100(){

        //given
        //when
        //then
        assertThrows(IllegalArgumentException.class,() -> new Coordinates(101,101));
    }

    @Test
    void constructorShouldFailIfXorYValueBelow0(){

        //given
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(-1,-1));
    }

    @Test
    void copyShouldReturnNewCoordinates(){

        //given
        Coordinates coordinates = new Coordinates(20,20);

        //when
        Coordinates copyCoordinates = Coordinates.copy(coordinates, 0,0);

        //then
        assertThat(copyCoordinates, not(sameInstance(coordinates)));
        assertThat(copyCoordinates,equalTo(coordinates));
    }

    @Test
    void copyShouldReturnAddCoordinates(){

        //given
        Coordinates coordinates = new Coordinates(20,20);

        //when
        Coordinates copyCoordinates = Coordinates.copy(coordinates, 10,10);

        //then
        assertThat(copyCoordinates.getX(), equalTo(30));
        assertThat(copyCoordinates.getY(), equalTo(30));
    }

}