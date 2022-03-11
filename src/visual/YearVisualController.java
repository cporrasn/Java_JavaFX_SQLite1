package visual;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Controller;
import model.Year;
import static visual.PrincipalVisualController.primaryStageListClientes;

public class YearVisualController {

    private static AnchorPane page;
    public static Stage primaryStageListYears = new Stage();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField yearTextField;

    @FXML
    private Button deleteYearButton;

    @FXML
    private Label yearLabel;

    @FXML
    private TableColumn yearTableColumn;

    @FXML
    private Button backButton;

    @FXML
    private AnchorPane YearAnchorPane;

    @FXML
    private TableView<Year> YearTableView;

    @FXML
    private Button updateYearButton;

    @FXML
    private Button insertYearButton;

    @FXML
    private Pane YearWindowPane;

    public static ObservableList<Year> listYears = FXCollections.observableArrayList();

    @FXML
    void insertYearButtonAction(ActionEvent event) throws ClassNotFoundException, SQLException {
        String year = yearTextField.getText();
        if (!yearTextField.getText().isEmpty()) {
            if (Controller.getController().insertYear(year)) {
                yearTextField.clear();
                listYears.clear();
                listYears.addAll(Controller.getController().getYearsFromDataBase());
            } else {
                yearTextField.clear();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error ");
                alert.setHeaderText("El año "+year+" ya existe.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Debe llenar el campo Año.");
            alert.showAndWait();
        }

    }

    @FXML
    void updateYearButtonAction(ActionEvent event) throws ClassNotFoundException, SQLException {
        String year = yearTextField.getText();
        if (YearTableView.getSelectionModel().getSelectedItem() != null && !yearTextField.getText().isEmpty()) {
            if (Controller.getController().updateYear(YearTableView.getSelectionModel().getSelectedItem().getIdYear(), Integer.valueOf(year))) {
                yearTextField.clear();
                listYears.clear();
                listYears.addAll(Controller.getController().getYearsFromDataBase());
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error ");
                alert.setHeaderText("El año "+year+" ya existe.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Debe llenar el campo Año.");
            alert.showAndWait();
        }

    }

    @FXML
    void deleteYearButtonAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (YearTableView.getSelectionModel().getSelectedItem() != null) {
            Controller.getController().deleteYear(YearTableView.getSelectionModel().getSelectedItem().getIdYear());
            listYears.clear();
            listYears.addAll(Controller.getController().getYearsFromDataBase());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Debe seleccionar un año de la tabla para ser eliminado.");
            alert.showAndWait();
        }

    }

    @FXML
    void backButtonAction(ActionEvent event) throws IOException {
        Stage primaryStagePrincipal = PrincipalVisualController.initialicePage();
        Principal.getPrincipal().getListScene().add(primaryStagePrincipal);
    }

    @FXML
    void initialize() throws ClassNotFoundException, SQLException {
        assert yearTextField != null : "fx:id=\"yearTextField\" was not injected: check your FXML file 'YearVisual.fxml'.";
        assert deleteYearButton != null : "fx:id=\"deleteYearButton\" was not injected: check your FXML file 'YearVisual.fxml'.";
        assert yearLabel != null : "fx:id=\"yearLabel\" was not injected: check your FXML file 'YearVisual.fxml'.";
        assert yearTableColumn != null : "fx:id=\"yearTableColumn\" was not injected: check your FXML file 'YearVisual.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'YearVisual.fxml'.";
        assert YearAnchorPane != null : "fx:id=\"YearAnchorPane\" was not injected: check your FXML file 'YearVisual.fxml'.";
        assert YearTableView != null : "fx:id=\"YearTableView\" was not injected: check your FXML file 'YearVisual.fxml'.";
        assert updateYearButton != null : "fx:id=\"updateYearButton\" was not injected: check your FXML file 'YearVisual.fxml'.";
        assert insertYearButton != null : "fx:id=\"insertYearButton\" was not injected: check your FXML file 'YearVisual.fxml'.";
        assert YearWindowPane != null : "fx:id=\"YearWindowPane\" was not injected: check your FXML file 'YearVisual.fxml'.";

        configureTableYear();
        listYears.clear();
        listYears.addAll(Controller.getController().getYearsFromDataBase());
    }

    @FXML
    void selectYearTable(MouseEvent event) {
        if (event.getClickCount() > 0) {
            if (YearTableView.getSelectionModel().getSelectedItem() != null) {
                Year year = YearTableView.getSelectionModel().getSelectedItem();
                yearTextField.setText(String.valueOf(year.getYear().getValue()));
            }
        }
    }

    @FXML
    void validate(KeyEvent event) {
        util.Validate.numbersOnly(yearTextField, 4);
    }

    private void configureTableYear() {
        this.yearTableColumn.setCellValueFactory(new PropertyValueFactory<Year, Integer>("idYear"));

        this.YearTableView.setItems(listYears);
        assert this.YearTableView.getItems() == listYears;
        this.YearTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        //  final ObservableList<Demanda> tableSelection = this.tvTablaDem.getSelectionModel().getSelectedItems();
        //  tableSelection.addListener(tableSelectionChangedDem);
    }

    public static Stage initialicePage() throws IOException {
        page = (AnchorPane) FXMLLoader.load(Principal.class
                .getResource("YearVisual.fxml"));

        Scene scene = new Scene(page);

        primaryStageListClientes.setScene(scene);

        primaryStageListClientes.setTitle(
                "ControlGym");
        primaryStageListClientes.setResizable(
                false);
        primaryStageListClientes.show();
        return primaryStageListClientes;
    }
}
