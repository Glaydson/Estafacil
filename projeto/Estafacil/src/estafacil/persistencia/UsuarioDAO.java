package estafacil.persistencia;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import estafacil.negocio.Usuario;

/**
 * DAO para acesso aos dados de Usuario
 * 
 * @author adfor.glaydson
 *
 */
public class UsuarioDAO {

	private static final String BRANCO = "";
	// conex�o com o banco
	private static Connection conexao = FabricaDeConexao.obterConexao();

	/**
	 * Obt�m a lista de usu�rios cadastrados no BD
	 * 
	 * @return
	 */
	public static List<Usuario> obterUsuarios() {
		try {
			List<Usuario> usuarios = new ArrayList<Usuario>();
			Usuario usuario = null;
			PreparedStatement stmt = conexao
					.prepareStatement("select * from usuario");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt("idusuario"));
				usuario.setLogin(rs.getString("login"));
				usuario.setTipoUsuario(rs.getString("tipousuario"));
				usuarios.add(usuario);
			}
			rs.close();
			stmt.close();
			return usuarios;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adiciona um novo usu�rio
	 * 
	 * @param usuario
	 *            Usu�rio a ser adicionado
	 * @throws Exception
	 *             Caso o usu�rio j� exista com o login informado, ou caso
	 *             ocorra algum erro na valida��o do usu�rio ou no acesso aos
	 *             dados.
	 */
	public static void adicionar(Usuario usuario) throws Exception {
		validarCampos(usuario);
		Usuario usu = buscar(usuario.getLogin());  // Verifica se j� existe
		if (usu == null) {
			String senhaCripto = obterSenhaCripto(usuario.getSenha());
			try {
				String sql = "insert into usuario (login, senha, tipousuario) values (?,?,?)";
				PreparedStatement stmt = conexao.prepareStatement(sql);
				stmt.setString(1, usuario.getLogin());
				stmt.setString(2, senhaCripto);
				stmt.setString(3, usuario.getTipoUsuario());
				stmt.execute();
				stmt.close();
				System.out.println("Usu�rio inserido!");
			} catch (SQLException e) {
				throw new Exception(e);
			}
		} else {
			throw new Exception("J� existe usu�rio com o login informado.");
		}
	}

	/**
	 * Autentica o usu�rio passado como par�metro, checando sua senha
	 * criptografada contra a senha armazenada em BD
	 * 
	 * @param usuario
	 *            Usu�rio que se deseja autenticar
	 * @return True caso o usu�rio seja autenticado, False caso contr�rio.
	 * @throws Exception
	 *             Caso o usu�rio n�o seja encontrado.
	 */
	public static boolean autenticar(Usuario usuario) throws Exception {
		boolean autenticado = false;
		String senhaCripto = obterSenhaCripto(usuario.getSenha());
		try {
			String sql = "select * from usuario where login = ?";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getLogin());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				if (senhaCripto.equals(rs.getString("senha"))) {
					autenticado = true;
				}
			} else {
				throw new Exception("Usu�rio n�o encontrado.");
			}
		} catch (SQLException e) {
			throw new Exception(e);
		}
		return autenticado;

	}

	/**
	 * Busca um usu�rio pelo seu login
	 * 
	 * @param login
	 *            Login do usu�rio que se deseja buscar
	 * @return Usu�rio com o login informado ou null caso n�o seja encontrado.
	 * @throws Exception
	 *             Caso ocorra algum erro no acesso aos dados.
	 */
	public static Usuario buscar(String login) throws Exception {
		Usuario usuario = null;
		try {
			String sql = "select * from usuario where login = ?";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, login);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt("idusuario"));
				usuario.setLogin(rs.getString("login"));
				usuario.setTipoUsuario(rs.getString("tipousuario"));
			}
		} catch (SQLException e) {
			throw new Exception(e);
		}
		return usuario;
	}

	/**
	 * Criptografa a senha do usu�rio para armazenamento no BD
	 * 
	 * @param senha
	 *            Senha a ser criptografada
	 * @return Senha criptografada
	 */
	private static String obterSenhaCripto(String senha) {
		MessageDigest algoritmo;
		String senhaCripto = null;
		try {
			algoritmo = MessageDigest.getInstance("SHA-256");
			byte messageDigest[] = algoritmo.digest(senha.getBytes("UTF-8"));
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
				hexString.append(String.format("%02X", 0xFF & b));
			}
			senhaCripto = hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		return senhaCripto;
	}

	/**
	 * Valida os campos de um usu�rio
	 * 
	 * @param usuario
	 *            Usu�rio a ser validado.
	 * @throws Exception
	 *             Caso algum dos campos esteja inv�lido.
	 */
	private static void validarCampos(Usuario usuario) throws Exception {
		if (usuario != null) {
			List<String> erros = new ArrayList<String>();
			if (usuario.getLogin() == null || usuario.getLogin().equals(BRANCO)) {
				erros.add("Login inv�lido ou n�o preenchido.");
			}
			if (usuario.getSenha() == null || usuario.getSenha().equals(BRANCO)) {
				erros.add("Senha inv�lida ou n�o preenchida.");
			}
			if (usuario.getTipoUsuario() == null
					|| usuario.getTipoUsuario().equals(BRANCO)) {
				erros.add("Tipo de Usu�rio inv�lido ou n�o preenchido.");
			}
		} else {
			throw new Exception("Usu�rio inv�lido.");
		}
	}
}
