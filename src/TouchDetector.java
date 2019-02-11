import lejos.hardware.port.Port;
import lejos.hardware.sensor.BaseSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.Touch;
import lejos.robotics.TouchAdapter;
import lejos.robotics.objectdetection.Feature;
import lejos.robotics.objectdetection.FeatureDetector;
import lejos.robotics.objectdetection.FeatureListener;
import lejos.robotics.objectdetection.TouchFeatureDetector;

public class TouchDetector {
    private EV3TouchSensor ts;
    private Touch touch;
    private TouchFeatureDetector touchDetector;
    private Touch release;
    private TouchFeatureDetector releaseDetector;
    static boolean isPressed = false;

    /*!
     * コンストラクタ
     */
    public TouchDetector(Port sensorPort) {
	// 赤外線センサー
	ts = new EV3TouchSensor(sensorPort);
	// タッチセンサーが押されたときに処理するように設定する
	touch = new TouchAdapter(ts);
	touchDetector = new TouchFeatureDetector(touch);
	touchDetector.addListener(new PressedProcess());

	// タッチセンサーが放されたときに処理するように設定する
	release = new ReleaseAdapter(ts);
	releaseDetector = new TouchFeatureDetector(release);
	releaseDetector.addListener(new ReleasedProcess());
    }
    
    public boolean getIsPressed() {
	return isPressed;
    }

    // タッチセンサーが押された時の処理を持つクラス
    static class PressedProcess implements FeatureListener {
	public void featureDetected(Feature feature, FeatureDetector detector) {
	    System.out.println("Touch Sensor Pressed");
	    isPressed = true;
	}
    }

    // タッチセンサーが放された時の処理を持つクラス
    static class ReleasedProcess implements FeatureListener {
	public void featureDetected(Feature feature, FeatureDetector detector) {
	    System.out.println("Touch Sensor Released");
	    isPressed = false;
	}
    }

}

//EV3のAPIにあるTouchAdapterのisPressed()の結果を反転させて、放されたときにイベントを発生させる
class ReleaseAdapter extends TouchAdapter {

    public ReleaseAdapter(BaseSensor touchSensor) {
	super(touchSensor);
    }

    public boolean isPressed() {
	return !super.isPressed();
    }

}