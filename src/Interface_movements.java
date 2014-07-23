/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pramo
 */
public class Interface_movements {
    
    public void smooth_move(javax.swing.JComponent pic, int Dir, double X, double Y, int speed){
       
     Thread a = new Thread(new move(pic,Dir,X,Y,speed));
        a.start();
    
    }
    
   
    
    
    public class move implements Runnable{
        javax.swing.JComponent PIC;
        int DIR, SPEED;
        double XX,YY;
        public move(javax.swing.JComponent pic, int Dir, double X, double Y, int speed){
        PIC = pic;
        DIR = Dir;
        XX= X;
        YY= Y;
        SPEED = speed;
        
        
        }
    
    public void run(){
    double i =0;
    double total = 0;
    
    while(i<Math.PI/2){
       // System.out.println( Math.round (2*Math.sin(i)));
       // total = total+2*Math.sin(i);
        int jx = (int) Math.round (XX*Math.sin(i));
        int jy = (int) Math.round (YY*Math.sin(i));
        
        int  x = PIC.getLocation().x;
        int  y = PIC.getLocation().y;
        if(DIR== 0){
        PIC.setLocation(x, y-jy);}
        if(DIR== 1){
        PIC.setLocation(x+jx, y-jy);}
        if(DIR== 2){
        PIC.setLocation(x+jx, y);}
        if(DIR== 3){
        PIC.setLocation(x+jx, y+jy);}
        if(DIR== 4){
        PIC.setLocation(x, y+jy);}
        if(DIR== 5){
        PIC.setLocation(x-jx, y+jy);}
        if(DIR== 6){
        PIC.setLocation(x-jx, y);}
        if(DIR== 7){
        PIC.setLocation(x-jx, y-jy);}
        
        
        
    i=i+0.005;
     try {
                    Thread.sleep(SPEED);
                } catch (InterruptedException ex) {
                    //Logger.getLogger.class.getName()).log(Level.SEVERE, null, ex);
                }
    
    
    }
    
     //   System.out.println(total);
    
    
    }
    }
}
    

