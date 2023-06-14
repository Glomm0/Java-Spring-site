/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameData.service;

import GameData.model.Game;
import GameData.repository.GameRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author а353
 */
@Service("gameService")
@Transactional
public class GameService {
    @Autowired
    GameRepository gameRepository;
    
    public List<Game> findAll(){
        return gameRepository.findAll();
    }
    public void save(Game game){
        gameRepository.save(game);
    }
    public List<Game>findAllByUserId(Long userId){
        return gameRepository.findAllByUserId(userId);
    }
    public void saveAll(List<Game> games){
        gameRepository.saveAll(games);
    }
    public void deleteById(Integer id){
        gameRepository.deleteById(id);
    }
    public Game findByAppId(Integer appId){
        
        return gameRepository.findByAppId(appId);
        
    }
    
}
