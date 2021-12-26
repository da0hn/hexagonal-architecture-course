package org.da0hn.account.core.domain.model;

import java.io.Serial;

public class BusinessException extends RuntimeException {

  @Serial private static final long serialVersionUID = -6928613625685489378L;

  public BusinessException(final String message) {
    super(message);
  }
}
