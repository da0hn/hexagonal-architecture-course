package org.da0hn.account.core.domain.model;

import java.math.BigDecimal;

import static java.util.Objects.isNull;
import static org.da0hn.account.core.domain.model.ErrorMessage.insufficientFunds;
import static org.da0hn.account.core.domain.model.ErrorMessage.mandatory;

public class Account {

  private Integer number;
  private String holder;
  private BigDecimal balance;

  public Account() {
    this.number = 0;
    this.balance = BigDecimal.ZERO;
    this.holder = "Não Informado";
  }

  public Account(final Integer number, final BigDecimal balance, final String holder) {
    this.number = number;
    this.balance = balance;
    this.holder = holder;
  }

  public void debit(final BigDecimal amount) {
    if(isNull(amount)) {
      mandatory("Valor débito");
    }
    if(amount.compareTo(BigDecimal.ZERO) <= 0) {
      mandatory("Valor débito");
    }
    if(amount.compareTo(this.balance) > 0) {
      insufficientFunds();
    }
    this.balance = this.balance.subtract(amount);
  }

  public void credit(final BigDecimal amount) {
    if(isNull(amount)) {
      mandatory("Valor crédito");
    }
    if(amount.compareTo(BigDecimal.ZERO) <= 0) {
      mandatory("Valor crédito");
    }
    if(amount.compareTo(this.balance) > 0) {
      insufficientFunds();
    }
    this.balance = this.balance.add(amount);
  }

  public BigDecimal getBalance() {
    return this.balance;
  }

  public Integer getNumber() {
    return this.number;
  }

  public void setNumber(final Integer number) {
    this.number = number;
  }

  public String getHolder() {
    return this.holder;
  }

  public void setHolder(final String holder) {
    this.holder = holder;
  }

  @Override public String toString() {
    return "Account{" +
           "number=" + this.number +
           ", holder='" + this.holder + '\'' +
           ", balance=" + this.balance +
           '}';
  }
}
