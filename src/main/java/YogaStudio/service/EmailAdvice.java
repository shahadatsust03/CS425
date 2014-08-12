/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YogaStudio.service;

import YogaStudio.domain.UserEntity;
import java.util.Date;
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
public class EmailAdvice {
    @Autowired
    private EmailService emailservice;

    public EmailAdvice(EmailService emailservice) {
        this.emailservice = emailservice;
    }
    
    @After("execution(* YogaStudio.service.UserService.add(..))&& args( fullname, email,username, dateOfBirth, street,city,country, state, zipcode,contactNum,authority)")
    public void sendEmail(JoinPoint joinpoint, String fullname, 
                       String email,
                       String username, 
                       Date dateOfBirth,
                       String street,
                       String city,
                       String country,
                       String state,
                       Long zipcode,
                       Long contactNum,
                       String authority) {
        try{           
            String subject = "Welcome YogaStudio.";
            String message = "Dear " + fullname + ",\r\n\r\n" + "Welcome to YogaStudio.\r\n\r\n" + "Your password is: " ;
                    
            emailservice.sendEmail(email,subject,message);
        }
        catch(Exception e){
         System.out.println("Error sending email "+e.getMessage());
        }

    }  
    
     @After("execution(* YogaStudio.service.UserService.add(..))&& args( id)")
    public void sendEmail1(JoinPoint joinpoint, Long id) {
        try{
           
            //System.out.println("khanuser");        
           // emailservice.generateEmailForNewAppRegistration(email);
        }
        catch(Exception e){
         System.out.println("Error sending email "+e.getMessage());
        }

    } 

}
