/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YogaStudio.service;

import YogaStudio.Controller.EmailController;
import YogaStudio.domain.UserEntity;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Shahadat
 */
@Aspect
public class LogAdvice {
    @Autowired
    private EmailController emailController;

    public LogAdvice(EmailController emailController) {
        this.emailController = emailController;
    }
    
    @After("execution(* YogaStudio.service.UserService.add(..))&& args( fullname,  email, userName,  authority)")
    public void sendEmail(JoinPoint joinpoint, String fullname, String email,String userName, String authority) {
        try{
           
            //System.out.println("khanuser");        
            emailController.generateEmailForNewAppRegistration(email);
        }
        catch(Exception e){
         System.out.println("Error sending email "+e.getMessage());
        }

    }

}
