package project.restaurant.controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import project.restaurant.model.User;

public class MenuController implements Initializable {

	private User user;

	@FXML
	private MenuItem mnoFavorecido;

	@FXML
	private MenuItem mnoTipoConta;

	@FXML
	private MenuItem mnoUsuario;

	@FXML
	private MenuItem mnoContasPagar;

	@FXML
	private MenuItem mnoContasReceber;

	@FXML
	private MenuItem mnoSair;

	@FXML
	private MenuItem mnoGraficoRecebimentosPagamentos;

	@FXML
	private MenuItem mnoGraficoGastosCategoria;

	@FXML
	private MenuItem mnoRelatorioContasReceber;

	@FXML
	private MenuItem mnoRelatorioContasPagar;

	@FXML
	private MenuItem mnoRelatorioUsuario;

	@FXML
	private MenuItem mnoGraficoListaUsuarioCadastroPorMes;

	@FXML
	private MenuItem mnoSobre;

	@FXML
	private HBox pnlStatusBar;

	@FXML
	private Label lblUsuario;

	@FXML
	private Label lblData;

	@FXML
	private Label lblHora;

	private Stage stage;

	// Configura��es iniciais da tela de menu
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.configuraBarraStatus();
		this.configuraStage();
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUsuario() {
		return user;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	// Quando abre a tela e coloca o nome do usu�rio da tela de status
	public void onShow() {
		this.lblUsuario.setText("Usu�rio: " + this.getUsuario().getName());
	}

	@FXML
	void mnoSair(ActionEvent event) {
		if (this.onCloseQuery()) {
			System.exit(0);
		} else {
			event.consume();
		}
	}

	// Evento de fechamento da tela com pergunta
	public boolean onCloseQuery() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Pergunta");
		alert.setHeaderText("Deseja sair do sistema?");
		ButtonType buttonTypeNO = ButtonType.NO;
		ButtonType buttonTypeYES = ButtonType.YES;
		alert.getButtonTypes().setAll(buttonTypeYES, buttonTypeNO);
		Optional<ButtonType> result = alert.showAndWait();
		return result.get() == buttonTypeYES ? true : false;
	}

	// Configura a tela inicialmente
	public void configuraStage() {
		this.setStage(new Stage());
		this.getStage().initModality(Modality.APPLICATION_MODAL);
		this.getStage().resizableProperty().setValue(Boolean.FALSE);
	}

	// Configura a barra de status para mostrar a hora e nome do usu�rio
	public void configuraBarraStatus() {
		DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.lblData.setText("Data: " + LocalDateTime.now().format(dataFormatada));

		Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
			DateTimeFormatter horaFormatada = DateTimeFormatter.ofPattern("HH:mm:ss");
			this.lblHora.setText("Hora: " + LocalDateTime.now().format(horaFormatada));
		}), new KeyFrame(Duration.seconds(1)));
		clock.setCycleCount(Animation.INDEFINITE);
		clock.play();
	}

}
