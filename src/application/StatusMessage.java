package application;

import java.time.LocalDateTime;

public class StatusMessage {
	
	private long id;
	private int no;
	private short areaId;
	private boolean valid;
	private String version;
	private String currentTime;
	
	public StatusMessage(long id, int no, short areaId, String version, String currentTime) {
		this.id = id;
		this.no = no;
		this.areaId = areaId;
		//this.valid = valid;
		this.version = version;
		this.currentTime = currentTime;
	}
	public long getId() {
		return id;
	}
	public int getNo() {
		return no;
	}
	public short getAreaId() {
		return areaId;
	}
	public boolean isValid() {
		return valid;
	}
	public String getVersion() {
		return version;
	}
	public String getCurrentTime() {
		return currentTime;
	}
	public void printData(){
		System.out.println("受信データ "+"id:"+this.id+" no:"+this.no+" areaId:"+this.areaId+" valid:"+this.valid+" version:"+this.version+" currentTime:"+ this.currentTime);
		
	}
}
