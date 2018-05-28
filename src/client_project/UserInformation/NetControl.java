/*
    This class handles changes to ip and port with very small amounts of 
    Error Checking. It assumes any input that isn't null is correct and
    Useable

    It also collects and shares data streamed from the server to the client


 */
package client_project.UserInformation;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.binary.Hex;
/**
 *
 * @author 101580150
 */
public class NetControl extends Thread  {
    private  PrintWriter osToServer;
    private boolean CONNECTED = false;
    private  boolean AUTHENTICATED = false;
    private boolean ERRORFLAG = false;
    private  boolean UPDATE = false; 
    private static Boolean SETTINGCONNECTION = true;
    private static String ErrorLog;
    private BufferedReader FromServer;
    private Socket connectToServer;
    boolean running_w;  
    private int port_w;
    private static String Ip_w;
    private static String fromServer;
    private static String username;
    private static String password;    
    private static DigestUtils Digest;    
    private String ACK = "ACK";
    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();


    @Override
    public void destroy() {
        super.destroy(); //To change body of generated methods, choose Tools | Templates.
    }
    NetControl()
    {                             
        System.out.println("*** Network Thread Initialized");
        port_w = -1;
        running_w = true;
    }
    
    public void setParameters(String ip, int port, String inputUsername, String inputPassword) 
    {                        
        username = inputUsername;
        password = inputPassword;
        port_w = port;
        Ip_w = ip;    
        fromServer = new String();
        System.out.println("*** Username: " + username);        
        System.out.println("*** Password: " + password);
        System.out.println("*** IP: " + Ip_w);
        System.out.println("*** Port: " + port_w);    
    }
    
    @Override
    public void run() 
    {         
        CONNECTED = true;                            
      if (Ip_w != null || port_w != -1){
        try {         
            connectToServer = new Socket(Ip_w, port_w);                                    
            // Create data input and output streams            
            FromServer = new BufferedReader
                    (new InputStreamReader(connectToServer.getInputStream()));
            osToServer = new PrintWriter(connectToServer.getOutputStream(), true);
            while (true){
                if (SETTINGCONNECTION)
                { 
                    setUpConnection();  // This is the credentual Check Stage=
                }                                           
                while (AUTHENTICATED) // If creds Statisfied, run network Communicaitons
                {                                           
                }                                       
                System.out.print("");            
            }
        } 
        catch (IOException ex) 
        {        
            Logger.getLogger(NetControl.class.getName()).log(Level.SEVERE, null, ex);                       
            System.out.println("*** Network Error: No server Found" + ex);            
        }                    
      }                    
      else 
      {
          System.out.println("*** Network Error: There is no Port/Ip bound");
      }
    } 
    
    private void setUpConnection()
    {
        while(!AUTHENTICATED && SETTINGCONNECTION){
            try{   
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
                     ToggleFlag("AUTHENTICATED");
                     ToggleFlag("UPDATE");
                 }
                 else{                                      
                     logError(fromServer);
                 }
             } 
             else {
                 logError(fromServer);
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
    public void RetryAuthentication(String inputUsername, String inputPassword)
    {                
        System.out.println("*** Reconecting: with  " + inputUsername);                             
        username = inputUsername;        
        password = inputPassword;  
        ToggleFlag("SETTINGCONNECTION");
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
                
            default:
                System.out.println(" No such Flag Found ");
                                                                                     
        }                                                     
    }
    
    //              ~~~~~~~~~~~~~~~~~Log functions~~~~~~~~~~~~~~~~
    
     private void sendMessage(String Message)
    {          
        System.out.println(":> Sending: " + Message);                             
        fromServer = null;
        osToServer.flush(); 
        osToServer.println(Message);        
    }   
     

    private void PrintToggledFlag(String InputBool, boolean result)
    {                
        System.out.println(":> Boolean  " + InputBool + " Changed to : " + result);                                     
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
