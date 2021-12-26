package org.da0hn.account.core.domain.model;

public final class ErrorMessage {

  private ErrorMessage() {
  }

  public static void mandatory(final String name) {
    throw new BusinessException(name + " é obrigatório.");
  }

  public static void noexistent(final String name) {
    throw new BusinessException(name + " é inexistente.");
  }

  public static void insufficientFunds() {
    throw new BusinessException("Saldo insuficiente.");
  }

  public static void sameAccount() {
    throw new BusinessException("Conta débito e crédito devem ser diferentes.");
  }
}
