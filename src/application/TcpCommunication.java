package application;

public class TcpCommunication implements ITcpCommunication {

	private ClientThread clientThread;
	private ServerThread serverThread;
	private TcpConfig tcpConfig;
	private FleetModule fleetModule;

	public TcpCommunication(TcpConfig tcpConfig, FleetModule fleetModule) {
		this.tcpConfig = tcpConfig;
		this.fleetModule = fleetModule;

	}

	@Override
	public void creatClientThread() {
		if(clientThread == null){
			System.out.println("Instance ClientThread");
			clientThread = new ClientThread(tcpConfig.getClientRemoteAddress(),tcpConfig.getClientBindAddress());
		}else{
			System.out.println("Already instanced ClientThread");
		}
	}

	@Override
	public void startClientCommunication() {
		if(clientThread != null){
			System.out.println("ClientThread run");
			clientThread.init();
			clientThread.start();
		}else{
			System.out.println("ClientThread is null");   
		}        
	}

	@Override
	public void stopClientCommunication() {

		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'stopClientCommunication'");
	}

	@Override
	public void sendMessage() {
		clientThread.sendMessage();
	}

	@Override
	public void createServerThread() {
		if(serverThread == null){
			System.out.println("Instance ServerThread");
			serverThread = new ServerThread(tcpConfig.getServerAddress());
		}else{
			System.out.println("Already instanced ServerThread");
		}

	}

	@Override
	public void startServerCommunication() {
		if(serverThread != null){
			System.out.println("ServerThread run");
			serverThread.init();
			serverThread.start();
		}else{
			System.out.println("ServerThread is null");
		}
	}

	@Override
	public void stopServerCommunication() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'stopServerCommunication'");
	}



	@Override
	public void receiveMessage(){
		serverThread.reaadMessage();

	}


}
