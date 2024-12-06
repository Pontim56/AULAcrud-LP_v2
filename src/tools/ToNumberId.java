/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

/**
 *
 * @author Darkazin
 */
public class ToNumberId {

    public static int toNumberId(String id) {
        
        String cleanId = id.replaceAll("[^\\d]", "");       
        int numberId = Integer.parseInt(cleanId);
        
        return numberId;
    }
    public static String CleanString(String id) {
        
        String cleanId = id.replaceAll("[^\\d]", "");
        if (cleanId == ""){
        cleanId = null;}
        return cleanId;
    }

}
