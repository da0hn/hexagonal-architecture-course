package org.gabriel.desktop.view;

import javafx.application.Application;
import javafx.stage.Stage;
import org.gabriel.desktop.build.develop.Build2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaFxAdapter extends Application {

  private ApplicationContext spring;

  public static void main(final String[] args) {
    launch(args);
  }

  @Override public void init() throws Exception {
    System.out.println("Iniciando o Spring...");
    spring = new AnnotationConfigApplicationContext(Build2.class);
  }

  @Override public void start(final Stage primaryStage) throws Exception {
    final var form = spring.getBean(TransferForm.class);
    form.show(primaryStage);
  }
}
