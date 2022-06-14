package frc.robot;


//change and import different motor controller classes if different motor controller used. 

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

/* ball system
    * Controls the intake and shooter
    * Two modes, autonomous and controller
    * initiate ballsystem in public class Robot
    * use method deploy in teleopPeriodic() to use through controller
*/
public class BallSystem {

    public PWMSparkMax pickup;
    public PWMSparkMax shoot;
    
    private final Timer timer = new Timer();

    // initiates SparkMax motor controllers to PWM pins 4 & 5 
    //(make sure 4 & 5 are not used in code somewhere else)
    public BallSystem(){
    
        shoot = new PWMSparkMax(4);
        pickup = new PWMSparkMax(5);

    }

    //deploys controller so you can shoot and pick up in tele op
    public void deploy(Joystick xcon, Driver drive){
        pickUp(xcon);
        shoot(xcon, drive);
    }

    //Operates the pickup using the buttons that correspond to 1 & 3 on controller
    //I think they are A & Y, but make sure on your own
    // This code used xbox controller. 
    public void pickUp(Joystick xcon){
        // pick up ball
        // for manual shooting
        if(xcon.getRawButton(1)){
            pickup.set(.7);
        }
        //lower ball
        else if (xcon.getRawButton(3)){
            pickup.set(-1);
        }
        //speed is zero when no buttons are being pressed. 
        else{
            pickup.set(0);
        }
        
    }

    //Operate the shooter. 
    public void shoot(Joystick xcon, Driver drive){


        //Button B I think
        //Subroutine to shoot ball, operates for 2 seconds. 
        //Lowers ball, lets top motor rev up, then shoot ball back up. 
        if(xcon.getRawButton(2)){
            timer.start();
            

            if (timer.get() > 1.5){
                pickup.set(0.7);
            }
            else if (timer.get() > .2){
                pickup.set(0);
                shoot.set(.75);
            }
            else if (timer.get() > 0){
                pickup.set(-.3);
            }

        }
        // Button X i think
        //just spins the top motor at 100% power.
        //For manual shooting 
        else if (xcon.getRawButton(4)){
            shoot.set(1);

        }

        else{
            shoot.set(0);
            timer.reset();
        }

        
        
    }

    //autonoums shoot
    public void shoot(){


        timer.start();
            
        if ( timer.get() > 2) {
            shoot.set(0);
            pickup.set(0);
        }
        else if (timer.get() > 1.5){
            pickup.set(0.7);
        }
        else if (timer.get() > .2){
            pickup.set(0);
            shoot.set(.75);
        }
        else if (timer.get() > 0){
            pickup.set(-.35);
        }
        
    }
    public void tReset()
    {
        timer.reset();
    }
    
}
