package org.gabriel.account.core.domain.model;

import org.gabriel.account.core.domain.service.Transfer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("Regra de Transferência")
class TransferAmountTest {

  private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
  private static final BigDecimal TWENTY = new BigDecimal(20);
  private Transfer transfer;
  private Account from;
  private Account to;

  @BeforeEach
  void setUp() {
    this.from = new Account(1, ONE_HUNDRED, "Gabriel");
    this.to = new Account(2, ONE_HUNDRED, "Nathally");
    this.transfer = new Transfer();
  }

  @Test
  @DisplayName("Valor nulo como obrigatório")
  void test1() {
    final var exception = assertThrows(
      BusinessException.class,
      () -> this.transfer.process(null, this.from, this.to),
      "Valor transferência como obrigatório"
    );
    assertThat(exception.getMessage(), is("Valor de transferência é obrigatório."));
  }

  @Test
  @DisplayName("Conta débito como obrigatória")
  void test2() {
    final var exception = assertThrows(
      BusinessException.class,
      () -> this.transfer.process(TWENTY, null, this.to),
      "Conta débito como obrigatório"
    );
    assertThat(exception.getMessage(), is("Conta débito é obrigatório."));
  }

  @Test
  @DisplayName("Conta crédito como obrigatória")
  void test3() {
    final var exception = assertThrows(
      BusinessException.class,
      () -> this.transfer.process(TWENTY, this.from, null),
      "Conta crédito como obrigatório"
    );
    assertThat(exception.getMessage(), is("Conta crédito é obrigatório."));
  }

  @Test
  @DisplayName("Transferir 20 reais")
  void test4() {
    this.transfer.process(TWENTY, this.from, this.to);
    assertEquals(ONE_HUNDRED.subtract(TWENTY), this.from.getBalance(), "Saldo da conta débito deve bater");
    assertEquals(ONE_HUNDRED.add(TWENTY), this.to.getBalance(), "Saldo da conta crédito deve bater");
  }


}
