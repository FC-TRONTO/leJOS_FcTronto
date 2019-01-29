import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import lejos.hardware.device.UART;
import lejos.hardware.port.Port;

public class USBController implements Runnable {
    private UART uart;
    private final int NUM_OF_STORED_VALUE = 2;
    private final int READ_BUFF_SIZE = 128;
    private int leftMotorPower = 0;
    private int rightMotorPower = 0;

    /*!
     * コンストラクタ
     */
    public USBController(Port uartPort) {
	uart = new UART(uartPort);
    }

    public void run() {
	while(true) {
	    readAndStoreValues();
	}
    }

    public void write(String input) {
	try {
	    uart.getOutputStream().write(input.getBytes());
	} catch (IOException e) {
	    // TODO 自動生成された catch ブロック
	    e.printStackTrace();
	}
    }

    public int[] getMotorPowers() {
	int[] motorPowers = new int[NUM_OF_STORED_VALUE];
	motorPowers[0] = leftMotorPower;
	motorPowers[1] = rightMotorPower;
	return motorPowers;
    }

    private void readAndStoreValues() {
	byte[] byteValues = readValues();
	if(byteValues != null) {
	    String str = new String(byteValues);
	    //System.out.printf("buffer = %s\n", str);
	    //lcd.drawString("READ = " + line, 1, 2);
	    // ,区切りで読み込んだデータを分割
	    String[] readDatas = str.trim().split(",", 0);
	    //System.out.printf("length = %d\n", readDatas.length);
	    // 読めたデータ数が正しければモータの値として記憶する
	    if(readDatas.length == NUM_OF_STORED_VALUE) {
		leftMotorPower = Integer.parseInt(readDatas[0]);
		rightMotorPower = Integer.parseInt(readDatas[1]);
	    }
	}

    }

    private byte[] readValues() {
	byte[] buff = new byte[READ_BUFF_SIZE];
	try {
	    int read = -1;
	    int totalLen = 0;
	    while((read = uart.getInputStream().read()) != 10) {
		if(read != -1) {
		    buff[totalLen] = (byte)read;
		    //System.out.printf("totalLen = %d, read = %d\n", totalLen, read);
		    totalLen++;
		}
	    }
	} catch (IOException e) {
	    // TODO 自動生成された catch ブロック
	    e.printStackTrace();
	}
	return buff;
    }
}