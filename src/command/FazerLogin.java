package command;

import java.io.IOException;
import java.io.File;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




import Model.Usuario;
import Service.UsuarioService;
import utils.CryptoAES;


public class FazerLogin implements Command {
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String pUsername = request.getParameter("username");
		String pPassword = request.getParameter("password");
		
		CryptoAES crypto = new CryptoAES();
		
		File chave = new File("C:\\Users\\Vanessa\\eclipse-workspace\\Prat_prog_Aula12","chave");
		try {
			crypto.geraCifra(pPassword.getBytes("ISO-8859-1"), chave);
			//crypto.geraCifra(pPassword.getBytes("ISO-8859-1"), new File("chave.simetrica"));
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | InvalidAlgorithmParameterException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Usuario usuario = null;
		try {
			usuario = new Usuario(pUsername, crypto.getTextoCifrado());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		UsuarioService service = new UsuarioService();
		boolean validar = service.validar(usuario);
		if(validar) {
			HttpSession session = request.getSession();
			session.setAttribute("logado", usuario);
			response.sendRedirect("index.jsp");
		}else {
			response.sendRedirect("index.jsp");
			System.out.println("Nao Logou" + usuario);
		}
		
	}
}
