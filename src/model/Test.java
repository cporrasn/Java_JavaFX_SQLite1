/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Raul
 */
public class Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ArrayList<Client> blClients= Controller.getController().blackList(1, 1);
        for(Client c: blClients){
            System.out.println(c.getClientName()+" "
                    +c.getClientLastNames()+" "
            +c.getClientSex());
        }
    }
    
}
