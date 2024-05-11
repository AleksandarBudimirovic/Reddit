package com.example.reddit.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.reddit.dto.UserDTO;
import com.example.reddit.model.User;
import com.example.reddit.repository.UserRepository;
import com.example.reddit.security.SecurityConfiguration;
import com.example.reddit.security.TokenUtils;

@Service("userDetailsService")
public class UserService implements UserDetailsService{

	 private final static String USER_ROLE = "USER";
	    private final static String ADMIN_ROLE = "ADMIN";

//	    @Autowired
//	    private UserDetailsService userDetailsService;
	    
	    @Autowired
	    UserRepository userRepository;

	    @Autowired
	    TokenUtils tokenUtils;

	    @Autowired
	    SecurityConfiguration configuration;


	    
	    public User findByUsername(String username) {
	        return userRepository.findByUsername(username);
	    }

	    
	    public UserDTO registrujKorisnika(UserDTO req) throws Exception {
	        User user = new User();
	        user.setAvatar(req.getAvatar());
	        user.setDescription(req.getDescription());
	        user.setUsername(req.getUsername());
	        user.setRole(USER_ROLE);
//	        user.setPassword(configuration.passwordEncoder().encode(req.getPassword()));
	        user.setPassword(req.getPassword());
	        user.setDisplayName(req.getDisplayName());
	        user.setRegistrationDate(new Date());
	        userRepository.save(user);
	        return getUserDTO(user);
	    }

	    
	    public UserDTO myProfile(String token) throws Exception {
	        String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
	        User user = userRepository.findByUsername(username);
	        return getUserDTO(user);
	    }


	    
	    public UserDTO izmena(UserDTO req, String token) throws Exception {
	        String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
	        User user = userRepository.findByUsername(username);

	        if(req.getPassword()!=null && !req.getPassword().isEmpty()) {
//	            if (!configuration.passwordEncoder().matches(req.getPassword(), user.getPassword())) {
	        	if (req.getPassword().equals(user.getPassword())) {
	                throw new Exception("Wrong password!");
	            }
	            if (req.getPassword() == null || user.getPassword() == null || !req.getPassword().equals(req.getPassword())) {
	                throw new Exception("Passwords dont match");
	            }
//	            user.setPassword(configuration.passwordEncoder().encode(req.getPassword()));
	            user.setPassword(req.getPassword());
	            
	        }

	        user.setAvatar(req.getAvatar());
	        user.setDescription(req.getDescription());
	        user.setUsername(req.getUsername());
	        user.setDisplayName(req.getDisplayName());
	        userRepository.save(user);
	        return getUserDTO(user);
	    }

	    
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        User user = userRepository.findByUsername(username);
	        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
	        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
	        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
	    }

	    private UserDTO getUserDTO(User user) {
	        UserDTO res = new UserDTO();

	        res.setAvatar(user.getAvatar());
	        res.setDescription(user.getDescription());
	        res.setUsername(user.getUsername());
	        res.setRole(user.getRole());
	        res.setDisplayName(user.getDisplayName());
	        res.setId(user.getId());

	        return res;
	    }
	    

		
		public User findOne(Long id) {
			return userRepository.findById(id).orElse(null);
		}
		
		
		public List<User> findAll() {

			return userRepository.findAll();
		}
		

		public User save(User user) {
			return userRepository.save(user);
			
		}
		
		
		public void remove(Long id){
			userRepository.deleteById(id);
		}
		
		public Page<User> findAll(Pageable page) {
			return userRepository.findAll(page);
		}
	
}
