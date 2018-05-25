
package client_project;




public class Application 
{
    ApplicationStateManager event;    
    // CONSTRUCTORS
    public Application(){}  
    
    // No size specified
    public Application(String title)          
    {
        event = new ApplicationStateManager();        
        int InitLayout = 1;                   // Starting Layout    
        int size = 800;                       // Set size
        event.Init(title, size, ApplicationStateManager.LayerSet.LOGINSCREEN);  // Sets Initial Values
    }
    
    // FULLSCREEN application
    public Application(String title, int width , int height, int InitLayout ) 
    {
        event = new ApplicationStateManager();        
                  // Starting Layout    
        event.Init(title,  width, height, ApplicationStateManager.LayerSet.LOGINSCREEN);  // Sets Initial Values
    }
     
    // Non-Fullscreen
    public Application(String title, int size, int InitLayout)// Basic details and Generation here
    {
        event = new ApplicationStateManager();        
        event.Init(title, size, ApplicationStateManager.LayerSet.LOGINSCREEN);  // Sets Initial Values
    }
        
    //FUNCTIONS
    public void run()                   // This is the location of the main Loop
    {
        boolean running = true;         // Is Running = True
                      
        while (running)                 // Application loop
        {
            event.update();
        }                           
    }
    
    // What the program needs
    // User details - Server sends, on connect, with details entered from log in 
    //        screen IF log in details are validated or when they are 
    //        (in the case of there being no blacklist)
    // 
    
    
    public void PreAppConfig()
    {
        
        
        
        
    }
}
