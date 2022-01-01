package org.da0hn.account.adapters;

import org.da0hn.account.core.domain.model.Account;
import org.da0hn.account.core.domain.model.BusinessException;
import org.da0hn.account.core.ports.AccountRepository;

import javax.inject.Named;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

@Named
public class FakeAccountRepositoryAdapter implements AccountRepository {

  private final Map<Integer, Account> database = new HashMap<>();


  public FakeAccountRepositoryAdapter() {
    this.database.put(10, new Account(10, new BigDecimal(100), "Gabriel Fake"));
    this.database.put(20, new Account(20, new BigDecimal(100), "Nathally Fake"));
  }


  @Override public Account get(final Integer number) {
    System.out.println("Fake banco de dados -> Conta get(number)");
    return this.database.get(number);
  }

  @Override public void update(final Account account) {
    System.out.println("Fake banco de dados -> update()");
    final var accountFound = this.database.get(account.getNumber());
    ifAccountNotFoundThrowException(account, accountFound);
    this.database.put(account.getNumber(), account);
  }

  private static void ifAccountNotFoundThrowException(final Account account, final Account accountFound) {
    if(isNull(accountFound)) {
      throw new BusinessException("Conta inexistente: " + account.getNumber());
    }
  }
}
