package blackAndWhite;

import java.util.Random;

public class Role {
	private int[][] board = new int[10][10];
	private ReverseDirection rd;
	
	public Role() { // ��l�Ƹ��,type1������,type2,3���X�l,type4���i���ʸ��|
		Random ran = new Random();
		for (int i = 0; i < 7;) {
			int tmp1, tmp2;
			tmp1 = ran.nextInt(10);
			tmp2 = ran.nextInt(10);
			if ((tmp1 < 4 || tmp1 > 5) && (tmp2 < 4 || tmp2 > 5)) {
				board[tmp1][tmp2] = 1;
				++i;
			}
		}
		setBoard(4, 4, 2); // ����4����
		setBoard(5, 5, 2);
		setBoard(4, 5, 3);
		setBoard(5, 4, 3);
		canMove(2);
		
	}

	public void printMap() // �L�X�ѽL
	{
		for (int i = 0; i < 10; ++i) {
			for (int j = 0; j < 10; ++j) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}



	public int shout(int sum) {
		if (sum >= 8)
			return 1;
		else if (sum >= 5)
			return 2;
		else
			return 0;
	}

	public int reverse(int x, int y, int type) { // ��8�Ӥ�V�i�����
		int sum = 0;
		ReverseDirection rd = new ReverseDirection(board);
		sum = rd.reverseRight(x, y, type, sum);
		sum = rd.reverseLeft(x, y, type, sum);
		sum = rd.reverseUp(x, y, type, sum);
		sum = rd.reverseDown(x, y, type, sum);
		sum = rd.reverseRightAndUp(x, y, type, sum);
		sum = rd.reverseRightAndDown(x, y, type, sum);
		sum = rd.reverseLeftAndUp(x, y, type, sum);
		sum = rd.reverseLeftAndDown(x, y, type, sum);
		
		board = rd.getBoard();
		return shout(sum); // �O�_���񭵮�

	}

	
	private void pathCheck(int x, int y, int type) { // ��8�Ӥ�V�M��i���ʸ��|
		FindPath fp = new FindPath(board);
		for (int i = 0; i < 10; ++i) {
			for (int j = 0; j < 10; ++j) {
				fp.findPathUp(x, y, type);
				fp.findPathDown(x, y, type);
				fp.findPathLeft(x, y, type);
				fp.findPathRight(x, y, type);
				fp.findPathLeftAndUp(x, y, type);
				fp.findPathLeftAndDown(x, y, type);
				fp.findPathRightAndUp(x, y, type);
				fp.findPathRightAndDown(x, y, type);
			}
		}
		fp.getBoard();
	}

	public void clean() { // �M�����n���i���ʸ��|
		for (int i = 0; i < 10; ++i) {
			for (int j = 0; j < 10; ++j) {
				if (board[i][j] == 4)
					board[i][j] = 0;
			}
		}
	}

	public void canMove(int type) { // ��Ҧ��X�l�M����|
		if (type == 2) {
			for (int i = 0; i < 10; ++i) {
				for (int j = 0; j < 10; ++j) {
					if (board[i][j] == 2) {
						pathCheck(i, j, 3);
					}
				}
			}
		}
		if (type == 3) {
			for (int i = 0; i < 10; ++i) {
				for (int j = 0; j < 10; ++j) {
					if (board[i][j] == 3) {
						pathCheck(i, j, 2);
					}
				}
			}
		}
	}

	public int getChessNumber(int type) {
		int sum = 0;
		for (int i = 0; i < 10; ++i) {
			for (int j = 0; j < 10; ++j) {
				if (board[i][j] == type) {
					sum += 1;
				}
			}
		}
		return sum;
	}

	public int getType(int x, int y) {
		return board[x][y];
	}

	public int isWin() { // �P�_�ӭt
		int p1 = getChessNumber(2);
		int p2 = getChessNumber(3);
		if (p1 > p2)
			return 1;
		else if (p1 < p2)
			return 2;
		else
			return 0;
	}

	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int x, int y, int type) {
		board[x][y] = type;
	}
}
