package Database;

import net.sf.json.JSONObject;

public class Video {
	private String path = "";
	private String videoID = "";
	
	public Video(String videoID) {
		Op_JsonFile checkfile=new Op_JsonFile();
		String jsonstr = checkfile.readJsonFile("Database/jsonfile/video.json");
		JSONObject jsonObj_video =JSONObject.fromObject(jsonstr);
		if (jsonObj_video.has(videoID)) {
			this.videoID=videoID;
			this.path=(String) ((JSONObject) jsonObj_video.get(videoID)).get("path");
		}
		else {
			this.videoID="-1";
			this.path="";
		}
	}
	
	public String getPath() {return this.path;}
	public String getVideoID() {return this.videoID;}
	
	public void setPath(String path) {this.path=path;}
	public void setVideoID(String videoID) {this.videoID=videoID;}
}
