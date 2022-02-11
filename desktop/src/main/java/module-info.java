module account.desktop {

  requires account.core;

  requires javax.inject;
  requires spring.tx;
  requires spring.core;
  requires spring.beans;
  requires spring.context;

  requires javafx.controls;
  requires java.sql;

  opens org.gabriel.desktop.build.develop;
  opens org.gabriel.desktop.build.homolog;
  opens org.gabriel.desktop.build.production;
  opens org.gabriel.desktop.view;
}
