package project.restaurant.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import project.restaurant.model.OrderPad;
import project.restaurant.model.User;

public class OrderPadAddController {

    @FXML
    private TextField txtTotal;

    @FXML
    private Button btnSubmit;
    
    private User user;
    
    private OrderPad orderPad;
    
    private Stage windowOrderPadEdit;
    
    private boolean okClick;
    @FXML
    void onSubmit(ActionEvent event) {
    	orderPad.setTotal(Double.parseDouble(txtTotal.getText()));
    	orderPad.setUser(this.getUser());
    	
    	this.okClick = true;
    	windowOrderPadEdit.close();
    }

	public User getUser() {
		return user;
	}
	
	public boolean isOkClick() {
		return okClick;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public OrderPad getOrderPad() {
		return orderPad;
	}

	public void setOrderPad(OrderPad orderPad) {
		this.orderPad = orderPad;
	}

	public Stage getWindowOrderPadEdit() {
		return windowOrderPadEdit;
	}

	public void setWindowOrderPadEdit(Stage windowOrderPadEdit) {
		this.windowOrderPadEdit = windowOrderPadEdit;
	}
    

}
