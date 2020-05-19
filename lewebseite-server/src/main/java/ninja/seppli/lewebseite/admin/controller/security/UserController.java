package ninja.seppli.lewebseite.admin.controller.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ninja.seppli.lewebseite.common.permission.model.ApplicationUser;
import ninja.seppli.lewebseite.common.permission.service.ApplicationUserService;

@RestController
@RequestMapping("/admin/users")
public class UserController {
	private ApplicationUserService userService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * Constructor
	 * @param userService
	 * @param bCryptPasswordEncoder
	 */
	@Autowired
	public UserController(ApplicationUserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@PostMapping("/sign-up")
	public void signUp(@RequestBody ApplicationUser user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userService.save(user);
	}

}
