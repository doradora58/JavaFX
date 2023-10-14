package application;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class ClientThread extends Thread {

	private static final int CONNECT_TIME_OUT = 1000;
	private int messageId;
	// if use bind
	private boolean isRun = false;
	SocketChannel socketChannel;
	InetSocketAddress remoteAddress;
	InetSocketAddress localAddress;

	ClientThread(InetSocketAddress remoteAddress, InetSocketAddress localAddress) {
		this.remoteAddress = remoteAddress;
		this.localAddress = localAddress;
	}

	public void init() {
		try {
			socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(true);
			socketChannel.socket().bind(localAddress);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void run() {
		this.isRun = true;
		this.messageId = 0;
		while (isRun) {
			try {
				if(!socketChannel.isConnected()) {
					System.out.println("ClientThread connecting...");
					socketChannel.socket().connect(remoteAddress,CONNECT_TIME_OUT);
					
				}
				else {
					System.out.println("ClientThread complete connect " + socketChannel.socket().getRemoteSocketAddress());
					
				}
			} catch (IOException e) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				continue;
			}

		}
	}

	public void sendMessage() {

		int dataSize = 14; //byte unit

		// 送信バッファデータを構築(今回はint型をテストするので最低4バイトを確保)
		ByteBuffer bb = ByteBuffer.allocate(dataSize);
		long id = messageId;
		int no = 2222;
		short areaId = 1234;
		boolean valid = true;
		String version ="1.1.1";
		Status status = new Status(id, no, areaId, version);
		status.printData();

		bb = serializeStatus(status);
		// 操作説明
		//        System.out.println("送信する数値を入力してEnterで送信します。");

		// 数値を入力させる(オーバーフローなどは考慮していない)
		//        bb.putInt(new Scanner(System.in).nextInt());
		// 送信準備を行う
		bb.flip();

		// 送信処理
		try {
			socketChannel.write(bb);
			//System.out.println("送信："+bb.getInt(0));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private ByteBuffer serializeStatus(Status status) {
		// TODO Auto-generated method stub
		int dataSize = 1024; //byte unit
		ByteBuffer buf = ByteBuffer.allocate(dataSize);
		// 8 byte
		//		buf.position(0);
		buf.putLong(status.getId());
		// 4 byte
		//		buf.position(8);
		buf.putInt(status.getNo());
		// 2 byte
		//		buf.position(12);
		buf.putShort(status.getAreaId());			

		//		buf.position(14);
		//		int versionDataSize = status.getVersion().getBytes().length;
		//		System.out.println("versionDataSize:" + versionDataSize);
		buf.put(status.getVersion().getBytes(StandardCharsets.UTF_8));

		//		int currentTimeDataSize = status.getCurrentTime().toString().length();
		//		System.out.println("currentTimeDataSize:" + currentTimeDataSize);
		buf.put(status.getCurrentTime().toString().getBytes(StandardCharsets.UTF_8));

		for(int i = 0; i < dataSize; i++) {
			if(i == 0) {
				System.out.print("送信データ "+buf.get(i)+", ");
			}else if(i!=dataSize-1) {
				System.out.print(buf.get(i)+", ");
			}else {
				System.out.println(buf.get(i));
			}
		}

		return buf;
	}

}
