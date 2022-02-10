package org.gabriel.account.core.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("Regra de crédito em conta")
class CreditAccountTest {


  private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
  private Account validAccount;

  @BeforeEach
  void setUp() {
    this.validAccount = new Account(1, ONE_HUNDRED, "Gabriel");
  }

  @Test
  @DisplayName("Valor crédito nulo como obrigatório")
  void test1() {
    final var exception = assertThrows(
      BusinessException.class,
      () -> this.validAccount.credit(null),
      "valor crédito obrigatório"
    );

    assertThat(exception.getMessage(), is("Valor crédito é obrigatório."));
  }

  @Test
  @DisplayName("Valor crédito negativo como obrigatório")
  void test2() {
    final var exception = assertThrows(
      BusinessException.class,
      () -> this.validAccount.credit(new BigDecimal(-10)),
      "valor crédito obrigatório"
    );
    assertThat(exception.getMessage(), is("Valor crédito é obrigatório."));
  }

  @Test
  @DisplayName("Valor crédito zero como obrigatório")
  void test3() {
    final var exception = assertThrows(
      BusinessException.class,
      () -> this.validAccount.credit(BigDecimal.ZERO),
      "valor crédito obrigatório"
    );
    assertThat(exception.getMessage(), is("Valor crédito é obrigatório."));
  }

  @Test
  @DisplayName("Valor crédito acima de zero")
  void test4() {
    this.validAccount.credit(BigDecimal.ONE);
    final var expected = ONE_HUNDRED.add(BigDecimal.ONE);
    assertThat(this.validAccount.getBalance(), is(expected));
  }


}
