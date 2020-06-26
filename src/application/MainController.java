package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class MainController implements Initializable {

	@FXML
	private ListView<String> lv;

	@FXML
	private ComboBox<String> timePos;

	@FXML
	private ComboBox<String> speedComo;

	@FXML
	private ToggleButton btnSpeed;

	@FXML
	private TextField txtSpeed;

	@FXML
	private ToggleButton btnEBISpeed;

	@FXML
	private TextField txtEBISpeed;

	@FXML
	private ToggleButton btnSBISpeed;

	@FXML
	private TextField txtSBISpeed;

	@FXML
	private ToggleButton btnEBPSpeed;

	@FXML
	private TextField txtEBPSpeed;

	@FXML
	private ToggleButton btnNBPSpeed;

	@FXML
	private TextField txtNBPSpeed;

	@FXML
	private TextField txtC3Pos;

	@FXML
	private TextField txtC3Eoa;

	@FXML
	private TextField txtC3DLRBG;

	@FXML
	private TextField txtC3Dis;

	@FXML
	private TextField txtC3Miles;
	
	@FXML
	private TextField txtC2Pos;
	
	@FXML
	private TextField txtC2LMA;
	
	@FXML
	private TextField txtC2DPos;
	
	@FXML
	private TextField txtC2IPos;
	
	@FXML
	private BorderPane border0;
	@FXML
	private BorderPane border1;
	@FXML
	private BorderPane border2;
	@FXML
	private BorderPane border3;
	@FXML
	private BorderPane border4;
	@FXML
	private BorderPane border5;
	@FXML
	private BorderPane border6;

	private double[] _arrSpeed;
	public static Random _random;
	private boolean _isPositive;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ObservableList<String> list = FXCollections.observableArrayList("M#24", "M#136", "M#24", "M#136", "M#24",
				"M#136", "M#24", "M#136", "M#24", "M#136", "M#24", "M#136", "M#24", "M#136", "M#24", "M#136");
		lv.setItems(list);

		ObservableList<String> list1 = FXCollections.observableArrayList("100m", "200m", "300m", "400m");
		timePos.setItems(list1);

		ObservableList<String> list2 = FXCollections.observableArrayList("80km/h", "100km/h", "200km/h", "250km/h",
				"300km/h", "350km/h");
		speedComo.setItems(list2);
		
		border0.setBorder(new Border(new BorderStroke(Color.WHITE, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		border1.setBorder(new Border(new BorderStroke(Color.WHITE, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		border2.setBorder(new Border(new BorderStroke(Color.WHITE, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		border3.setBorder(new Border(new BorderStroke(Color.WHITE, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		border4.setBorder(new Border(new BorderStroke(Color.WHITE, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		border5.setBorder(new Border(new BorderStroke(Color.WHITE, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		border6.setBorder(new Border(new BorderStroke(Color.WHITE, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		_random = new Random();
		_isPositive = true;

		_arrSpeed = new double[9];
		_arrSpeed[0] = 173.20;
		_arrSpeed[1] = 241.46;
		_arrSpeed[2] = 231.75;
		_arrSpeed[3] = 270;
		_arrSpeed[4] = 200;
		_arrSpeed[5] = 224.34;
		_arrSpeed[6] = 209.14;
		_arrSpeed[7] = 270;
		_arrSpeed[8] = 300;

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				Platform.runLater(() -> {
					double delta = MainController._random.nextInt(8) + 2 + MainController._random.nextDouble();

					if (btnSpeed.isSelected()) {
						_arrSpeed[0] = NormalizeSpeed(_arrSpeed[0], delta);
						txtSpeed.setText(String.format("%.2f", NormalizeSpeed(_arrSpeed[0], delta)));
					}
					if (btnEBISpeed.isSelected()) {
						_arrSpeed[1] = NormalizeSpeed(_arrSpeed[1], delta);
						txtEBISpeed.setText(String.format("%.2f", NormalizeSpeed(_arrSpeed[1], delta)));
					}
					if (btnSBISpeed.isSelected()) {
						_arrSpeed[2] = NormalizeSpeed(_arrSpeed[2], delta);
						txtSBISpeed.setText(String.format("%.2f", NormalizeSpeed(_arrSpeed[2], delta)));
					}

					if (btnEBPSpeed.isSelected()) {
						_arrSpeed[4] = NormalizeSpeed(_arrSpeed[4], delta);
						txtEBPSpeed.setText(String.format("%.2f", NormalizeSpeed(_arrSpeed[4], delta)));
					}
					if (btnNBPSpeed.isSelected()) {
						_arrSpeed[5] = NormalizeSpeed(_arrSpeed[5], delta);
						txtNBPSpeed.setText(String.format("%.2f", NormalizeSpeed(_arrSpeed[5], delta)));
					}

					double min = _arrSpeed[0], max = _arrSpeed[0];
					for (int i = 1; i < _arrSpeed.length; i++) {
						if (_arrSpeed[i] > max)
							max = _arrSpeed[i];
						if (_arrSpeed[i] < min)
							min = _arrSpeed[i];
					}
					if (max > 349)
						_isPositive = false;
					if (min < 1)
						_isPositive = true;

					delta += delta += delta;
					txtC3Pos.setText(String.format("%.1f", NormalizeDis(Double.parseDouble(txtC3Pos.getText()), delta)));
					txtC3Eoa.setText(String.format("%.1f", NormalizeDis(Double.parseDouble(txtC3Eoa.getText()), delta)));
					txtC3DLRBG.setText(
							String.format("%.1f", NormalizeDis(Double.parseDouble(txtC3DLRBG.getText()), delta)));
					txtC3Dis.setText(String.format("%.1f", NormalizeDis(Double.parseDouble(txtC3Dis.getText()), -delta)));
					txtC3Miles.setText(String.format("%.0f", Double.parseDouble(txtC3Miles.getText()) + delta));

					txtC2Pos.setText(String.format("%.1f", NormalizeDis(Double.parseDouble(txtC2Pos.getText()), delta)));
					txtC2LMA.setText(String.format("%.1f",NormalizeDis(Double.parseDouble(txtC2LMA.getText()), delta))); 
					txtC2DPos.setText(String.format("%.1f",NormalizeDis(Double.parseDouble(txtC2DPos.getText()), delta)));
					txtC2IPos.setText(String.format("%.1f",NormalizeDis(Double.parseDouble(txtC2IPos.getText()), -delta)));
				});
			}
		}, 1000, 1000);
	}

	private double NormalizeSpeed(double speed, double delta) {
		if (_isPositive)
			speed += delta;
		else
			speed -= delta;
		if (speed > 350) {
			speed = 350;
		} else if (speed < 0) {
			speed = 0;
		}

		return speed;
	}

	private double NormalizeDis(double dis, double delta) {
		if (_isPositive)
			dis += delta;
		else
			dis -= delta;

		if (dis < 0)
			dis = 0;

		return dis;
	}
}
