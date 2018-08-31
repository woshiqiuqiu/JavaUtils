import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GoBang {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameBackGround gbg = new GameBackGround();
	}

}

class GameBackGround extends JFrame {

	/**
	 * 游戏棋盘 主要功能: 1.重新绘制棋盘 2.重制棋盘 3.改变棋局状态 内部类：虚拟棋盘
	 */
	private static final long serialVersionUID = 1L;

//	设置棋盘大小:Weigh-宽 、Heigh-高
	private static final byte GAME_BACK_GROUND_WEIGH = 19;
	private static final byte GAME_BACK_GROUND_HEIGH = 19;
//	设置游戏区域大小
	private static final int GAME_PANEL_WEIGH = 475;
	private static final int GAME_PANEL_HEIGH = 470;
//	设置游戏窗口大小
	private static final int GAME_WINDOWS_WEIGH = 900;
	private static final int GAME_WINDOWS_HEIGH = 600;
	
	//游戏窗口
	private JFrame GameWindow;
	
	//静态字符串
	private static final String WINDOW_TITLE = "五子棋";
	
//	构造
	public GameBackGround() {
		GameWindow = new JFrame();
		GameWindow.setTitle(WINDOW_TITLE);
		GameWindow.setSize(GAME_WINDOWS_WEIGH, GAME_WINDOWS_HEIGH);
		GameWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
		GameWindow.setVisible(true);
	}
	
	/**
	 * 棋盘图像绘制类
	 */
	class GamePanel extends JPanel{

		@Override
		protected void paintComponent(Graphics arg0) {
			// TODO Auto-generated method stub
			super.paintComponent(arg0);
			
			int row, line;
			
			for(row = 0; row < GameBackGround.GAME_BACK_GROUND_WEIGH; row++) {
				arg0.drawLine(0, 25*row, 475, 25*row);
			}
			for(line = 0; line < GameBackGround.GAME_BACK_GROUND_HEIGH; line++) {
				arg0.drawLine(25*line, 0, 25*line, 475);
			}
		}
		
	}

	/**
	 * 虚拟棋盘类 属性：棋盘
	 */
	private class Chess {
		// boolean[x][y][has chess, and color of chess]
		private Boolean[][][] Chesses;

		public Chess() {
			Chesses = new Boolean[19][19][2];
			setChessVoid();
		}

		// 棋盘置空，消除所有棋子
		public void setChessVoid() {
			int cX, cY;
			for (cX = 0; cX < Chesses.length; cX++) {
				for (cY = 0; cY < Chesses[cX].length; cY++) {
					Chesses[cX][cY][0] = false;
					Chesses[cX][cY][1] = false;
				}
			}
		}

		// 添加棋子(横坐标，纵坐标，颜色-true白，flase黑) 添加成功返回True 失败False
		public boolean addChess(int x, int y, boolean color) {
			if (Chesses[x][y][0]) {
				Chesses[x][y][0] = false;
				Chesses[x][y][1] = color;
				return true;
			} else {
				return false;
			}
		}

		// 查询棋盘某位置状态（0 - 没有棋子, 1 白色棋子，2黑色棋子)
		public int getChessStatus(int x, int y) {
			if (Chesses[x][y][0]) {
				return 0;
			} else {
				if (Chesses[x][y][1]) {
					return 1;
				} else {
					return 0;
				}
			}
		}
	}
}