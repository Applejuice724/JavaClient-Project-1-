/*
    This checks to see if the input Is invalid
    Currently Checks these values.
    * IP
        - If it contains only numbers and three fields divided by "." 
            containing a max of 3 numbers


 */
package client_project.UserInformation;

public class UserInputChecker {    
    public UserInputChecker() {}   
    
    public boolean CheckNetworkInformation(String InputIP, String InputPort)
    {
        String ip_l = InputIP;
        String port_l = InputPort;                        
        
        boolean InvalidInput;

        if (ip_l != null && port_l != null)
        {                                   
            InvalidInput = checkIfContainsLetter(ip_l); // Checks if there's any Letters    
            if (InvalidInput) return false;
            InvalidInput = CheckCorrectIPSyntax(ip_l);  // Checks Syntax

            if (!InvalidInput) return true;               
        }        
        return false;
    }
    
    public boolean checkIfContainsLetter(String InputString)
    {        
            for (int i = 0; i <= InputString.length()-1 ; i++)
            { 
                String temp = InputString.substring(i, i+1);                        
                
                if (!temp.matches("[0-9]+") && !temp.matches(".")) 
                {                            
                    return true;                
                }
            }        
                            
            return false;    
    }
    
    public boolean CheckCorrectIPSyntax(String InputIpString)
    {

        int Ipaddr[] = new int[4];
        int FieldBreakPosition[] = new int[4];
        int FieldCount = 0;        
        int count = 0;
        String Fields[] = new String[4];                 
        String CurrentField = "";
                
        
        for (int i = 0; i <= InputIpString.length()-1 ; i++) // Breaks IP into Fields
            {             
                String temp = InputIpString.substring(i, i+1);                                        
                switch (temp)
                {                
                    case ".":
                        FieldCount++;
                        if (FieldCount >=4) {
                            return false;                                        
                        }
                        FieldBreakPosition[count] = i;                           
                        //System.out.println("!! Current Field " + CurrentField);
                        Fields[count] = CurrentField;
                        CurrentField = "";
                        count += 1;   
                        //System.out.println("!! Current Field Count= " + count); 
                        break;    
                        
                    default:
                        CurrentField = CurrentField + temp;
                        break;
                }
            }         
         Fields[count] = CurrentField;

        // System.out.println("!! Current Field " + CurrentField); 

         count = 0;
         for (int i = 0; i <= 3 ; i++) // Truns those Fields into Ints
         {
             String Temp = Fields[i];
             Ipaddr[count] = Integer.parseInt(Temp);                      
             //System.out.println("!! Current Field int " + Ipaddr[count]); 
             if (Ipaddr[count] > 999) 
             {
             }
         }            
                                
        if (FieldBreakPosition[0] == 0 || FieldBreakPosition[1] == 0 || FieldBreakPosition[2] == 0 || FieldBreakPosition[3] != 0) 
        {                                
            return false;
        }
        return true;
    }                
}
