package com.katari.examples.testmaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;

public abstract class RequiresLoggedUserTests extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
    private AuthenticationManager am;
	
	private final String username = "roberto";
	private final String password = "password";
	
	@BeforeClass
	public final void loginDefaultUser() {
		loginUser(username, password);
    }
	
	public final void loginUser(String username, String password) {
		Authentication auth = new UsernamePasswordAuthenticationToken(username, password);
        SecurityContextHolder.getContext().setAuthentication(am.authenticate(auth));
	}
}
