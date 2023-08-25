package pl.tomwodz.testing.account;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;


@Tag("fries")
class AccountTest {

    @Test
    void newAccountShouldNotBeActiveAfterCreation(){
        //given
        //when
        Account newAccount = new Account();
        //then
        assertFalse(newAccount.isActive(),"Check if new account is not active");
        assertThat(newAccount.isActive(),equalTo(false));
        assertThat(newAccount.isActive(),is(false));
    }

    @Test
    void accountShouldBrActiveAfterActivation(){
        //given
        Account newAccount = new Account();

        //when
        newAccount.active();

        //then
        assertTrue(newAccount.isActive());
        assertThat(newAccount.isActive(), equalTo(true));
    }

    @Test
    void newlyCreatedAccountShouldNotHaveDefaultDeliveryAddressSet() {

        //given
        Account account = new Account();

        //when
        Address address = account.getDefaultDeliveryAddress();

        //then
        assertNull(address);
        assertThat(address, nullValue());

    }

    @Test
    void defaultDeliveryAddressShouldNotBuNullAfterBeginSet() {

        //given
        Address address = new Address("Programistów", "77c");
        Account account = new Account();
        account.setDefaultDeliveryAddress(address);

        //when
        Address defaultAddress = account.getDefaultDeliveryAddress();

        //then
        assertNotNull(defaultAddress);
        assertThat(defaultAddress, is(notNullValue()));

    }

    @RepeatedTest(5)
    void newAccountWithNotNullAddressShouldByActive(){

        //given
        Address address = new Address("Wiśniowa","77");

        //when
        Account account = new Account(address);

        //then
        assumingThat(address != null, () -> {
            assertTrue(account.isActive());
        });

    }

    @Test //Conformance
    void invalidEmailShouldBeThrowException(){

        //given
        Account account = new Account();

        //when
        //then
        assertThrows(IllegalArgumentException.class, ()-> account.setEmail("wrong"));
    }

    @Test
    void validEmailShouldBeSet(){
        //given
        Account account = new Account();

        //when
        account.setEmail("test@test.pl");

        //then
        assertThat(account.getEmail(), is("test@test.pl"));
 }

}
