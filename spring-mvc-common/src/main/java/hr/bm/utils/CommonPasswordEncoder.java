package hr.bm.utils;

import org.springframework.security.crypto.password.PasswordEncoder;

public class CommonPasswordEncoder implements PasswordEncoder {

	private final int CONTROL_NUMBER = 2016;

	public String encode(CharSequence rawPassword) {
		String ret = "" + (rawPassword.hashCode() + CONTROL_NUMBER);
		return ret;
	}

	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		if (rawPassword == null || encodedPassword == null) {
			return false;
		}
		String rawPasswordCode = "" + (rawPassword.hashCode() + CONTROL_NUMBER);
		return rawPasswordCode.equals(encodedPassword);
	}

}
