/*!
 * @file IrSeekerController.java
 * @brief 赤外線センサー制御プログラム
 * 
 * 参考ページ：https://github.com/ETrobocon/etroboEV3
 * 
 */
import lejos.hardware.port.Port;
import lejos.hardware.sensor.HiTechnicIRSeekerV2;
import lejos.robotics.SampleProvider;

public class IrSeekerController {
    // 赤外線センサー
    private HiTechnicIRSeekerV2 irSeeker;
    private SampleProvider modulatedMode;  // 変調モード
    private float[] sampleAngle;
    /*!
     * コンストラクタ
     */
    public IrSeekerController(Port sensorPort) {
	// 赤外線センサー
	irSeeker = new HiTechnicIRSeekerV2(sensorPort);
	modulatedMode = irSeeker.getModulatedMode(); // 変調モード
	sampleAngle = new float[modulatedMode.sampleSize()];
    }
    
    /*!
     * 赤外線センサーによりボールとの角度を取得する
     * @return ボールとの角度
     */
    public final float getIrSeekerAngle() {
	modulatedMode.fetchSample(sampleAngle, 0);
        return sampleAngle[0];
    }
    
    /*!
     * 赤外線センサーによりボールとの角度をint型で取得する
     * @return ボールとの角度
     */
    public final int getIrSeekerAngleByInt() {
	modulatedMode.fetchSample(sampleAngle, 0);
	if (Float.isNaN(sampleAngle[0])) {
	    return 180;
	}
	return (int)sampleAngle[0];
    }
}
