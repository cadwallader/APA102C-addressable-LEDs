package org.usfirst.frc.team2465.robot;
import edu.wpi.first.wpilibj.SPI;
import java.util.Random;
import edu.wpi.first.wpilibj.SPI.Port;
import java.util.Arrays;

/**
 * @author Andrew Cadwallader for FRC Team 2465 Kaua'iBots
 *
 */

public class LEDStrip {
	SPI spi;
	int bitrate = 4000000;
	int striplength = 120;

	LEDStrip(){
		spi = new SPI(Port.kOnboardCS0);
        spi.setClockRate(bitrate);
        spi.setMSBFirst();
        spi.setSampleDataOnFalling();
        spi.setClockActiveLow();
        spi.setChipSelectActiveLow();
	}

    public boolean startFrame(){
        byte[] header = new byte[4];
        Arrays.fill(header, (byte)0x00);
        return true;
    }

    public boolean emptyFrame(){
        byte[] cmd = new byte[4];
        Arrays.fill(cmd, (byte)0x00);//Fill Start Frame and Pololu End Frame
        cmd[0] = (byte)0xE0;
        return true;
    }

    public boolean randomFrame(){
        byte[] cmd = new byte[4];//Create Array of bytes
        Arrays.fill(cmd, (byte)0x00);//Fill Start Frame and Pololu End Frame
        byte head = (byte)0xE0;//Set head to 11100000
        Random random = new Random();
        lum = (byte)0x01;
        cmd[0] = (byte)(lum | head);//Header plus Brightness
        rgb = (byte)random.nextInt(256);
        cmd[1]=rgb;//Blue
        rgb = (byte)random.nextInt(256);
        cmd[2]=rgb;//Green
        rgb = (byte)random.nextInt(256);
        cmd[3]=rgb;//Red
        return true;
    }

    public boolean write() {
    	byte[] cmd = new byte[cmdsize];//Create Array of bytes
    	Arrays.fill(cmd, (byte)0x00);//Fill Start Frame and Pololu End Frame
    	byte head = (byte)0xE0;//Set head to 11100000    	
	    byte lum = (byte)0x00;//Set brightness
	    byte rgb = (byte)0x00;//Set color
	    for(int i=4 ; i<(striplength+1)*4 ; i+=4){cmd[i] = head;}//Attach header to command frames
	    
    	Random random = new Random();
    	//int dim = 10;//Probability of an LED to light up
    	//int draw = random.nextInt(striplength);
    	//draw = draw/dim;
    	int draw = 10;//Uncomment to set fixed number of lights on

    	//To randomly select which LEDs to turn on
	    //Fill an array of length "draw" with random integers from 1 through "striplength" (inclusive)
    	int[] array = new int[draw];
    	for(int item : array){
    		array[item] = random.nextInt(striplength)+1;
    	}

    	//To turn on each LED selected above, set random Brightness and Color
    	for(int index=4 ; index<(striplength+1)*4 ; index+=4){		
    		//int index = (i)*4;
    		//lum = (byte)random.nextInt(256);
    		lum = (byte)0x01;
    		cmd[index] = (byte)(lum | head);//Header plus Brightness
        	rgb = (byte)random.nextInt(256);
        	cmd[index+1]=rgb;//Blue
        	rgb = (byte)random.nextInt(256);
        	cmd[index+2]=rgb;//Green
        	rgb = (byte)random.nextInt(256);
        	cmd[index+3]=rgb;//Red
    	}
        
        if (spi.write(cmd, cmd.length) != cmd.length) {
            return false; // WRITE ERROR
        }
        return true;
    }
    
    //Tests first 3 lights in strip using previously successful code
    /*
    public boolean test() {
        byte[] cmd = new byte[17];
        cmd[0] = cmd[1] = cmd[2] = cmd[3] = (byte)0x00;
        cmd[4] = cmd[5] = (byte)0xF0;
        cmd[6] = cmd[7] = (byte)0x00;
        cmd[8] = (byte)0xFF;
        cmd[9] = (byte)0x00;
        cmd[10] = (byte)0x00;
        cmd[11] = (byte)0xFF;
        cmd[12] = (byte)0xFF;
        cmd[13] = (byte)0x00;
        cmd[14] = (byte)0xFF;
        cmd[15] = (byte)0x00;
        cmd[16] = (byte)0x00;
        
        if (spi.write(cmd, cmd.length) != cmd.length) {
            return false; // WRITE ERROR
        }
        return true;
    }
    */
    public boolean test() {
    	byte[] cmd = new byte[cmdsize];//Create Array of bytes
    	Arrays.fill(cmd, (byte)0x00);//Fill Start Frame and Pololu End Frame
    	byte head = (byte)0xE0;//Set head to 11100000    	
	    for(int i=4 ; i<cmdsize ; i+=4){cmd[i] = head;}//Attach header to command frames

        
        if (spi.write(cmd, cmd.length) != cmd.length) {
            return false; // WRITE ERROR
        }
        return true;
    }
}
