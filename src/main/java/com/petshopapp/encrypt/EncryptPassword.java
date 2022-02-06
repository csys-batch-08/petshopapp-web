package com.petshopapp.encrypt;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptPassword {
	static String plainText = "oracle";
	public static final int AES_KEY_SIZE = 256;
	public static final int GCM_IV_LENGTH = 12;
	public static final int GCM_TAG_LENGTH = 16;

	private EncryptPassword() {

	}

	public static byte[] encrypt(byte[] plaintext, SecretKey key, byte[] iv) throws Exception {

		// Get Cipher Instance
		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

		// Create SecretKeySpec
		SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");

		// Create GCMParameterSpec
		GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv);

		// Initialize Cipher for ENCRYPT_MODE
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmParameterSpec);

		return cipher.doFinal(plaintext);
	}

	public static String decrypt() throws Exception {

		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(AES_KEY_SIZE);
		// Generate Key
		SecretKey key = keyGenerator.generateKey();
		byte[] iv = new byte[GCM_IV_LENGTH];
		SecureRandom random = new SecureRandom();
		random.nextBytes(iv);
		byte[] cipherText = encrypt("oracle".getBytes(), key, iv);
		// Get Cipher Instance
		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

		// Create SecretKeySpec
		SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");

		// Create GCMParameterSpec
		GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv);

		// Initialize Cipher for DECRYPT_MODE
		cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmParameterSpec);

		// Perform decrypt password
		byte[] decryptedText = cipher.doFinal(cipherText);

		return new String(decryptedText);
	}
}
