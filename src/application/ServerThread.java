package application;

import java.io.IOException;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class ServerThread extends Thread{


	private boolean isRun = false;
	private InetSocketAddress serverAddress;

	private SocketChannel socketChannel;
	private ServerSocketChannel serverSocketChannel;
	boolean isBlockingMode = true;

	ServerThread(InetSocketAddress serverAddress){
		this.isRun = false;
		this.serverAddress = serverAddress;
	}

	public void init(){
		try {
			this.serverSocketChannel = ServerSocketChannel.open();
			// ノンブロッキングモード
			this.serverSocketChannel.socket().bind(serverAddress);
			this.serverSocketChannel.configureBlocking(isBlockingMode);
			System.out.println("ServerThread LocalAddress "+serverSocketChannel.getLocalAddress());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run(){
		isRun = true;
		while(isRun){
			try {
				System.out.println("ServerThread accepting...");
				socketChannel = this.serverSocketChannel.accept();

				System.out.println("ServerThread complete connect");
				boolean isConnecting = true;

				while(isConnecting) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}


				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	public void reaadMessage() {
		int dataSize =1024;
		ByteBuffer buf = ByteBuffer.allocate(dataSize);
		if(socketChannel!=null){
			try {
				socketChannel.read(buf);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			StatusMessage statusMessage = deserializeStatus(buf);
			statusMessage.printData();
		}
	}

	private StatusMessage deserializeStatus(ByteBuffer buf) {
		// TODO Auto-generated method stub
		int dataSize =1024;
		if(buf != null){
			//intデータを受信データの0バイト目から読み込み
			for (int i = 0; i < dataSize; i++) {
				if(i == 0) {
					System.out.print("受信データ "+buf.get(i)+", ");
				}else if(i != dataSize-1) {
					System.out.print(buf.get(i)+", ");
				}else {
					System.out.println(buf.get(i));
				}
			}
		}
		//		buf.position(0);
		buf.flip();
		long id = buf.getLong();
		buf.position(8);
		int no = buf.getInt();
		buf.position(12);
		short areaId = buf.getShort();
		//boolean valid =buf.get;
		buf.position(14);
		buf.limit(19);
		String version = StandardCharsets.UTF_8.decode(buf).toString();
		//		System.out.println(version);
		//		String version = versionBuf.toString();
		buf.position(19);
		buf.limit(45);
		//		CharBuffer newContent = StandardCharsets.UTF_8.decode(buf);
		//		System.out.println(newContent);
		//		String version = newContent.toString();
		String currentTime = StandardCharsets.UTF_8.decode(buf).toString();
		//		System.out.println(currentTime);

		return new StatusMessage(id, no, areaId, version, currentTime);//new StatusMessage(id, no, areaId, version, currentTime);
	}

	public void close(){
		isRun = false;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			//ソケットチャンネルクローズ
			socketChannel.close();
			//サーバーチャンネルクローズ
			serverSocketChannel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
