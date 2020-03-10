package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import db.DB;

public class Insert {

	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		// para criar conexao
		Connection conn = null;
		// para receber os dados e enviar para database
		PreparedStatement st = null;

		try {
			// criar uma conexao
			conn = DB.getConnection();
			// criar o preparedstatement apartir da conexao e recebe a squery
			st = conn.prepareStatement(
					"INSERT INTO seller (Name, Email, Birthdate, BaseSalary, DepartmentId) VALUES (?, ?, ?, ?, ?)");

			// inserir os dados
			st.setString(1, "Danilson Sanches");
			st.setString(2, "dsanches06@gmail.com");
			// inserir data para database atraves do java.sql.Date
			st.setDate(3, new java.sql.Date(sdf.parse("06/07/1982").getTime()));
			st.setDouble(4, 1200.00);
			st.setInt(5, 1);

			// executar a squery para alterar os dados da tabela, inserção ou edição
			int rows = st.executeUpdate();
			System.out.println("ROWS: " + rows);

		}
		catch (SQLException | ParseException e) {
			System.out.println("Error: " + e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}

}
