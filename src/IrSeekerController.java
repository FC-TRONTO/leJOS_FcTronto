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
    private static final int DIS_CORRECTION_COEFFICIENT = 10;
    private static final float DIS_REFERENCE_VALUE = 1.f;
    
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
	/*
	for(int i = 0; i < sampleAngle.length; i++) {
	    System.out.printf("sampleAngle[%d] = %f¥n", i, sampleAngle[i]);
	}
	*/
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
    
    /*!
     * 赤外線センサーによりボールとの角度と距離をfloat型で取得する
     * @return ボールとの角度
     */
    public final float[] getIrSeekerAngleAndDistanceByFloat() {
	modulatedMode.fetchSample(sampleAngle, 0);
	/*
	for(int i = 0; i < sampleAngle.length; i++) {
	    System.out.printf("sampleAngle[%d] = %f\n", i, sampleAngle[i]);
	}
	*/
	return getAngleAndDistanceFromSample(sampleAngle);
    }
    
    private final float[] getAngleAndDistanceFromSample(float[] sample) {
	float[] returnValues = new float[2];
	float firstValue = -100.f;
	float secondValue = -100.f;
	int firstIndex = -1;
	int secondIndex = -1;
	for(int i = 0; i < sample.length; i++) {
	    if(sample[i] > firstValue) {
		// 元々の最大値は2番目の値として再記憶
		secondValue = firstValue;
		secondIndex = firstIndex;
		// 最大値を記憶
		firstValue = sample[i];
		firstIndex = i;
	    }else if(sample[i] > secondValue) {
		secondValue = sample[i];
		secondIndex = i;
	    }
	}
	returnValues[0] = firstIndex;
	returnValues[1] = DIS_REFERENCE_VALUE - firstValue;
	if(Math.abs(firstIndex - secondIndex) == 1 && (firstValue - secondValue) < (firstValue / DIS_CORRECTION_COEFFICIENT)) {
	    returnValues[0] = (firstIndex + secondIndex) / 2.f;
	}
	/*
	for(int i = 0; i < returnValues.length; i++) {
	    System.out.printf("returnValues(ir)[%d] = %f\n", i, returnValues[i]);
	}
	*/
	return returnValues;
    }
}