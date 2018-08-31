import java.awt.Color;
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
	private static final byte GAME_BACK_GROUND_WEIGH = 21;
	private static final byte GAME_BACK_GROUND_HEIGH = 21;
//	设置游戏区域大小
	private static final int GAME_PANEL_WEIGH = 500;
	private static final int GAME_PANEL_HEIGH = 500;
//	设置游戏窗口大小
	private static final int GAME_WINDOWS_WEIGH = 900;
	private static final int GAME_WINDOWS_HEIGH = 600;

	// 游戏窗口
	private JFrame GameWindow;

	// 静态字符串
	private static final String WINDOW_TITLE = "五子棋";

//	构造
	public GameBackGround() {
		GameWindow = new JFrame();
		GameWindow.setTitle(WINDOW_TITLE);
		GameWindow.setSize(GAME_WINDOWS_WEIGH, GAME_WINDOWS_HEIGH);
		GameWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);

//		添加基本Panel
		JPanel basePanel = new JPanel();
		basePanel.setLayout(null); // 设置布局方式为绝对布局

//		创建游戏画布并添加游戏画布
		GamePanel gamePanel = new GamePanel();
		gamePanel.setBounds(10, 10, 550, 550);
		basePanel.add(gamePanel);

		GameWindow.add(basePanel);
		GameWindow.setVisible(true);
	}

	/**
	 * 棋盘图像绘制类
	 */
	class GamePanel extends JPanel {
		// 实现棋盘
		private Chess chess = new Chess();
		//设置棋子大小
		private static final int CHESS_SIZE = 20;

		@Override
		protected void paintComponent(Graphics arg0) {
			// TODO Auto-generated method stub
			super.paintComponent(arg0);

			int row, line;
			// 位移
			int disp = 15;

			
			// 打印背景板
			arg0.setColor(Color.YELLOW);
			arg0.fillRect(disp, disp, GameBackGround.GAME_PANEL_WEIGH, GameBackGround.GAME_PANEL_HEIGH);
			arg0.setColor(Color.BLACK);
			// 打印线条和行号
			for (row = 0; row < GameBackGround.GAME_BACK_GROUND_HEIGH; row++) {
				arg0.drawString((row + 1) + "", 0, 25 * row + disp);
				arg0.drawLine(disp, 25 * row + disp, disp + GameBackGround.GAME_PANEL_HEIGH, 25 * row + disp);
			}
			for (line = 0; line < GameBackGround.GAME_BACK_GROUND_WEIGH; line++) {
				arg0.drawString((char) (line + 'A') + "", 25 * line + disp, disp - 5);
				arg0.drawLine(disp + 25 * line, disp, disp + 25 * line, GameBackGround.GAME_PANEL_WEIGH + disp);
			}

			// 打印基础点(6 6\6 16\ 11 11\16 6\ 16 16)
			arg0.fillOval(5 * 25 + disp - 5, 5 * 25 + disp - 5, 10, 10);
			arg0.fillOval(5 * 25 + disp - 5, 15 * 25 + disp - 5, 10, 10);
			arg0.fillOval(10 * 25 + disp - 5, 10 * 25 + disp - 5, 10, 10);
			arg0.fillOval(15 * 25 + disp - 5, 5 * 25 + disp - 5, 10, 10);
			arg0.fillOval(15 * 25 + disp - 5, 15 * 25 + disp - 5, 10, 10);

			for (int x = 0; x < GameBackGround.GAME_BACK_GROUND_WEIGH; x++) {
				for (int y = 0; y < GameBackGround.GAME_BACK_GROUND_WEIGH; y++) {
					int status = chess.getChessStatus(x, y);
					switch(status) {
					case 1:{
						arg0.setColor(Color.WHITE);
						arg0.fillOval((x - 1) * 25 + disp - CHESS_SIZE / 2, (y - 1) * 25 + disp - CHESS_SIZE / 2, CHESS_SIZE, CHESS_SIZE);
						break;
					}
					case 2:{
						arg0.setColor(Color.BLACK);
						arg0.fillOval((y - 1) * 25 + disp - CHESS_SIZE / 2, (y - 1) * 25 + disp - CHESS_SIZE / 2, CHESS_SIZE, CHESS_SIZE);
						break;
					}
					default:{
						break;
					}
					}
				}
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
			Chesses = new Boolean[21][21][2];
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