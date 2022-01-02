package org.da0hn.account.core.usecases.impl;

import org.da0hn.account.core.domain.model.Account;
import org.da0hn.account.core.domain.service.Transfer;
import org.da0hn.account.core.ports.AccountRepository;
import org.da0hn.account.core.usecases.ports.TransferPort;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.Optional;

import static java.util.Objects.isNull;
import static org.da0hn.account.core.domain.model.ErrorMessage.mandatory;
import static org.da0hn.account.core.domain.model.ErrorMessage.noexistent;

@Named
public class TransferPortImpl implements TransferPort {

  private final AccountRepository repository;
  private final Transfer transfer;

  @Inject
  public TransferPortImpl(final AccountRepository repository, final Transfer transfer) {
    this.repository = repository;
    this.transfer = transfer;
  }

  @Override public Account getAccount(final Integer number) {
    return this.repository.get(number);
  }

  @Override
  @Transactional
  public void transfer(final BigDecimal amount, final Integer fromId, final Integer toId) {
    if(isNull(amount)) {
      mandatory("Valor");
    }
    if(isNull(fromId)) {
      mandatory("Conta débito");
    }
    if(isNull(toId)) {
      mandatory("Conta crédito");
    }

    final var accountFrom = this.getAccount(fromId, () -> noexistent("Conta débito"));
    final var accountTo = this.getAccount(toId, () -> noexistent("Conta crédito"));

    this.transfer.process(amount, accountFrom, accountTo);
  }

  private Account getAccount(final Integer number, final Runnable errorCallback) {
    final var maybeAccount = Optional.ofNullable(this.repository.get(number));
    if(maybeAccount.isEmpty()) {
      errorCallback.run();
    }
    return maybeAccount.get();
  }
}
