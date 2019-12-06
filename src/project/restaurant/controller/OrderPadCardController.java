package project.restaurant.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import project.restaurant.model.OrderPad;

public class OrderPadCardController implements Initializable{

    @FXML
    private Text lblCardHeader;

    @FXML
    private Text lblCardBody;

    @FXML
    private Text lblCardFooter;
    
    private OrderPad orderPad;
    
    public void onShow() {
    	lblCardHeader.setText("OrderPad #" + this.getOrderPad().getId().toString());
    	lblCardBody.setText("Price: R$" + this.getOrderPad().getTotal().toString());
    	lblCardFooter.setText(this.getOrderPad().getFormattedCreatedAt());
    }

	public OrderPad getOrderPad() {
		return orderPad;
	}

	public void setOrderPad(OrderPad orderPad) {
		this.orderPad = orderPad;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
    
    
}
