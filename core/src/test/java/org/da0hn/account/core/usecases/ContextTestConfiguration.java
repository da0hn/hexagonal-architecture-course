package org.da0hn.account.core.usecases;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
  "org.da0hn.account.core",
  "org.da0hn.account.adapters"
})
public class ContextTestConfiguration {

}
