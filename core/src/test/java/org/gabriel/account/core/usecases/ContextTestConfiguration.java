package org.gabriel.account.core.usecases;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
  "org.gabriel.account.core.domain.service",
  "org.gabriel.account.core.usecases.impl",
  "org.gabriel.account.adapters"
})
public class ContextTestConfiguration {

}
