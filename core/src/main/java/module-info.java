module account.core {
  requires javax.inject;
  requires spring.tx;

  exports org.da0hn.account.adapters;
  exports org.da0hn.account.core.usecases.impl;
  exports org.da0hn.account.core.usecases.ports;
  exports org.da0hn.account.core.domain.model;
  exports org.da0hn.account.core.domain.service;

  opens org.da0hn.account.adapters;
  opens org.da0hn.account.core.usecases.impl;
  opens org.da0hn.account.core.usecases.ports;
  opens org.da0hn.account.core.domain.service;

}

