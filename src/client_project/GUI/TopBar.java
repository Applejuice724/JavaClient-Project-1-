/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_project.GUI;

import client_project.Client_project;
import client_project.ApplicationStateManager;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



public final class TopBar extends ApplicationStateManager implements ActionListener  {
    // Private Variables               
    private javax.swing.JButton LogOutBttn;
    public  javax.swing.JButton ExitButton;        
    private javax.swing.JButton ResetButton;
    
    public TopBar()
    {
        InitComponents();        
    }
        
    @SuppressWarnings("unchecked")
    public void InitComponents() 
    {       

       ExitButton = new javax.swing.JButton("Exit");                           
       LogOutBttn = new javax.swing.JButton("Log Out");   
       ResetButton = new javax.swing.JButton("Reset");
       ExitButton.addActionListener(ApplicationStateManager.actionPerformed);
       
       javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
       this.setLayout(layout);
       
       layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)     
                    .addGroup(layout.createSequentialGroup() 
                            .addComponent(LogOutBttn, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                            .addGap(1)
                            .addComponent(ResetButton, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)                                
                            .addGap(1)                             
                            .addComponent(ExitButton, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LogOutBttn, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)            
                    .addComponent(ExitButton, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)            
                    .addComponent(ResetButton, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)                
        );
        
        ExitButton.addActionListener(this);
        ResetButton.addActionListener(this); 
        LogOutBttn.addActionListener(this);
        ResetButton.setPreferredSize(new Dimension(100, 100)); 

        ExitButton.setPreferredSize(new Dimension(100, 100));                
        LogOutBttn.setPreferredSize(new Dimension(100, 100));
        ResetButton.setPreferredSize(new Dimension(100, 100)); 
    }  
    public void setLogOutButtonVisible(boolean inputBoolean)
    {
        LogOutBttn.setVisible(inputBoolean);
    }

    @Override
    public void actionPerformed(ActionEvent e)  {
         Object source = e.getSource();
         if (source == ExitButton)       
         {                                 
           System.out.println("Exiting....");
           System.gc();
           System.exit(0);                  
         }         
         if (source == LogOutBttn)       
         {                                               
             System.out.println("TOPBAR SELECTED");
             super.setLayerSelect(LayerSet.LOGINSCREEN);
         }        
         if (source == ResetButton)      // Resets application
            {
               try
                {
                     final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";                
                     final File currentJar = new File(Client_project.class.getProtectionDomain().getCodeSource().getLocation().toURI());

                     /* is it a jar file? */
                     if(!currentJar.getName().endsWith(".jar"))
                       return;

                     /* Build command: java -jar application.jar */
                     final ArrayList<String> command = new ArrayList<String>();
                     command.add(javaBin);
                     command.add("-jar");
                     command.add(currentJar.getPath());

                     final ProcessBuilder builder = new ProcessBuilder(command);
                     builder.start();
                     System.exit(0);
                }           
                catch(IOException exception){
                 System.exit(0);
                } catch (URISyntaxException ex) {            
                   Logger.getLogger(ApplicationStateManager.class.getName()).log(Level.SEVERE, null, ex);
               }            
            } 
    }      
}
