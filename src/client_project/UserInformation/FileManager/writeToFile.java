/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_project.UserInformation.FileManager;

import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Butthole
 */
public class writeToFile {
    public void OverwriteFile(String file, String data)
    {        
        try {
            PrintWriter writer = new PrintWriter(file, "UTF-8");
            writer.println("Error Log");
            writer.println(data);
            writer.close();
        }catch(IOException ex){System.out.println(ex);              

        }
    }
    
}
