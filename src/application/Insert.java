package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
					"INSERT INTO seller (Name, Email, Birthdate, BaseSalary, DepartmentId) VALUES (?, ?, ?, ?, ?)",
					//usar para obter o id da tabela
					Statement.RETURN_GENERATED_KEYS);

			// inserir os dados
			st.setString(1, "Ivan Santos");
			st.setString(2, "ivan@hotmail.com");
			// inserir data para database atraves do java.sql.Date
			st.setDate(3, new java.sql.Date(sdf.parse("08/02/1990").getTime()));
			st.setDouble(4, 1800.00);
			st.setInt(5, 4);

			// executar a squery para alterar os dados da tabela, inserção ou edição
			int rows = st.executeUpdate();
			
			if (rows > 0) {
				//obter o resultado da squery em resultset
				ResultSet rs = st.getGeneratedKeys();
				//percorrer o resultset
				while(rs.next()) {
					//obter o id, colocando valor 1 para obter o valor da primeira coluna
					int id = rs.getInt(1);
					System.out.println("Id: "+id);
				}
				//fechar result set
				DB.closeResultSet(rs);
			}else {
				System.out.println("No rows affected!");
			}

		} catch (SQLException | ParseException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}

}
