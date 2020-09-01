import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Grid extends JPanel {
	private int[][] cell;
	private int row;
	private int col;
	private int nState;
	private int beginX = 20;
	private int beginY = 20;
	private int stepX;
	private int stepY;
	private Color[] colors;
	
	Grid(int row, int col, int n) {
		this.cell = new int[row][col];
		this.row = row;
		this.col = col;
		this.nState = n;
		this.colors = new Color[n];
		this.colors[0] = Color.white;
		this.colors[1] = Color.black;
		for (int i = 2; i < n; i++) {
			int R = (int)(Math.random() * 256);
			int G = (int)(Math.random() * 256);
			int B = (int)(Math.random() * 256);
			this.colors[i] = new Color (R, G, B); 
		}
	}
	
	public int scan(Coordinate coor) {
		return cell[coor.y][coor.x];
	}
	
	public void flip(Coordinate coor) {
		cell[coor.y][coor.x]++;
		cell[coor.y][coor.x] %= nState;
	}
	
	public boolean isOutOfBound(Coordinate coor) {
		if (coor.x < 0 || coor.y < 0 || coor.x >= col || coor.y >= row) {
			return true;
		}
		return false;
	}
	
	@Override
	 protected void paintComponent(Graphics g) {
		int height = this.getHeight() - this.beginY;
		int width = this.getWidth() - this.beginX;
		this.stepX = Math.floorDiv(width, col);
		this.stepY = Math.floorDiv(height, row);
		super.paintComponent(g);
		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++) {
				g.setColor(this.colors[cell[i][j]]);
				g.fillRect(beginX + i * stepX, beginY + j * stepY, stepX, stepY);
			}
//		g.setColor(Color.black);
//		for (int i = 0; i <= col; i++) {
//			g.drawLine(i * stepX + beginX, beginY, i * stepX + beginX, beginY + row * stepY);
//		}
//		for (int i = 0; i <= row; i++) {
//			g.drawLine(beginX, i * stepY + beginY, beginX + col * stepX, i * stepY + beginY);
//		}
	}
}
