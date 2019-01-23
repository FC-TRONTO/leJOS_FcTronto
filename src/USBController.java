import java.io.IOException;

import lejos.hardware.device.UART;
import lejos.hardware.port.Port;

public class USBController {
    private UART uart;
    
    /*!
     * コンストラクタ
     */
    public USBController(Port uartPort) {
	uart = new UART(uartPort);
    }
    
    public int read(byte[] buff) {
	int len = 0;
	while(true) {
	    int len_diff = -1;
	    try {
		len_diff = uart.getInputStream().read(buff);
	    } catch (IOException e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	    }
	    if(len_diff < 0) {
		return len;
	    }
	    len += len_diff;
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
}
