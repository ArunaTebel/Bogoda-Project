/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Pramo
 */
public class Main_versioning {
    
    
    
    public static int SoftwareVersion(){
    int version = 0;
    
    // this class is for costomize the software for different Factories
    //specify the return value accordingly 
    
   // version = 1;    //Bogoda Group Tea Factory
    //version = 2;    //Arbour Valley Tea Factory  
    
    
    return 2;
    
    } 
    
}
/*
// here are the positions where differences occur for each version
   2. Arbour Valley Tea Factory
        1.GL_report_generator ---> monthly ledger cal
              *. Welfare calculated differently
              *. method welfare_type_2 is called
        2.pGreenleaf ------> below init_components---- 'Transport' button is enabled
        3.Reports_GL ------> below init_components----'Transport Report' button enabled
        4.Task_manager-----> method set_task_man----GL5,GL5lable, prebebprog1 enabled
        5.Main_Window------> below init_components icon, backgrounds, title, welcome screen picture 
*/