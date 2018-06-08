package Service;

import java.io.File;
import java.io.IOException;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

import java.security.NoSuchAlgorithmException;

import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import DAO.UsuarioDAO;
import Model.Usuario;
import utils.CryptoAES;

public class UsuarioService {
	UsuarioDAO dao;

	public UsuarioService() {
		dao = new UsuarioDAO();
	}

	public boolean validar(Usuario usuario) {
		return dao.validar(usuario);
	}

	public void criar(Usuario usuario) throws SQLException, InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException,
			ClassNotFoundException, IOException {
		CryptoAES crypto = new CryptoAES();

		File chave = new File("C:\\Users\\Vanessa\\eclipse-workspace\\Prat_prog_Aula12","chave");
		crypto.geraCifra(usuario.getPassword(), chave);
		try {
			usuario.setPassword(crypto.getTextoCifrado());
		} catch (Exception e) {
			e.printStackTrace();
		}
		dao.criar(usuario);
	}

}
