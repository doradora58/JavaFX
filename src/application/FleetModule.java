package application;

import java.io.IOException;

public class FleetModule {

	TcpCommunication tcpCommunication;
	TcpConfig tcpConfig;

	FleetModule(){
		boolean config = true;
		if(config){
			// I need to read external file.
			String clientLocalIp = "127.0.0.1";
			String clientRemoteIp = "192.168.110.100";
			int clientLocalPort = 51000;
			int clientRemotePort = 55000;
			String serverLocalIp = "192.168.110.100";
			int serverLocalPort = 55000;
			// setting file configuration
			System.out.println("Set TcpConfig");
			tcpConfig = new TcpConfig();
			tcpConfig.setClientConfig(clientRemoteIp,clientRemotePort);
			tcpConfig.setClientBindConfig(clientLocalIp,clientLocalPort);
			tcpConfig.setServerConfig(serverLocalIp,serverLocalPort);
		}

	}

	public void init() {
		if(tcpConfig != null){ 
			System.out.println("Instance TcpCommunication");
			tcpCommunication = new TcpCommunication(tcpConfig, this);
			tcpCommunication.creatClientThread();
			tcpCommunication.createServerThread();
//			start();

		}
	}

	public void start(){
		// tcpCommunication
		if(tcpCommunication != null){
			tcpCommunication.startClientCommunication();
			tcpCommunication.startServerCommunication();
		}
	}

	public void Stop(){

	}

}
