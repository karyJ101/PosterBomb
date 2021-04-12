/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Kary Johnson
 */
public class Validation implements Serializable {
    
    public boolean maxInputLength(String userInfo, int maxLength){
        return userInfo.length() <= maxLength;
    }
    //Checks the length of an input
    public boolean minInputLength(String userInfo, int minLength){
        return userInfo.length() > minLength;
    }
    
    //Checks that user has entered all required information
    public boolean allFilled(String[] userInfo){
        for(String info : userInfo ){
            if(info == null || info.isEmpty()){
                return false;
            }
        }
        return true;
    }
    
    // Checks for invalid characters
    public boolean invalidChars(String input){
        for(int i = 0; i < input.length(); i++){
            if(!Character.isLetter(input.charAt(i))){
                return false;
            }
        }
        return true;
    }
    
    public boolean inputMatch(String first, String second){
        return second.equals(first);
    }
    
    public boolean emailPattern(String email){
        String regex = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    public String hashPassword(String password) throws NoSuchAlgorithmException{ // used to hash password
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(password.getBytes());// process the password
        byte[] bytes = messageDigest.digest(); // Gets the bytes of the MD5 digest
        StringBuilder builder = new StringBuilder(); // string builder used to build ne hash
        for(int i = 0; i < bytes.length; i++){
            builder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1)); // Build the string of characters
        }                
        return builder.toString();
    }
}
