package org.gabriel.account.core.domain.service;

import org.gabriel.account.core.domain.model.Account;

import javax.inject.Named;
import java.math.BigDecimal;

import static java.util.Objects.isNull;
import static org.gabriel.account.core.domain.model.ErrorMessage.mandatory;

@Named
public class Transfer {

  public void process(final BigDecimal amount, final Account from, final Account to) {
    if(isNull(amount)) {
      mandatory("Valor de transferência");
    }
    if(isNull(from)) {
      mandatory("Conta débito");
    }
    if(isNull(to)) {
      mandatory("Conta crédito");
    }
    from.debit(amount);
    to.credit(amount);
  }

}
