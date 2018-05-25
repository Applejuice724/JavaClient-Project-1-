/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_project.UserInformation;

/**
 *
 * @author Butthole
 */
public class ErrorHandle {
    public String Error(String Error)
    {
        if (Error.equals("DENY1")) return ("Incorrect Username");
        if (Error.equals("DENY2")) return ("Incorrect Password");                
        return Error;
    }    
}
