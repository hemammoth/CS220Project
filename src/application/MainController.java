package application;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import org.json.JSONException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class MainController implements Initializable {
	private HashMap stockMap = new HashMap();
	private GeneralTree stockTree = new GeneralTree('$');
	private CircularDoublyLinkedList watchlists = new CircularDoublyLinkedList(); 
	private Creator app = new Creator();

	@FXML
	private Label companyNameLabel;
	@FXML
	private Label currentPriceLabel;
	@FXML
	private Label changeLabel;
	@FXML
	private Label percentChangeLabel;
	@FXML
	private Label openLabel;
	@FXML
	private Label closeLabel;
	@FXML
	private Label highLabel;
	@FXML
	private Label lowLabel;
	@FXML
	private Label totalSharesLabel;
	@FXML
	private Label floatSharesLabel;
	@FXML
	private Label averageVolumeLabel;
	@FXML
	private Label discriptionLabel;
	@FXML
	private Label CEOLabel;
	@FXML
	private Label exchangeLabel;
	@FXML
	private Label sectorLabel;
	@FXML
	private Label industryLabel;
	@FXML
	private Label websiteLabel;
	@FXML
	private Label timeLabel;
	@FXML
	private Label high52Label;
	@FXML
	private Label low52Label;
	@FXML
	private Label y5Label;
	@FXML
	private Label y2Label;
	@FXML
	private Label y1Label;
	@FXML
	private Label m6Label;
	@FXML
	private Label m3Label;
	@FXML
	private Label m1Label;
	@FXML
	private Label d5Label;
	@FXML
	private Label label1;
	@FXML
	private Label label2;
	@FXML
	private Label label3;
	@FXML
	private Label label4;
	@FXML
	private Label label5;
	@FXML
	private Label label6;
	@FXML
	private Label label7;
	@FXML
	private Label label8;
	@FXML
	private Label label9;
	@FXML
	private Label label10;
	@FXML
	private Label label11;
	@FXML
	private Label label12;
	@FXML
	private Label label13;
	@FXML
	private Label label14;
	@FXML
	private Label label15;
	@FXML
	private Label label16;
	@FXML
	private Label label17;
	@FXML
	private Label label18;
	@FXML
	private Label label19;
	@FXML
	private Label label20;
	@FXML
	private Label label21;
	@FXML
	private Label label22;
	@FXML
	private Label label23;
	@FXML
	private Label label24;
	@FXML
	private Label label25;

	@FXML
	private Label label30;

	@FXML 
	private AnchorPane moversUp;

	@FXML 
	private AnchorPane moversDown;

	@FXML
	private JFXButton updateMarketButton;

	@SuppressWarnings("rawtypes")
	@FXML
	private JFXComboBox tickerSearch;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			app.create(stockMap, stockTree);
		} catch (JSONException | IOException e) {}
		try {
			app.create(stockMap, stockTree, null);
		} catch (JSONException | IOException e) {}

		//try {
		//setMovers();
		//} catch (JSONException | IOException e1) {}

		app.createDefultWatchlist(watchlists);
		GridPane grid = makeGrid();
		grid.setGridLinesVisible(true);
		paneLoadLabel.getChildren().add(grid);

		tickerSearch.getItems().addAll(stockTree.getTickerList(""));

		UnaryOperator<TextFormatter.Change> filter = change -> {
			change.setText(change.getText().toUpperCase());
			return change;
		};

		TextFormatter<String> textFormatter = new TextFormatter(filter);
		tickerSearch.getEditor().setTextFormatter(textFormatter);

		tickerSearch.getSelectionModel().selectedItemProperty().addListener((v, oldItem, newItem) -> {
			try {
				companyNameLabel.setText(stockMap.get(newItem.toString()).getCompanyName());
			} catch (NullPointerException e) {
			}
		});
	}


	@FXML 
	public void setMovers() throws JSONException, IOException {
		try {
			updateMarket();
			moversUp.getChildren().clear();
			moversDown.getChildren().clear();
			GridPane gainers = new GridPane();
			GridPane losers = new GridPane();
			ArrayList<String> sortedTickers = app.mergeSort(stockMap, stockTree, StockInfo.class.getMethod("getPercentChange"));

			Label lbl1 = new Label("__TICKER__");
			Label lbl2 = new Label("__PRICE__");
			Label lbl3 = new Label("change");
			Label lbl4 = new Label("%change");

			lbl1.setTextFill(Color.web("#22BB22"));
			lbl2.setTextFill(Color.web("#22BB22"));
			lbl3.setTextFill(Color.web("#22BB22"));
			lbl4.setTextFill(Color.web("#22BB22"));

			lbl1.setStyle("-fx-font-size:18px;");
			lbl2.setStyle("-fx-font-size:18px;");
			lbl3.setStyle("-fx-font-size:18px;");
			lbl4.setStyle("-fx-font-size:18px;");

			gainers.addRow(0, lbl1, lbl2, lbl3, lbl4);

			for (int j = 0, i = sortedTickers.size() - 1; i > sortedTickers.size() - 11; i--) {
				lbl1 = new Label(sortedTickers.get(i));
				lbl2 = new Label(stockMap.get(sortedTickers.get(i)).getLastPrice());
				lbl3 = new Label(stockMap.get(sortedTickers.get(i)).getChange());
				lbl4 = new Label(stockMap.get(sortedTickers.get(i)).getPercentChange() + "%");

				lbl1.setTextFill(Color.web("#22BB22"));
				lbl2.setTextFill(Color.web("#22BB22"));
				lbl3.setTextFill(Color.web("#22BB22"));
				lbl4.setTextFill(Color.web("#22BB22"));

				lbl1.setStyle("-fx-font-size:18px;");
				lbl2.setStyle("-fx-font-size:18px;");
				lbl3.setStyle("-fx-font-size:18px;");
				lbl4.setStyle("-fx-font-size:18px;");

				j++;
				gainers.addRow(j, lbl1, lbl2, lbl3, lbl4);
			}

			Label lbl5 = new Label("__TICKER__");
			Label lbl6 = new Label("__PRICE__");
			Label lbl7 = new Label("change");
			Label lbl8 = new Label("%change");

			lbl5.setTextFill(Color.web("#FF0000"));
			lbl6.setTextFill(Color.web("#FF0000"));
			lbl7.setTextFill(Color.web("#FF0000"));
			lbl8.setTextFill(Color.web("#FF0000"));

			lbl5.setStyle("-fx-font-size:18px;");
			lbl6.setStyle("-fx-font-size:18px;");
			lbl7.setStyle("-fx-font-size:18px;");
			lbl8.setStyle("-fx-font-size:18px;");

			losers.addRow(0, lbl5, lbl6, lbl7, lbl8);

			for (int i = 0; i < 10; i++) {
				lbl1 = new Label(sortedTickers.get(i));
				lbl2 = new Label(stockMap.get(sortedTickers.get(i)).getLastPrice());
				lbl3 = new Label(stockMap.get(sortedTickers.get(i)).getChange());
				lbl4 = new Label(stockMap.get(sortedTickers.get(i)).getPercentChange() + "%");

				lbl1.setTextFill(Color.web("#FF0000"));
				lbl2.setTextFill(Color.web("#FF0000"));
				lbl3.setTextFill(Color.web("#FF0000"));
				lbl4.setTextFill(Color.web("#FF0000"));

				lbl1.setStyle("-fx-font-size:18px;");
				lbl2.setStyle("-fx-font-size:18px;");
				lbl3.setStyle("-fx-font-size:18px;");
				lbl4.setStyle("-fx-font-size:18px;");

				losers.addRow(i+1, lbl1, lbl2, lbl3, lbl4);
			}

			gainers.setGridLinesVisible(true);
			losers.setGridLinesVisible(true);
			moversUp.getChildren().add(gainers);
			moversDown.getChildren().add(losers);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {}	
	}


	@FXML
	private Pane paneLoadLabel;
	@FXML
	private JFXTextField addField;

	@FXML 
	public void addStock(){
		if (watchlists.getCurrentNode().watchlist.getStack().contains(addField.getText().toUpperCase()) || 
				stockMap.get(addField.getText().toUpperCase()) == null) {

		} else {
			paneLoadLabel.getChildren().clear();
			GridPane grid = makeGrid();
			watchlists.getCurrentNode().watchlist.push(addField.getText().toUpperCase());

			Label lbl1 = new Label(watchlists.getCurrentNode().watchlist.peek());
			Label lbl2 = new Label(stockMap.get(watchlists.getCurrentNode().watchlist.peek()).getLastPrice()); 
			Label lbl3 = new Label(stockMap.get(watchlists.getCurrentNode().watchlist.peek()).getChange());
			Label lbl4 = new Label(stockMap.get(watchlists.getCurrentNode().watchlist.peek()).getPercentChange() + "%");

			lbl1.setStyle("-fx-font-size:15px;");
			lbl2.setStyle("-fx-font-size:15px;");
			lbl3.setStyle("-fx-font-size:15px;");
			lbl4.setStyle("-fx-font-size:15px;");

			grid.addRow(watchlists.getCurrentNode().watchlist.getIndex()+1, lbl1, lbl2, lbl3, lbl4);
			grid.setGridLinesVisible(true);
			paneLoadLabel.getChildren().add(grid);
		}
	}

	@FXML 
	public void removeStock() {
		watchlists.getCurrentNode().watchlist.pop();
		paneLoadLabel.getChildren().clear();
		GridPane grid = makeGrid();
		grid.setGridLinesVisible(true);
		paneLoadLabel.getChildren().add(grid);
	}

	private GridPane makeGrid() {
		try {
			Label label26 = new Label("_TICKER_");
			Label label27 = new Label("___PRICE___");
			Label label28 = new Label("CHANGE");
			Label label29 = new Label("%CHANGE");

			label30.setText(watchlists.getCurrentNode().name);
			GridPane grid = new GridPane();
			grid.addRow(0, label26, label27, label28, label29);
			for (int i = 0; i<watchlists.getCurrentNode().watchlist.getStack().size(); i++) {
				Label lbl1 = new Label(watchlists.getCurrentNode().watchlist.getStack().get(i));
				Label lbl2 = new Label(stockMap.get(watchlists.getCurrentNode().watchlist.getStack().get(i)).getLastPrice()); 
				Label lbl3 = new Label(stockMap.get(watchlists.getCurrentNode().watchlist.getStack().get(i)).getChange());
				Label lbl4 = new Label(stockMap.get(watchlists.getCurrentNode().watchlist.getStack().get(i)).getPercentChange() + "%");

				lbl1.setStyle("-fx-font-size:15px;");
				lbl2.setStyle("-fx-font-size:15px;");
				lbl3.setStyle("-fx-font-size:15px;");
				lbl4.setStyle("-fx-font-size:15px;");

				grid.addRow(i+1, lbl1, lbl2, lbl3, lbl4);
			}

			return grid;
		} catch (NullPointerException e) {
			return null;
		}

	}

	@FXML
	private void nextList() {
		watchlists.next();
		paneLoadLabel.getChildren().clear();
		GridPane grid = makeGrid();
		grid.setGridLinesVisible(true);
		paneLoadLabel.getChildren().add(grid);
	}

	@FXML
	private void prevList() {
		watchlists.prev();
		paneLoadLabel.getChildren().clear();
		GridPane grid = makeGrid();
		grid.setGridLinesVisible(true);
		paneLoadLabel.getChildren().add(grid);
	}

	@FXML
	private void addList() {
		Stage alertBox = new Stage();
		alertBox.initModality(Modality.APPLICATION_MODAL);
		alertBox.setMinWidth(200);
		
		JFXTextField name = new JFXTextField();
		JFXButton closeButton = new JFXButton("Set watchlist name");
		closeButton.setStyle("-fx-background-color: #22BB22;");
        closeButton.setOnAction(e -> alertBox.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(name, closeButton);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        alertBox.setScene(scene);
        alertBox.showAndWait();
		
		watchlists.add(name.getText());
		paneLoadLabel.getChildren().clear();
		GridPane grid = makeGrid();
		grid.setGridLinesVisible(true);
		paneLoadLabel.getChildren().add(grid);
	}

	@FXML
	private void removeList() {
		try {
			watchlists.remove(watchlists.getCurrentNode().index);
			paneLoadLabel.getChildren().clear();
			GridPane grid = makeGrid();
			grid.setGridLinesVisible(true);
			paneLoadLabel.getChildren().add(grid);
		} catch (NullPointerException e) {}

	}

	@SuppressWarnings("unchecked")
	@FXML 
	public void generateSearchTab() {
		tickerSearch.show();
		tickerSearch.getEditor().setOnKeyTyped((KeyEvent k) -> {
			if (k.getCharacter().charAt(0) == '\r') {
				try {
					companyNameLabel.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getCompanyName());
					try {
						currentPriceLabel.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getLastPrice());
						changeLabel.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getChange());
						percentChangeLabel.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getPercentChange() + "%");
						openLabel.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getOpen());
						closeLabel.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getClose());
						highLabel.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getHigh());
						lowLabel.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getLow());
						totalSharesLabel.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getSharesOutstanding());
						floatSharesLabel.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getSharesFloat());
						averageVolumeLabel.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getAverageVolume());
						discriptionLabel.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getDiscription());
						CEOLabel.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getCEO());
						exchangeLabel.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getExchange());
						sectorLabel.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getSector());
						industryLabel.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getIndustry());
						websiteLabel.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getWebsite());
						timeLabel.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getTime());
						high52Label.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getWeek52High());
						low52Label.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getWeek52Low());
						y5Label.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getYear5ChangePercent());
						y2Label.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getYear2ChangePercent());
						y1Label.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getYear1ChangePercent());
						m6Label.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getMonth6ChangePercent());
						m3Label.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getMonth3ChangePercent());
						m1Label.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getMonth1ChangePercent());
						d5Label.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getDay5ChangePercent());

						if (Double.parseDouble(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getChange()) >= 0) {
							tickerSearch.setFocusColor(Color.web("#22BB22"));
							tickerSearch.setUnFocusColor(Color.web("#22BB22"));
							updateMarketButton.setTextFill(Color.web("#22BB22"));
							updateMarketButton.setRipplerFill(Color.web("#22BB22"));
							currentPriceLabel.setTextFill(Color.web("#22BB22"));
							companyNameLabel.setTextFill(Color.web("#22BB22"));
							changeLabel.setTextFill(Color.web("#22BB22"));
							percentChangeLabel.setTextFill(Color.web("#22BB22"));
							openLabel.setTextFill(Color.web("#22BB22"));
							closeLabel.setTextFill(Color.web("#22BB22"));
							highLabel.setTextFill(Color.web("#22BB22"));
							lowLabel.setTextFill(Color.web("#22BB22"));
							totalSharesLabel.setTextFill(Color.web("#22BB22"));
							floatSharesLabel.setTextFill(Color.web("#22BB22"));
							averageVolumeLabel.setTextFill(Color.web("#22BB22"));
							discriptionLabel.setTextFill(Color.web("#22BB22"));
							CEOLabel.setTextFill(Color.web("#22BB22"));
							exchangeLabel.setTextFill(Color.web("#22BB22"));
							sectorLabel.setTextFill(Color.web("#22BB22"));
							industryLabel.setTextFill(Color.web("#22BB22"));
							websiteLabel.setTextFill(Color.web("#22BB22"));
							timeLabel.setTextFill(Color.web("#22BB22"));
							high52Label.setTextFill(Color.web("#22BB22"));
							low52Label.setTextFill(Color.web("#22BB22"));
							y5Label.setTextFill(Color.web("#22BB22"));
							y2Label.setTextFill(Color.web("#22BB22"));
							y1Label.setTextFill(Color.web("#22BB22"));
							m6Label.setTextFill(Color.web("#22BB22"));
							m3Label.setTextFill(Color.web("#22BB22"));
							m1Label.setTextFill(Color.web("#22BB22"));
							d5Label.setTextFill(Color.web("#22BB22"));
							label1.setTextFill(Color.web("#22BB22"));
							label2.setTextFill(Color.web("#22BB22"));
							label3.setTextFill(Color.web("#22BB22"));
							label4.setTextFill(Color.web("#22BB22"));
							label5.setTextFill(Color.web("#22BB22"));
							label6.setTextFill(Color.web("#22BB22"));
							label7.setTextFill(Color.web("#22BB22"));
							label8.setTextFill(Color.web("#22BB22"));
							label9.setTextFill(Color.web("#22BB22"));
							label10.setTextFill(Color.web("#22BB22"));
							label11.setTextFill(Color.web("#22BB22"));
							label12.setTextFill(Color.web("#22BB22"));
							label13.setTextFill(Color.web("#22BB22"));
							label14.setTextFill(Color.web("#22BB22"));
							label15.setTextFill(Color.web("#22BB22"));
							label16.setTextFill(Color.web("#22BB22"));
							label17.setTextFill(Color.web("#22BB22"));
							label18.setTextFill(Color.web("#22BB22"));
							label19.setTextFill(Color.web("#22BB22"));
							label20.setTextFill(Color.web("#22BB22"));
							label21.setTextFill(Color.web("#22BB22"));
							label22.setTextFill(Color.web("#22BB22"));
							label23.setTextFill(Color.web("#22BB22"));
							label24.setTextFill(Color.web("#22BB22"));
							label25.setTextFill(Color.web("#22BB22"));

						} else {
							tickerSearch.setFocusColor(Color.web("#FF0000"));
							tickerSearch.setUnFocusColor(Color.web("#FF0000"));
							updateMarketButton.setTextFill(Color.web("#FF0000"));
							updateMarketButton.setRipplerFill(Color.web("#FF0000"));
							currentPriceLabel.setTextFill(Color.web("#FF0000"));
							companyNameLabel.setTextFill(Color.web("#FF0000"));
							changeLabel.setTextFill(Color.web("#FF0000"));
							percentChangeLabel.setTextFill(Color.web("#FF0000"));
							openLabel.setTextFill(Color.web("#FF0000"));
							closeLabel.setTextFill(Color.web("#FF0000"));
							highLabel.setTextFill(Color.web("#FF0000"));
							lowLabel.setTextFill(Color.web("#FF0000"));
							totalSharesLabel.setTextFill(Color.web("#FF0000"));
							floatSharesLabel.setTextFill(Color.web("#FF0000"));
							averageVolumeLabel.setTextFill(Color.web("#FF0000"));
							discriptionLabel.setTextFill(Color.web("#FF0000"));
							CEOLabel.setTextFill(Color.web("#FF0000"));
							exchangeLabel.setTextFill(Color.web("#FF0000"));
							sectorLabel.setTextFill(Color.web("#FF0000"));
							industryLabel.setTextFill(Color.web("#FF0000"));
							websiteLabel.setTextFill(Color.web("#FF0000"));
							timeLabel.setTextFill(Color.web("#FF0000"));
							high52Label.setTextFill(Color.web("#FF0000"));
							low52Label.setTextFill(Color.web("#FF0000"));
							y5Label.setTextFill(Color.web("#FF0000"));
							y2Label.setTextFill(Color.web("#FF0000"));
							y1Label.setTextFill(Color.web("#FF0000"));
							m6Label.setTextFill(Color.web("#FF0000"));
							m3Label.setTextFill(Color.web("#FF0000"));
							m1Label.setTextFill(Color.web("#FF0000"));
							d5Label.setTextFill(Color.web("#FF0000"));
							label1.setTextFill(Color.web("#FF0000"));
							label2.setTextFill(Color.web("#FF0000"));
							label3.setTextFill(Color.web("#FF0000"));
							label4.setTextFill(Color.web("#FF0000"));
							label5.setTextFill(Color.web("#FF0000"));
							label6.setTextFill(Color.web("#FF0000"));
							label7.setTextFill(Color.web("#FF0000"));
							label8.setTextFill(Color.web("#FF0000"));
							label9.setTextFill(Color.web("#FF0000"));
							label10.setTextFill(Color.web("#FF0000"));
							label11.setTextFill(Color.web("#FF0000"));
							label12.setTextFill(Color.web("#FF0000"));
							label13.setTextFill(Color.web("#FF0000"));
							label14.setTextFill(Color.web("#FF0000"));
							label15.setTextFill(Color.web("#FF0000"));
							label16.setTextFill(Color.web("#FF0000"));
							label17.setTextFill(Color.web("#FF0000"));
							label18.setTextFill(Color.web("#FF0000"));
							label19.setTextFill(Color.web("#FF0000"));
							label20.setTextFill(Color.web("#FF0000"));
							label21.setTextFill(Color.web("#FF0000"));
							label22.setTextFill(Color.web("#FF0000"));
							label23.setTextFill(Color.web("#FF0000"));
							label24.setTextFill(Color.web("#FF0000"));
							label25.setTextFill(Color.web("#FF0000"));
						}

					} catch (NullPointerException e) {

					}
				} catch (NullPointerException e) {
					companyNameLabel.setText("Sorry ticker \"" + (tickerSearch.getEditor().getText()).toUpperCase() + "\" isn't supported by our system");
					currentPriceLabel.setText("N/A");
					changeLabel.setText("N/A");
					percentChangeLabel.setText("N/A");
					openLabel.setText("N/A");
					closeLabel.setText("N/A");
					highLabel.setText("N/A");
					lowLabel.setText("N/A");
					totalSharesLabel.setText("N/A");
					floatSharesLabel.setText("N/A");
					averageVolumeLabel.setText("N/A");
					discriptionLabel.setText("N/A");
					CEOLabel.setText("N/A");
					exchangeLabel.setText("N/A");
					sectorLabel.setText("N/A");
					industryLabel.setText("N/A");
					websiteLabel.setText("N/A");
					timeLabel.setText("N/A");
					high52Label.setText("N/A");
					low52Label.setText("N/A");
					y5Label.setText("N/A");
					y2Label.setText("N/A");
					y1Label.setText("N/A");
					m6Label.setText("N/A");
					m3Label.setText("N/A");
					m1Label.setText("N/A");
					d5Label.setText("N/A");
				} catch (ArrayIndexOutOfBoundsException e) {
					companyNameLabel.setText("Sorry ticker \"" + (tickerSearch.getEditor().getText()).toUpperCase() + "\" isn't supported by our system");
					currentPriceLabel.setText("N/A");
					changeLabel.setText("N/A");
					percentChangeLabel.setText("N/A");
					openLabel.setText("N/A");
					closeLabel.setText("N/A");
					highLabel.setText("N/A");
					lowLabel.setText("N/A");
					totalSharesLabel.setText("N/A");
					floatSharesLabel.setText("N/A");
					averageVolumeLabel.setText("N/A");
					discriptionLabel.setText("N/A");
					CEOLabel.setText("N/A");
					exchangeLabel.setText("N/A");
					sectorLabel.setText("N/A");
					industryLabel.setText("N/A");
					websiteLabel.setText("N/A");
					timeLabel.setText("N/A");
					high52Label.setText("N/A");
					low52Label.setText("N/A");
					y5Label.setText("N/A");
					y2Label.setText("N/A");
					y1Label.setText("N/A");
					m6Label.setText("N/A");
					m3Label.setText("N/A");
					m1Label.setText("N/A");
					d5Label.setText("N/A");
				}
			} else if (k.getCharacter().charAt(0) >= 'A' && k.getCharacter().charAt(0) <= 'z') {
				tickerSearch.getItems().clear();
				try {
					tickerSearch.getItems().addAll(stockTree.getTickerList((tickerSearch.getEditor().getText()).toUpperCase()));
					tickerSearch.show();
				} catch (NullPointerException e) {
				}
			} else if (k.getCharacter().charAt(0) == '\b') {
				tickerSearch.getItems().clear();
				try {
					tickerSearch.getItems().addAll(stockTree.getTickerList((tickerSearch.getEditor().getText()).toUpperCase()));
					tickerSearch.show();
				} catch (NullPointerException e) {
				}
			}
		});
	}

	@FXML 
	public void updateMarket() throws JSONException, IOException {
		app.create(stockMap, stockTree, null);
		try {
			companyNameLabel.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getCompanyName());
			try {
				currentPriceLabel.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getLastPrice());
				changeLabel.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getChange());
				percentChangeLabel.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getPercentChange()  + "%");
				openLabel.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getOpen());
				closeLabel.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getClose());
				highLabel.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getHigh());
				lowLabel.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getLow());
				totalSharesLabel.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getSharesOutstanding());
				floatSharesLabel.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getSharesFloat());
				averageVolumeLabel.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getAverageVolume());
				discriptionLabel.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getDiscription());
				CEOLabel.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getCEO());
				exchangeLabel.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getExchange());
				sectorLabel.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getSector());
				industryLabel.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getIndustry());
				websiteLabel.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getWebsite());
				timeLabel.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getTime());
				high52Label.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getWeek52High());
				low52Label.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getWeek52Low());
				y5Label.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getYear5ChangePercent());
				y2Label.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getYear2ChangePercent());
				y1Label.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getYear1ChangePercent());
				m6Label.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getMonth6ChangePercent());
				m3Label.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getMonth3ChangePercent());
				m1Label.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getMonth1ChangePercent());
				d5Label.setText(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getDay5ChangePercent());

				if (Double.parseDouble(stockMap.get((tickerSearch.getEditor().getText()).toUpperCase()).getChange()) >= 0) {
					tickerSearch.setFocusColor(Color.web("#22BB22"));
					tickerSearch.setUnFocusColor(Color.web("#22BB22"));
					updateMarketButton.setTextFill(Color.web("#22BB22"));
					updateMarketButton.setRipplerFill(Color.web("#22BB22"));
					currentPriceLabel.setTextFill(Color.web("#22BB22"));
					companyNameLabel.setTextFill(Color.web("#22BB22"));
					changeLabel.setTextFill(Color.web("#22BB22"));
					percentChangeLabel.setTextFill(Color.web("#22BB22"));
					openLabel.setTextFill(Color.web("#22BB22"));
					closeLabel.setTextFill(Color.web("#22BB22"));
					highLabel.setTextFill(Color.web("#22BB22"));
					lowLabel.setTextFill(Color.web("#22BB22"));
					totalSharesLabel.setTextFill(Color.web("#22BB22"));
					floatSharesLabel.setTextFill(Color.web("#22BB22"));
					averageVolumeLabel.setTextFill(Color.web("#22BB22"));
					discriptionLabel.setTextFill(Color.web("#22BB22"));
					CEOLabel.setTextFill(Color.web("#22BB22"));
					exchangeLabel.setTextFill(Color.web("#22BB22"));
					sectorLabel.setTextFill(Color.web("#22BB22"));
					industryLabel.setTextFill(Color.web("#22BB22"));
					websiteLabel.setTextFill(Color.web("#22BB22"));
					timeLabel.setTextFill(Color.web("#22BB22"));
					high52Label.setTextFill(Color.web("#22BB22"));
					low52Label.setTextFill(Color.web("#22BB22"));
					y5Label.setTextFill(Color.web("#22BB22"));
					y2Label.setTextFill(Color.web("#22BB22"));
					y1Label.setTextFill(Color.web("#22BB22"));
					m6Label.setTextFill(Color.web("#22BB22"));
					m3Label.setTextFill(Color.web("#22BB22"));
					m1Label.setTextFill(Color.web("#22BB22"));
					d5Label.setTextFill(Color.web("#22BB22"));
					label1.setTextFill(Color.web("#22BB22"));
					label2.setTextFill(Color.web("#22BB22"));
					label3.setTextFill(Color.web("#22BB22"));
					label4.setTextFill(Color.web("#22BB22"));
					label5.setTextFill(Color.web("#22BB22"));
					label6.setTextFill(Color.web("#22BB22"));
					label7.setTextFill(Color.web("#22BB22"));
					label8.setTextFill(Color.web("#22BB22"));
					label9.setTextFill(Color.web("#22BB22"));
					label10.setTextFill(Color.web("#22BB22"));
					label11.setTextFill(Color.web("#22BB22"));
					label12.setTextFill(Color.web("#22BB22"));
					label13.setTextFill(Color.web("#22BB22"));
					label14.setTextFill(Color.web("#22BB22"));
					label15.setTextFill(Color.web("#22BB22"));
					label16.setTextFill(Color.web("#22BB22"));
					label17.setTextFill(Color.web("#22BB22"));
					label18.setTextFill(Color.web("#22BB22"));
					label19.setTextFill(Color.web("#22BB22"));
					label20.setTextFill(Color.web("#22BB22"));
					label21.setTextFill(Color.web("#22BB22"));
					label22.setTextFill(Color.web("#22BB22"));
					label23.setTextFill(Color.web("#22BB22"));
					label24.setTextFill(Color.web("#22BB22"));
					label25.setTextFill(Color.web("#22BB22"));

				} else {
					tickerSearch.setFocusColor(Color.web("#FF0000"));
					tickerSearch.setUnFocusColor(Color.web("#FF0000"));
					updateMarketButton.setTextFill(Color.web("#FF0000"));
					updateMarketButton.setRipplerFill(Color.web("#FF0000"));
					currentPriceLabel.setTextFill(Color.web("#FF0000"));
					companyNameLabel.setTextFill(Color.web("#FF0000"));
					changeLabel.setTextFill(Color.web("#FF0000"));
					percentChangeLabel.setTextFill(Color.web("#FF0000"));
					openLabel.setTextFill(Color.web("#FF0000"));
					closeLabel.setTextFill(Color.web("#FF0000"));
					highLabel.setTextFill(Color.web("#FF0000"));
					lowLabel.setTextFill(Color.web("#FF0000"));
					totalSharesLabel.setTextFill(Color.web("#FF0000"));
					floatSharesLabel.setTextFill(Color.web("#FF0000"));
					averageVolumeLabel.setTextFill(Color.web("#FF0000"));
					discriptionLabel.setTextFill(Color.web("#FF0000"));
					CEOLabel.setTextFill(Color.web("#FF0000"));
					exchangeLabel.setTextFill(Color.web("#FF0000"));
					sectorLabel.setTextFill(Color.web("#FF0000"));
					industryLabel.setTextFill(Color.web("#FF0000"));
					websiteLabel.setTextFill(Color.web("#FF0000"));
					timeLabel.setTextFill(Color.web("#FF0000"));
					high52Label.setTextFill(Color.web("#FF0000"));
					low52Label.setTextFill(Color.web("#FF0000"));
					y5Label.setTextFill(Color.web("#FF0000"));
					y2Label.setTextFill(Color.web("#FF0000"));
					y1Label.setTextFill(Color.web("#FF0000"));
					m6Label.setTextFill(Color.web("#FF0000"));
					m3Label.setTextFill(Color.web("#FF0000"));
					m1Label.setTextFill(Color.web("#FF0000"));
					d5Label.setTextFill(Color.web("#FF0000"));
					label1.setTextFill(Color.web("#FF0000"));
					label2.setTextFill(Color.web("#FF0000"));
					label3.setTextFill(Color.web("#FF0000"));
					label4.setTextFill(Color.web("#FF0000"));
					label5.setTextFill(Color.web("#FF0000"));
					label6.setTextFill(Color.web("#FF0000"));
					label7.setTextFill(Color.web("#FF0000"));
					label8.setTextFill(Color.web("#FF0000"));
					label9.setTextFill(Color.web("#FF0000"));
					label10.setTextFill(Color.web("#FF0000"));
					label11.setTextFill(Color.web("#FF0000"));
					label12.setTextFill(Color.web("#FF0000"));
					label13.setTextFill(Color.web("#FF0000"));
					label14.setTextFill(Color.web("#FF0000"));
					label15.setTextFill(Color.web("#FF0000"));
					label16.setTextFill(Color.web("#FF0000"));
					label17.setTextFill(Color.web("#FF0000"));
					label18.setTextFill(Color.web("#FF0000"));
					label19.setTextFill(Color.web("#FF0000"));
					label20.setTextFill(Color.web("#FF0000"));
					label21.setTextFill(Color.web("#FF0000"));
					label22.setTextFill(Color.web("#FF0000"));
					label23.setTextFill(Color.web("#FF0000"));
					label24.setTextFill(Color.web("#FF0000"));
					label25.setTextFill(Color.web("#FF0000"));
				}

			} catch (NullPointerException e) {

			}
		} catch (NullPointerException e) {
			companyNameLabel.setText("Sorry ticker \"" + (tickerSearch.getEditor().getText()).toUpperCase() + "\" isn't supported by our system");
			currentPriceLabel.setText("N/A");
			changeLabel.setText("N/A");
			percentChangeLabel.setText("N/A");
			openLabel.setText("N/A");
			closeLabel.setText("N/A");
			highLabel.setText("N/A");
			lowLabel.setText("N/A");
			totalSharesLabel.setText("N/A");
			floatSharesLabel.setText("N/A");
			averageVolumeLabel.setText("N/A");
			discriptionLabel.setText("N/A");
			CEOLabel.setText("N/A");
			exchangeLabel.setText("N/A");
			sectorLabel.setText("N/A");
			industryLabel.setText("N/A");
			websiteLabel.setText("N/A");
			timeLabel.setText("N/A");
			high52Label.setText("N/A");
			low52Label.setText("N/A");
			y5Label.setText("N/A");
			y2Label.setText("N/A");
			y1Label.setText("N/A");
			m6Label.setText("N/A");
			m3Label.setText("N/A");
			m1Label.setText("N/A");
			d5Label.setText("N/A");
		} catch (ArrayIndexOutOfBoundsException e) {
			companyNameLabel.setText("Sorry ticker \"" + (tickerSearch.getEditor().getText()).toUpperCase() + "\" isn't supported by our system");
			currentPriceLabel.setText("N/A");
			changeLabel.setText("N/A");
			percentChangeLabel.setText("N/A");
			openLabel.setText("N/A");
			closeLabel.setText("N/A");
			highLabel.setText("N/A");
			lowLabel.setText("N/A");
			totalSharesLabel.setText("N/A");
			floatSharesLabel.setText("N/A");
			averageVolumeLabel.setText("N/A");
			discriptionLabel.setText("N/A");
			CEOLabel.setText("N/A");
			exchangeLabel.setText("N/A");
			sectorLabel.setText("N/A");
			industryLabel.setText("N/A");
			websiteLabel.setText("N/A");
			timeLabel.setText("N/A");
			high52Label.setText("N/A");
			low52Label.setText("N/A");
			y5Label.setText("N/A");
			y2Label.setText("N/A");
			y1Label.setText("N/A");
			m6Label.setText("N/A");
			m3Label.setText("N/A");
			m1Label.setText("N/A");
			d5Label.setText("N/A");
		}
	}

}

