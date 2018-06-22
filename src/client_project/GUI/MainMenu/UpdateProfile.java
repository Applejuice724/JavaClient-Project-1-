/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_project.GUI.MainMenu;

import client_project.GUI.MainMenu.ModTools.modAccess;
import client_project.ApplicationStateManager.ModAccess;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class UpdateProfile extends javax.swing.JPanel implements ActionListener
{            
    // Asthetic Utilities
    private javax.swing.BorderFactory Borderfactory ;
    private javax.swing.border.Border Border;        
    private java.awt.Color color;
    private javax.swing.SpringLayout ProfileUpdateLayout = new javax.swing.SpringLayout(); 
    private static modAccess modAccessTerminal = new modAccess();    
            // Update Labels  
    private javax.swing.JPanel ProfileUpdate;
    private javax.swing.JButton ModTools;

    // WIDGETS   
    

    
    public UpdateProfile()
    {   
        InitComponents();        
    }
        
    @SuppressWarnings("unchecked")
    public final void InitComponents()
    {             
        ModTools = new javax.swing.JButton("Mod Tools");                
        ModTools.setVisible(false); // !!!!!!! set to false after testing
        ProfileUpdate = new javax.swing.JPanel();
        Border = Borderfactory.createLineBorder(color.BLACK, 1);
        this.setBorder(Border);
        setUpdateScreen();               
    }
    public void setModTollsVisible(boolean booleanValue)
    {
        ModTools.setVisible(booleanValue);         
    }
        
     public void actionPerformed(ActionEvent e) {
         
        if (e.getSource().equals(ModTools) )
        {                    
            modAccessTerminal.createFrame();
        }      
     }
     public String getAddUserString()    
     {
         return modAccessTerminal.getAddUserString();
     }
     
     public ModAccess getCurrentAction()    
     {     
         return modAccessTerminal.getCurrentAction();    
     }              

    public boolean checkActionPerformed()
    {
        if (modAccessTerminal == null) return false;
        return modAccessTerminal.checkActionPerformed();
    }
    
     void setUpdateScreen()
    {
        this.add(ProfileUpdate);
        ProfileUpdate.add(ModTools);
        ProfileUpdate.setBorder(Border);
        ProfileUpdate.setLayout(ProfileUpdateLayout);       
        ProfileUpdate.setPreferredSize(new Dimension(1000,240));        
        ProfileUpdateLayout.putConstraint           // Profile Input
                    (javax.swing.SpringLayout.WEST, ProfileUpdate, 
                           10 ,javax.swing.SpringLayout.WEST, this);               
        ProfileUpdateLayout.putConstraint           // Profile Input       
                    (javax.swing.SpringLayout.SOUTH, ProfileUpdate, 
                           -20 ,javax.swing.SpringLayout.SOUTH, this);
        ModTools.setPreferredSize(new Dimension(100,50));
        ProfileUpdateLayout.putConstraint           // Profile Input
                    (javax.swing.SpringLayout.EAST, ModTools, 
                           990 ,javax.swing.SpringLayout.WEST, ProfileUpdate);               
        ProfileUpdateLayout.putConstraint           // Profile Input       
                    (javax.swing.SpringLayout.NORTH, ModTools, 
                           -230 ,javax.swing.SpringLayout.SOUTH, ProfileUpdate);                
        ModTools.addActionListener(this);
    }    
}
