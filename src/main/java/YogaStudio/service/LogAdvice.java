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

    private EmailController email=new EmailController();

    @After("execution(* YogaStudio.service.UserService.add(..))&& args(userentity)")
    public void sendEmail(JoinPoint joinpoint, UserEntity userentity) {
        try{
        String email1=userentity.getEmail();
        //System.out.println("khanuser");
        
        email.generateEmailForNewAppRegistration(email1);
        }
        catch(Exception e){
         System.out.println("Error sending email "+e.getMessage());
        }

    }

}
