package org.usfirst.frc.team2682.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Encoder;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	Joystick joy1;
	RobotDrive chassis1;
	Timer time;
	Encoder nCode;
	
    
    public void robotInit() {
    	
    	joy1 = new Joystick(1);
    	chassis1 = new RobotDrive(1,2);
    	time = new Timer();
    	nCode = new Encoder(1,2);
    	
    	
    }
    
    /**
     * This function is run once each time the robot enters autonomous mode
     */
    public void autonomousInit() {
    	chassis1.arcadeDrive(0.5,0);
    	
    	while(nCode.get() < 700){
    		time.delay(0.01);
    	}
    	
    	chassis1.arcadeDrive(0,0);
    	
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	
    	//if(nCode.get() > 3000)
    	//chassis1.arcadeDrive(0.5,0);
    }
    
    /**
     * This function is called once each time the robot enters tele-operated mode
     */
    public void teleopInit(){
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    
    chassis1.arcadeDrive(joy1.getRawAxis(1),joy1.getRawAxis(2));	
    	
    	
    }
    
    /**
     * This function is called periodically during test mode
     */
    
    
}
