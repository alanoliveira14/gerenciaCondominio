package br.com.gerenciador.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    static {
        try{
            Class.forName ("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static Connection obtemConexao () throws SQLException {
        Connection c = DriverManager.getConnection ("jdbc:mysql://us-cdbr-iron-east-03.cleardb.net:3306/heroku_43e989bb842fe2c", "b20da04202afd0", "0169c99a");
        return c;
    }

}
