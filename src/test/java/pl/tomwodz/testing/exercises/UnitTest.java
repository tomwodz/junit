package pl.tomwodz.testing.exercises;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.tomwodz.testing.exercises.Cargo;
import pl.tomwodz.testing.exercises.Coordinates;
import pl.tomwodz.testing.exercises.Unit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class UnitTest {

    private Unit unit;
    @BeforeEach
    void initializerUnit() {
        unit = new Unit(new Coordinates(0,0),10,10);
    }


    @Test
    void unitShouldNotMoveWithoutFuel() {

        //given

        //when

        //then
        assertThrows(IllegalStateException.class, () -> unit.move(20,10));
    }

    @Test
    void unitShouldReduceFuelWhenMoving () {

        //given

        //when
        unit.move(4,4);

        //then
        assertThat(unit.getFuel(), is(2));
    }

    @Test
    void movedUnitShouldReturnNewCoordinates() {

        //given

        //when
        Coordinates move = unit.move(4,4);

        //then
        assertThat(move, equalTo(new Coordinates(4,4)));

    }

    @Test
    void fuelingShouldNotExceedMaxFuelLimit() {

        //given

        //when
        unit.tankUp();

        //then
        assertThat(unit.getFuel(), is(10));

    }

    @Test
    void cargoCanNotExceedMAxWeightLimit() {

        //given
        Cargo cargo1 = new Cargo("cargo1", 4);
        Cargo cargo2 = new Cargo("cargo2", 12);

        //when
        unit.loadCargo(cargo1);

        //then
        assertThrows(IllegalStateException.class, () -> unit.loadCargo(cargo2));
    }

    @Test
    void unloadingAllCargoShouldReduceUnitLoadToZero() {

        //given
        Cargo cargo1 = new Cargo("cargo1", 4);
        Cargo cargo2 = new Cargo("cargo2", 6);

        unit.loadCargo(cargo1);
        unit.loadCargo(cargo2);

        //when
        unit.unloadAllCargo();

        //then
        assertThat(unit.getLoad(), is(0));
    }
}