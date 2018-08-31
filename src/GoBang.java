import javax.swing.JFrame;

public class GoBang {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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