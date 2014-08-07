/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.Controller;

import YogaStudio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author sMazumder
 */
public class Main {
    @Autowired
    private static UserService userService;
    public static boolean check(){
        UserService userService = new UserService();
        return userService.authenticateUser("shahadat", "shahadat");
    }
    public static void main(String[] args){
        boolean flag = Main.check();
       if(userService.authenticateUser("shahadat", "shahadat")){
           System.out.println("Yes");
       } 
    }
}
