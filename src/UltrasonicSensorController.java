/*!
 * @file UltrasonicSensorController.java
 * @brief 超音波距離センサー制御プログラム
 * 
 * 参考ページ：https://github.com/ETrobocon/etroboEV3
 * 
 */
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class UltrasonicSensorController {
    // 超音波センサー
    private EV3UltrasonicSensor sonar;
    private SampleProvider distanceMode;  // 距離検出モード
    private float[] sampleDistance;
    
    /*!
     * コンストラクタ
     */
    public UltrasonicSensorController(Port sensorPort) {
	// 超音波センサー
	sonar = new EV3UltrasonicSensor(sensorPort);
	distanceMode = sonar.getDistanceMode(); // 距離検出モード
	sampleDistance = new float[distanceMode.sampleSize()];
	sonar.enable();
    }
    
    /*!
     * 超音波センサーにより障害物との距離を取得する
     * @return 障害物との距離(m)
     */
    public final float getSonarDistance() {
        distanceMode.fetchSample(sampleDistance, 0);
        return sampleDistance[0];
    }
}
