package com.demo.clockin.common.lang;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Random;
import java.util.UUID;

//import org.mindrot.jbcrypt.BCrypt;

/**
 * 密码加密工具类
 *
 * @author Lee
 *
 */
public class BCryptUtil {

	/**
	 * 加密
	 *
	 * @param password
	 *            密码明文
	 * @return 密码密文
	 */
	public static String encrypt(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	/**
	 * 校验密码
	 *
	 * @param plaintext
	 *            明文
	 * @param ciphertext
	 *            密文
	 * @return 密码是否正确
	 */
	public static boolean checkPassword(String plaintext, String ciphertext) {
		return BCrypt.checkpw(plaintext, ciphertext);
	}

	/**
	 * 随机Hash Key
	 *
	 * @return
	 */
	public static String randomHash() {
		return String.valueOf(Math.abs(randomUUID().hashCode()));
	}

	/**
	 * 生成32随机字符串
	 *
	 * @return
	 */
	public static String randomUUID() {
		return StringUtils.remove(UUID.randomUUID().toString(), "-");
	}

	/**
	 * 生成32随机字符串
	 *
	 * @return
	 */
	public static String randomUUID(int size) {
		size = size > 32 ? 32 : size;
		String uuid = StringUtils.remove(UUID.randomUUID().toString(), "-");
		uuid = StringUtils.substring(uuid, 0, size);
		return uuid;
	}

	public static String randomNumber(int size) {
		Random rm = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < size; i++) {
			sb.append(rm.nextInt(10));
		}
		return sb.toString();
	}


}
