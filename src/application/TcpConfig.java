package application;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class TcpConfig {

	private String clientLocalIp;
	private String clientRemoteIp;
	private int clientLocalPort;
	private int clientRemotePort;
	private String serverLocalIp;
	private int serverLocalPort;
	
    TcpConfig(){
    	
    }

	public void setClientConfig(String clientRemoteIp, int clientRemotePort) {
		// TODO Auto-generated method stub
		this.clientRemoteIp =clientRemoteIp;
		this.clientRemotePort = clientRemotePort;
	}

	public void setClientBindConfig(String clientLocalIp, int clientLocalPort) {
		// TODO Auto-generated method stub
		this.clientLocalIp = clientLocalIp;
		this.clientLocalPort = clientLocalPort;
	}

	public void setServerConfig(String serverLocalIp, int serverLocalPort) {
		// TODO Auto-generated method stub
		this.serverLocalIp = serverLocalIp;
		this.serverLocalPort = serverLocalPort;
	}
	public InetSocketAddress getClientRemoteAddress(){
		return new InetSocketAddress(clientRemoteIp, clientRemotePort);
	}
	public InetSocketAddress getClientBindAddress() {
		return new InetSocketAddress(clientLocalIp,clientLocalPort);
	}
	public InetSocketAddress getServerAddress() {
		return new InetSocketAddress(serverLocalIp, serverLocalPort);
	}

}
