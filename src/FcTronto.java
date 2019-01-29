import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.port.SensorPort;
import lejos.utility.Delay;

/*!
 * @file FcTront.java
 * @brief FC-TRONTOメインプログラム
 * 
 */

public class FcTronto {

    public static void main(String[] args) {
	// TODO 自動生成されたメソッド・スタブ
	MainProcessController mainProcess = new MainProcessController();
	while(true) {
	    mainProcess.execMainProcess();
	}
    }

}

/*
███████╗ ██████╗    ████████╗██████╗  ██████╗ ███╗   ██╗████████╗ ██████╗ 
██╔════╝██╔════╝    ╚══██╔══╝██╔══██╗██╔═══██╗████╗  ██║╚══██╔══╝██╔═══██╗
█████╗  ██║            ██║   ██████╔╝██║   ██║██╔██╗ ██║   ██║   ██║   ██║
██╔══╝  ██║            ██║   ██╔══██╗██║   ██║██║╚██╗██║   ██║   ██║   ██║
██║     ╚██████╗       ██║   ██║  ██║╚██████╔╝██║ ╚████║   ██║   ╚██████╔╝
╚═╝      ╚═════╝       ╚═╝   ╚═╝  ╚═╝ ╚═════╝ ╚═╝  ╚═══╝   ╚═╝    ╚═════╝ 
*/