/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_project.UserInformation.FileManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author Butthole
 */
public class File_Manager {
    protected static  final String ErrorLog = "ErrorLog.log";
    private static ReadFile Readconfig;
    private static CreateFiles CreateFile;
    private static writeToFile FileWrite;
    Boolean IsNull;
    
    public File_Manager()
    {
       
    }   
    public void init()
    {                
        if (CreateFile == null)CreateFile = new CreateFiles();
        if (Readconfig == null)Readconfig = new ReadFile();
        if (FileWrite == null)FileWrite = new writeToFile(); 
    }
            
    public void createFolder(String path)
    {
        File  f = new File(path);
        f.mkdir();        
        System.out.println("Server Folder created in: " + path);              
    }    
            
    public void Createlog(String Error)
    {
        System.out.println(":>Creating Log for error: " + Error);              
        if (!Readconfig.DoesExist(ErrorLog))
        {            
            System.out.println(":>Log exists, Overwriting...");              
        }     
        if (Readconfig.DoesExist(ErrorLog))
        {
            CreateFile.CreateFile(ErrorLog);
            FileWrite.OverwriteFile(ErrorLog, Error);            
        }
    }
    
     public String readTextFile(String filePath)
    {
        if(Readconfig.DoesExist(filePath)) Readconfig.ReadFile(filePath);                               
        return null;
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
}
