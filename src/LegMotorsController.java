/*!
 * @file LegMotorsController.java
 * @brief 足回りモーター制御プログラム
 *
 * 参考ページ：https://qiita.com/murotani/items/2a98164f9b3819f8ee64
 * 
 */
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.robotics.RegulatedMotor;

/*!
 * @class LegMotorsController
 * @brief 足回りモーターの制御を行うクラス
 */
public class LegMotorsController {
    private RegulatedMotor leftMotor;
    private RegulatedMotor rightMotor;
    
    /*!
     * コンストラクタ
     */
    public LegMotorsController(Port leftMorotPort, Port rightMorotPort) {
	leftMotor = new EV3LargeRegulatedMotor(leftMorotPort);
	rightMotor = new EV3LargeRegulatedMotor(rightMorotPort);
    }
    
    /*!
     * @fn public final boolean goStraight(int power)
     * @brief 直進処理
     * @param[in] speedPercentage 直進速度比率(正の値の時前進、負の値の時後退)
     * @return boolean 引数異常の有無
     */
    public final boolean goStraight(int speedPercentage) {
	// 引数チェック
	if(speedPercentage > 100 || speedPercentage < -100) {
	    // 引数不正
	    return false;
	}
	
	// 速度比率から速度を計算し、モーターに設定する
	setLegMotorsAbsSpeedByParcentage(speedPercentage, leftMotor, rightMotor);
	if(speedPercentage > 0) {
	    // 前進
	    leftMotor.forward();
	    rightMotor.forward();
	}else {
	    // 後退
	    leftMotor.backward();
	    rightMotor.backward();
	}
	return true;
    }
    
    /*!
     * @fn public final boolean turn(int power)
     * @brief 旋回処理
     * @param[in] speedRatio 旋回速度比率(正の値の時左旋回、負の値の時右旋回)
     * @return boolean 引数異常の有無
     */
    public final boolean turn(int speedPercentage) {
	// 引数チェック
	if(speedPercentage > 100 || speedPercentage < -100) {
	    // 引数不正
	    return false;
	}
	
	// 速度比率から速度を計算し、モーターに設定する
	setLegMotorsAbsSpeedByParcentage(speedPercentage, leftMotor, rightMotor);
	if(speedPercentage > 0) {
	    // 左旋回
	    leftMotor.backward();
	    rightMotor.forward();
	}else {
	    // 右旋回
	    leftMotor.forward();
	    rightMotor.backward();
	}
	return true;
    }
    
    /*!
     * @fn public final boolean setMotorPower(int leftPower, int rightPower)
     * @brief モータ回転速度設定
     * @param[in] leftPower 左モータ速度[-100,100]
     * @param[in] rightPower 右モータ速度[-100,100]
     * @return boolean 引数異常の有無
     */
    public final boolean setMotorPower(int leftPower, int rightPower) {
	// 引数チェック
	if((leftPower > 100 || leftPower < -100) || (rightPower > 100 || rightPower < -100)) {
	    // 引数不正
	    return false;
	}
	
	// 引数から速度を計算し、モーターに設定する
	setEachLegMotorsAbsSpeedByParcentage(leftPower, rightPower, leftMotor, rightMotor);
	if(leftPower > 0) {
	    leftMotor.forward();
	}else {
	    leftMotor.backward();
	}
	if(rightPower > 0) {
	    rightMotor.forward();
	}else {
	    rightMotor.backward();
	}
	return true;
    }
    
    private final float getAbsSpeedByParcentage(int speedPercentage) {
	return (0.01f * Math.abs(speedPercentage) * Math.min(leftMotor.getMaxSpeed(), rightMotor.getMaxSpeed()));
    }
    
    private final void setLegMotorsAbsSpeedByParcentage(int speedPercentage, RegulatedMotor leftMotor, RegulatedMotor rightMotor) {
	int speed = (int)getAbsSpeedByParcentage(speedPercentage);
	// スピード設定
	leftMotor.setSpeed(speed);
	rightMotor.setSpeed(speed);
    }
    
    private final void setEachLegMotorsAbsSpeedByParcentage(int leftSpeedPercentage, int rightSpeedPercentage, RegulatedMotor leftMotor, RegulatedMotor rightMotor) {
	// スピード設定
	leftMotor.setSpeed((int)getAbsSpeedByParcentage(leftSpeedPercentage));
	rightMotor.setSpeed((int)getAbsSpeedByParcentage(rightSpeedPercentage));
    }

}
