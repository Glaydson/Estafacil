package estafacil.testes;

import java.util.ArrayList;
import java.util.List;

import estafacil.negocio.Usuario;
import estafacil.persistencia.UsuarioDAO;

public class TesteUsuarios {

	private static final String GERENTE = "Gerente";
	private static final String FUNCIONARIO = "Funcionário";

	public static void main(String[] args) {

		// INCLUSÃO DE USUÁRIOS

		Usuario gerente1 = new Usuario();
		gerente1.setLogin("gerente1");
		gerente1.setSenha("abc");
		gerente1.setTipoUsuario(GERENTE);

		Usuario funcionario1 = new Usuario();
		funcionario1.setLogin("funcionario1");
		funcionario1.setSenha("abc");
		funcionario1.setTipoUsuario(FUNCIONARIO);

		Usuario funcionario2 = new Usuario();
		funcionario2.setLogin("funcionario2");
		funcionario2.setSenha("abc");
		funcionario2.setTipoUsuario(FUNCIONARIO);

		Usuario funcionario3 = new Usuario();
		funcionario3.setLogin("funcionario2");
		funcionario3.setSenha("abc");
		funcionario3.setTipoUsuario(FUNCIONARIO);

		try {
			// UsuarioDAO.adicionar(gerente1);
			// UsuarioDAO.adicionar(funcionario1);
			// UsuarioDAO.adicionar(funcionario2);
			List<Usuario> usuarios = new ArrayList<Usuario>();
			usuarios = UsuarioDAO.obterUsuarios();
			for (Usuario usuario : usuarios) {
				System.out.println(usuario.toString());
			}
			// Adicionando um funcionário com login duplicado
			// UsuarioDAO.adicionar(funcionario3);

			// Autenticando usuário OK
			Usuario usuarioOK = new Usuario();
			usuarioOK.setLogin("funcionario2");
			usuarioOK.setSenha("abc");
			System.out.println(UsuarioDAO.autenticar(usuarioOK));

			// Autenticando usuário Não OK
			Usuario usuarioNaoOK = new Usuario();
			usuarioNaoOK.setLogin("funcionario2");
			usuarioNaoOK.setSenha("abcd");
			System.out.println(UsuarioDAO.autenticar(usuarioNaoOK));

		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}

	}

}
