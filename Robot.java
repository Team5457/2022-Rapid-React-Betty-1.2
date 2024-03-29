// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;



import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;





/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {

  //controllers
  private final Joystick xcon = new Joystick(0);
  private final Joystick sJoy = new Joystick(1);

  //timer
  private final Timer m_timer = new Timer();


  // Drive, Ball, & Hanger systems
  private final BallSystem ballSystem = new BallSystem();
  private final Hanger hang = new Hanger();
  private final Driver drive= new Driver();

  //Camera
  // Do not remove, this initiates the camera and it will show up in smartdashboard
  private final Camera camera = new Camera();


  
  public static double currentAngle;
  public static Boolean angleIsGood;
  public static final int kTimeoutMS = 30;
  

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  

  @Override
  public void robotInit()
  {


    
    
  }
  
  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {
    //Resets and starts the timer, m_timer is the "main timer", there are different 
    //sub timers that are used in other places
    m_timer.reset();
    m_timer.start();
    ballSystem.tReset();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {


    
    if(m_timer.get() >0 && m_timer.get()<2.1)
    {
      drive.stopMotor();
      ballSystem.shoot();
    }
    
    else if(m_timer.get()>2.10 && m_timer.get() < 3)
    {
      drive.DriveMotorSpeeds(-.6, -.6);
    }
    
    //Template Autonomous
    /*
    // Drive for 2 seconds
    if (m_timer.get() < 2.0) {
      driveSystem.arcadeDrive(0.5, 0.0); // drive forwards half speed
    } else {
      driveSystem.stopMotor(); // stop robot
    }

    */
  }

  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {

    //hang.reset();
		

  }

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {

    hang.hangerMove(sJoy);
    

    drive.driveRobot(xcon);
    ballSystem.deploy(xcon, drive);


  }

  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
