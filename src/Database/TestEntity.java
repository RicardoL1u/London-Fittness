package Database;

public class TestEntity {
	public static void main(String args[]) {
		Trainee tr=new Trainee("Tom", "1qwerr");
		System.out.println(tr.getEmail());

		Trainee tr2=new Trainee("Alic", "1qwerr");
		System.out.println(tr2.getUserID());

		Trainee tr3=new Trainee("Monkey King", "dntghh", "dntg@163.com", 10, 1, "C001");
		System.out.println(tr3.getUserID());

		Trainee tr4=new Trainee("Monkey King", "dntghh");
		System.out.println(tr4.getUserID());

		System.out.println("--------------video--------------");
		Video vi1=new Video("A001");
		System.out.println(vi1.getPath());

		Video vi2=new Video("A002");
		System.out.println(vi2.getPath());
		System.out.println(vi2.getVideoID());
	}
}
