/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GameData.repository;

import GameData.model.Game;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author а353
 */
@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    @Query(value="SELECT * from games where games.user_id=:userId",nativeQuery = true)
    public List<Game> findAllByUserId(@Param("userId")Long userId);
    @Query(value="SELECT * from games where games.appid=:appID",nativeQuery = true)
    public Game findByAppId(@Param("appID") Integer appId);
}
