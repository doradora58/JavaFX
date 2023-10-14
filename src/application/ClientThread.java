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
	boolean isBlockingMode = true;
	ClientThread(InetSocketAddress remoteAddress, InetSocketAddress localAddress) {
		this.remoteAddress = remoteAddress;
		this.localAddress = localAddress;
	}

	public void init() {
		try {
			socketChannel = SocketChannel.open();
//			socketChannel.bind(localAddress);
			socketChannel.configureBlocking(isBlockingMode);
			System.out.println("ClientThread LocalAddress "+socketChannel.getLocalAddress());
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
					System.out.println("ClientThread connecting..."+remoteAddress);
					socketChannel.socket().connect(remoteAddress,CONNECT_TIME_OUT);			
					System.out.println("ClientThread complete connect " + socketChannel.socket().getRemoteSocketAddress());

				}
				else {
					System.out.println("ClientThread complete connect " + socketChannel.socket().getRemoteSocketAddress());
					boolean isConnecting =true;
					while(isConnecting ) {

						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							
							e.printStackTrace();
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					socketChannel = SocketChannel.open();
//					socketChannel.socket().bind(localAddress);
					socketChannel.configureBlocking(isBlockingMode);
					System.out.println("ClientThread LocalAddress "+socketChannel.getLocalAddress());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}
	}

	public void sendMessage() {

		int byteBufferSize = 14; //byte unit

		// 送信バッファデータを構築(今回はint型をテストするので最低4バイトを確保)
		ByteBuffer byteBuffer = ByteBuffer.allocate(byteBufferSize);
		byte data = 0;
		while(byteBuffer.hasRemaining()) {
			byteBuffer.put(data);
			data++;
		}

		//		byteBuffer = serializeStatus(status);
		// 操作説明
		//        System.out.println("送信する数値を入力してEnterで送信します。");

		// 数値を入力させる(オーバーフローなどは考慮していない)
		//        bb.putInt(new Scanner(System.in).nextInt());
		// 送信準備を行う
		byteBuffer.flip();

		// 送信処理
		try {
			socketChannel.write(byteBuffer);
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
