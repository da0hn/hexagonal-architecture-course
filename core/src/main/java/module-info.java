module account.core {
  requires javax.inject;
  requires spring.tx;

  exports org.gabriel.account.adapters;
  exports org.gabriel.account.core.usecases.impl;
  exports org.gabriel.account.core.usecases.ports;
  exports org.gabriel.account.core.domain.model;
  exports org.gabriel.account.core.domain.service;

  opens org.gabriel.account.adapters;
  opens org.gabriel.account.core.usecases.impl;
  opens org.gabriel.account.core.usecases.ports;
  opens org.gabriel.account.core.domain.service;

}

