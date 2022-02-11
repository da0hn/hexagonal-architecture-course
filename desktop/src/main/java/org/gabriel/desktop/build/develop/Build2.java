package org.gabriel.desktop.build.develop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// Responsável por configurar os serviços do spring
@Configuration
@ComponentScan({
  // adaptadores front-end javafx
  "org.gabriel.desktop.build.develop",
  "org.gabriel.desktop.view",
  // core do sistema
  "org.gabriel.account.core",
  // adaptadores falsos
  "org.gabriel.account.adapters"
})
public class Build2 {
  // Build 2 - Adaptador JavaFX -> Sistema <- Adaptadores Mocks
}
