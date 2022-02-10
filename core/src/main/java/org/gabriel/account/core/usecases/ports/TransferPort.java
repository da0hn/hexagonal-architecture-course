package org.gabriel.account.core.usecases.ports;

import org.gabriel.account.core.domain.model.Account;

import java.math.BigDecimal;

public interface TransferPort {

  Account getAccount(Integer number);

  void transfer(BigDecimal amount, Integer fromId, Integer toId);

}
