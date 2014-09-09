package estafacil.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Fábrica de conexões para evitar a duplicação do código para obter uma conexão
 * ao BD
 * 
 * @author glaydson
 * 
 */
public class FabricaDeConexao {

	public static Connection obterConexao() {
		String stringDeConexao = "jdbc:mysql://localhost/estafacil";
		String usuario = "root";
		String senha = "12345";

		Connection conexao = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection(stringDeConexao, usuario,
					senha);
		} catch (SQLException e) {
			System.out.println("Não pôde conectar ao Banco de dados");
		} catch (ClassNotFoundException e) {
			System.out.println("Não pôde carregar o driver JDBC");
		}
		return conexao;
	}
}