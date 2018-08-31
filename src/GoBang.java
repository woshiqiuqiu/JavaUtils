import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

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

		MouseInterfaceImplement mii = new MouseInterfaceImplement();
		mii.setGamePanel(gamePanel);
		gamePanel.addMouseMotionListener(mii);

		MouseClick mc = new MouseClick();
		mc.setGamePanel(gamePanel);
		gamePanel.addMouseListener(mc);

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
		// 设置棋子大小
		private static final int CHESS_SIZE = 20;

		private int mouseX = 11, mouseY = 11;
		private boolean chess_color = true;

		// 设置当前的鼠标位置
		public void setMouseXY(int x, int y) {
			mouseX = x;
			mouseY = y;
			this.repaint();
		}

		// 通知添加棋子的回调
		public void setChessOn() {
			if (chess.addChess(mouseX, mouseY, chess_color)) {
				chess_color = !chess_color;
				this.repaint();
				if(GoBangCheck.check(chess.getChesses())) {
					System.out.println("True");
					
				}
			}
		}

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
			// 打印线条和行号
			arg0.setColor(Color.BLACK);
			for (row = 0; row < GameBackGround.GAME_BACK_GROUND_HEIGH; row++) {
				arg0.drawString((row + 1) + "", 0, 25 * row + disp);
				arg0.drawLine(disp, 25 * row + disp, disp + GameBackGround.GAME_PANEL_HEIGH, 25 * row + disp);
			}
			for (line = 0; line < GameBackGround.GAME_BACK_GROUND_WEIGH; line++) {
				arg0.drawString((char) (line + 'A') + "", 25 * line + disp, disp - 5);
				arg0.drawLine(disp + 25 * line, disp, disp + 25 * line, GameBackGround.GAME_PANEL_WEIGH + disp);
			}

			// 打印基础点(6,6\6,16\11,11\16,6\16,16)
			arg0.fillOval(5 * 25 + disp - 5, 5 * 25 + disp - 5, 10, 10);
			arg0.fillOval(5 * 25 + disp - 5, 15 * 25 + disp - 5, 10, 10);
			arg0.fillOval(10 * 25 + disp - 5, 10 * 25 + disp - 5, 10, 10);
			arg0.fillOval(15 * 25 + disp - 5, 5 * 25 + disp - 5, 10, 10);
			arg0.fillOval(15 * 25 + disp - 5, 15 * 25 + disp - 5, 10, 10);

			for (int x = 0; x < GameBackGround.GAME_BACK_GROUND_WEIGH; x++) {
				for (int y = 0; y < GameBackGround.GAME_BACK_GROUND_WEIGH; y++) {
					int status = chess.getChessStatus(x, y);
					switch (status) {
					case 1: {
						arg0.setColor(Color.WHITE);
						arg0.fillOval((x - 1) * 25 + disp - CHESS_SIZE / 2, (y - 1) * 25 + disp - CHESS_SIZE / 2,
								CHESS_SIZE, CHESS_SIZE);
						break;
					}
					case 2: {
						arg0.setColor(Color.BLACK);
						arg0.fillOval((x - 1) * 25 + disp - CHESS_SIZE / 2, (y - 1) * 25 + disp - CHESS_SIZE / 2,
								CHESS_SIZE, CHESS_SIZE);
						break;
					}
					default: {
						break;
					}
					}
				}
			}
			arg0.setColor(Color.RED);
			arg0.drawRect((mouseX - 1) * 25 + disp - CHESS_SIZE / 2, (mouseY - 1) * 25 + disp - CHESS_SIZE / 2,
					CHESS_SIZE, CHESS_SIZE);
		}

	}

	// 鼠标点击事件
	class MouseClick implements MouseListener {

		GamePanel gamePanel;

		public void setGamePanel(GamePanel gamePanel) {
			this.gamePanel = gamePanel;

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					gamePanel.setChessOn();
				}
			});
			thread.start();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	// 鼠标移动监听事件
	class MouseInterfaceImplement implements MouseMotionListener {

		int x = 15, y = 15;
		GamePanel gamePanel;

		public void setGamePanel(GamePanel gamePanel) {
			this.gamePanel = gamePanel;
		}

		// 鼠标阈值
		private static final int mouseThreshold = 10;

		@Override
		public void mouseDragged(MouseEvent e) {

		}

		@Override
		public void mouseMoved(MouseEvent e) {

			// TODO Auto-generated method stub
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					if (e.getX() - x > mouseThreshold || e.getX() - x < -mouseThreshold || e.getY() - y > mouseThreshold
							|| e.getY() - y < -mouseThreshold) {
						x = (e.getX() - 3) / 25 * 25;
						y = (e.getY() - 3) / 25 * 25;
						if (x / 25 + 1 > 21 || y / 25 + 1 > 21)
							return;
						gamePanel.setMouseXY(x / 25 + 1, y / 25 + 1);
					}
				}
			});
			thread.start();
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
					Chesses[cX][cY][0] = true;
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
					return 2;
				}
			}
		}
		public Boolean[][][] getChesses() {
			return Chesses;
		}
	}
}

class GoBangCheck {
	public static boolean check(Boolean[][][] chess) {
		int i, j;
		for(i = 0; i < chess.length; i++) {
			for(j = 0; j < chess[i].length; j++) {
				if(!chess[i][j][0])
				if(checkOne(chess, i, j)) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean checkOne(Boolean[][][] chess, int x, int y) {

		System.out.println(x + " " + y);
		int cx, cy, count;
		boolean color = chess[x][y][1];

		for (cx = x; cx < chess.length; cx++) {
			if (chess[cx][y][1] != color  && !chess[cx][y][0])
				break;
		}
		if (cx - x + 1 == 5)
			return true;
		for (cx = x; cx >= 0; cx--) {
			if (chess[cx][y][1] != color && !chess[cx][y][0])
				break;
		}
		if (x - cx + 1 == 5)
			return true;

		for (cy = y; cy < chess[x].length; cy++) {
			if (chess[x][cy][1] != color && !chess[x][cy][0])
				break;
		}
		if (cx - y + 1 == 5)
			return true;
		for (cy = y; cy >= 0; cy--) {
			if (chess[x][cy][1] != color && !chess[x][cy][0])
				break;
		}
		if (y - cx + 1 == 5)
			return true;

		for (count = 0, cx = x, cy = y; cx + count < chess.length && cy + count < chess[cx + count].length; count++) {
			if(chess[cx + count][cy + count][1] != color && !chess[cx + count][cy + count][0]) {
				break;
			}
		}
		if(count == 5) return true;
		
		for (count = 0, cx = x, cy = y; cx + count < chess.length && cy - count >= 0; count++) {
			if(chess[cx + count][cy - count][1] != color && !chess[cx + count][cy - count][0]) {
				break;
			}
		}
		if(count == 5) return true;

		for (count = 0, cx = x, cy = y; cx - count >= 0 && cy + count < chess[cx - count].length; count++) {
			if(chess[cx - count][cy + count][1] != color && !chess[cx - count][cy + count][0]) {
				break;
			}
		}
		if(count == 5) return true;
		
		for (count = 0, cx = x, cy = y; cx - count >= 0 && cy - count >= 0; count++) {
			if(chess[cx - count][cy - count][1] != color && !chess[cx - count][cy - count][0]) {
				break;
			}
		}
		if(count == 5) return true;
		
		return false;
	}
}