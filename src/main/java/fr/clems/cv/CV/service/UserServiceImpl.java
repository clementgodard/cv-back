package fr.clems.cv.CV.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.clems.cv.CV.dao.UserDAO;
import fr.clems.cv.CV.pojo.MyUser;

@Service
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	private UserDAO userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<fr.clems.cv.CV.entity.User> optuser = this.userDao.findByUsername(username);
		if (!optuser.isPresent())
			throw new UsernameNotFoundException("Unknown username");
		
		MyUser user = new MyUser(optuser.get());
		
		System.out.println(user.getAuthorities());
		
		return user;
	}

}
