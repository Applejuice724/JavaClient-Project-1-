/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_project.UserInformation.FileManager;
import java.io.File;

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
    
}
