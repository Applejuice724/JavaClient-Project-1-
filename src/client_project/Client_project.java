//                              Author: Jeremy Grassi
//  License: Open Source
//  Purpose of the main class: 

//  Initiate Application AFTER doing Pre-Application Handling like checking 
//  Applicaiton instance or other features that can be added in the future. 
//  Anything to set up before main application happens here

package client_project;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;

public class Client_project {            
    static int InitLayout = 1;

    
    public static void main(String[] args) {

        SetUpPreferences();
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();        
        String Title = "My Applicaiton";
        Application App = new Application(Title, width, height, InitLayout); // New application 
        App.run();
    }
    
    private static void SetUpPreferences()
    {
        ArrayList list = new ArrayList();
        
        
    }
    
}
