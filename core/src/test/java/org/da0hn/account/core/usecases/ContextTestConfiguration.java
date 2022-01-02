package org.da0hn.account.core.usecases;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
  "org.da0hn.account.core.domain.service",
  "org.da0hn.account.core.usecases.impl",
  "org.da0hn.account.adapters"
})
public class ContextTestConfiguration {

}
