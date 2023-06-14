/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameData.converter;

import GameData.service.ApiRequests;
import GameData.model.User;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.core.convert.converter.Converter; 


/**
 *
 * @author а353
 */
public class UserConverter implements Converter<String,User> {

    @Override
    public User convert(String id) {
        ApiRequests ar=new ApiRequests();
        try {
            
            return ar.getUserById(Long.parseLong(id));
        } catch (IOException ex) {
            Logger.getLogger(UserConverter.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    

    
    
}
