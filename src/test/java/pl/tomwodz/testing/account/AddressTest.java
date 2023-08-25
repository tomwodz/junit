package pl.tomwodz.testing.account;

import org.hamcrest.Matchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

class AddressTest {

    @ParameterizedTest
    @CsvSource({"Fabryczna, 10", "Armii Krajowej, 57/11", "'Romka, Tomka, Atonka', 40"})
    void givenAddressShouldNotBeEmptyAndHaveProperNames(String street, String number){
        assertThat(street, Matchers.notNullValue());
        assertThat(street, Matchers.containsString("a"));
        assertThat(number, Matchers.notNullValue());
        assertThat(number.length(), lessThan(8));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/addresses.csv")
    void addressFromFileShouldNotBeEmptyAndHaveProperNames(String street, String number){
        assertThat(street, Matchers.notNullValue());
        assertThat(street, Matchers.containsString("a"));
        assertThat(number, Matchers.notNullValue());
        assertThat(number.length(), lessThan(8));
    }


}
