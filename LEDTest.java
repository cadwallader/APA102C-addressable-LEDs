/**
 * 
 */
package org.usfirst.frc.team2465.robot;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SPI.Port;

/**
 * @author Andrew
 *
 */

public class LEDTest {
	SPI fred;
	int bitrate = 1000000;
	LEDTest(){
		fred = new SPI(Port.kOnboardCS0);
        fred.setClockRate(bitrate);
        fred.setMSBFirst();
        fred.setSampleDataOnFalling();
        fred.setClockActiveLow();
        fred.setChipSelectActiveLow();
	}

    public boolean write() {
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
        
        if ( fred.write(cmd, cmd.length) != cmd.length) {
            return false; // WRITE ERROR
        }
        return true;
    }
}
