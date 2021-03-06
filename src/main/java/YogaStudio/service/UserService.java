/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.service;


import YogaStudio.dao.generic.UserDAO;
import YogaStudio.domain.CreditCardEntity;
import YogaStudio.domain.CustomerEntity;
import YogaStudio.domain.FacultyEntity;
import YogaStudio.domain.UserEntity;
import YogaStudio.util.*;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author Shahadat
 */
public class UserService {
    
    @Autowired
    private UserDAO userDao;
      
    public UserService(){}
    
    public UserService(UserDAO userDao){
        this.userDao=userDao;      
    }
    
    public boolean authenticateUser(String username, String password){
       Byte enabled = 1;
       return  userDao.authenticateUser( username,password,enabled);
    }
    public List<UserEntity> getAll() {
        return userDao.getAll();     
    }

    public boolean add(String fullname, String email,String userName, String authority) {    
        String password = Util.generatePassword();// auto generate password;//
        UserEntity user = new CustomerEntity(userName,password,fullname,email);
        //set the user authority
        user.setAUTHORITY(authority);
        //enable the user account
        user.setActivests(Byte.valueOf("1"));
        //determine if a user with such email already exists
        if (!userDao.userExists("email", user.getEmail()))
        {
              return userDao.add(user);   
        } 
        else
            return false;
       // email.generateEmailForNewAppRegistration("shohagcoe@gmail.com");   
    }
    
    public UserEntity add(String fullname, 
                       String email,
                       String username, 
                       Date dateOfBirth,
                       String street,
                       String city,
                       String country,
                       String state,
                       Long zipcode,
                       Long contactNum,
                       String authority) 
         {    
        String password = Util.generatePassword();// auto generate password;//
        boolean saved = false;
        UserEntity user = new CustomerEntity(username, 
                                             password, 
                                             fullname, 
                                             email,
                                             authority, 
                                             dateOfBirth, 
                                             contactNum, 
                                             street,  
                                             city, 
                                             state,
                                             country, 
                                             zipcode);
        //set the user authority
        user.setAUTHORITY(authority);
        //enable the user account
        user.setActivests(Byte.valueOf("1"));
        //determine if a user with such email already exists
        if (!userDao.userExists("email", user.getEmail()))
        {
              saved =userDao.add(user);   
        } 
        return (saved)? user: null; 
    }
    
    public List<UserEntity> findBy(String fieldName,String value) 
    {           
      return userDao.findBy(fieldName, value);
    }
    
    public CustomerEntity findCustomerBy(String fieldName,String value){
        List<CustomerEntity> list = userDao.findEntityBy("CustomerEntity",fieldName, value);
        return (!list.isEmpty())? list.get(0): null;
    }
    
    public FacultyEntity findFacultyBy(String fieldName,String value){
        List<FacultyEntity> list = userDao.findEntityBy("FacultyEntity",fieldName, value);
        return (!list.isEmpty())? list.get(0): null;
    }

    public UserEntity get(Long id) {     
        return userDao.get(id);      
    }
    
    public UserEntity getUser(int id) {     
        return userDao.get(id);      
    }
    
    public UserEntity update(Long userId, UserEntity userentity) {     
        return userDao.update(userId, userentity);    
    }
       
    public void delete(Long userId) {    
        userDao.delete(userId);   
    }
    
    public UserEntity findUser(String username, String password) {
        return  userDao.findUser( username,password);
    }
    
    public UserEntity findUser(String username) {
        List<UserEntity> users = userDao.findEntityBy("UserEntity", "username", username);
        if(users.size() != 0)
            return users.get(0);
        return null;
    }
    
    public UserEntity findUserBy(String fieldName,String value){
        List<UserEntity> list = userDao.findEntityBy("UserEntity",fieldName, value);
        return (!list.isEmpty())? list.get(0): null;
    }

    public boolean updatePassword(UserEntity userentity) {
        String password = Util.generatePassword();// auto generate password;//
        userentity.setPassword(password);
        return  userDao.updateUser(userentity);
    }
    
    public boolean addCreditCard(UserEntity userentity, Long cardNumber, Date expiryDate){
     return userDao.addCreditCard(userentity,cardNumber,expiryDate);
    }
    
    //remove credit card
    public boolean removeCreditCard(UserEntity user){
        try{
          return userDao.removeCreditCard(user);
        }
        catch(Exception e){
           return false;
        }
    }
}
