/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_project.GUI.LogInScreen;

import client_project.ApplicationStateManager;
import client_project.UserInformation.UserInputChecker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author 101580150
 */
public final class LogInInput extends ApplicationStateManager implements ActionListener {
        
    javax.swing.SpringLayout MainWinlayout = new javax.swing.SpringLayout();  
    private UserInputChecker CheckSyntax;
    private final javax.swing.JPanel LogInDetails;
    private final javax.swing.JButton LogBttn;
    private final javax.swing.JLabel LogLbl_U ;
    private final javax.swing.JLabel LogLbl_P;
    private final javax.swing.JTextField Login_UsrNM;
    private final javax.swing.JPasswordField Login_Pass ;    
    private final javax.swing.JLabel IP_INPUT_LBL;
    private final javax.swing.JLabel Port_Input_LBL ;
    private final javax.swing.JTextField Ip_Input;
    private final javax.swing.JTextField Port_Input;    
    private final javax.swing.JLabel Error;
    private javax.swing.BorderFactory Borderfactory ;
    private javax.swing.border.Border Border;
    private java.awt.Color color;
    
    
    // Actions made by the log in screen Go here
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if (source == LogBttn)               
        {
            String ip_w = Ip_Input.getText();                     
            String port_w = Port_Input.getText();
            String name_w = Login_UsrNM.getText();
            String Password = Login_Pass.getText();
            Boolean IncorrectInput = CheckSyntax.CheckNetworkInformation(ip_w, port_w);
            if (!IncorrectInput) 
            {                
                super.SetNetworkInfo(ip_w, port_w, name_w, Password);
                super.StartNetExchange();
            }
            
        }
        
    }
    
    public void setError(String inputError)
    {
        Error.setText("Error! " + inputError);
        Error.setVisible(true);
    }
    
    
    public LogInInput()                        
    {            
        Border = Borderfactory.createLineBorder(color.BLACK, 1);
        LogInDetails = new javax.swing.JPanel();
        LogBttn = new javax.swing.JButton("Log In");
        LogLbl_U = new javax.swing.JLabel("Enter User Name:");
        LogLbl_P = new javax.swing.JLabel("Enter Password:");
        Login_UsrNM = new javax.swing.JTextField("myserverADMIN", 20);
        Login_Pass = new javax.swing.JPasswordField("ABC123" ,10);    
        IP_INPUT_LBL = new javax.swing.JLabel("Enter IP information");
        Port_Input_LBL = new javax.swing.JLabel("Enter port:");
        Ip_Input = new javax.swing.JTextField("127.0.0.1", 20);
        Port_Input = new javax.swing.JTextField("8001", 6);
        Error = new javax.swing.JLabel(); Error.setVisible(false);
        CheckSyntax = new UserInputChecker();
        SetUpComponents();
    }
    
    void SetUpComponents()
    {
        this.setLayout(MainWinlayout);
        this.setBorder(Border);
        
        this.add(LogInDetails);
        this.add(LogBttn);
        this.add(LogLbl_U);
        this.add(LogLbl_P);
        this.add(Login_UsrNM);
        this.add(Login_Pass);
        this.add(IP_INPUT_LBL);
        this.add(Port_Input_LBL);
        this.add(Ip_Input);
        this.add(Port_Input); 
        this.add(Error);
        this.setVisible(true);
        SetcomponentLocation();    
    }
    
    void SetcomponentLocation()
    {
         // Ease of use Variables
                LogBttn.addActionListener(this);
                // GROUP ONE
                int LoginPosx = 850;       // Changes made to this
                int LoginPosY = 250;        // Change all in group one
                
                int PasswordPosx = -230;    // Changes Password label
                int PasswordPosy = -50;     // When changed, changes username 
                                            // info too
                
                int PortPosx = -255;        // Changes port label                        
                int PortPosy = 50;         // When changed, changes ip
                                            // info                                                                      
                // Button Log IN This is the anchor for all components
                MainWinlayout.putConstraint                
                    (javax.swing.SpringLayout.EAST, LogBttn, LoginPosx
                            , javax.swing.SpringLayout.EAST, LogInDetails);
                MainWinlayout.putConstraint
                            (javax.swing.SpringLayout.NORTH, LogBttn, LoginPosY 
                                    , javax.swing.SpringLayout.NORTH, LogInDetails);
                                
                MainWinlayout.putConstraint                
                    (javax.swing.SpringLayout.EAST, Error, 800
                            , javax.swing.SpringLayout.EAST, LogInDetails);
                MainWinlayout.putConstraint
                            (javax.swing.SpringLayout.NORTH, Error, 0 
                                    , javax.swing.SpringLayout.NORTH, LogInDetails);
                                                 
                // ########### USER INFO INPUT #################
                // LOGGIN IN WIDGETS
                // PASSWORD
                MainWinlayout.putConstraint  // LABEL              
                    (javax.swing.SpringLayout.EAST, LogLbl_P, PasswordPosx
                            , javax.swing.SpringLayout.EAST, LogBttn);
                MainWinlayout.putConstraint  // LABEL
                            (javax.swing.SpringLayout.NORTH, LogLbl_P, PasswordPosy 
                                    , javax.swing.SpringLayout.NORTH, LogBttn);                 
                MainWinlayout.putConstraint  // TEXTFIELD              
                    (javax.swing.SpringLayout.EAST, Login_Pass, 11
                            , javax.swing.SpringLayout.EAST, LogLbl_P);
                MainWinlayout.putConstraint  // TEXTFIELD  
                            (javax.swing.SpringLayout.NORTH, Login_Pass, 20 
                                    , javax.swing.SpringLayout.NORTH, LogLbl_P);   
                // USERNAME
                MainWinlayout.putConstraint  // LABEL                   
                    (javax.swing.SpringLayout.EAST, LogLbl_U, -250
                            , javax.swing.SpringLayout.EAST, LogLbl_P);
                MainWinlayout.putConstraint  // LABEL  
                            (javax.swing.SpringLayout.NORTH, LogLbl_U, 0
                                    , javax.swing.SpringLayout.NORTH, LogLbl_P);
                MainWinlayout.putConstraint  // TEXTFIELD                
                    (javax.swing.SpringLayout.EAST, Login_UsrNM, 58
                            , javax.swing.SpringLayout.EAST, LogLbl_U);
                MainWinlayout.putConstraint  // TEXTFIELD
                            (javax.swing.SpringLayout.NORTH, Login_UsrNM, 20 
                                    , javax.swing.SpringLayout.NORTH, LogLbl_U);                                  
                // ########### IP INPUT #################
                // PORT INPUT
                MainWinlayout.putConstraint   // LABEL             
                    (javax.swing.SpringLayout.EAST, Port_Input_LBL  , PortPosx
                            , javax.swing.SpringLayout.EAST, LogBttn);
                MainWinlayout.putConstraint   // LABEL
                            (javax.swing.SpringLayout.NORTH, Port_Input_LBL , PortPosy 
                                    , javax.swing.SpringLayout.NORTH, LogBttn);
                MainWinlayout.putConstraint   // TEXTFIELD              
                    (javax.swing.SpringLayout.EAST, Port_Input   , 5
                            , javax.swing.SpringLayout.EAST, Port_Input_LBL);
                MainWinlayout.putConstraint   // TEXTFIELD 
                            (javax.swing.SpringLayout.NORTH, Port_Input   , 20 
                                    , javax.swing.SpringLayout.NORTH, Port_Input_LBL);  
                // IP ADDRESS INPUT
                MainWinlayout.putConstraint                
                    (javax.swing.SpringLayout.EAST, IP_INPUT_LBL, -220
                            , javax.swing.SpringLayout.EAST, Port_Input_LBL);
                MainWinlayout.putConstraint
                            (javax.swing.SpringLayout.NORTH, IP_INPUT_LBL, 0
                                    , javax.swing.SpringLayout.NORTH, Port_Input_LBL);
                 MainWinlayout.putConstraint                
                    (javax.swing.SpringLayout.EAST, Ip_Input   , 53
                            , javax.swing.SpringLayout.EAST, IP_INPUT_LBL);
                MainWinlayout.putConstraint
                            (javax.swing.SpringLayout.NORTH, Ip_Input   , 20 
                                    , javax.swing.SpringLayout.NORTH, IP_INPUT_LBL);                  
                
    }  
}
