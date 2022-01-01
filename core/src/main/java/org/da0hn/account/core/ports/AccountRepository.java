package org.da0hn.account.core.ports;

import org.da0hn.account.core.domain.model.Account;

public interface AccountRepository {

  Account get(Integer number);

  void update(Account account);

}
