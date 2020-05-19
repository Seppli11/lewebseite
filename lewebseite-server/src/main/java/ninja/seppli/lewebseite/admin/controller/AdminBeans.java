package ninja.seppli.lewebseite.admin.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 * Contains all beans for the admin area
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
@Configuration
public class AdminBeans {
	/**
	 * @return the {@link BCryptPasswordEncoder}
	 */
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
