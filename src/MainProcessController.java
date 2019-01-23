import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class MainProcessController {
    // 定数定義
    private static final Port SENSORPORT_IRSEEKER = SensorPort.S1; // 赤外線センサーポート
    private static final Port SENSORPORT_SONAR = SensorPort.S2; // 超音波センサーポート
    private static final Port MOTORPORT_LEFT = MotorPort.A; // 左モーターポート
    private static final Port MOTORPORT_RIGHT = MotorPort.B; // 右モーターポート
    private static final Port MOTORPORT_DRIBBLE = MotorPort.D; // ドリブルモーターポート
    private static final float THRESHOLD_BALL_DETECT = 0.05f;
    private static final int CORRECTION_VALUE_SPEED = 10;

    private IrSeekerController irSeekerController;
    private LegMotorsController legMotorsController;
    private UltrasonicSensorController ultrasonicSensorController;
    private EV3 ev3;
    private TextLCD lcd;
    RegulatedMotor dribbleMotor;

    private enum MainStateE {
	HAVE_BALL, NOT_HAVE_BALL;
    }

    public MainProcessController() {
	irSeekerController = new IrSeekerController(SENSORPORT_IRSEEKER);
	legMotorsController = new LegMotorsController(MOTORPORT_LEFT, MOTORPORT_RIGHT);
	ultrasonicSensorController = new UltrasonicSensorController(SENSORPORT_SONAR);
	ev3 = (EV3) BrickFinder.getLocal();
	lcd = ev3.getTextLCD();
	dribbleMotor = new EV3LargeRegulatedMotor(MOTORPORT_DRIBBLE);
	// スピード設定
	dribbleMotor.setSpeed((int) dribbleMotor.getMaxSpeed());
	// 前進
	dribbleMotor.forward();
    }

    public void execMainProcess() {
	handleMainState(getMainState());
    }

    /*!
     * 現在のメイン状態を取得する
     * @return 現在のメイン状態
     */
    private MainStateE getMainState() {
	MainStateE nowState;
	// 距離センサの値が閾値より小さかったらボール保持状態と判断する
	if (ultrasonicSensorController.getSonarDistance() < THRESHOLD_BALL_DETECT) {
	    nowState = MainStateE.HAVE_BALL;
	} else {
	    nowState = MainStateE.NOT_HAVE_BALL;
	}
	lcd.drawString("STATE = " + nowState, 1, 0);
	return nowState;
    }

    /*!
     * 現在のメイン状態に応じた処理を実行する
     * @return 現在のメイン状態
     */
    private void handleMainState(MainStateE mainState) {
	switch(mainState) {
	case HAVE_BALL:
	    break;
	case NOT_HAVE_BALL:
	    while(irSeekerController.getIrSeekerAngle() != 0) {
		int speedPacentage = 0;
		float angle = irSeekerController.getIrSeekerAngle();
        	lcd.drawString("angle = " + angle, 1, 1);
        	if(angle > 100) {
        	    speedPacentage = 100;
        	}else if(angle < -100) {
        	    speedPacentage = -100;
        	}else if(Float.isNaN(angle)){
        	    speedPacentage = 100;
        	}else {
        	    speedPacentage = (int)angle;
        	}
        	speedPacentage /= CORRECTION_VALUE_SPEED;
        	lcd.drawString("speed = " + speedPacentage, 1, 2);
        	legMotorsController.turn(speedPacentage);
	    }
	    legMotorsController.goStraight(10);
	    Delay.msDelay(1000);
	    break;
	default:
	    break;
	}
    }
}
