package org.gabriel.desktop.view;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import org.gabriel.account.core.usecases.ports.TransferPort;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.Optional;

@Named
public class TransferForm {

  private final TransferPort transferPort;
  private TextField debitTextField;
  private TextField nameDebitTextField;
  private TextField creditTextField;
  private TextField nameCreditTextField;
  private TextField valueTextField;


  @Inject
  public TransferForm(final TransferPort transferPort) {
    this.transferPort = transferPort;
  }

  private static Integer getValueAsInteger(final String value) {
    try {
      return Integer.valueOf(value);
    }
    catch(final Exception exception) {
      return null;
    }
  }

  private static void message(final String text) {
    final var alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Transferência Bancária");
    alert.setHeaderText(null);
    alert.setContentText(text);
    alert.showAndWait();
  }

  private void clear() {
    debitTextField.setText("");
    nameDebitTextField.setText("");
    creditTextField.setText("");
    nameCreditTextField.setText("");
    valueTextField.setText("");
  }

  private BigDecimal getValueAsBigDecimal() {
    try {
      return new BigDecimal(valueTextField.getText());
    }
    catch(final Exception exception) {
      return null;
    }
  }

  private FlowPane buildView() {
    final var flowPane = new FlowPane();
    flowPane.setHgap(10);
    flowPane.setVgap(10);

    flowPane.getChildren().add(new Label("Conta débito: "));

    setupDebitTextField(flowPane);
    setupDebitNameTextField(flowPane);

    flowPane.getChildren().add(new Label("Conta Crédito: "));

    setupCreditTextField(flowPane);
    setupCreditNameTextField(flowPane);
    setupTransferButton(flowPane);

    return flowPane;
  }

  private void setupCreditNameTextField(final FlowPane flowPane) {
    nameCreditTextField = new TextField();
    nameCreditTextField.setPrefWidth(200);
    flowPane.getChildren().add(nameCreditTextField);
  }

  private void setupTransferButton(final FlowPane flowPane) {
    final var button = new Button();
    button.setOnAction(event -> {
      try {
        transferPort.transfer(
          getValueAsBigDecimal(),
          getValueAsInteger(debitTextField.getText()),
          getValueAsInteger(creditTextField.getText())
        );
      }
      catch(final Exception exception) {
        message(exception.getMessage());
      }
    });
    button.setText("Tranferir");
    flowPane.getChildren().add(button);
  }

  private void setupCreditTextField(final FlowPane flowPane) {
    creditTextField = new TextField();
    creditTextField.setPrefWidth(50);
    creditTextField.focusedProperty().addListener((observable, oldvalue, newValue) -> {
      if(!newValue) fetchAccountData(creditTextField, nameCreditTextField);
    });
    flowPane.getChildren().add(creditTextField);
  }

  private void setupDebitNameTextField(final FlowPane flowPane) {
    nameDebitTextField = new TextField();
    nameDebitTextField.setPrefWidth(300);
    nameDebitTextField.setEditable(false);
    flowPane.getChildren().add(nameDebitTextField);
  }

  private void setupDebitTextField(final FlowPane flowPane) {
    debitTextField = new TextField();
    debitTextField.setPrefWidth(50);
    debitTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
      if(!newValue) fetchAccountData(debitTextField, nameDebitTextField);
    });
    flowPane.getChildren().add(debitTextField);
  }

  public void show(final Stage stage) {
    stage.setTitle("Adaptador JavaFx");
    final var scene = new Scene(buildView(), 500, 100);
    stage.setScene(scene);
    stage.show();
  }

  private void fetchAccountData(final TextField input, final TextField output) {
    try {
      final var maybeAccount = Optional.ofNullable(transferPort.getAccount(
        getValueAsInteger(input.getText()))
      );
      if(maybeAccount.isEmpty()) {
        output.setText("");
      }
      else {
        final var account = maybeAccount.get();
        output.setText(account + " - Saldo R$ " + account.getBalance());
      }
    }
    catch(final Exception exception) {
      message(exception.getMessage());
    }
  }

}
