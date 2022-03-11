/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visual;

/**
 *
 * @author CYNTHIA
 */
import com.sun.imageio.plugins.common.SubImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import model.Client;
import model.ClientAccount;
import model.Controller;
import util.ConversionImage;

public class PrincipalVisualController {

    private static AnchorPane page;
    public static Stage primaryStageListClientes = new Stage();

    @FXML
    private AnchorPane anchorPage;

    @FXML
    private Label lastNamesLabel;

    @FXML
    private Button showAccountsButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button updateClientButton;

    @FXML
    private MenuBar menuBar1;

    @FXML
    private TextField findClientTextField;

    @FXML
    private Label monthLabel;

    @FXML
    private TableColumn asistanceTableColumn;

    @FXML
    private TableColumn sexTableColum;

    @FXML
    private CheckBox paymentCheckBox;

    @FXML
    private Pane clientFieldPane;

    @FXML
    private AnchorPane ClientAnchorPane;

    @FXML
    private TextField nameClientTextField;

    @FXML
    private Button addClientPictureButton;

    @FXML
    private Button deleteClientButton;

    @FXML
    private ImageView idPicture;

    @FXML
    private TableView<ClientAccount> ClientAccountTableView;

    @FXML
    private AnchorPane clientAccountAnchorPane;

    @FXML
    private Button updateButton;

    @FXML
    private Pane insertClientPane;

    @FXML
    private Button addAccountButton;

    @FXML
    private ComboBox<String> yearComboBox;

    @FXML
    private ComboBox<String> sexClientComboBox;

    @FXML
    private TableView<Client> ClientTableView;

    @FXML
    private Pane clientAccountPane;

    @FXML
    private Label yearLabel;

    @FXML
    private Label sexClientLabel;

    @FXML
    private Button promoteClientButton;

    @FXML
    private TableColumn lastaNamesTableColum;

    @FXML
    private Button insertButton;

    @FXML
    private Label nameClientLabel;

    @FXML
    private MenuItem CloseMenuItem;

    @FXML
    private Button insertClientButton;

    @FXML
    private TableColumn yearTableColumn;

    @FXML
    private CheckBox asistanceCheckBox;

    @FXML
    private ComboBox<String> monthComboBox;

    @FXML
    private MenuItem blackListMenuItem;

    @FXML
    private Menu reportMenu;

    @FXML
    private Button tableRefreshButton;

    @FXML
    private TableColumn monthTableColumn;

    @FXML
    private TableColumn nameTableColum;

    @FXML
    private TableColumn paymentTableColumn;

    @FXML
    private TextField lastNamesClientTextField;

    @FXML
    private ProgressIndicator progress;

    @FXML
    private HBox hbprogress;

    Task copyWorker;

    public static ObservableList<Client> listClients = FXCollections.observableArrayList();
    public static ObservableList<ClientAccount> listAccounts = FXCollections.observableArrayList();
    public static ObservableList<Client> listFoundedClients = FXCollections.observableArrayList();

 //   public static String urlImage ="src//img//imagenes.png";
    public static byte[] imagen=null;

    @FXML
    void initialize() throws ClassNotFoundException, SQLException {
        assert lastNamesLabel != null : "fx:id=\"lastNamesLabel\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";
        assert showAccountsButton != null : "fx:id=\"showAccountsButton\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";
        assert deleteButton != null : "fx:id=\"deleteButton\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";
        assert updateClientButton != null : "fx:id=\"updateClientButton\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";
        assert menuBar1 != null : "fx:id=\"menuBar1\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";
        assert findClientTextField != null : "fx:id=\"findClientTextField\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";
        assert monthLabel != null : "fx:id=\"monthLabel\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";
        assert asistanceTableColumn != null : "fx:id=\"asistanceTableColumn\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";
        assert sexTableColum != null : "fx:id=\"sexTableColum\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";
        assert paymentCheckBox != null : "fx:id=\"paymentCheckBox\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";
        assert clientFieldPane != null : "fx:id=\"clientFieldPane\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";
        assert ClientAnchorPane != null : "fx:id=\"ClientAnchorPane\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";
        assert nameClientTextField != null : "fx:id=\"nameClientTextField\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";
        assert ClientAccountTableView != null : "fx:id=\"ClientAccountTableView\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";
        assert clientAccountAnchorPane != null : "fx:id=\"clientAccountAnchorPane\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";
        assert updateButton != null : "fx:id=\"updateButton\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";
        assert insertClientPane != null : "fx:id=\"insertClientPane\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";
        assert addAccountButton != null : "fx:id=\"addAccountButton\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";
        assert yearComboBox != null : "fx:id=\"yearComboBox\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";
        assert ClientTableView != null : "fx:id=\"ClientTableView\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";
        assert clientAccountPane != null : "fx:id=\"clientAccountPane\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";
        assert yearLabel != null : "fx:id=\"yearLabel\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";
        assert deleteClientButton != null : "fx:id=\"deleteClientButton\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";
        assert sexClientLabel != null : "fx:id=\"sexClientLabel\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";
        assert promoteClientButton != null : "fx:id=\"promoteClientButton\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";
        assert lastaNamesTableColum != null : "fx:id=\"lastaNamesTableColum\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";
        assert insertButton != null : "fx:id=\"insertButton\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";
        assert nameClientLabel != null : "fx:id=\"nameClientLabel\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";
        assert CloseMenuItem != null : "fx:id=\"CloseMenuItem\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";
        assert insertClientButton != null : "fx:id=\"insertClientButton\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";
        assert yearTableColumn != null : "fx:id=\"yearTableColumn\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";
        assert asistanceCheckBox != null : "fx:id=\"asistanceCheckBox\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";
        assert monthComboBox != null : "fx:id=\"monthComboBox\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";
//        assert findClientButton != null : "fx:id=\"findClientButton\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";
        assert monthTableColumn != null : "fx:id=\"monthTableColumn\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";
        assert nameTableColum != null : "fx:id=\"nameTableColum\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";
        assert paymentTableColumn != null : "fx:id=\"paymentTableColumn\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";
        assert lastNamesClientTextField != null : "fx:id=\"lastNamesClientTextField\" was not injected: check your FXML file 'PrincipalVisual.fxml'.";

        configureTableAccountes();
        configureTableClients();
        clientAccountPane.setVisible(false);
        insertClientPane.setVisible(false);
        listClients.clear();
        listClients.addAll(Controller.getController().getClientsFromDataBase());
        listAccounts.clear();
        loadSexClientComboBox();
        loadYearCombobox();
        loadMonthCombobox();

    }

    public void loadSexClientComboBox() {
        ObservableList select = FXCollections.observableArrayList("F", "M");
        sexClientComboBox.setItems(select);
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
    void close(ActionEvent event) throws Exception {

    }

    @FXML
    void addClientPictureButtonAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        Stage a = new Stage();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.jpg)", "*.jpg");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(a);
        if (file != null) {
//            urlImage = file.getAbsolutePath();
            ImageInputStream imgfo1 = new FileImageInputStream(file);
            BufferedImage imagefo1 = ImageIO.read(imgfo1);
            byte[] imgBytefo1 = ConversionImage.BufferedImage_to_Byte_Array_JPG(imagefo1);
            Image imgShowfo1 = ConversionImage.getImagefromByteArray(imgBytefo1);
            idPicture.setImage(imgShowfo1);
            imagen=imgBytefo1;
        }
    }

    @FXML
    void deleteClientPictureButtonAction(ActionEvent event) throws IOException {
        File clientImg = new File("src//img//imagenes.png");
        File filefo1 = new File(clientImg.getAbsolutePath());
        ImageInputStream imgfo1 = new FileImageInputStream(filefo1);
        BufferedImage imagefo1 = ImageIO.read(imgfo1);
        byte[] imgBytefo1 = ConversionImage.BufferedImage_to_Byte_Array_JPG(imagefo1);
        Image imgShowfo1 = ConversionImage.getImagefromByteArray(imgBytefo1);
        idPicture.setImage(imgShowfo1);
        imagen=imgBytefo1;
    }

//    @FXML
//    void findClientButtonAction(ActionEvent event) throws ClassNotFoundException, SQLException {
//        CharSequence cs = findClientTextField.getText().subSequence(0, findClientTextField.getText().length());
//        ArrayList<Client> clients = Controller.getController().getFoundedClientsFromDataBase(cs);
//        if (!clients.isEmpty()) {
//            listFoundedClients.clear();
//            listFoundedClients.addAll(clients);
//            setClientsFounded();
//        }
//    }
    @FXML
    void findClientOnAKeyPresed(KeyEvent keyEvent) throws ClassNotFoundException, SQLException {
        util.Validate.lettersOnly(findClientTextField);
        CharSequence cs = findClientTextField.getText().subSequence(0, findClientTextField.getText().length());
        ArrayList<Client> clients = Controller.getController().getFoundedClientsFromDataBase(cs);
        if (!clients.isEmpty()) {
            listFoundedClients.clear();
            listFoundedClients.addAll(clients);
            setClientsFounded();
        }
        if (findClientTextField.getText().isEmpty()) {
            listClients.clear();
            listClients.addAll(Controller.getController().getClientsFromDataBase());
            configureTableClients();
        }
    }

    @FXML
    void showAccountsButtonAction(ActionEvent event) throws ClassNotFoundException, SQLException {
        if (ClientTableView.getSelectionModel().getSelectedItem() != null) {
            clientAccountPane.setVisible(true);
            insertClientPane.setVisible(true);
            listAccounts.clear();
            listAccounts.addAll(Controller.getController().getClientAcuontsFromDataBase(ClientTableView.getSelectionModel().getSelectedItem().getIdClient()));
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Seleccione un cliente");
            alert.showAndWait();
        }
    }

    @FXML
    void findClientTextFieldAction(KeyEvent event) {

    }

    @FXML
    void promoteClientButtonAction(ActionEvent event) {
        insertClientPane.setVisible(true);
        clientAccountPane.setVisible(false);
    }

    @FXML
    void validateName(KeyEvent event) {
        util.Validate.lettersOnly(nameClientTextField);
    }

    @FXML
    void validateLastName(KeyEvent event) {
        util.Validate.lettersOnly(lastNamesClientTextField);

    }

    public void clearClientFields() throws IOException {
        nameClientTextField.clear();
        lastNamesClientTextField.clear();
        sexClientComboBox.getSelectionModel().select(-1);
        File clientImg = new File("src//img//imagenes.png");
        File filefo1 = new File(clientImg.getAbsolutePath());
        ImageInputStream imgfo1 = new FileImageInputStream(filefo1);
        BufferedImage imagefo1 = ImageIO.read(imgfo1);
        byte[] imgBytefo1 = ConversionImage.BufferedImage_to_Byte_Array_JPG(imagefo1);
        Image imgShowfo1 = ConversionImage.getImagefromByteArray(imgBytefo1);
        idPicture.setImage(imgShowfo1);
        imagen=imgBytefo1;
    }

    public void clearClientAccountFields() {
        asistanceCheckBox.setSelected(false);
        paymentCheckBox.setSelected(false);
        yearComboBox.getSelectionModel().select("Seleccione");
        monthComboBox.getSelectionModel().select("Seleccione");
    }

    @FXML
    void insertClientButtonAction(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        String name = nameClientTextField.getText();
        String lastNames = lastNamesClientTextField.getText();
        String sex = sexClientComboBox.getSelectionModel().getSelectedItem();
        byte[] by = imagen;

        if (!name.isEmpty() && !lastNames.isEmpty() && !sexClientComboBox.getSelectionModel().isSelected(-1)) {
                if (Controller.getController().insertClient(name, lastNames, sex, by)) {
                    clearClientFields();
                    listClients.clear();
                    listClients.addAll(Controller.getController().getClientsFromDataBase());
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error ");
                    alert.setHeaderText("Ya existe un cliente con el nombre " + name + " " + lastNames + ".");
                    alert.showAndWait();
                }
            
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Llene todos los campos.");
            alert.showAndWait();
        }

    }

    @FXML
    void tableRefreshButtonAction(ActionEvent event) throws ClassNotFoundException, SQLException {
        listClients.clear();
        listClients.addAll(Controller.getController().getClientsFromDataBase());
        configureTableClients();
        insertClientPane.setVisible(false);
        clientAccountPane.setVisible(false);
        findClientTextField.clear();
    }

    @FXML
    void updateClientButtonAction(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {

        if (ClientTableView.getSelectionModel().getSelectedItem() != null) {
            String name = nameClientTextField.getText();
            String lastNames = lastNamesClientTextField.getText();
            String sex = sexClientComboBox.getSelectionModel().getSelectedItem();
            byte[] by =imagen;
            if (!name.isEmpty() && !lastNames.isEmpty() && sex != null) {
                
                if (Controller.getController().updateClient(ClientTableView.getSelectionModel().getSelectedItem().getIdClient(), name, lastNames, sex, by)) {
                    listClients.clear();
                    listClients.addAll(Controller.getController().getClientsFromDataBase());
                    clearClientFields();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error ");
                    alert.setHeaderText("Ya existe un cliente con el nombre " + name + " " + lastNames + ".");
                    alert.showAndWait();
                }
//                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error ");
                alert.setHeaderText("Llene todos los campos.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Seleccione un cliente de la tabla de clientes para ser modificado.");
            alert.showAndWait();
        }
    }

    @FXML
    void deleteClientButtonAction(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        if (ClientTableView.getSelectionModel().getSelectedItem() != null) {
            Controller.getController().deleteClient(ClientTableView.getSelectionModel().getSelectedItem().getIdClient());
            listClients.clear();
            listClients.addAll(Controller.getController().getClientsFromDataBase());
            clearClientFields();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Seleccione un cliente de la tabla de clientes para ser eliminado.");
            alert.showAndWait();
        }
    }

    /*    @FXML
     void addClientAccountButtonAction(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
     String name = nameClientTextField.getText();
     String lastNames = lastNamesClientTextField.getText();
     String sex = sexClientComboBox.getSelectionModel().getSelectedItem();
     File file = new File(urlImage);
     ImageInputStream imgfo1 = new FileImageInputStream(file);
     BufferedImage imagefo1 = ImageIO.read(imgfo1);
     byte[] by = ConversionImage.BufferedImage_to_Byte_Array_JPG(imagefo1);
     if(!ClientTableView.getSelectionModel().isEmpty()){
     if (!name.isEmpty() && !lastNames.isEmpty() && sex != null) {     
     if (Controller.getController().insertClient(name, lastNames, sex, by)) {
     clearClientFields();
     listClients.clear();
     listClients.addAll(Controller.getController().getClientsFromDataBase());
     }
     listAccounts.clear();
     listAccounts.addAll(Controller.getController().getClientAcuontsFromDataBase(ClientTableView.getSelectionModel().getSelectedItem().getIdClient()));
     clientAccountPane.setVisible(true);
     insertClientPane.setVisible(false);
        
     } else {
     Alert alert = new Alert(Alert.AlertType.ERROR);
     alert.setTitle("Error ");
     alert.setHeaderText("Llene todos los campos.");
     alert.showAndWait();
        
     }
     }else{
     Alert alert = new Alert(Alert.AlertType.ERROR);
     alert.setTitle("Error ");
     alert.setHeaderText("Seleccione un cliente");
     alert.showAndWait();
     }  
     }*/
    @FXML
    void insertAccountButtonAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (ClientTableView.getSelectionModel().getSelectedItem() != null) {
            if (yearComboBox.getSelectionModel().getSelectedItem() != null && monthComboBox.getSelectionModel().getSelectedItem() != null) {
                int idClient = ClientTableView.getSelectionModel().getSelectedItem().getIdClient();
                int idYear = Controller.getController().getYear(yearComboBox.getSelectionModel().getSelectedItem());
                int idMonth = Controller.getController().getMonth(monthComboBox.getSelectionModel().getSelectedItem());
                String asistance = null;
                if (asistanceCheckBox.isSelected()) {
                    asistance = "si";
                } else {
                    asistance = "no";
                }
                String payment = null;
                if (paymentCheckBox.isSelected()) {
                    payment = "si";
                } else {
                    payment = "no";
                }
                if (Controller.getController().insertClientAccount(idClient, idYear, idMonth, asistance, payment)) {
                    clearClientAccountFields();
                    listAccounts.clear();
                    ArrayList<ClientAccount> lista = Controller.getController().getClientAcuontsFromDataBase(idClient);
                    listAccounts.addAll(lista);

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error ");
                    alert.setHeaderText("El cliente ya tiene una cuenta con ese mes y ese año.");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error ");
                alert.setHeaderText("Llene todos los campos");
                alert.showAndWait();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Seleccione un cliente");
            alert.showAndWait();
        }
    }

    @FXML
    void updateAccountButtonAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (ClientAccountTableView.getSelectionModel().getSelectedItem() != null) {
            int idYear = Controller.getController().getYear(yearComboBox.getSelectionModel().getSelectedItem());
            int idMonth = Controller.getController().getMonth(monthComboBox.getSelectionModel().getSelectedItem());
            String asistance = null;
            if (asistanceCheckBox.isSelected()) {
                asistance = "si";
            } else {
                asistance = "no";
            }
            String payment = null;
            if (paymentCheckBox.isSelected()) {
                payment = "si";
                asistance = "si";
            } else {
                payment = "no";
            }
            if (Controller.getController().updateClientAccount(ClientAccountTableView.getSelectionModel().getSelectedItem().getIdClientAccount(), ClientAccountTableView.getSelectionModel().getSelectedItem().getIdClient(), idYear, idMonth, asistance, payment)) {
                ArrayList<ClientAccount> lista = Controller.getController().getClientAcuontsFromDataBase(ClientAccountTableView.getSelectionModel().getSelectedItem().getIdClient());
                listAccounts.clear();
                listAccounts.addAll(lista);
                clearClientAccountFields();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error ");
                alert.setHeaderText("La cuenta ya existe.");
                alert.showAndWait();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Seleccione una cuenta");
            alert.showAndWait();
        }
    }

    @FXML
    void deleteAccountButtonAction(ActionEvent event) throws ClassNotFoundException, SQLException {
        if (ClientAccountTableView.getSelectionModel().getSelectedItem() != null) {
            Controller.getController().deleteClientAccount(ClientAccountTableView.getSelectionModel().getSelectedItem().getIdClientAccount(), ClientAccountTableView.getSelectionModel().getSelectedItem().getIdClient());
            clearClientAccountFields();
            ArrayList<ClientAccount> lista = Controller.getController().getClientAcuontsFromDataBase(ClientAccountTableView.getSelectionModel().getSelectedItem().getIdClient());
            listAccounts.clear();
            listAccounts.addAll(lista);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Seleccione una cuenta");
            alert.showAndWait();
        }
    }

    @FXML
    void yearComboBoxAction(ActionEvent event
    ) {

    }

    @FXML
    void monthComboBoxAction(ActionEvent event
    ) {

    }

    @FXML
    void selectTableAccountent(MouseEvent event) throws SQLException, ClassNotFoundException {
        if (event.getClickCount() > 0) {
            if (ClientAccountTableView.getSelectionModel().getSelectedItem() != null) {
                ClientAccount ca = ClientAccountTableView.getSelectionModel().getSelectedItem();
                yearComboBox.getSelectionModel().select(String.valueOf(Controller.getController().getYear(ca.getIdYear()).getYear().getValue())); //tienes que hacer un metodo que busque el year por su id
                monthComboBox.getSelectionModel().select(String.valueOf(Controller.getController().getMonth(ca.getIdMonth()).getMonth().getValue()));
                if (ca.getAssistance().getValue().equalsIgnoreCase("si")) {
                    asistanceCheckBox.setSelected(true);
                } else {
                    asistanceCheckBox.setSelected(false);
                }
                if (ca.getPayment().getValue().equalsIgnoreCase("si")) {
                    paymentCheckBox.setSelected(true);
                } else {
                    paymentCheckBox.setSelected(false);
                }
            }
        }
    }

    @FXML
    void paymentCheckBoxAction(ActionEvent event
    ) {
        asistanceCheckBox.setSelected(true);
    }

    @FXML
    void selectionTableClients(MouseEvent event) throws ClassNotFoundException, SQLException, IOException {

        copyWorker = createWorkerTablaClient(event);
        hbprogress.getChildren().clear();
        hbprogress.getChildren().add(progress);
        hbprogress.setAlignment(Pos.CENTER);
        hbprogress.setVisible(true);
        progress.setVisible(true);
        progress.progressProperty().unbind();
        progress.progressProperty().bind(copyWorker.progressProperty());
        copyWorker.messageProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(newValue);
            }
        });
        Thread h = new Thread();
        h = new Thread(copyWorker);
        h.start();
    }

    public Task createWorkerTablaClient(MouseEvent event) {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                boolean enc = false;
                while (!enc) {
                    anchorPage.setDisable(true);
                    Thread.sleep(300);
                    Platform.runLater(() -> {
                        if (event.getClickCount() > 0) {
                            if (ClientTableView.getSelectionModel().getSelectedItem() != null) {
                                try {
                                    insertClientPane.setVisible(true);
                                    nameClientTextField.setText(ClientTableView.getSelectionModel().getSelectedItem().getClientName().getValue());
                                    lastNamesClientTextField.setText(ClientTableView.getSelectionModel().getSelectedItem().getClientLastNames().getValue());
                                    sexClientComboBox.getSelectionModel().select(ClientTableView.getSelectionModel().getSelectedItem().getClientSex().getValue());
                                    byte[] picture = ClientTableView.getSelectionModel().getSelectedItem().getPicture();
                                    if (picture != null) {
                                        imagen=picture;
                                        Image imgShowfo1 = ConversionImage.getImagefromByteArray(picture);
                                        idPicture.setImage(imgShowfo1);
                                    } else {
                                        File clientImg = new File("src//img//imagenes.png");
                                        File filefo1 = new File(clientImg.getAbsolutePath());
                                        ImageInputStream imgfo1 = new FileImageInputStream(filefo1);
                                        BufferedImage imagefo1 = ImageIO.read(imgfo1);
                                        byte[] imgBytefo1 = ConversionImage.BufferedImage_to_Byte_Array_JPG(imagefo1);
                                        Image imgShowfo1 = ConversionImage.getImagefromByteArray(imgBytefo1);
                                        idPicture.setImage(imgShowfo1);
                                        imagen=imgBytefo1;
                                    }
                                } catch (IOException ex) {
                                    Logger.getLogger(PrincipalVisualController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                        anchorPage.setDisable(false);
                    });
                    progress.progressProperty().unbind();
                    copyWorker.cancel(true);
                    enc = true;
                    System.out.println("cancelled2");
                }
                progress.setVisible(false);
                hbprogress.setVisible(false);
                hbprogress.getChildren().clear();
                return true;
            }
        };
    }

    public Task createWorker(MouseEvent event) {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                boolean enc = false;
                while (!enc) {
                    anchorPage.setDisable(true);
                    Thread.sleep(300);
                    Platform.runLater(() -> {
                        if (event.getClickCount() > 0) {
                            if (ClientTableView.getSelectionModel().getSelectedItem() != null) {
                                try {
                                    listAccounts.clear();
                                    listAccounts.addAll(Controller.getController().getClientAcuontsFromDataBase(ClientTableView.getSelectionModel().getSelectedItem().getIdClient()));
                                } catch (ClassNotFoundException ex) {
                                    Logger.getLogger(PrincipalVisualController.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (SQLException ex) {
                                    Logger.getLogger(PrincipalVisualController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                        anchorPage.setDisable(false);
                    });
                    progress.progressProperty().unbind();
                    copyWorker.cancel(true);
                    enc = true;
                    System.out.println("cancelled2");
                }
                progress.setVisible(false);
                hbprogress.setVisible(false);
                hbprogress.getChildren().clear();
                return true;
            }
        };
    }

    @FXML
    void promteYearMenuItemAction(ActionEvent event) throws IOException {
        Stage primaryStageYear = YearVisualController.initialicePage();
        Principal.getPrincipal().getListScene().add(primaryStageYear);
    }

    @FXML
    void closeMenuItemAction(ActionEvent event) {
        System.exit(0);
    }

    public static Stage initialicePage() throws IOException {
        page = (AnchorPane) FXMLLoader.load(Principal.class
                .getResource("PrincipalVisual.fxml"));
        Scene scene = new Scene(page);

        primaryStageListClientes.setScene(scene);

        primaryStageListClientes.setTitle(
                "ControlGym");
        primaryStageListClientes.setResizable(
                false);
        primaryStageListClientes.show();
        return primaryStageListClientes;
    }

    private void configureTableClients() {
        this.nameTableColum.setCellValueFactory(new PropertyValueFactory<Client, String>("clientName"));
        this.lastaNamesTableColum.setCellValueFactory(new PropertyValueFactory<Client, String>("clientLastNames"));
        this.sexTableColum.setCellValueFactory(new PropertyValueFactory<Client, String>("clientSex"));
        this.ClientTableView.setItems(listClients);
        assert this.ClientTableView.getItems() == listClients;
        this.ClientTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        //  final ObservableList<Demanda> tableSelection = this.tvTablaDem.getSelectionModel().getSelectedItems();
        //  tableSelection.addListener(tableSelectionChangedDem);
    }

    private void configureTableAccountes() {
        this.asistanceTableColumn.setCellValueFactory(new PropertyValueFactory<ClientAccount, Boolean>("assistance"));
        this.paymentTableColumn.setCellValueFactory(new PropertyValueFactory<ClientAccount, Boolean>("payment"));
        this.yearTableColumn.setCellValueFactory(new PropertyValueFactory<ClientAccount, Integer>("idYear"));
        this.monthTableColumn.setCellValueFactory(new PropertyValueFactory<ClientAccount, String>("idMonth"));
        this.ClientAccountTableView.setItems(listAccounts);
        assert this.ClientAccountTableView.getItems() == listAccounts;
        this.ClientAccountTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        //  final ObservableList<Demanda> tableSelection = this.tvTablaDem.getSelectionModel().getSelectedItems();
        //  tableSelection.addListener(tableSelectionChangedDem);
    }

    private void setClientsFounded() {
        this.yearTableColumn.setCellValueFactory(new PropertyValueFactory<ClientAccount, Integer>("idYear"));
        this.monthTableColumn.setCellValueFactory(new PropertyValueFactory<ClientAccount, String>("idMonth"));
        this.asistanceTableColumn.setCellValueFactory(new PropertyValueFactory<ClientAccount, Boolean>("assistance"));
        this.paymentTableColumn.setCellValueFactory(new PropertyValueFactory<ClientAccount, Boolean>("payment"));
        this.ClientTableView.setItems(listFoundedClients);
        assert this.ClientTableView.getItems() == listFoundedClients;
        this.ClientAccountTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    @FXML
    void blackListMenuItemAction(ActionEvent event) throws IOException {
        Stage primaryStageBlakList = BlacListVisualContoller.initialicePage();
        Principal.getPrincipal().getListScene().add(primaryStageBlakList);

    }

}
