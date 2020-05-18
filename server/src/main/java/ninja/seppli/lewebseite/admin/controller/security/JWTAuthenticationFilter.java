package ninja.seppli.lewebseite.admin.controller.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import ninja.seppli.lewebseite.common.permission.dto.LoginUser;

/**
 * Authentication filter for the rest api
 *
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	/**
	 * Auth manager
	 */
	private AuthenticationManager authenticationManager;

	/**
	 * Constructor
	 *
	 * @param authenticationManager the manager
	 */
	@Autowired
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			LoginUser user = new ObjectMapper().readValue(request.getInputStream(), LoginUser.class);
			return authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.username, user.password));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}


	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String token = JWT.create().withSubject(((User) authResult.getPrincipal()).getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME)).sign(Algorithm.HMAC512(SecurityConstants.SECRET));
		response.setHeader(SecurityConstants.HEADER_STRING, token);
	}

}
