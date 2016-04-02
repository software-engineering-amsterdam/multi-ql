package uva.ql;



public class App {
	
	public static void main(String[] args) {
		String filePath = "";
		boolean internal = true;

		if (args.length == 0) {
			filePath = "resources/simpleDefault.ql";
		} else {
			filePath = args[0];
			internal = false;
		}
		
		QL ql = new QL(filePath, internal);
		
		try {
			ql.start();
		} catch (Exception e) {
			System.out.println("An error occurred please contact the dev... " + e.getMessage());
		}
	}
}