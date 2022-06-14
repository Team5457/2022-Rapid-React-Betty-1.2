package frc.robot;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.Joystick;

public class Hanger {


Spark hangerMotor;
double rotations = 0;



public Hanger(){
    
    hangerMotor = new Spark(6);
    
    
}

public void hangerMove(Joystick sjoy){
        
    //sjoy is inversed, up is negative

        if(sjoy.getRawAxis(1) < -.2f){
            hangerMotor.set(1);
        }
        else if(sjoy.getRawButton(11)){
            if (sjoy.getRawAxis(1) > .2f){
                hangerMotor.set(-1); 
            }
        }
        else{
            hangerMotor.set(0);
        }
}

        
public double getRotation(){
    return rotations;
}

/*public void reset(){
    AmIndex.reset();
}*/




}
