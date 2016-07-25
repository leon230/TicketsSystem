package ch.makery.address.util;

import ch.makery.address.DBConnect;
import ch.makery.address.MainApp;
import ch.makery.address.model.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * Created by Lukasz.Homik on 2016-07-05.
 */
public class RetrieveData {
    private ObservableList<Ticket> ticketData = FXCollections.observableArrayList();
    private MainApp mainApp;

    public ObservableList<Ticket> getData(String SQL) {
        try {
            Connection c;
            c = DBConnect.connect();
            //String SQL = "SELECT FIRST_NAME, LAST_NAME FROM  Persons";
            ResultSet rs = c.createStatement().executeQuery(SQL);

            while (rs.next()) {
                //Iterate Row
                //ObservableList<String> row = FXCollections.observableArrayList();
                String[] str = new String[23];
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    //row.add(rs.getString(i));
                    str[i - 1] = rs.getString(i);
                    //System.out.println(str[i]);
                }
                ticketData.add(new Ticket(str[0], str[1], str[2], str[3], str[4], str[5], str[6], str[7], str[8], str[9], str[10], str[11], str[12], str[13]
                        , str[14], str[15], str[16], str[17], str[18], str[19], str[20], str[21], null, str[22]));
//                System.out.println("Row [1] added "+row.toString() );
//                System.out.println("Row [1] added "+row.toString() );
                //ticketData.add(new Person(row);
                //data.add(row);

            }
        } catch (Exception exc) {
            //mainApp.seterrorMsg("DB error retrieve data ");
            //mainApp.seterrorMsg("DB error retrieve data ");
            System.out.println("DB error retrieve data " + exc.toString());


        }

        return ticketData;



    }
}
