package application;

import java.sql.Connection;
import java.sql.SQLException;

import db.DB;

public class Main {

	public static void main(String[] args) throws SQLException {

		//criar uma nova conexao
		Connection conn = DB.getConnection();
		//fechar a conexao
		DB.closeConnection();

	}
}
