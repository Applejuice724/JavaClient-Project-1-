/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_project.UserInformation;

import client_project.ApplicationStateManager;
import java.io.BufferedReader;
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
    NetControl networkThread;
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
        if (networkThread == null ) {                    
            System.out.println("*** Starting Connection");    
            networkThread = new NetControl();
            networkThread.setParameters(InputIP, InputPort, inputUserName, inputPass);                
            networkThread.start();
        }
        else
        {
            if(networkThread.IsConnected()) { // ToDo, check if Ip/port has changed
                networkThread.RetryAuthentication(inputUserName, inputPass);
            }
        }
    }  
    public void Update()
    {
        super.setLayerSelect(LayerSelect.MAINMENU);
        networkThread.ToggleFlag("UPDATE");
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
