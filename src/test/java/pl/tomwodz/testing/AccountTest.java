package pl.tomwodz.testing;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;


class AccountTest {

    @Test
    void newAccountShouldNotBeActiveAfterCreation(){
        //given
        //when
        Account newAccount = new Account();
        //then
        assertFalse(newAccount.isActive(),"Check if new account is not active");
        assertThat(newAccount.isActive()).isFalse();
    }

    @Test
    void accountShouldBrActiveAfterActivation(){
        //given
        Account newAccount = new Account();

        //when
        newAccount.active();

        //then
        assertTrue(newAccount.isActive());
        assertThat(newAccount.isActive()).isTrue();
    }

    @Test
    void newlyCreatedAccountShouldNotHaveDefaultDeliveryAddressSet() {

        //given
        Account account = new Account();

        //when
        Address address = account.getDefaultDeliveryAddress();

        //then
        assertNull(address);
        assertThat(address).isNull();

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
        assertThat(defaultAddress).isNotNull();

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

}
