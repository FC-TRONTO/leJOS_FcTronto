import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;

/*!
 * @file FcTront.java
 * @brief FC-TRONTOメインプログラム
 * 
 */

public class FcTronto {
    // 定数定義
    private static final Port  SENSORPORT_IRSEEKER  = SensorPort.S1;	// 赤外線センサーポート
    private static final Port  SENSORPORT_SONAR     = SensorPort.S2;	// 超音波センサーポート
    private static final Port  MOTORPORT_LEFT 	    = MotorPort.A;	// 左モーターポート
    private static final Port  MOTORPORT_RIGHT 	    = MotorPort.B;	// 右モーターポート

    public static void main(String[] args) {
	// TODO 自動生成されたメソッド・スタブ
	IrSeekerController irSeekerController = new IrSeekerController(SENSORPORT_IRSEEKER);
	LegMotorsController legMotorsController = new LegMotorsController(MOTORPORT_LEFT, MOTORPORT_RIGHT);
	UltrasonicSensorController ultrasonicSensorController = new UltrasonicSensorController(SENSORPORT_SONAR);
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