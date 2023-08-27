package pl.tomwodz.testing.exercises;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class UnitServiceTest {

    @InjectMocks
    private UnitService unitService;

    @Mock
    private CargoRepository cargoRepository;

    @Mock
    private UnitRepository unitRepository;

    private Unit unit;
    @BeforeEach
    void initializerUnit() {
        unit = new Unit(new Coordinates(0,0),10,10);
    }

    @Test
    void addedCargoShouldBeLoadedOnUnit(){

        //given
        Cargo cargo = new Cargo("cargo1", 4);

        given(cargoRepository.findCargoByName("cargo1")).willReturn(Optional.of(cargo));

        //when
        unitService.addCargoByName(unit, "cargo1");

        //then
        then(cargoRepository.findCargoByName("cargo1"));
        assertThat(unit.getLoad(), is(4));
        assertThat(unit.getCargo().get(0), equalTo(cargo));

    }

    @Test
    void shouldThrowExceptionIfNoCargoIsFoundToAdd(){

        //given
        given(cargoRepository.findCargoByName("cargo1")).willReturn(Optional.empty());

        //when
        //then
        assertThrows(NoSuchElementException.class, () -> unitService.addCargoByName(unit, "cargo1"));

    }

    @Test
    void shouldReturnUnitByCoordinates(){

        //given
        given(unitRepository.getUnitByCoordinates(new Coordinates(0,0))).willReturn(unit);

        //when
        Unit result = unitService.getUnitOn(new Coordinates(0,0));

        //then
        assertThat(result, sameInstance(unit));

    }

    @Test
    void shouldReturnExceptionIfUnitNotFound() {
        //given
        given(unitRepository.getUnitByCoordinates(new Coordinates(0,0))).willReturn(null);

        //when
        //then
        assertThrows(NoSuchElementException.class, () -> unitService.getUnitOn(new Coordinates(0,0)));
    }

}