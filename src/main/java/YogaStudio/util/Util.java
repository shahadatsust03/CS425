/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package YogaStudio.util;

import org.apache.commons.lang.RandomStringUtils;

/**
 *
 * @author ebrima
 */
public class Util {
        //private method to generate password
    public static String generatePassword(){
      return RandomStringUtils.randomAlphanumeric(6);
    }
}
