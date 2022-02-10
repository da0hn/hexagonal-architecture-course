package org.gabriel.account.core.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("Regra de débito em conta")
class DebitAccountTest {


  private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
  private Account validAccount;

  @BeforeEach
  void setUp() {
    this.validAccount = new Account(1, ONE_HUNDRED, "Gabriel");
  }


  @Test
  @DisplayName("Valor débito nulo como obrigatório")
  void test1() {
    final var exception = assertThrows(
      BusinessException.class,
      () -> this.validAccount.debit(null),
      "valor débito obrigatório"
    );

    assertThat(exception.getMessage(), is("Valor débito é obrigatório."));
  }

  @Test
  @DisplayName("Valor débito negativo como obrigatório")
  void test2() {
    final var exception = assertThrows(
      BusinessException.class,
      () -> this.validAccount.debit(new BigDecimal(-10)),
      "valor débito obrigatório"
    );
    assertThat(exception.getMessage(), is("Valor débito é obrigatório."));
  }

  @Test
  @DisplayName("Valor débito zero como obrigatório")
  void test3() {
    final var exception = assertThrows(
      BusinessException.class,
      () -> this.validAccount.debit(BigDecimal.ZERO),
      "valor débito obrigatório"
    );
    assertThat(exception.getMessage(), is("Valor débito é obrigatório."));
  }

  @Test
  @DisplayName("Valor débito acima do saldo")
  void test4() {
    final var exception = assertThrows(
      BusinessException.class,
      () -> this.validAccount.debit(ONE_HUNDRED.add(BigDecimal.ONE)),
      "Valor débito acima do saldo"
    );
    assertThat(exception.getMessage(), is("Saldo insuficiente."));
  }


  @Test
  @DisplayName("Valor débito igual ao saldo")
  void test5() {
    this.validAccount.debit(ONE_HUNDRED);
    assertThat(this.validAccount.getBalance(), is(BigDecimal.ZERO));
  }

  @Test
  @DisplayName("Valor débito menor que o saldo")
  void test6() {
    this.validAccount.debit(BigDecimal.TEN);
    final var expected = ONE_HUNDRED.subtract(BigDecimal.TEN);
    assertThat(this.validAccount.getBalance(), is(expected));
  }


}
