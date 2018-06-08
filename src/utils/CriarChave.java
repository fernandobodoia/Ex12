package utils;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.swing.JOptionPane;

public class CriarChave {

	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException,
			CertificateException, KeyStoreException, IOException {
		CryptoAES aes = new CryptoAES();
		aes.geraChave(new File("C:\\Users\\Vanessa\\eclipse-workspace\\Prat_prog_Aula12\\chave"));
		JOptionPane.showMessageDialog(null, "Chave criada!");
	}

}
