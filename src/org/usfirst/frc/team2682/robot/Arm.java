package org.usfirst.frc.team2682.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;


public class Arm extends Object{
//Represents a limited rotation arm run with a relay and two limit switches.
	DigitalInput fLimit;
	DigitalInput rLimit;
	Relay op;
	
	public Arm(DigitalInput forward, DigitalInput backward, Relay operator) {
		fLimit = forward;
		rLimit = backward;
		op = operator;
		
	}
	
	public void update(boolean forward, boolean back){
		
		
		if(forward && !fLimit.get()){
			
			op.set(Relay.Value.kForward);
			
		} else if (back && !rLimit.get()){
			
			
			
			
		} else {
			
			
			
		}
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
}
