/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ebrima
 */
public class Util {
        //private method to generate password
    public static String generatePassword(){
      return RandomStringUtils.randomAlphanumeric(6);
    }
    public static String uploadImage(MultipartFile file,String rootPath){
       
          if (!file.isEmpty()) {
            try {
                String name = RandomStringUtils.randomNumeric(12).concat(file.getOriginalFilename()); //generate random number as the name for the file
                byte[] bytes = file.getBytes();
 
                // Creating the directory to store file
                //String rootPath = System.getProperty("resources.home");
                //File.separator + "resources"+File.separator + "public"+File.separator +"images"
                File dir = new File(rootPath + File.separator + "images");
                if (!dir.exists())
                    dir.mkdirs();
  

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
 
               // logger.info("Server File Location="
                       // + serverFile.getAbsolutePath());
 
                return serverFile.getAbsolutePath(); //return the full path of the file
            } catch (Exception e) {
                //return "You failed to upload " + name + " => " + e.getMessage();
                return null; // return null file path file upload failed
            }
        } else {
            return null; // return null file path file upload failed
        }
    }
}
