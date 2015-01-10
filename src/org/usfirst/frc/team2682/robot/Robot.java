package org.usfirst.frc.team2682.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.DigitalInput;


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
	Relay armMotor;
	DigitalInput fStop;
	DigitalInput bStop;
	Timer revTimer;
	
	boolean sweepForward;
	boolean sweepBackward;
    
    public void robotInit() {
    	
    	joy1 = new Joystick(1);
    	chassis1 = new RobotDrive(1,2);
    	time = new Timer();
    	nCode = new Encoder(1,2);
    	
    	armMotor = new Relay(1);
    	fStop = new DigitalInput(1);
    	bStop = new DigitalInput(2);
    	
    	
    }
    
    /**
     * This function is run once each time the robot enters autonomous mode
     */
    public void autonomousInit() {
    	//start the robot at half speed
    	chassis1.arcadeDrive(0.5,0);
    	
    	
    	/*
    	//delay until encoder is over a value
    	while(nCode.get() < 3000){
    		time.delay(0.01);
    	}
    	
    	//Stop the robot
    	chassis1.arcadeDrive(0,0);
    	*/
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	//This version of the encoder code is more clear.
    	if(nCode.get() > 3000)
    		chassis1.arcadeDrive(0,0);
    		
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
    	
    //If button 6 is pressed and we are not already moving forward then move forward. Also wipe the timer and set backwards value to false to avoid conflictions.
    if(joy1.getRawButton(6) && !sweepForward){
    	sweepForward = true;
    	sweepBackward = false;
    	revTimer.stop();
    	revTimer.reset();
    	
    }
    
    
    //check the boolean values and set the relay accordingly
    if(sweepForward == true){
    	
    	armMotor.set(Relay.Value.kForward);
    	
    } else if (sweepBackward) {
    	
    	armMotor.set(Relay.Value.kForward);
    }
    
    //If the arm is moving forward and the fStop limit is depressed then stop the arm and start the reversal delay timer
    if(sweepForward && fStop.get()){
    	armMotor.set(Relay.Value.kOff);
    	revTimer.start();
    	sweepForward = false;
    }
    //If the reversal delay timer has been running longer then the delay then start the arm in the other direction and wipe the timer
    if (revTimer.get() > 0.25){
    	sweepBackward = true;
    	revTimer.stop();
    	revTimer.reset();
    	
    }
    
    //if the arm is moving backwards and the rStop limit is depressed then stop the motor and set the value to false
    if(sweepBackward && bStop.get()){
    	armMotor.set(Relay.Value.kOff);
    	sweepBackward = false;
    }
    
    }
    
    /**
     * This function is called periodically during test mode
     */
    
    
}
