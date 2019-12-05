package project.restaurant.controller;

import java.util.Optional;

import com.sun.org.apache.xpath.internal.operations.NotEquals;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import project.restaurant.dao.UserDao;
import project.restaurant.model.User;

public class RegisterController {

    @FXML
    private GridPane pnlLogin;

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
    private TextField txtNome;

    @FXML
    private Label lblNome;

    @FXML
    private PasswordField txtConfirmSenha;

    @FXML
    private Label lblConfirmSenha;

    @FXML
    private Button btnConfirm;

    @FXML
    private Button btnClean;

    @FXML
    void OnClean(ActionEvent event) {
    	
    }

    @FXML
    void OnConfirm(ActionEvent event) {
    	try {
    		User user = new User();
    		UserDao userDao = new UserDao();
        	user.setName(this.txtNome.getText());
        	user.setEmail(this.txtLogin.getText());
        	if(!(this.txtSenha.getText().equals(this.txtConfirmSenha.getText()))) 
        		throw new Exception("As senhas não conferem");
        	user.setPassword(this.txtSenha.getText());
        	userDao.register(user);
        	
        	Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Sucesso");
			alert.setHeaderText("Operação realizada com sucesso.");
			alert.setContentText("O usuário foi cadastrado com Sucesso!");

			alert.showAndWait();
    	}catch(Exception err) {
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro");
			alert.setHeaderText("Erro no login");
			alert.setContentText(err.getMessage());

			alert.showAndWait();
			this.txtLogin.requestFocus();
    	}
	}

}
