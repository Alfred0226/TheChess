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

	private void findPathUp(int x, int y, int type) {
		if (y != 0) {
			if (board[x][y - 1] == type) {
				for (int k = y - 1; k > -1; --k) {
					if (board[x][k] == 0) {
						board[x][k] = 4;
						break;
					} else if (board[x][k] == type) {
						continue;
					} else {
						break;
					}
				}
			}
		}

	}

	private void findPathDown(int x, int y, int type) {
		if (y != 9) {
			if (board[x][y + 1] == type) {
				for (int k = y + 1; k < 10; ++k) {
					if (board[x][k] == 0) {
						board[x][k] = 4;
						break;
					} else if (board[x][k] == type) {
						continue;
					} else {
						break;
					}
				}
			}
		}
	}

	private void findPathLeft(int x, int y, int type) {
		if (x != 0) {
			if (board[x - 1][y] == type) {
				for (int k = x - 1; k > -1; --k) {
					if (board[k][y] == 0) {
						board[k][y] = 4;
						break;
					} else if (board[k][y] == type) {
						continue;
					} else {
						break;
					}
				}
			}
		}
	}

	private void findPathRight(int x, int y, int type) {
		if (x != 9) {
			if (board[x + 1][y] == type) {
				for (int k = x + 1; k < 10; ++k) {
					if (board[k][y] == 0) {
						board[k][y] = 4;
						break;
					} else if (board[k][y] == type) {
						continue;
					} else {
						break;
					}
				}
			}
		}
	}

	private void findPathRightAndUp(int x, int y, int type) {
		if (x != 9 && y != 0) {
			if (board[x + 1][y - 1] == type) {
				for (int k = 1;; ++k) {
					if (x + k > 9 || y - k < 0)
						break;
					if (board[x + k][y - k] == 0) {
						board[x + k][y - k] = 4;
						break;
					} else if (board[x + k][y - k] == type) {
						continue;
					} else {
						break;
					}

				}
			}
		}
	}

	private void findPathRightAndDown(int x, int y, int type) {
		if (x != 9 && y != 9) {
			if (board[x + 1][y + 1] == type) {
				for (int k = 1;; ++k) {
					if (x + k > 9 || y + k > 9)
						break;
					if (board[x + k][y + k] == 0) {
						board[x + k][y + k] = 4;
						break;
					} else if (board[x + k][y + k] == type) {
						continue;
					} else {
						break;
					}

				}
			}
		}
	}

	private void findPathLeftAndUp(int x, int y, int type) {
		if (x != 0 && y != 0) {
			if (board[x - 1][y - 1] == type) {
				for (int k = 1;; ++k) {
					if (x - k < 0 || y - k < 0)
						break;
					if (board[x - k][y - k] == 0) {
						board[x - k][y - k] = 4;
						break;
					} else if (board[x - k][y - k] == type) {
						continue;
					} else {
						break;
					}

				}
			}
		}

	}

	private void findPathLeftAndDown(int x, int y, int type) {
		if (x != 0 && y != 9) {
			if (board[x - 1][y + 1] == type) {
				for (int k = 1;; ++k) {
					if (x - k < 0 || y + k > 9)
						break;
					if (board[x - k][y + k] == 0) {
						board[x - k][y + k] = 4;
						break;
					} else if (board[x - k][y + k] == type) {
						continue;
					} else {
						break;
					}
				}
			}
		}
	}

	private void pathCheck(int x, int y, int type) { // ��8�Ӥ�V�M��i���ʸ��|
		for (int i = 0; i < 10; ++i) {
			for (int j = 0; j < 10; ++j) {
				findPathUp(x, y, type);
				findPathDown(x, y, type);
				findPathLeft(x, y, type);
				findPathRight(x, y, type);
				findPathLeftAndUp(x, y, type);
				findPathLeftAndDown(x, y, type);
				findPathRightAndUp(x, y, type);
				findPathRightAndDown(x, y, type);
			}
		}
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
