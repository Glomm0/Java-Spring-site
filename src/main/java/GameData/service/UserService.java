/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameData.service;

import GameData.model.Game;
import GameData.model.User;
import GameData.repository.GameRepository;
import GameData.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author а353
 */
@Service("userService")
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;
    
    public List<User> findAll(){
        return userRepository.findAll();
    }
    public void save(User user){
        
        userRepository.saveAndFlush(user);
    }
}
