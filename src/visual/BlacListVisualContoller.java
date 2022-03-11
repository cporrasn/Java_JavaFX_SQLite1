/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visual;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Client;
import model.Controller;
import static visual.PrincipalVisualController.primaryStageListClientes;

/**
 *
 * @author JULIETA
 */
public class BlacListVisualContoller {

    private static AnchorPane page;
    public static Stage primaryStageListBlacList = new Stage();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> yearComboBox;

    @FXML
    private Button pdfExportButton;

    @FXML
    private TableColumn nameClientTableColum;

    @FXML
    private TableColumn sexClientTableColumn;

    @FXML
    private Pane blackListPane;

    @FXML
    private TableView debtClientsTableView;

    @FXML
    private Label yearLabel;

    @FXML
    private ComboBox<String> monthComboBox;

    @FXML
    private Label monthLabel;

    @FXML
    private AnchorPane blackListAnchorPane;

    @FXML
    private Button seeDebtClientsButton;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn lastNamesClientTableColumn;

    public static ObservableList<Client> debtClients = FXCollections.observableArrayList();

    public static Stage initialicePage() throws IOException {
        page = (AnchorPane) FXMLLoader.load(Principal.class
                .getResource("BlackListVisual.fxml"));

        Scene scene = new Scene(page);

        primaryStageListClientes.setScene(scene);

        primaryStageListClientes.setTitle(
                "ControlGym");
        primaryStageListClientes.setResizable(
                false);
        primaryStageListClientes.show();
        return primaryStageListClientes;
    }

    private void configureTableDebtClients() {
        this.nameClientTableColum.setCellValueFactory(new PropertyValueFactory<Client, String>("clientName"));
        this.lastNamesClientTableColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("clientLastNames"));
        this.sexClientTableColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("clientSex"));
        this.debtClientsTableView.setItems(debtClients);
        assert this.debtClientsTableView.getItems() == debtClients;
        this.debtClientsTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        //  final ObservableList<Demanda> tableSelection = this.tvTablaDem.getSelectionModel().getSelectedItems();
        //  tableSelection.addListener(tableSelectionChangedDem);
    }

    public void loadYearCombobox() throws ClassNotFoundException, SQLException {
        ObservableList select = FXCollections.observableArrayList(Controller.getController().getYearsNamesFromDataBase());
        yearComboBox.setItems(select);
    }

    public void loadMonthCombobox() throws ClassNotFoundException, SQLException {
        ObservableList select = FXCollections.observableArrayList(Controller.getController().getMonthNamesFromDataBase());
        monthComboBox.setItems(select);
    }

    @FXML
    void yearComboBoxAction(ActionEvent event) {

    }

    @FXML
    void monthComboBoxAction(ActionEvent event) {

    }

    @FXML
    void seeDebtClientsButtonAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        int idYear = Controller.getController().getYear(yearComboBox.getSelectionModel().getSelectedItem());
        int idMonth = Controller.getController().getMonth(monthComboBox.getSelectionModel().getSelectedItem());
        if (yearComboBox.getSelectionModel().getSelectedItem() != null && monthComboBox.getSelectionModel().getSelectedItem() != null) {
            debtClients.clear();
            debtClients.addAll(Controller.getController().blackList(idYear, idMonth));
            configureTableDebtClients();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Debe seleccionar el mes y el año.");
            alert.showAndWait();
        }
    }

    @FXML
    void pdfExportButtonAction(ActionEvent event) throws FileNotFoundException {
        try {
            FileChooser fileChooser = new FileChooser();
        Stage a = new Stage();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(a);
            if (file!=null) {
                OutputStream file1 = new FileOutputStream(file.getAbsolutePath());

            Document document = new Document();
            PdfWriter.getInstance(document, file1);
            document.open();
            Paragraph p = new Paragraph("Clientes que no han pagado el mes de " + monthComboBox.getSelectionModel().getSelectedItem() + ".", FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new CMYKColor(0, 255, 0, 0)));
            p.setAlignment(1);
            document.add(p);
            document.add(new Paragraph("\n"));
            PdfPTable pt = new PdfPTable(1);
            pt.addCell("Nombre y Apellidos");

            for (Client client : debtClients) {
                pt.addCell(client.getClientName().getValue() + " " + client.getClientLastNames().getValue());
            }
            document.add(pt);

            document.close();
            file1.close();
            }
  

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @FXML
    void backButtonAction(ActionEvent event) throws IOException {
        Stage primaryStagePrincipal = PrincipalVisualController.initialicePage();
        Principal.getPrincipal().getListScene().add(primaryStagePrincipal);
    }

    @FXML
    void initialize() throws ClassNotFoundException, SQLException {
        assert yearComboBox != null : "fx:id=\"yearComboBox\" was not injected: check your FXML file 'BlacListVisual.fxml'.";
        assert pdfExportButton != null : "fx:id=\"pdfExportButton\" was not injected: check your FXML file 'BlacListVisual.fxml'.";
        assert nameClientTableColum != null : "fx:id=\"nameClientTableColum\" was not injected: check your FXML file 'BlacListVisual.fxml'.";
        assert sexClientTableColumn != null : "fx:id=\"sexClientTableColumn\" was not injected: check your FXML file 'BlacListVisual.fxml'.";
        assert blackListPane != null : "fx:id=\"blackListPane\" was not injected: check your FXML file 'BlacListVisual.fxml'.";
        assert debtClientsTableView != null : "fx:id=\"debtClientsTableView\" was not injected: check your FXML file 'BlacListVisual.fxml'.";
        assert yearLabel != null : "fx:id=\"yearLabel\" was not injected: check your FXML file 'BlacListVisual.fxml'.";
        assert monthComboBox != null : "fx:id=\"monthComboBox\" was not injected: check your FXML file 'BlacListVisual.fxml'.";
        assert monthLabel != null : "fx:id=\"monthLabel\" was not injected: check your FXML file 'BlacListVisual.fxml'.";
        assert blackListAnchorPane != null : "fx:id=\"blackListAnchorPane\" was not injected: check your FXML file 'BlacListVisual.fxml'.";
        assert seeDebtClientsButton != null : "fx:id=\"seeDebtClientsButton\" was not injected: check your FXML file 'BlacListVisual.fxml'.";
        assert lastNamesClientTableColumn != null : "fx:id=\"lastNamesClientTableColumn\" was not injected: check your FXML file 'BlacListVisual.fxml'.";

        loadMonthCombobox();
        loadYearCombobox();

    }

}
