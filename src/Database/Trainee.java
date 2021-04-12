package Database;

import net.sf.json.*;
import java.io.File;

public class Trainee {
	private String userName = "";
	private String password = "";
	private String email = "";
	private int age = 0;
	private int sex = 0;
	private String userID = "";
	
	public Trainee (String userName, String password) {
		Op_JsonFile readfile=new Op_JsonFile();

		String jsonstr = readfile.readJsonFile("./Database/jsonfile/user.json");
		//System.out.println(jsonstr);
		JSONObject jsonObj =JSONObject.fromObject(jsonstr);
		if (jsonObj.has(userName)) {
			//String loginResult = jsonObj.getString(userName);
			String truePassword =(String) ((JSONObject) jsonObj.get(userName)).get("password");
			if (password.equals(truePassword)) {
				this.userName=userName;
				this.password=truePassword;
				this.email=(String) ((JSONObject) jsonObj.get(userName)).get("email");
				this.age=Integer.parseInt((String) ((JSONObject) jsonObj.get(userName)).get("age"));
				this.sex=Integer.parseInt((String) ((JSONObject) jsonObj.get(userName)).get("age"));
				this.userID=(String) ((JSONObject) jsonObj.get(userName)).get("userID");
			}
			else {
				this.userID="-2";
			}
			
		}
		else {
			this.userID="-1";
		}
		//String result2 =(String) ((JSONObject) jsonObj.get("Alice")).get("password");
		//boolean result3 = jsonObj.has("Alice");
		//System.out.println("qqqqqqqqqq");
		//System.out.println(result3);
	}
	
	public Trainee (String userName, String password, String email, int age, int sex, String userID) {
		Op_JsonFile modifyfile=new Op_JsonFile();
		String jsonstr = modifyfile.readJsonFile("./Database/jsonfile/user.json");
		//System.out.println(jsonstr);
		JSONObject jsonObj =JSONObject.fromObject(jsonstr);
		if (jsonObj.has(userName)) {
			this.userID="-1";
		}
		else {
			this.userName=userName;
			this.password=password;
			this.email=email;
			this.age=age;
			this.sex=sex;
			this.userID=userID;

			JSONObject jsonObjArr = new JSONObject();
			
			jsonObjArr.put("userName", userName);
	        jsonObjArr.put("password", password);
	        jsonObjArr.put("userID", userID);
	        jsonObjArr.put("email", email);
	        jsonObjArr.put("age", String.valueOf(age));
	        jsonObjArr.put("sex", String.valueOf(sex));
	        
	        jsonObj.put(userName, jsonObjArr);
	        //System.out.println(jsonObj);
	        
	        String jsonString=jsonObj.toString();
	        Tool tool=new Tool();
	        String JsonString=tool.stringToJSON(jsonString);
	        modifyfile.writeJsonFile("./Database/jsonfile/user.json", JsonString);
		}
	}
	
	public String getUserName() {return this.userName;}
	public String getPassword() {return this.password;}
	public String getEmail() {return this.email;}
	public int getAge() {return this.age;}
	public int getSex() {return this.sex;}
	public String getUserID() {return this.userID;}
	
	public void setUserName(String name) {this.userName=name;}
	public void setPassword(String password) {this.password=password;}
	public void setEmail(String email) {this.email=email;}
	public void setAge(int age) {this.age=age;}
	public void setSex(int sex) {this.sex=sex;}
	public void setUserID(String id) {this.userID=id;}
}
