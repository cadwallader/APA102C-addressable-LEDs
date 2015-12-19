/**
 * Defines autonomous and operational control of the Kaua'iBots LEDRobot using the following components:
 * Addressable RGB 120-LED Strip, 5V, 2m (APA102C) https://www.pololu.com/product/2556
 * National Instruments' RoboRio
 */

package org.usfirst.frc.team2465.robot;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends SampleRobot {
	Joystick stick;
	LEDStrip strip;
	
    public Robot() {
        strip = new LEDStrip();
    	stick = new Joystick(0);
    }

    /**
     * null
     */
    public void autonomous() {
    }

    /**
     * Blinks a light autonomously
     */
    public void operatorControl() {
       while (isOperatorControl() && isEnabled()) {
            strip.startFrame();
            for(int i=1 ; i<=120 ; i++){
                strip.randomFrame();
            }
            Timer.delay(.1);
        }
    }

    /**
     * Runs during test mode
     */
    public void test() {
    	while (isEnabled()) {
            strip.test();
            Timer.delay(.1);}
    }
}
