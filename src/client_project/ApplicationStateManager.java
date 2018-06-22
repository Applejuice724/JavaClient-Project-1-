
/*
    This class is used to do specify what buttons/layouts are currently being 
    used and to initialize the graphical interface when appropiate

    Layout manager Key:
    0 = No update (Used so the layout is only called once per change)
    1 = Log in screen
    2 = Main menu


*/
package client_project;
import client_project.GUI.LogInScreen.LogInInput;
import client_project.GUI.MainMenu.DirectoryInterface;
import client_project.GUI.TopBar;
import client_project.GUI.MainMenu.ProfileInterface;
import client_project.UserInformation.FileManager.File_Manager;
import client_project.UserInformation.UserController;
import java.awt.event.ActionListener;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Color;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.BorderFactory;
import javax.swing.border.Border;


public class ApplicationStateManager extends javax.swing.JPanel{
    public enum ModAccess {ADDUSR, QUERY, NONE};
    public enum LayerSet {MAINMENU, LOGINSCREEN, NONE, TEST};
    public static ActionListener actionPerformed;
    //          _____________PRIVATE FUNCTIONS_________________
    JFrame Panel_w;
    Container con;
    SpringLayout MainWinlayout = new SpringLayout();
    
    // Widget Groups
    TopBar topBar;
    private static LogInInput LogIninput;
    private static ProfileInterface DisplayProfile;
    private static DirectoryInterface listFiles;
        
    // Functional Utilities
    public static LayerSet LayerSelect;       // What layout to put on screen  
    private static String ip_w;        
    private static String Username_w;
    private static String Password_w;        
    private static int port;
    private static UserController Network;
    private static File_Manager fileManager;
    
    //          _____________PUBLIC FUNCTIONS_________________
    
    public void Init(String title, int width, int height, LayerSet Layout)       
    { 
        Panel_w  = new JFrame(title);          // Sets the title 
        Panel_w.setPreferredSize( new Dimension( width, height ) );                       
        LayerSelect = Layout;  
        PanelInfo();        
    }
     public void Init(String title, int size, LayerSet Layout)       
    { 
        Panel_w  = new JFrame(title);          // Sets the title       
        Panel_w.setPreferredSize(new Dimension(size + size, size));    // Sets size                           
        LayerSelect = Layout;           
        PanelInfo();   
    }  
     public void sendFile(File inputFile, String fileName)
     {                 
         Network.sendFiletoServer(inputFile, fileName);
     }
   
     public void SetNetworkInfo(String IpInput, String Portinput, String username, String pass)
     {
         ip_w = IpInput;  
         port = Integer.parseInt(Portinput);
         Username_w = username;   
         Password_w = pass; 
         System.out.println("*** Ip Changed to " + ip_w);                  
     }
     public void ReacttoModTerminal()
     {
         ModAccess ModTerminal = DisplayProfile.getCurrentAction();
         if (ModTerminal != null){
            switch(ModTerminal)
            {
                case ADDUSR:                 
                    Network.sendAdminUserAddRequestController(DisplayProfile.getAddUserString());
                    break;
                default:             
                    break;
            }
         }
     }
     public void StartNetExchange()
     {                      
         Network.StartNetworkExchange(ip_w, port, Username_w, Password_w);                                  
     }     
     public void setLayerSelect(LayerSet LayerSelected) // This is used to set the layout
     {
            LayerSelect = LayerSelected;                                               
     }     
     public void CreateErrorLog(String Error)
     {
         fileManager.Createlog(Error);
         LogIninput.setError(Error);         
     }
     public void updateProfileDisplay(String userdata[])
     {
         DisplayProfile.updateProfileDisplay(userdata);
         listFiles = new DirectoryInterface(userdata[1]);                 
         listFiles.setPreferredSize(new Dimension (600,450));
     }    
    //  This function checks for updates before it applies them when it is needed
    //  other functionality will be contained in a seperate function, this 
    //  function is ONLY used for updating the widgets/screens
    void update(){ 
      //  LayerSelect = 1;
        switch(LayerSelect)
       {
           case NONE:   // Checks for updates
               if (Network.needUpdate()&& Network.Authenticated()) Network.Update();
               if (Network.errorReceived()) Network.ReacttoError();
               if (DisplayProfile.checkActionPerformed() && DisplayProfile != null)ReacttoModTerminal();
               break;
                
           case LOGINSCREEN:  // Log in Screen
               // With the layer selected, we add constraints                          
               System.out.println("* Menu Screen Selected");
               con.removeAll();                // Remove Everything                
               SetConstraints(LayerSet.LOGINSCREEN);  // Add constraints 
               ContainerRefresh();
               Network.closeCon();
               topBar.setLogOutButtonVisible(false);
               con.add(topBar);
               con.add(LogIninput);
               ContainerRefresh();
               LayerSelect = LayerSet.NONE;
               break;
               
           case MAINMENU:  // Update to Main Menu
               System.out.println("** Log In Screen Selected");                               
               con.removeAll();                // Remove Everything  
               SetConstraints(LayerSelect);                              
               ContainerRefresh();
               topBar.setLogOutButtonVisible(true);               
               con.add(topBar);                               
               con.add(listFiles);
               con.add(DisplayProfile);  
               ContainerRefresh();
               LayerSelect = LayerSet.NONE;
               break;                              
           case TEST:               
               break;
           
           default:                                      
               break;       
       }        
    }
    void SetConstraints(LayerSet ConstraintSelect)
    {
        switch(ConstraintSelect)
       {
            case LOGINSCREEN:
                int LogIninputX = 405;
                int LogIninputy = -180;
                                
                MainWinlayout.putConstraint                
                    (SpringLayout.WEST, LogIninput, LogIninputX
                            , SpringLayout.WEST, con);
                MainWinlayout.putConstraint
                            (SpringLayout.SOUTH, LogIninput, LogIninputy 
                                    , SpringLayout.SOUTH, con);                   
                // ################# SYSTEM FUNCTIONS ################                                                                 
                MainWinlayout.putConstraint                
                    (SpringLayout.EAST, topBar, 0
                            , SpringLayout.EAST, con);
                MainWinlayout.putConstraint
                            (SpringLayout.NORTH, topBar, 0 
                                    , SpringLayout.NORTH, con);                                             
                break;                
            case MAINMENU:                                                                                                    

                MainWinlayout.putConstraint                
                    (SpringLayout.EAST, topBar, 0
                            , SpringLayout.EAST, con);
                MainWinlayout.putConstraint
                            (SpringLayout.NORTH, topBar, 0 
                                    , SpringLayout.NORTH, con);  
                MainWinlayout.putConstraint                
                    (SpringLayout.NORTH, listFiles, 120
                            , SpringLayout.NORTH, con);
                MainWinlayout.putConstraint                
                    (SpringLayout.WEST, listFiles, 645
                            , SpringLayout.WEST, con);   
                MainWinlayout.putConstraint                
                    (SpringLayout.NORTH, DisplayProfile, 100
                            , SpringLayout.NORTH, con);
                MainWinlayout.putConstraint                
                    (SpringLayout.WEST, DisplayProfile, 10
                            , SpringLayout.WEST, con);                                                                    
                break;        
        }
    }
    
    void PanelInfo()
    {                         
        Border border = BorderFactory.createLineBorder(Color.WHITE, 25);
        Panel_w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Panel_w.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        Panel_w.setUndecorated(true);  
        con = new Container();
        con = Panel_w.getContentPane();
        con.setLayout(MainWinlayout);
        
        // Component Groups
        topBar              = new TopBar();
        DisplayProfile      = new ProfileInterface();
        LogIninput          = new LogInInput();
        Network             = new UserController();
        fileManager         = new File_Manager();
        fileManager.init();        
        Panel_w.setVisible(true);        
        // WIDGET Sizes
        // SYSTEM WIDGETS
        topBar.setPreferredSize(new Dimension (300,100));        
        // Log In Screen                
        LogIninput.setPreferredSize(new Dimension(1000,700));       
        // Menu                                
        DisplayProfile.setPreferredSize(new Dimension(1280,800));
        // List Directories

    }    
    void ContainerRefresh()
    {                     
              con.setVisible(false);          // Refresh Screen        
              con.setVisible(true);           // Refresh Screen      
    }    
}