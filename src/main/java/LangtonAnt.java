import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class LangtonAnt { 
	Grid grid;
	Coordinate currentCoor;
	Direction currentDir;
	boolean[] rule;
	JFrame window;
	
	LangtonAnt() {
//		String rule = "RRLLLRLLLRRR";
//		String rule = "RL";
//		String rule = "RLLLLLLLLLLLLLLL";
//		String rule = "LRLLLLLRL";
//		String rule = "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRL";
		String rule = "RRLLRRLLRRRRRRRRRRRLLRRLL";
		window = new JFrame();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		window.setSize(screenSize);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		grid = new Grid(200, 200, rule.length());
		window.add(grid);
		window.setVisible(true);
		currentCoor = new Coordinate(100, 100);
		currentDir = Direction.Right;
		setRule(rule);
	}
	
	private void setRule(String rule) {
		this.rule = new boolean[rule.length()];
		for (int i = 0; i < rule.length(); i++) 
			this.rule[i] = (rule.charAt(i) == 'R') ? true : false; 
	}
	
	public Direction turn(int currentState) {
		switch(currentDir) {
		case Right:
			if (rule[currentState])
				return Direction.Down;
			else
				return Direction.Up;
		case Left:
			if (rule[currentState])
				return Direction.Up;
			else
				return Direction.Down;
		case Up:
			if (rule[currentState])
				return Direction.Right;
			else
				return Direction.Left;
		case Down:
			if (rule[currentState])
				return Direction.Left;
			else
				return Direction.Right;
		default:
			return currentDir;
		}
	}
	
	public boolean move() {
		Coordinate newCoor;
		switch(currentDir) {
		case Right:
			newCoor = new Coordinate(currentCoor.x - 1, currentCoor.y);
			break;
		case Left:
			newCoor = new Coordinate(currentCoor.x + 1, currentCoor.y);
			break;
		case Up:
			newCoor = new Coordinate(currentCoor.x, currentCoor.y + 1);
			break;
		case Down:
			newCoor = new Coordinate(currentCoor.x, currentCoor.y - 1);
			break;
		default:
			newCoor = currentCoor;
		}
		if (grid.isOutOfBound(newCoor))
			return false;
		this.currentCoor = newCoor;
		return true;
	}
	
	public boolean transition() {
		int state = grid.scan(currentCoor);
		currentDir = turn(state);
		grid.flip(currentCoor);
		try {
		    Thread.sleep(1);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
		window.repaint();
		return move();	
	}
	
	public void run() {
		while(transition());
	}
	
	public static void main(String[] args) {
		LangtonAnt ant = new LangtonAnt();
		ant.run();
	}
}  