/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
Resources:
        https://stackoverflow.com/questions/32529/how-do-i-restrict-jfilechooser-to-a-directory
 */


package client_project.GUI.MainMenu;

import client_project.ApplicationStateManager;
import client_project.UserInformation.FileManager.File_Manager;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.filechooser.FileSystemView;

public class DirectoryInterface extends ApplicationStateManager implements ActionListener{   
    private static File_Manager manage = new File_Manager();
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.filechooser.FileSystemView fileSystem;
    private java.io.File clientHomeDir;
    private java.io.File HomeDirectory;
    private String clientFolder = "ClientFile";
    private javax.swing.JButton Upload;
    private javax.swing.JPanel mainPanel = new javax.swing.JPanel();
    private javax.swing.JPanel DirectoryVeiw = new javax.swing.JPanel();
    private javax.swing.SpringLayout mainLayout = new javax.swing.SpringLayout();  
    
    public DirectoryInterface(String inputUser) 
    {        
        initComponents(inputUser);
    }                        
    public void initComponents(String user) { 
        if (!manage.DoesExist(clientFolder)) manage.createFolder(clientFolder);                
        clientFolder = clientFolder+"\\"+user;
        if (!manage.DoesExist(clientFolder)) manage.createFolder(clientFolder);        
        fileSystem = new DirectoryRestrictedFileSystemView(new File(clientFolder)); 
        jFileChooser1 = new javax.swing.JFileChooser(fileSystem);
        jFileChooser1.addActionListener(new java.awt.event.ActionListener() {            
            public void actionPerformed(java.awt.event.ActionEvent evt) { 
                Object source = evt.getSource();
                if (source == Upload)
                {
                    System.out.println("nice");
                }
                jFileChooser1ActionPerformed(evt); 
            }
        }); 
        jFileChooser1.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (JFileChooser.DIRECTORY_CHANGED_PROPERTY.equals(
                        evt.getPropertyName())) {
                    jFileChooser1.approveSelection();                    
                }                                 
            }
        });                                                                
        AddComponents();
        this.setVisible(true);
    } 
    public void setCurrentUserDirectory(String inputUser)
    {
        clientHomeDir = HomeDirectory;
        clientFolder = clientFolder+"\\"+inputUser;
        clientHomeDir = new File(clientFolder);
        if (!manage.DoesExist(clientFolder)) manage.createFolder(clientFolder);     
        jFileChooser1.setCurrentDirectory(clientHomeDir); 
        disableButton(jFileChooser1, "FileChooser.upFolderIcon");                    
        disableButton(jFileChooser1, "FileChooser.homeFolderIcon");  
        fileSystem = new DirectoryRestrictedFileSystemView(new File(clientFolder));
        jFileChooser1 = new javax.swing.JFileChooser(fileSystem);        
    }                
    private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent e) {                 
        if (jFileChooser1.getCurrentDirectory().equals(clientHomeDir) || (jFileChooser1.getCurrentDirectory().equals(new File(clientFolder)))){        
            disableButton(jFileChooser1, "FileChooser.upFolderIcon");            
            disableButton(jFileChooser1, "FileChooser.homeFolderIcon");  
            jFileChooser1.setApproveButtonText("Upload");
        }                   
        else
        {
            enableButton(jFileChooser1, "FileChooser.upFolderIcon"); 
        }            
        System.out.println("dir change "+jFileChooser1.getCurrentDirectory());
    }   
    private static void disableButton(final Container c, final String iconString) {
        int len = c.getComponentCount();
        for (int i = 0; i < len; i++) {
            Component comp = c.getComponent(i);
            if (comp instanceof JButton) {
                JButton b = (JButton) comp;
                Icon icon = b.getIcon();
                if (icon != null
                        && icon == UIManager.getIcon(iconString)) {
                    b.setEnabled(false);
                }
            } else if (comp instanceof Container) {
                disableButton((Container) comp, iconString);
            }
        }
    }    
    private static void enableButton(final Container c, final String iconString) {
        int len = c.getComponentCount();
        for (int i = 0; i < len; i++) {
            Component comp = c.getComponent(i);
            if (comp instanceof JButton) {
                JButton b = (JButton) comp;
                Icon icon = b.getIcon();
                if (icon != null
                        && icon == UIManager.getIcon(iconString)) {
                    b.setEnabled(true);
                }
            } else if (comp instanceof Container) {
                enableButton((Container) comp, iconString);
            }
        }
    }
    private void AddComponents()
    {                          
        Upload = new javax.swing.JButton("Upload");
        jFileChooser1.setCurrentDirectory(new File(clientFolder));
        jFileChooser1.setApproveButtonText("Upload");
        jFileChooser1.setControlButtonsAreShown(false);
        jFileChooser1.setPreferredSize(new Dimension(500,450));        
        System.out.println("Server Files located :"+clientHomeDir);                
        mainPanel.setPreferredSize(new Dimension(500,500));
        DirectoryVeiw.setPreferredSize(new Dimension(500,500));        
        Upload.setPreferredSize(new Dimension(100,100));               
        Upload.addActionListener(this);
        DirectoryVeiw.add(jFileChooser1);
//        setBackground(Color.BLACK);
        setComponentPosition();
        clientHomeDir = jFileChooser1.getCurrentDirectory();
        HomeDirectory = clientHomeDir;
        disableButton(jFileChooser1, "FileChooser.upFolderIcon");
        disableButton(jFileChooser1, "FileChooser.homeFolderIcon");
    }
    private void setComponentPosition()
    {       
        mainPanel.setBackground(Color.red);
        mainLayout.putConstraint           // Main Background 
                    (javax.swing.SpringLayout.WEST, mainPanel, 
                            0 ,javax.swing.SpringLayout.WEST, this);
               
        mainLayout.putConstraint           // Main Background       
                    (javax.swing.SpringLayout.NORTH, mainPanel, 
                           -10 ,javax.swing.SpringLayout.NORTH, this);                
        mainLayout.putConstraint           // Button Upload 
                    (javax.swing.SpringLayout.WEST, Upload, 
                            0 ,javax.swing.SpringLayout.EAST, mainPanel);
               
        mainLayout.putConstraint           // Button Upload       
                    (javax.swing.SpringLayout.NORTH, Upload, 
                           23 ,javax.swing.SpringLayout.NORTH, mainPanel);        
        this.setLayout(mainLayout);                               
        this.add(mainPanel);                                
        this.add(Upload);
        mainPanel.add(DirectoryVeiw);  
        Upload.setVisible(true);
        jFileChooser1.setVisible(true);
        mainPanel.setVisible(true);
        this.setVisible(true);        
    }

    @Override
    public void actionPerformed(ActionEvent e) {                      
        Object source = e.getSource();
        if (source == Upload)        
        {
            try
            {        
                System.out.println("Button pressed!");
                File Sendfile = jFileChooser1.getSelectedFile();
                super.sendFile(Sendfile, Sendfile.getName());        
            }catch(Exception ett){}
        }
    }
            
    private class DirectoryRestrictedFileSystemView extends FileSystemView
    {
        private final File[] rootDirectories;
        DirectoryRestrictedFileSystemView(File rootDirectory)
        {
            this.rootDirectories = new File[] {rootDirectory};
        }
        DirectoryRestrictedFileSystemView(File[] rootDirectories)
        {
            this.rootDirectories = rootDirectories;
        }
        @Override
        public File createNewFolder(File containingDir) throws IOException
        {       
            throw new UnsupportedOperationException("Unable to create directory");
        }
        @Override
        public File[] getRoots()
        {
            return rootDirectories;
        }
        @Override
        public boolean isRoot(File file)
        {
            for (File root : rootDirectories) {
                if (root.equals(file)) {
                    return true;
                }
            }
            return false;
        }
    }
}
