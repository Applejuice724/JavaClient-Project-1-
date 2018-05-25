/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_project.GUI.MainMenu;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class UpdateProfile extends ProfileInterface implements ActionListener
{            
    // Asthetic Utilities
    private javax.swing.BorderFactory Borderfactory ;
    private javax.swing.border.Border Border;        
    private  java.awt.Color color;
    // WIDGETS
    private javax.swing.JButton UpdateButton; 
    private  javax.swing.JTextField FirstName;        
    private javax.swing.JTextField LastName;        
    private javax.swing.JTextField Staff;
    
    

    
    public UpdateProfile()
    {
   
        InitComponents();        
    }
        
    @SuppressWarnings("unchecked")
    public final void InitComponents()
    {
               
        Border = Borderfactory.createLineBorder(color.BLACK, 1);
        this.setBorder(Border);
        
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    
        
    }
    
    
}
