package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import util.ConnectionDatabase;

public class Controller {

    private LinkedList<Client> clients;
    private LinkedList<ClientAccount> clientAccounts;
    private LinkedList<Month> months;
    private LinkedList<Year> years;
    private static Controller controller = null;

    public static Controller getController() throws ClassNotFoundException, SQLException {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }
    private String idClient;

    public Controller() throws ClassNotFoundException, SQLException {
        super();
        this.clientAccounts = new LinkedList<ClientAccount>();
        this.clients = new LinkedList<Client>();
        this.months = new LinkedList<Month>();
        this.years = new LinkedList<Year>();
        ConnectionDatabase.getConnectionDatabase();
    }

    public LinkedList<Month> getMonths() {
        return months;
    }

    public void setMonths(LinkedList<Month> months) {
        this.months = months;
    }

    public LinkedList<Client> getClients() {
        return clients;
    }

    public void setClients(LinkedList<Client> clients) {
        this.clients = clients;
    }

    public LinkedList<ClientAccount> getClientAccounts() {
        return clientAccounts;
    }

    public void setClientAccounts(LinkedList<ClientAccount> clientAccounts) {
        this.clientAccounts = clientAccounts;
    }

    /**
     * inserta un cliente en la base de datos
     *
     * @param client
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public boolean insertClient(String nameClient, String lastNamesClient, String sexClient, byte[] img) throws ClassNotFoundException, SQLException {
        Statement st = null;
        boolean isInserted = false;
        st = ConnectionDatabase.getConnectionDatabase().getConnection().createStatement();
        String validation = "SELECT * FROM Client where nameClient= '" + nameClient + "' and lastNamesClient='" + lastNamesClient + "';";
        ResultSet rs = st.executeQuery(validation);
        if (!rs.next()) {
            int idC = (int) (Math.random() * 100000000);
            String sql = "INSERT INTO Client (idClient,nameClient,lastNamesClient,sexClient)" + "VALUES (" + String.valueOf(idC) + ",'" + nameClient + "','" + lastNamesClient + "','" + sexClient + "'" + ");";
            st.executeUpdate(sql);
            ConnectionDatabase.getConnectionDatabase().getConnection().commit();
            isInserted = true;
            if (img.length != 0) {
                String sql1 = "UPDATE Client set picture = ? where nameClient= '" + nameClient + "' and lastNamesClient='" + lastNamesClient + "';";
                PreparedStatement ps = ConnectionDatabase.getConnectionDatabase().getConnection().prepareStatement(sql1);
                ps.setBytes(1, img);
                ps.execute();
                ConnectionDatabase.getConnectionDatabase().getConnection().commit();
                isInserted = true;
            }
        }
        st.close();
        rs.close();
        return isInserted;
    }
    

    public boolean insertClientPicture(byte[] array, String name, String app) throws ClassNotFoundException, ClassNotFoundException, ClassNotFoundException, ClassNotFoundException, SQLException {    
        Statement st = null;
        boolean isInserted = false;
        st = ConnectionDatabase.getConnectionDatabase().getConnection().createStatement();
        String validation = "SELECT * FROM Client where nameClient= '" + name + "' and lastNamesClient='" + app + "';";
        ResultSet rs = st.executeQuery(validation);
        if (!rs.next()) {
            String sql = "UPDATE Client set picture = '" + String.valueOf(array) + "' where nameClient= '" + name + "' and lastNamesClient='" + app + "';";
            st.executeUpdate(sql);
            ConnectionDatabase.getConnectionDatabase().getConnection().commit();
            isInserted = true;
        }
        st.close();
        rs.close();
        return isInserted;
    }

    /**
     * inserta una cuenta de cliente en la base de datos.
     *
     * @param ca
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public boolean insertClientAccount(int idClient, int idYear, int idMonth, String asistance, String payment) throws ClassNotFoundException, SQLException {
        boolean isInserted = false;
        Statement st = ConnectionDatabase.getConnectionDatabase().getConnection().createStatement();
        String validation = "SELECT * FROM ClientAccount where idClient= " + idClient + " and idYear=" + idYear + " and idMonth=" + idMonth + ";";
        ResultSet rs = st.executeQuery(validation);
        if (!rs.next()) {
            int idCA = (int) (Math.random() * 100000000);
            String sql = "INSERT INTO ClientAccount (idClientAccount,idClient,idYear,idMonth,asistance,payment) VALUES (" + String.valueOf(idCA) + "," + idClient + "," + idYear + "," + idMonth + ",'" + asistance + "','" + payment + "');";
            st.executeUpdate(sql);
            ConnectionDatabase.getConnectionDatabase().getConnection().commit();
            isInserted = true;
        }
        st.close();
        rs.close();
        return isInserted;
    }

    /**
     * inserta un año en la base de datos.
     *
     * @param year
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public boolean insertYear(String year) throws ClassNotFoundException, SQLException {
        boolean isInserted = false;
        Statement st = ConnectionDatabase.getConnectionDatabase().getConnection().createStatement();
        String validation = "SELECT * FROM Year where year= '" + year + "';";
        ResultSet rs = st.executeQuery(validation);
        if (!rs.next()) {
            int idY = (int) (Math.random() * 100000000);
            String sql = "INSERT INTO Year (idYear,year)" + "VALUES(" + String.valueOf(idY) + "," + year + ");";
            st.executeUpdate(sql);
            ConnectionDatabase.getConnectionDatabase().getConnection().commit();
            isInserted = true;
        }
        st.close();
        rs.close();
        return isInserted;
    }

    /**
     * actualiza un cliente.
     *
     * @param idClient
     * @param newClientName
     * @param newClientLastNames
     * @param newClientSex
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public boolean updateClient(int idClient, String newClientName, String newClientLastNames, String newClientSex, byte[] newPicture) throws ClassNotFoundException, SQLException {
        boolean isUpdated = false;
        String validation = "SELECT * FROM Client where nameClient= '" + newClientName + "' and lastNamesClient='" + newClientLastNames + "' and sexClient='" + newClientSex + "' and picture=?;";
        ResultSet rs;
        try (PreparedStatement pst = ConnectionDatabase.getConnectionDatabase().getConnection().prepareStatement(validation)) {
            pst.setBytes(1, newPicture);
            rs = pst.executeQuery();
            if (!rs.next()) {
                Statement st=ConnectionDatabase.getConnectionDatabase().getConnection().createStatement();
                String sql1 = "UPDATE Client set nameClient = '" + newClientName + "' where idClient=" + idClient + ";";
                String sql2 = "UPDATE Client set lastNamesClient = '" + newClientLastNames + "' where idClient=" + idClient + ";";
                String sql3 = "UPDATE Client set sexClient = '" + newClientSex + "' where idClient=" + idClient + ";";
                st.executeUpdate(sql1);
                st.executeUpdate(sql2);
                st.executeUpdate(sql3);
                String sql4 = "UPDATE Client set picture = ? where nameClient= '" + newClientName + "' and lastNamesClient='" + newClientLastNames + "';";
                PreparedStatement ps = ConnectionDatabase.getConnectionDatabase().getConnection().prepareStatement(sql4);
                ps.setBytes(1, newPicture);
                ps.execute();
                ConnectionDatabase.getConnectionDatabase().getConnection().commit();
                isUpdated = true;
            }
        }
        rs.close();
        return isUpdated;
    }

    /**
     * actualiza una cuenta de cliente.
     *
     * @param idClientAccount
     * @param idClient
     * @param newIdYear
     * @param newIdMonth
     * @param newAsistance
     * @param newPayment
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public boolean updateClientAccount(int idClientAccount, int idClient, int newIdYear, int newIdMonth, String newAsistance, String newPayment) throws ClassNotFoundException, SQLException {
        boolean isUpdated = false;
        Statement st = ConnectionDatabase.getConnectionDatabase().getConnection().createStatement();
        String validation = "SELECT * FROM ClientAccount where idClient= " + idClient + " and idYear= " + newIdYear + " and idMonth=" + newIdMonth + " and asistance='" + newAsistance + "' and payment='" + newPayment + "';";
        ResultSet rs = st.executeQuery(validation);
        if (!rs.next()) {
            String sql1 = "UPDATE ClientAccount set idYear =" + newIdYear + " where idClientAccount=" + idClientAccount + " AND " + "idClient=" + idClient + ";";
            String sql2 = "UPDATE ClientAccount set idMonth = " + newIdMonth + " where idClientAccount=" + idClientAccount + " AND " + "idClient=" + idClient + ";";
            String sql3 = "UPDATE ClientAccount set asistance = '" + newAsistance + "' where idClientAccount=" + idClientAccount + " AND " + "idClient=" + idClient + ";";
            String sql4 = "UPDATE ClientAccount set payment = '" + newPayment + "' where idClientAccount=" + idClientAccount + " AND " + "idClient=" + idClient + ";";
            st.executeUpdate(sql1);
            st.executeUpdate(sql2);
            st.executeUpdate(sql3);
            st.executeUpdate(sql4);
            ConnectionDatabase.getConnectionDatabase().getConnection().commit();
            isUpdated = true;
        }
        st.close();
        rs.close();
        return isUpdated;
    }

    /**
     * actualiza un año.
     *
     * @param idYear
     * @param newYear
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public boolean updateYear(int idYear, int newYear) throws ClassNotFoundException, SQLException {
        boolean isUpdated = false;
        Statement st = ConnectionDatabase.getConnectionDatabase().getConnection().createStatement();
        String validation = "SELECT * FROM Year where year= '" + newYear + "';";
        ResultSet rs = st.executeQuery(validation);
        if (!rs.next()) {
            String sql = "UPDATE Year set year = '" + newYear + "' where idYear=" + idYear + ";";
            st.executeUpdate(sql);
            ConnectionDatabase.getConnectionDatabase().getConnection().commit();
            isUpdated = true;
        }
        st.close();
        return isUpdated;
    }

    /**
     * Elimina un cliente y todas su cuentas.
     *
     * @param idClient
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void deleteClient(int idClient) throws ClassNotFoundException, SQLException {
        Statement st = ConnectionDatabase.getConnectionDatabase().getConnection().createStatement();
        String sql1 = "DELETE from Client where idClient=" + idClient + ";";
        String sql2 = "DELETE from ClientAccount where idClient=" + idClient + ";";
        st.executeUpdate(sql1);
        st.executeUpdate(sql2);
        ConnectionDatabase.getConnectionDatabase().getConnection().commit();
        st.close();
    }

    /**
     * elimina una cuenta de cliente.
     *
     * @param idClientAccount
     * @param idClient
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void deleteClientAccount(int idClientAccount, int idClient) throws ClassNotFoundException, SQLException {
        Statement st = ConnectionDatabase.getConnectionDatabase().getConnection().createStatement();
        String sql = "DELETE from ClientAccount where idClientAccount=" + idClientAccount + " AND " + "idClient=" + idClient + ";";
        st.executeUpdate(sql);
        ConnectionDatabase.getConnectionDatabase().getConnection().commit();
        st.close();
    }

    /**
     * elimina un año y todas las cuentas en la que este aparece.
     *
     * @param idYear
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void deleteYear(int idYear) throws SQLException, ClassNotFoundException {
        Statement st = ConnectionDatabase.getConnectionDatabase().getConnection().createStatement();
        String sql1 = "DELETE from Year where idYear=" + idYear + ";";
        String sql2 = "DELETE from ClientAccount where idYear=" + idYear + ";";
        st.executeUpdate(sql1);
        st.executeUpdate(sql2);
        ConnectionDatabase.getConnectionDatabase().getConnection().commit();
        st.close();

    }

    /**
     * devuelve los clientes desde la base de datos.
     *
     * @return LinkedList<Client>
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<Client> getClientsFromDataBase() throws ClassNotFoundException, SQLException {
        ArrayList<Client> clients = new ArrayList<Client>();
        Statement st = ConnectionDatabase.getConnectionDatabase().getConnection().createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Client;");
        Client client = null;
        while (rs.next()) {
            client = new Client(rs.getInt("idClient"), rs.getString("nameClient"), rs.getString("lastNamesClient"), rs.getString("sexClient"), rs.getBytes("picture"));
            clients.add(client);
        }
        st.close();
        rs.close();
        return clients;
    }

    /**
     * devuelve todas las cuentas de un cliente.
     *
     * @param idClient
     * @return LinkedList<ClientAccount>
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<ClientAccount> getClientAcuontsFromDataBase(int idClient) throws ClassNotFoundException, SQLException {
        ArrayList<ClientAccount> clientAccounts = new ArrayList<ClientAccount>();
        Statement st = ConnectionDatabase.getConnectionDatabase().getConnection().createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM ClientAccount where idClient= " + idClient + ";");
        ClientAccount clientAccount = null;
        while (rs.next()) {
            clientAccount = new ClientAccount(rs.getInt("idClientAccount"), rs.getInt("idClient"), rs.getInt("idMonth"), rs.getInt("idYear"), rs.getString("asistance"), rs.getString("payment"));
            clientAccounts.add(clientAccount);
        }
        st.close();
        rs.close();
        return clientAccounts;
    }

    /**
     * devuelve los años de la base de datos.
     *
     * @return LinkedList<Year>
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<Year> getYearsFromDataBase() throws ClassNotFoundException, SQLException {
        ArrayList<Year> years = new ArrayList<Year>();
        Statement st = ConnectionDatabase.getConnectionDatabase().getConnection().createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Year;");
        Year year = null;
        while (rs.next()) {
            year = new Year(rs.getInt("idYear"), rs.getInt("year"));
            years.add(year);
        }
        st.close();
        rs.close();
        return years;
    }

    public ArrayList<String> getYearsNamesFromDataBase() throws ClassNotFoundException, SQLException {
        ArrayList<String> years = new ArrayList<>();
        Statement st = ConnectionDatabase.getConnectionDatabase().getConnection().createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Year;");
        String year = null;
        while (rs.next()) {
            year = String.valueOf(rs.getInt("year"));
            years.add(year);
        }
        st.close();
        rs.close();
        return years;
    }

    /**
     * Devuelve los meses de la base de datos.
     *
     * @return LinkedList<Month>
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<Month> getMonthsFromDataBase() throws ClassNotFoundException, SQLException {
        ArrayList<Month> months = new ArrayList<Month>();
        Statement st = ConnectionDatabase.getConnectionDatabase().getConnection().createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Month;");
        Month month = null;
        while (rs.next()) {
            month = new Month(rs.getInt("idMonth"), rs.getString("month"));
            months.add(month);
        }
        st.close();
        rs.close();
        return months;
    }

    public ArrayList<String> getMonthNamesFromDataBase() throws ClassNotFoundException, SQLException {
        ArrayList<String> months = new ArrayList<>();
        Statement st = ConnectionDatabase.getConnectionDatabase().getConnection().createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Month;");
        String month = null;
        while (rs.next()) {
            month = rs.getString("month");
            months.add(month);
        }
        st.close();
        rs.close();
        return months;
    }

    public Year getYear(int idYear) throws SQLException, ClassNotFoundException {
        Year year = null;
        Statement st = ConnectionDatabase.getConnectionDatabase().getConnection().createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Year where idYear=" + idYear + ";");
        while (rs.next()) {
            year = new Year(rs.getInt("idYear"), rs.getInt("year"));
        }
        st.close();
        rs.close();
        return year;
    }

    public Month getMonth(int idMonth) throws SQLException, ClassNotFoundException {
        Month month = null;
        Statement st = ConnectionDatabase.getConnectionDatabase().getConnection().createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Month where idMonth=" + idMonth + ";");
        while (rs.next()) {
            month = new Month(rs.getInt("idMonth"), rs.getString("month"));
        }
        st.close();
        rs.close();
        return month;
    }

    public int getYear(String year) throws SQLException, ClassNotFoundException {
        int idYear = -1;
        Statement st = ConnectionDatabase.getConnectionDatabase().getConnection().createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Year where year=" + year + ";");
        while (rs.next()) {
            idYear = rs.getInt("idYear");
        }
        st.close();
        rs.close();
        return idYear;
    }

    public int getMonth(String month) throws SQLException, ClassNotFoundException {
        int idMonth = -1;
        Statement st = ConnectionDatabase.getConnectionDatabase().getConnection().createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Month where month='" + month + "';");
        while (rs.next()) {
            idMonth = rs.getInt("idMonth");
        }
        st.close();
        rs.close();
        return idMonth;
    }

    public ArrayList<Client> getFoundedClientsFromDataBase(CharSequence cs) throws ClassNotFoundException, SQLException {
        ArrayList<Client> clients = new ArrayList<Client>();
        ArrayList<Client> foundedClients = new ArrayList<Client>();
        Statement st = ConnectionDatabase.getConnectionDatabase().getConnection().createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Client;");
        Client client = null;
        while (rs.next()) {
            client = new Client(rs.getInt("idClient"), rs.getString("nameClient"), rs.getString("lastNamesClient"), rs.getString("sexClient"), rs.getBytes("picture"));
            clients.add(client);
        }
        st.close();
        rs.close();
        for (Client c : clients) {
            if (c.getClientName().getValue().startsWith(cs.toString()) || c.getClientLastNames().getValue().startsWith(cs.toString())) {
                foundedClients.add(c);
            }
        }

        return foundedClients;
    }

    public ArrayList<Client> blackList(int idYear, int idMonth) throws SQLException, ClassNotFoundException {
        ArrayList<Client> debtClients = new ArrayList<>();
        Statement st = ConnectionDatabase.getConnectionDatabase().getConnection().createStatement();
        ResultSet rs = st.executeQuery("SELECT Client.idClient , Client.nameClient ,  Client.lastNamesClient , Client.sexClient, Client.picture FROM Client INNER JOIN ClientAccount ON Client.idClient=ClientAccount.idClient where ClientAccount.idYear= " + idYear + " and ClientAccount.idMonth= " + idMonth + " and ClientAccount.asistance= 'si' and ClientAccount.payment= 'no';");
        Client client = null;
        while (rs.next()) {
            client = new Client(rs.getInt("idClient"), rs.getString("nameClient"), rs.getString("lastNamesClient"), rs.getString("sexClient"), rs.getBytes("picture"));
            debtClients.add(client);
        }
        return debtClients;
    }

}
