package project.restaurant.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import project.restaurant.dao.OrderPadDao;

public class TenMostExpensiveOrderPadsGraphController implements Initializable{
	@FXML
	private BarChart<String, Double> barChart;

	@FXML
	private CategoryAxis categoryAxis;

	@FXML
	private NumberAxis numberAxis;

	private ObservableList<String> observableListPos = FXCollections.observableArrayList();

	// Atributos para manipulação de Banco de Dados
	private final OrderPadDao orderPadDao = new OrderPadDao();

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		// Obtém an array com nomes dos meses em inglês.
		String[] arrayPos = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
		// Converte o array em uma lista e adiciona em nossa ObservableList de meses.
		observableListPos.addAll(Arrays.asList(arrayPos));

		// Associa os nomes de mês como categorias para o eixo horizontal.
		categoryAxis.setCategories(observableListPos);
		Map<Integer, Double> data = this.getOrderPadDao().TenHighestOrderPads();

		for (Map.Entry<Integer, Double> el : data.entrySet()) {
			XYChart.Series<String, Double> series = new XYChart.Series<>();
			series.setName(String.valueOf((el.getKey())));

			series.getData().add(new XYChart.Data<>(String.valueOf(el.getKey()), el.getValue()));
			
			barChart.getData().add(series);
		}
	}

	public OrderPadDao getOrderPadDao() {
		return this.orderPadDao;
	}
}
