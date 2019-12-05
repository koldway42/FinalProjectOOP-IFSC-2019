package project.restaurant.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import project.restaurant.dao.UserDao;
import project.restaurant.model.User;

public class LoginController implements Initializable {

	@FXML
	private GridPane pnlLogin;

	@FXML
	private Text lblAcesso;

	@FXML
	private Label lblLogin;

	@FXML
	private TextField txtLogin;

	@FXML
	private Label lblSenha;

	@FXML
	private PasswordField txtSenha;

	@FXML
	private HBox pnlBotoes;

	@FXML
	private Button btnLogin;

	@FXML
	private Button btnLimpar;
	
	private Stage stage;
	
	@FXML
	void entrar(ActionEvent event) throws IOException {
		User user = new User();
		user.setPassword(this.txtSenha.getText());
		user.setEmail(this.txtLogin.getText());
		UserDao userDao = new UserDao();
		try {
			user = userDao.login(user);
			if (user.getId() != 0) {

				// Carregando o arquivo da tela de menu
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/restaurant/view/Menu.fxml"));
				Parent menuXML = loader.load();

				// Carregando a classe de controle do arquivo da tela de login
				MenuController menuController = loader.getController();
				menuController.setUser(user);

				Scene menuLayout = new Scene(menuXML);
				Stage menuJanela = new Stage();
				menuJanela.resizableProperty().setValue(Boolean.FALSE);
				menuJanela.setMaximized(true);
				menuJanela.setTitle("Sistema de Contas a Pagar e Receber - IFSC");
				menuJanela.setScene(menuLayout);
				menuJanela.setOnShown(new EventHandler<WindowEvent>() {
					@Override
					public void handle(WindowEvent event) {
						menuController.onShow();
					}
				});
				// Atribuindo evento para fechar a tela de login
				menuJanela.setOnCloseRequest(e -> {
					if (menuController.onCloseQuery()) {
						System.exit(0);
					} else {
						e.consume();
					}
				});
				menuJanela.show();

				// Fechando a janela (palco) de login
				Stage loginJanela = (Stage) this.btnLogin.getScene().getWindow();
				loginJanela.close();
			}
		} catch (Exception err) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro");
			alert.setHeaderText("Erro no login");
			alert.setContentText(err.getMessage());

			alert.showAndWait();
			this.txtLogin.requestFocus();
			
			err.printStackTrace();

		}
	}

	@FXML
	void limpar(ActionEvent event) {
		this.txtLogin.setText("");
		this.txtSenha.setText("");
		this.txtLogin.requestFocus();
	}
	
   @FXML
    void OnRegister(ActionEvent event) {
	   try {
			// Carregando o arquivo da tela de usuario
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/restaurant/view/Register.fxml"));
			Parent registerXML = loader.load();

			// Carregando a classe de controle do arquivo da tela
			RegisterController registerController = loader.getController();
			Scene registerLayout = new Scene(registerXML);

			this.getStage().setScene(registerLayout);
			this.getStage().setTitle("Cadastro de conta a pagar");

			this.getStage().show();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
   
   public void configuraStage() {
		this.setStage(new Stage());
		this.getStage().initModality(Modality.APPLICATION_MODAL);
		this.getStage().resizableProperty().setValue(Boolean.FALSE);
	}
	
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		configuraStage();
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	

}
