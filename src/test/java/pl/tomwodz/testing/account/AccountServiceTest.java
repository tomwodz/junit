package pl.tomwodz.testing.account;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class AccountServiceTest {

    @Test
    void getAllActiveAccounts() {

        //given
        List<Account> accounts = prepareAccountDate();
        AccountRepository accountRepository = mock(AccountRepository.class);
        AccountService accountService = new AccountService(accountRepository);
        given(accountRepository.getAllAccounts()).willReturn(accounts);

        //when
        List<Account> accountList = accountService.getAllActiveAccounts();

        //then
        assertThat(accountList, Matchers.hasSize(2));

    }

    @Test
    void geNotActiveAccounts() {

        //given
        AccountRepository accountRepository = mock(AccountRepository.class);
        AccountService accountService = new AccountService(accountRepository);
        given(accountRepository.getAllAccounts()).willReturn(Collections.emptyList());

        //when
        List<Account> accountList = accountService.getAllActiveAccounts();

        //then
        assertThat(accountList, Matchers.hasSize(0));

    }

    private List<Account> prepareAccountDate(){
        Address address1 = new Address("Kolorowa","33");
        Account account1 = new Account(address1);

        Account account2 = new Account();

        Address address3 = new Address("Wąska","5");
        Account account3 = new Account(address3);

        return Arrays.asList(account1,account2,account3);
    }
}
