package eud.nyu.cs.pqs.connectfour;

public class ConnectFourApp {
	public static void main(String[] args) {
		ConnectFourModel model = new ConnectFourModel();
		ConnectFourView view = new ConnectFourView(model);
		model.startGame();
	}
}
