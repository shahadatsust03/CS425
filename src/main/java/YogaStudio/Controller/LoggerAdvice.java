/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.Controller;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 *
 * @author Shahadat
 */
@Aspect
public class LoggerAdvice {
    @Before("execution(* YogaStudioController.*.*(..))")
    public void log(JoinPoint joinpoint){
        System.out.println("In " + joinpoint.getClass().getName() + ":" +joinpoint.getSignature().getName());
    }
}
