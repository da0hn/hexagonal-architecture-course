package org.gabriel.account.core.ports;

import org.gabriel.account.core.domain.model.Account;

public interface AccountRepository {

  Account get(Integer number);

  void update(Account account);

}
