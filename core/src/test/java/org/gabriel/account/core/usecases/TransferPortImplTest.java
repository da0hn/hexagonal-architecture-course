package org.gabriel.account.core.usecases;

import org.gabriel.account.core.domain.model.BusinessException;
import org.gabriel.account.core.usecases.ports.TransferPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.inject.Inject;
import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
@DisplayName("Caso de Uso - Serviço de Transferência")
@ContextConfiguration(classes = ContextTestConfiguration.class)
class TransferPortImplTest {

  private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
  private static final BigDecimal FIFTY = new BigDecimal(50);
  private static final Integer FROM_ACCOUNT_ID = 10;
  private static final Integer TO_ACCOUNT_ID = 20;
  private static final Integer INEXISTENT_ACCOUNT_ID = 30;

  @Inject
  TransferPort transferPort;

  @Test
  @DisplayName("Pesquisa conta com número nulo")
  void test1() {
    final var account = this.transferPort.getAccount(null);
    assertNull(account, "Deve carregar uma conta nula.");
  }

  @Test
  @DisplayName("Pesquisa cnota com número inexistente")
  void test2() {
    final var account = this.transferPort.getAccount(INEXISTENT_ACCOUNT_ID);
    assertNull(account, "Conta deve ser nula");
  }

  @Test
  @DisplayName("pesquisa conta com número existente")
  void test3() {
    final var account = this.transferPort.getAccount(FROM_ACCOUNT_ID);
    assertNotNull(account, "Conta deve ser preenchida");
  }

  @Test
  @DisplayName("conta débito como obrigatória")
  void test4() {
    final var exception = assertThrows(
      BusinessException.class,
      () -> this.transferPort.transfer(FIFTY, null, TO_ACCOUNT_ID),
      "Conta débito é obrigatório"
    );
    assertEquals("Conta débito é obrigatório.", exception.getMessage(), "Mensagem de erro deve bater");
  }

  @Test
  @DisplayName("conta crédito como obrigatória")
  void test5() {
    final var exception = assertThrows(
      BusinessException.class,
      () -> this.transferPort.transfer(FIFTY, FROM_ACCOUNT_ID, null),
      "Conta crédito é obrigatório"
    );
    assertEquals("Conta crédito é obrigatório.", exception.getMessage(), "Mensagem de erro deve bater");
  }

  @Test
  @DisplayName("valor como obrigatória")
  void test6() {
    final var exception = assertThrows(
      BusinessException.class,
      () -> this.transferPort.transfer(null, FROM_ACCOUNT_ID, TO_ACCOUNT_ID),
      "valor é obrigatório"
    );
    assertEquals("Valor é obrigatório.", exception.getMessage(), "Mensagem de erro deve bater");
  }

  @Test
  @DisplayName("conta débito inexistente")
  void test7() {
    final var exception = assertThrows(
      BusinessException.class,
      () -> this.transferPort.transfer(FIFTY, INEXISTENT_ACCOUNT_ID, TO_ACCOUNT_ID),
      "Conta débito inexistente"
    );
    assertEquals("Conta débito é inexistente.", exception.getMessage(), "Mensagem de erro deve bater");
  }

  @Test
  @DisplayName("conta crédito inexistente")
  void test8() {
    final var exception = assertThrows(
      BusinessException.class,
      () -> this.transferPort.transfer(FIFTY, FROM_ACCOUNT_ID, INEXISTENT_ACCOUNT_ID),
      "Conta crédito inexistente"
    );
    assertEquals("Conta crédito é inexistente.", exception.getMessage(), "Mensagem de erro deve bater");
  }

  @Test
  @DisplayName("mesma conta débito e crédito")
  void test9() {
    final var exception = assertThrows(
      BusinessException.class,
      () -> this.transferPort.transfer(FIFTY, FROM_ACCOUNT_ID, FROM_ACCOUNT_ID),
      "Mesma conta débito e crédito"
    );
    assertEquals(
      "Conta débito e crédito devem ser diferentes.",
      exception.getMessage(),
      "Conta débito e crédito devem ser diferentes"
    );
  }

  @Test
  @DisplayName("Transferência de 50 reais")
  void test10() {
    this.transferPort.transfer(FIFTY, FROM_ACCOUNT_ID, TO_ACCOUNT_ID);

    final var fromAccount = this.transferPort.getAccount(FROM_ACCOUNT_ID);
    final var toAccount = this.transferPort.getAccount(TO_ACCOUNT_ID);

    assertEquals(ONE_HUNDRED.add(FIFTY), toAccount.getBalance(), "Saldo crédito deve bater");
    assertEquals(ONE_HUNDRED.subtract(FIFTY), fromAccount.getBalance(), "Saldo débito deve bater");

  }


}
