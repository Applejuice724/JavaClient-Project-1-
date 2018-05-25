
package client_project.GUI.MainMenu;

import client_project.ApplicationStateManager;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ProfileInterface extends ApplicationStateManager implements ActionListener  {
    
    // Private Variables               
    private javax.swing.BorderFactory Borderfactory ;
    private javax.swing.border.Border Border;
    private  java.awt.Color color;
    
    // JPANELS        
    private javax.swing.JPanel ProfileUpdate;
    private javax.swing.JPanel PanelRow1;       
    private javax.swing.JPanel PanelRow2;    
    private javax.swing.JPanel PanelRow3;    
    private javax.swing.JPanel PanelRow4;        
    private javax.swing.JPanel PanelRow5;       
    private javax.swing.JPanel PanelRow6;            
    
    // Layouts
    javax.swing.SpringLayout ProfileUpdateLayout = new javax.swing.SpringLayout();     
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
        
    // Update Labels
    private javax.swing.JLabel UpdateProfileName;          
    private javax.swing.JLabel UpdateLastName;  
    private javax.swing.JLabel UpdateFirstName;
    
    // Update Text Fields
    private javax.swing.JTextField UpdateProfileNameINPUT;
    private javax.swing.JTextField UpdateFirstNameINPUT;        
    private javax.swing.JTextField UpdateLastNameINPUT;
    
        
    // Update Button Fields
    private javax.swing.JButton RequestUpdate;
    
    
    public ProfileInterface()
    {
        InitComponents();        
    }
        
    @SuppressWarnings("unchecked")
    void InitComponents() 
    {                                        
        Border = Borderfactory.createLineBorder(color.BLACK, 1);
        ProfileUpdate = new javax.swing.JPanel();        
        PanelRow1 = new javax.swing.JPanel();
        PanelRow2 = new javax.swing.JPanel();        
        PanelRow3 = new javax.swing.JPanel();
        PanelRow4 = new javax.swing.JPanel();       
        PanelRow5 = new javax.swing.JPanel();
        PanelRow6 = new javax.swing.JPanel(); 
        
        // Profile Update Widgets                
        UpdateProfileName = new javax.swing.JLabel("Update Profile Name:"); 
        UpdateProfileNameINPUT = new javax.swing.JTextField(12);
                
        UpdateFirstName= new javax.swing.JLabel("Update First Name:"); 
        UpdateFirstNameINPUT = new javax.swing.JTextField(12);
        
        UpdateLastName = new javax.swing.JLabel("Update Last Name:"); 
        UpdateLastNameINPUT = new javax.swing.JTextField(12);    
        
        RequestUpdate = new javax.swing.JButton("Request Update"); 
        
        // Profile infomation Widgets
        ProfileName = new javax.swing.JLabel("Profile:"); 
        ProfileName_Input = new javax.swing.JLabel("Example_profile");
        
        UserName = new javax.swing.JLabel("Username"); 
        UserName_Input = new javax.swing.JLabel("ExampleLogInName");                

        FirstName = new javax.swing.JLabel("$FIRST_NAME");
        FirstName_Input = new javax.swing.JLabel("Dane");
        
        LastName = new javax.swing.JLabel("LAST_NAME");         
        LastName_Input = new javax.swing.JLabel("Joe");
        
        StaffID = new javax.swing.JLabel("STAFF_ID");
        StaffID_Input = new javax.swing.JLabel("32");
        
        UpdateLABEL = new javax.swing.JLabel("Last Update:");                                       
        UpdateLABEL_Input = new javax.swing.JLabel("Example_Date");  
        
        
        
        setComponents();
        this.setLayout(MainWinlayout);
        this.setBorder(Border);                        
        this.setVisible(true);
    }  
    
     
    
    void setComponents()
    {
        setRow(PanelRow1, LayoutRow1, ProfileName, ProfileName_Input, 10);
        setRow(PanelRow2, LayoutRow2, UserName, UserName_Input, 90);
        setRow(PanelRow3, LayoutRow3, FirstName, FirstName_Input, 180);
        setRow(PanelRow4, LayoutRow4, LastName, LastName_Input, 260);
        setRow(PanelRow5, LayoutRow5, StaffID, StaffID_Input, 340);
        setRow(PanelRow6, LayoutRow6, UpdateLABEL, UpdateLABEL_Input, 420);               
        setUpdateScreen();

    }   
        
    void setUpdateScreen()
    {
        this.add(ProfileUpdate);
        ProfileUpdate.setBorder(Border);
        ProfileUpdate.setLayout(ProfileUpdateLayout);
        ProfileUpdate.add(UpdateProfileName);                
        ProfileUpdate.add(UpdateProfileNameINPUT);                
        ProfileUpdate.add(UpdateFirstName);
        ProfileUpdate.add(UpdateFirstNameINPUT);
        ProfileUpdate.add(UpdateLastName);
        ProfileUpdate.add(UpdateLastNameINPUT);                        
        ProfileUpdate.add(RequestUpdate); 
        
        ProfileUpdate.setPreferredSize(new Dimension(1250,300));
        
        MainWinlayout.putConstraint           // Profile Input
                    (javax.swing.SpringLayout.WEST, ProfileUpdate, 
                           10 ,javax.swing.SpringLayout.WEST, this);
       
        
        MainWinlayout.putConstraint           // Profile Input       
                    (javax.swing.SpringLayout.SOUTH,ProfileUpdate, 
                           -20 ,javax.swing.SpringLayout.SOUTH, this);
        
        ProfileUpdateLayout.putConstraint     // Profile Input
                    (javax.swing.SpringLayout.WEST,UpdateProfileName , 
                           50 ,javax.swing.SpringLayout.WEST, ProfileUpdate);
               
        ProfileUpdateLayout.putConstraint     // Profile Input       
                    (javax.swing.SpringLayout.NORTH,UpdateProfileName, 
                           25 ,javax.swing.SpringLayout.NORTH, ProfileUpdate);
        
        ProfileUpdateLayout.putConstraint     // Profile Input
                    (javax.swing.SpringLayout.WEST,UpdateProfileNameINPUT , 
                           140 ,javax.swing.SpringLayout.WEST, UpdateProfileName);
               
        ProfileUpdateLayout.putConstraint     // Profile Input       
                    (javax.swing.SpringLayout.SOUTH,UpdateProfileNameINPUT, 
                           0 ,javax.swing.SpringLayout.SOUTH, UpdateProfileName);  
        
        
        ProfileUpdateLayout.putConstraint     // FIRST NAME Input       
                    (javax.swing.SpringLayout.WEST,UpdateFirstName, 
                           350 ,javax.swing.SpringLayout.WEST, UpdateProfileName);  
        
        ProfileUpdateLayout.putConstraint     // FIRST NAME  Input       
                    (javax.swing.SpringLayout.NORTH,UpdateFirstName, 
                           0 ,javax.swing.SpringLayout.NORTH, UpdateProfileName);  
        
        ProfileUpdateLayout.putConstraint     // FIRST NAME  Input       
                    (javax.swing.SpringLayout.SOUTH,UpdateFirstNameINPUT, 
                           0 ,javax.swing.SpringLayout.SOUTH, UpdateFirstName);  
        
        ProfileUpdateLayout.putConstraint     // FIRST NAME  Input       
                    (javax.swing.SpringLayout.WEST,UpdateFirstNameINPUT, 
                           150 ,javax.swing.SpringLayout.WEST, UpdateFirstName);     
        
                
        ProfileUpdateLayout.putConstraint     // // LAST NAME Input       
                    (javax.swing.SpringLayout.WEST,UpdateLastName, 
                           0 ,javax.swing.SpringLayout.WEST, UpdateProfileName);  
        
        ProfileUpdateLayout.putConstraint     // LAST NAME Input       
                    (javax.swing.SpringLayout.NORTH,UpdateLastName, 
                           100 ,javax.swing.SpringLayout.NORTH, UpdateProfileName);  
        
        ProfileUpdateLayout.putConstraint     // LAST NAME Input       
                    (javax.swing.SpringLayout.SOUTH,UpdateLastNameINPUT, 
                           0 ,javax.swing.SpringLayout.SOUTH, UpdateLastName);  
        
        ProfileUpdateLayout.putConstraint     // LAST NAME Input       
                    (javax.swing.SpringLayout.WEST,UpdateLastNameINPUT, 
                           140 ,javax.swing.SpringLayout.WEST, UpdateLastName);  
        
                
        ProfileUpdateLayout.putConstraint     // UPDATE BUTTON       
                    (javax.swing.SpringLayout.SOUTH,RequestUpdate, 
                           0 ,javax.swing.SpringLayout.SOUTH, UpdateLastName);  
        
        ProfileUpdateLayout.putConstraint     // UPDATE BUTTON     
                    (javax.swing.SpringLayout.WEST,RequestUpdate, 
                           415 ,javax.swing.SpringLayout.WEST, UpdateLastName); 
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

    @Override
    public void actionPerformed(ActionEvent e) {
    
        
    }
}