/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_project.UserInformation;

import client_project.ApplicationStateManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author 101580150
 */
public class UserController extends ApplicationStateManager {
    
    private Socket UserSocket;
    private String IpAddress;
    private int PortNumber;
    private NetControl networkThread;
    ErrorHandle ReportError;
    PrintWriter out;
    BufferedReader in;
    BufferedReader stdIn;
    boolean Authenticated_w;
    
    public void ReacttoError()
    {
        System.out.println(":> Reporting errors");            
        ReportError = new ErrorHandle();
        String error_w = ReportError.Error(networkThread.getError());
        super.CreateErrorLog(error_w);        
    }
    
    public UserController()
    {
        // Default Values
        IpAddress = "127.0.0.1";
        PortNumber = 68;
    }

     
    public void StartNetworkExchange(String InputIP, int InputPort, String inputUserName, String inputPass)
    {                                                              
        System.out.println("*** Starting Connection");  
        closeCon();      
        networkThread.setParameters(InputIP, InputPort, inputUserName, inputPass);                               
        networkThread.start();       
    }  
    public void Update()
    {
        switch (networkThread.getupdateDefinition())    
        {
            case USERINFORMATIONUPDATE:
                super.updateProfileDisplay(networkThread.getUserData());
                super.setLayerSelect(LayerSelect.MAINMENU);               
                break;   
        }                
        networkThread.ToggleFlag("UPDATE");
        networkThread.changeUpdateDefine(NetControl.updateDefinition.NONE);
    }
    public void sendFiletoServer(File inputFile, String fileName)
    {
        try {
            networkThread.sendFile(inputFile, fileName);                  
        } catch (IOException ex) {}          
    }
      
    public void closeCon()
    {
        if (networkThread != null)networkThread.closeCon();                
        if (networkThread != null)networkThread.stopEverything();
        networkThread = null;
        networkThread = new NetControl();
    }
    public void sendAdminUserAddRequestController(String SQLStatement)
    {
        networkThread.sendAdminUserAddRequest(SQLStatement);
    }
    public boolean needUpdate()
    {        
        if (networkThread != null )return networkThread.needUpdate();
        return false;
    }
    public boolean Authenticated()
    {
        if (networkThread != null )return networkThread.getAuthenticated();
        return false;
    }
    public boolean errorReceived()
    {                
        if (networkThread != null )return networkThread.getErrorFlag();
        return false;
    }    
}
