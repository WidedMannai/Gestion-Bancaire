package org.sid.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserService {
        
	/*@Autowired
	  private static UserRepository userRepository;
	
	@Autowired
	private static RoleRepository roleRepository;
	    	
	    
	    private static  BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	    
	    
	    
	    
	    public UserService(UserRepository userRepository,
	                       RoleRepository roleRepository
	                       ) {
	        this.userRepository = userRepository;
	        this.roleRepository = roleRepository;
	    }

	    public User findUserByEmail(String email) {
	        return userRepository.findByEmail(email);
	    }

	    public User findUserByUserName(String username) {
	        return userRepository.findByUsername(username);
	    }

	    public static  User saveUser(User user) {
	        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	        user.setActive(true);
	        Role userRole = roleRepository.findByRole("ADMIN");
	        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
	        return userRepository.save(user);
	    }

	*/
}
