/*
    This class handles changes to ip and port with very small amounts of 
    Error Checking. It assumes any input that isn't null is correct and
    Useable

    It also collects and shares data streamed from the server to the client


 */
package client_project.UserInformation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.digest.DigestUtils;
/**
 *
 * @author 101580150
 */
public class NetControl extends Thread  {        
    private BufferedReader FromServer;
    private Socket connectToServer;     
    public static updateDefinition upDefine;
    private  static PrintWriter osToServer;
    private InputStream InStream;
    private FileOutputStream FileOut;
    public enum updateDefinition {USERINFORMATIONUPDATE, ERRORUPDATE, LOGGINGOFF, FILETRANSFER, NONE}        
    private final String USERINFOREQUEST = "USERINFOREQUEST";
    private final String FILETRANREQ =     "FILETRANREQ";
    private final String ADDUSRREQ =     "ADDUSERREQ";
    private boolean running;
    private  boolean CONNECTED;
    private  boolean AUTHENTICATED ;
    private boolean ERRORFLAG;
    private static boolean UPDATE; 
    private boolean PROCESS;
    private static Boolean SETTINGCONNECTION;
    private static String ErrorLog;    
    boolean running_w;  
    private int port_w;
    private static String Ip_w;
    private static String fromServer;
    private String profile;
    private String username;
    private String firstName;
    private String lastName;
    private String staffID;
    private String lastLogInDate;
    private String password;   
    private String UserData[] = new String[6];
    private static DigestUtils Digest;    
    private String ACK = "ACK";
    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();        


    NetControl()
    {                             
        System.out.println("*** Network Thread Initialized");
        port_w = -1;
        running_w = true;
        running = true; 
        CONNECTED = false;
        AUTHENTICATED = false;
        ERRORFLAG = false;    
        UPDATE = false;    
        PROCESS = false;
        SETTINGCONNECTION = true;
    }
    
    public void setParameters(String ip, int port, String inputUsername, String inputPassword) 
    {                        
        username = inputUsername;
        password = inputPassword;
        port_w = port;
        Ip_w = ip;    
        fromServer = new String();
    }
    public void stopEverything()
    {
        try {
            running = false;
            if(osToServer != null)osToServer.close();
            if(FromServer != null)FromServer.close();
            if(connectToServer != null)connectToServer.close();
        } catch (IOException ex) {
            Logger.getLogger(NetControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("*** Network Thread killed");
    }
    
    public void run() 
    {                   
        System.out.println("*** Username: " + username);        
        System.out.println("*** Password: " + password);
        System.out.println("*** IP: " + Ip_w);
        System.out.println("*** Port: " + port_w);    
        
        while (running){
            CONNECTED = true;                                  
            if (Ip_w != null || port_w != -1){
            try {         
                connectToServer = new Socket(Ip_w, port_w);                                    
                // Create data input and output streams            
                FromServer = new BufferedReader
                        (new InputStreamReader(connectToServer.getInputStream()));
                osToServer = new PrintWriter(connectToServer.getOutputStream(), true);                         	  
                while (!connectToServer.equals(connectToServer.isClosed()))                    
                {         
                    while(SETTINGCONNECTION)
                    {                      
                        setUpConnection();  // This is the credentual Check Stage=
                    }                                           
                    while (AUTHENTICATED) // If creds Statisfied, run network Communicaitons
                    {                                                               
                    }                                                                        
                }  
            } 
            catch (IOException ex) 
            {        
                Logger.getLogger(NetControl.class.getName()).log(Level.SEVERE, null, ex);                       
                System.out.println("*** Network Error: No server Found");      
            }                           
          }                    
          else 
          {
              System.out.println("*** Network Error: There is no Port/Ip bound");
          }
        }
    }
    public void sendFile(File inputPath, String fileName) throws FileNotFoundException, IOException
    {    
        try{
            if (!PROCESS){            
                ToggleFlag("PROCESS");
                System.out.println("Sending File: "+inputPath);    
                sendMessage(FILETRANREQ); 
                if (FromServer.readLine().replaceAll("//s", "") == ACK);
                sendMessage(fileName);  
                if (FromServer.readLine().replaceAll("//s", "") == ACK);   
                // *****************        	 
                OutputStream o = connectToServer.getOutputStream();	  
                ObjectOutputStream s = new ObjectOutputStream(o); 
                byte[] content = Files.readAllBytes(inputPath.toPath());  
                s.writeObject(content);
                FromServer = new BufferedReader
                            (new InputStreamReader(connectToServer.getInputStream()));        
                osToServer = new PrintWriter(connectToServer.getOutputStream(), true);  
                if (FromServer.readLine().replaceAll("//s", "") == ACK) System.out.print("Majoer success!");
                ToggleFlag("PROCESS");
            }   
        }catch(Exception e){System.out.println("Send File Error!: "+e);}
    }  
    public void sendAdminUserAddRequest(String SQLMessage)
    {
        try{            
            if (!PROCESS)
            {            
                ToggleFlag("PROCESS");
                sendMessage(ADDUSRREQ);
                fromServer = FromServer.readLine().replaceAll("//s", ""); 
                if (!fromServer.equals(ACK)) return; 
                System.out.println("Add USer Second");
                sendMessage(username);   
                fromServer = FromServer.readLine().replaceAll("//s", ""); 
                if (!fromServer.equals(ACK)) return;  
                sendMessage(SQLMessage); 
                fromServer = FromServer.readLine().replaceAll("//s", ""); 
                if (!fromServer.equals(ACK)) return;                 
                // Put in Confirmaiton message here                                                
                ToggleFlag("PROCESS");
            }       
        }catch(Exception e){System.out.println("Send File Error!: "+e);}        
    }

    private void setUpConnection()
    {
          if (!PROCESS)
            {          
                try{    
                          
                    ToggleFlag("PROCESS");
                    int Timeout = 0;
                    if (Timeout >= 15) ToggleFlag("SETTINGCONNECTION");
                    System.out.println("*** Setting up Connection please wait");            
                    sendMessage("NETWORKSTART"); // Send Start Message                   
                    fromServer = FromServer.readLine().replaceAll("//s", "");                   
                    if (fromServer.equals(ACK)) SendCredentials(); // If message is sent back, send credentuals                        
                    Timeout++;
                }catch(IOException e){}            
            }
    }
    private void SendCredentials() 
    {                 
        System.out.println("*** Network Connection: Established, Authenticating...");
         try{     
             sendMessage(username); 
             fromServer = FromServer.readLine(); 
             if (fromServer.equals(ACK)) 
             {
                 System.out.println("*** Network Connection: Name Accepted, Password being sent...");
                 sendMessage(MySQLPassword(password));                          
                 fromServer = FromServer.readLine().replaceAll("//s", "");                  
                 if (fromServer.equals(ACK))
                 {
                     System.out.println("*** Authentation compelte..."); 
                     changeUpdateDefine(updateDefinition.USERINFORMATIONUPDATE);
                     ToggleFlag("AUTHENTICATED");
                     ToggleFlag("UPDATE");   
                     ToggleFlag("PROCESS");
                 }
                 else{                                      
                     logError(fromServer);
                     running = false;
                     SETTINGCONNECTION = false;
                     connectToServer.shutdownOutput();
                     ToggleFlag("PROCESS");
                     return;

                 }
             } 
             else {
                 logError(fromServer); 
                 running = false;
                 SETTINGCONNECTION = false;
                 return;
             }
         }catch(IOException e){}        
    }                
    private void logError(String ServerMessage)
    {      
        System.out.println("! Network ERROR: " + ErrorLog); 
        ToggleFlag("ERRORFLAG");
        ToggleFlag("SETTINGCONNECTION");
        if (!AUTHENTICATED){
            SETTINGCONNECTION = false;        
        }
        if (ErrorLog != null) ErrorLog = ErrorLog+" "+ServerMessage;      
        else ErrorLog = ServerMessage;
    }  

    public String getError() /// retruns error log in the net controller if !null
    {
        ToggleFlag("ERRORFLAG");
        if (ErrorLog != null) 
        {
            return ErrorLog;            
        }        
        else{return ("No Error Found"); }
    }
    public boolean IsConnected()
    {
        return CONNECTED;
    }
    
    public boolean getErrorFlag()
    {                
        return ERRORFLAG;
    }
    
    public boolean needUpdate()
    {
        return UPDATE;
    }
    
    public boolean getAuthenticated()
    {
        return AUTHENTICATED;
    }
    public void closeCon()
    {
        try{
            connectToServer.close();
        }catch(Exception e){}
    }
        
    public void ToggleFlag(String InputBool)
    {           
        switch(InputBool)
        {
            case "UPDATE":   
                UPDATE = !UPDATE;
                PrintToggledFlag(InputBool, UPDATE);
                break;
                
            case "CONNECTED":            
                CONNECTED = !CONNECTED;            
                PrintToggledFlag(InputBool, CONNECTED);            
                break;
            
            case "AUTHENTICATED":                    
                AUTHENTICATED = !AUTHENTICATED;            
                PrintToggledFlag(InputBool, AUTHENTICATED);
                break;
                
            case "ERRORFLAG":                            
                ERRORFLAG = !ERRORFLAG;            
                PrintToggledFlag(InputBool, ERRORFLAG);
                break;
                
            case "SETTINGCONNECTION":                            
                SETTINGCONNECTION = !SETTINGCONNECTION;            
                PrintToggledFlag(InputBool, SETTINGCONNECTION);
                break;
                
            case "PROCESS":
                PROCESS = !PROCESS;                                
                PrintToggledFlag(InputBool, PROCESS);
                break;
            default:
                System.out.println(" No such Flag Found ");
                                                                                     
        }                                                     
    }    
    //              ~~~~~~~~~~~~~~~~~Log functions~~~~~~~~~~~~~~~~    
     private void sendMessage(String Message)
    {     
        try{              
            System.out.println(":> Sending: " + Message);                                         
            fromServer = null;            
            osToServer.flush();             
            osToServer.println(Message);        
            
        }catch(Exception e){System.out.println(e);
}
    }          
     private void PrintToggledFlag(String InputBool, boolean result)
    {                
//        System.out.println(":> Boolean  " + InputBool + " Changed to : " + result);                                     
    }
    
    public static String MySQLPassword(String plainText)
    {
        try{
        byte[] utf8 = plainText.getBytes("UTF-8");
        byte[] test = Digest.sha(Digest.sha(utf8));
        return "*" + bytesToHex(test).toUpperCase();}
        catch(Exception e)
        {             
            System.out.println(":> ENCRYPTION Error:>");                                     
            return "Error";
        }  
    }
    public void changeUpdateDefine(updateDefinition definition)
    {
        upDefine = definition;
        if (upDefine ==  updateDefinition.USERINFORMATIONUPDATE)getUserInformaitonfromServer();
    }
    public Boolean getUserInformaitonfromServer()
    {        
        sendMessage(USERINFOREQUEST);  
        String data_w[] = new String[6];
        for (int i = 0; i < 6; i++)
        {
            try{
                data_w[i]= FromServer.readLine().replaceAll("//s", "");             
//                System.out.println("fuck "+data_w[i]);
            }catch(Exception e){}
        }
        profile=        data_w[0];
        username=       data_w[1];                
        firstName=      data_w[2];                                
        lastName=       data_w[3];                                
        staffID=        data_w[4];                                               
        lastLogInDate=  data_w[5];
        UserData= data_w;
        return true;
    }
    public String getUserName(){return username;}
    public String getProfile(){return profile;}
    public String getfirstName(){return firstName;}
    public String getLastName(){return lastName;}
    public String getstaffID(){return staffID;}
    public String getLastLogInDate(){return lastLogInDate;}
    public String[] getUserData(){return UserData;}
    public updateDefinition getupdateDefinition (){return upDefine;}
    public void setDefaultFlags()
    {             
        CONNECTED = false;       
        AUTHENTICATED = false;      
        ERRORFLAG = false;       
        UPDATE = false; 
    }
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }  
}
