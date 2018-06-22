
package client_project.GUI.MainMenu;

import client_project.ApplicationStateManager;
import java.awt.Dimension;


public class ProfileInterface extends ApplicationStateManager {          
    public ProfileInterface()
    {
        InitComponents();        
    }
    public void updateProfileDisplay(String[] newUsername)
    {
        ProfileName_Input.setText(newUsername[0]);
        UserName_Input.setText(newUsername[1]);
        FirstName_Input.setText(newUsername[2]);
        LastName_Input.setText(newUsername[3]);
        StaffID_Input.setText(newUsername[4]);
        UpdateLABEL_Input.setText(newUsername[5]);
        if (newUsername[0].equals("ADMIN"))userControls.setModTollsVisible(true);
        else{userControls.setModTollsVisible(false);}
    }
    
    public void updateDirectory()
    {                
    }
    
        
    @SuppressWarnings("unchecked")
    void InitComponents() 
    {                                        
        Border = Borderfactory.createLineBorder(color.BLACK, 1);
        PanelRow1 = new javax.swing.JPanel();
        PanelRow2 = new javax.swing.JPanel();        
        PanelRow3 = new javax.swing.JPanel();
        PanelRow4 = new javax.swing.JPanel();       
        PanelRow5 = new javax.swing.JPanel();
        PanelRow6 = new javax.swing.JPanel();             
        userControls = new UpdateProfile();      
        // Profile infomation Widgets
        ProfileName = new javax.swing.JLabel("PROFILE:"); 
        ProfileName_Input = new javax.swing.JLabel("Example_profile");        
        UserName = new javax.swing.JLabel("USERNAME:"); 
        UserName_Input = new javax.swing.JLabel("ExampleLogInName");                
        FirstName = new javax.swing.JLabel("FIRST NAME:");
        FirstName_Input = new javax.swing.JLabel("Dane");        
        LastName = new javax.swing.JLabel("LAST NAME");         
        LastName_Input = new javax.swing.JLabel("Joe");        
        StaffID = new javax.swing.JLabel("STAFF ID");
        StaffID_Input = new javax.swing.JLabel("32");        
        UpdateLABEL = new javax.swing.JLabel("Last Login:");                                       
        UpdateLABEL_Input = new javax.swing.JLabel("Example_Date");                          
        setComponents();
        this.setLayout(MainWinlayout);
        setUpdateScreen();
        this.setBorder(Border);                        
        this.setVisible(true);
    }  
    
    void setUpdateScreen()
    {
        userControls.setPreferredSize(new Dimension(1000,250));
        this.add(userControls);
        MainWinlayout.putConstraint          // Profile Input
                    (javax.swing.SpringLayout.WEST, userControls, 
                            100 ,javax.swing.SpringLayout.WEST, this);
               
        MainWinlayout.putConstraint           // Profile Input       
                    (javax.swing.SpringLayout.NORTH,userControls, 
                           500 ,javax.swing.SpringLayout.NORTH, this);        
    }             
    void setComponents()
    {
        setRow(PanelRow1, LayoutRow1, ProfileName, ProfileName_Input, 10);
        setRow(PanelRow2, LayoutRow2, UserName, UserName_Input, 90);
        setRow(PanelRow3, LayoutRow3, FirstName, FirstName_Input, 180);
        setRow(PanelRow4, LayoutRow4, LastName, LastName_Input, 260);
        setRow(PanelRow5, LayoutRow5, StaffID, StaffID_Input, 340);
        setRow(PanelRow6, LayoutRow6, UpdateLABEL, UpdateLABEL_Input, 420);               
    }  
    
    public boolean checkActionPerformed()        
    {             
        return userControls.checkActionPerformed();        
    }

     
    public ModAccess getCurrentAction()        
    {     
         return userControls.getCurrentAction();        
    }    
              
    public String getAddUserString()       
    {    
        return userControls.getAddUserString();     
    }       
    // 
    // SetRow Fncitonality
    // Takes the Panel for a specific Row, adds it's components
    // And sets the Position for this specific Panel           
    void setRow
        (javax.swing.JPanel InputPanel, javax.swing.SpringLayout InputLayout,
                javax.swing.JLabel INPUTLABL_1, javax.swing.JLabel INPUTLABEL_2,
                int PosY)
    {                                            
        InputPanel.setPreferredSize(new Dimension(600,50));
        InputPanel.setBorder(Border);
        this.add(InputPanel); 
        InputPanel.setLayout(InputLayout);
        InputPanel.add(INPUTLABL_1);                
        InputPanel.add(INPUTLABEL_2);
        InputPanel.setBorder(Border);
                                              
        int FirstRowPosx = 10;                
        MainWinlayout.putConstraint          // Profile Input
                    (javax.swing.SpringLayout.WEST, InputPanel, 
                            FirstRowPosx ,javax.swing.SpringLayout.WEST, this);
               
        MainWinlayout.putConstraint           // Profile Input       
                    (javax.swing.SpringLayout.NORTH,InputPanel, 
                           PosY ,javax.swing.SpringLayout.NORTH, this);
        
        
        InputLayout.putConstraint          // Profile LABEL
                    (javax.swing.SpringLayout.WEST, INPUTLABL_1, 
                            145 ,javax.swing.SpringLayout.WEST, InputPanel);
               
        InputLayout.putConstraint           // Profile LABEL       
                    (javax.swing.SpringLayout.NORTH,INPUTLABL_1, 
                            15 ,javax.swing.SpringLayout.NORTH, InputPanel);
                 
        InputLayout.putConstraint          // Profile Input
                    (javax.swing.SpringLayout.WEST, INPUTLABEL_2, 
                            400 ,javax.swing.SpringLayout.WEST, InputPanel);
               
        InputLayout.putConstraint           // Profile Input       
                    (javax.swing.SpringLayout.NORTH,INPUTLABEL_2, 
                            15 ,javax.swing.SpringLayout.NORTH, InputPanel); 
    }                 


    private static UpdateProfile userControls;
        // Private Variables               
    private javax.swing.BorderFactory Borderfactory ;
    private javax.swing.border.Border Border;
    private  java.awt.Color color;    
    // JPANELS        
    private javax.swing.JPanel PanelRow1;       
    private javax.swing.JPanel PanelRow2;    
    private javax.swing.JPanel PanelRow3;    
    private javax.swing.JPanel PanelRow4;        
    private javax.swing.JPanel PanelRow5;       
    private javax.swing.JPanel PanelRow6;                
    // Layouts
    javax.swing.SpringLayout MainWinlayout = new javax.swing.SpringLayout();          
    javax.swing.SpringLayout LayoutRow1 = new javax.swing.SpringLayout();   
    javax.swing.SpringLayout LayoutRow2 = new javax.swing.SpringLayout();        
    javax.swing.SpringLayout LayoutRow3 = new javax.swing.SpringLayout();
    javax.swing.SpringLayout LayoutRow4 = new javax.swing.SpringLayout();
    javax.swing.SpringLayout LayoutRow5 = new javax.swing.SpringLayout();
    javax.swing.SpringLayout LayoutRow6 = new javax.swing.SpringLayout();
    // Labels
    private javax.swing.JLabel ProfileName;    
    private javax.swing.JLabel UserName;       
    private javax.swing.JLabel FirstName;    
    private javax.swing.JLabel LastName;       
    private javax.swing.JLabel StaffID;    
    private javax.swing.JLabel UpdateLABEL;          
    // User Information        
    private javax.swing.JLabel ProfileName_Input;    
    private javax.swing.JLabel UserName_Input;       
    private javax.swing.JLabel FirstName_Input;    
    private javax.swing.JLabel LastName_Input;       
    private javax.swing.JLabel StaffID_Input;    
    private javax.swing.JLabel UpdateLABEL_Input;         

}