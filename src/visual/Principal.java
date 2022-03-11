/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visual;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import static java.sql.DriverManager.println;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Client;
import model.Controller;
import util.ConnectionDatabase;

/**
 *
 * @author CYNTHIA
 */
public class Principal extends Application {

    private ArrayList<Stage> listScene = new ArrayList<Stage>();

    private static Principal principal;

    public static Principal getPrincipal() {
        if (principal == null) {
            principal = new Principal();
        }
        return principal;
    }

    @Override
    public void start(Stage primaryStage) throws ClassNotFoundException, SQLException {
        //  Controller.getController().insertClient(new Client(0,"Raul", "BM", "M"));

        
        //Controller.getController().deleteClient(19954008);
       // ConnectionDatabase c = new ConnectionDatabase();
       /* Statement stmt = null;
        stmt = c.getConnectionDatabase().getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Client;");
        while (rs.next()) {
            int id = rs.getInt("idClient");
            String name = rs.getString("nameClient");
            String age = rs.getString("lastNamesClient");
            String address = rs.getString("sexClient");
            // float salary = rs.getFloat("salary");
            System.out.println("ID = " + id);
            System.out.println("NAME = " + name);
            System.out.println("LASTNAME = " + age);
            System.out.println("ADDRESS = " + address);
            // System.out.println("SALARY = " + salary);
            System.out.println();
        }
        rs.close();
        stmt.close();*/
      //  c.getConnectionDatabase().getConnection().close();

        try {
            createScene();
            PrincipalVisualController.initialicePage();

        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Application.launch(Principal.class, (java.lang.String[]) null);
    }

    public void createScene() throws IOException {
        Stage primaryStagePrincipal = PrincipalVisualController.initialicePage();
        Principal.getPrincipal().getListScene().add(primaryStagePrincipal);

    }

    public ArrayList<Stage> getListScene() {
        return listScene;
    }
}
