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
	/*
	MainProcessController mainProcess = new MainProcessController();
	while(true) {
	    mainProcess.execMainProcess();
	}
	*/
	byte [] buffer = new byte[1024];
	USBController usbController = new USBController(SensorPort.S3);
	EV3 ev3 = (EV3) BrickFinder.getLocal();
	TextLCD lcd = ev3.getTextLCD();
	//int len = usbController.read(buffer);
	int count = 0;
	String text = String.valueOf(count) + "," + String.valueOf(count*2);
	while(true) {
	    usbController.write(text);
	    lcd.drawString("text = " + text, 1, 0);
	//lcd.drawString("len = " + len, 1, 0);
	//lcd.drawString("buff[0] = " + buffer[0], 1, 1);
	    count++;
	    Delay.msDelay(2000);
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