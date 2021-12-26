package org.da0hn.account.core.domain.model;

public class BusinessException extends RuntimeException {

  public BusinessException(final String message) {
    super(message);
  }
}
