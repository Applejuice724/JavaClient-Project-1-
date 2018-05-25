/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_project.UserInformation.FileManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadFile extends File_Manager {         
    Boolean FatelError;    
    private String PruneVariables( String InputVariable)
    {  
        int StartOfValue = 0;                                    
        String TempValue = "";           
        for (int i = 0; i <= InputVariable.length() - 1 ; i++)          
        {                                
            String Temp = InputVariable.substring(i, i + 1);                                    
            switch (Temp)                           
            {                        
                case ":":                                                                
                    TempValue = "";                                                              
                    break;                                       
                default:                
                    TempValue = TempValue+Temp;                    
                    break;                    
            }                                                        
        }                             
        return TempValue;                   
    }
    
    public String ReadFile(Path InputFile) 
    {        
        String Content = "";
        try (InputStream in = Files.newInputStream(InputFile);    
                BufferedReader reader =      
                        new BufferedReader(new InputStreamReader(in))) {    
            
            String Line = null;    
            while ((Line = reader.readLine()) != null) { 
                Content = Content + Line;
            }

            return Content;

        } catch (IOException x) {   
            System.err.println(x);
            System.out.println("Error, file Not readable: " + x);            
            Content = "File Not readAble";
            return Content;
        }                                
    }
    
    public String ReadFile(String InputFile) 
    {        
        Path Path_w = FileSystems.getDefault().getPath(InputFile);   
        String Content = "";
        try (InputStream in = Files.newInputStream(Path_w);    
                BufferedReader reader =      
                        new BufferedReader(new InputStreamReader(in))) {                
            String Line = null;    
            while ((Line = reader.readLine()) != null) { 
                Content = Content + Line;
            }
            return Content;
        } catch (IOException x) {   
            System.err.println(x);
            System.out.println("Error, file Not readable: " + x);            
            Content = "File Not readAble";
            return Content;
        }                                
    }         
            
    public Boolean DoesExist(Path Filename)
    {                
        try (InputStream in = Files.newInputStream(Filename);    
                BufferedReader reader =      
                        new BufferedReader(new InputStreamReader(in))) 
        {                
            String line = null;    
            while ((line = reader.readLine()) != null) {       
            }
            return true;
        } catch (IOException x) { 
            System.err.println(x);                        
            return false;
        }                                
    }    
    
    public Boolean DoesExist(String Filename)
    {        
        Path Path_w = FileSystems.getDefault().getPath(Filename);   
        try (InputStream in = Files.newInputStream(Path_w);    
                BufferedReader reader =      
                        new BufferedReader(new InputStreamReader(in))) 
        {                
            String line = null;    
            while ((line = reader.readLine()) != null) {       
            }
            return true;
        } catch (IOException x) { 
            System.err.println(x);                        
            return false;
        }                                
    }
    
    

}

