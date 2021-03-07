package fr.clems.cv.CV.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.clems.cv.CV.dao.UserDAO;
import fr.clems.cv.CV.entity.User;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserDAO userDao;
	
    @GetMapping("/")
    public User user(String username) 
    {
    	Optional<User> user = this.userDao.findByUsername(username);
    	
    	if (user.isPresent()) {
    		return user.get();
    	} else
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pas d'utilisateur correspondant");
    }
    
}
