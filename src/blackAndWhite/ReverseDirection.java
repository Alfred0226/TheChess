package blackAndWhite;

public class ReverseDirection {
	private int[][] board;
	
	public ReverseDirection(int[][] board)
	{
		this.board = board;
	}
	
	public int[][] getBoard()
	{
		return board;
	}
	
	public int reverseRight(int x, int y, int type, int sum) {
		for (int i = x, in = 0; i < 10; ++i) {

			if (board[i][y] == 1 || board[i][y] == 4 || board[i][y] == 0) {
				break;
			} else if (board[i][y] == type) {
				for (int j = x; j < i; ++j) {
					board[j][y] = type;
					++in;
					++sum;

				}
				if (in >= 1) {
					--sum;
					break;
				}

			}
		}
		return sum;
	}

	public int reverseLeft(int x, int y, int type, int sum) {
		for (int i = x, in = 0; i > -1; --i) {

			if (board[i][y] == 1 || board[i][y] == 4 || board[i][y] == 0) {
				break;
			} else if (board[i][y] == type) {
				for (int j = x; j > i; --j) {
					board[j][y] = type;
					++in;
					++sum;

				}
				if (in >= 1) {
					--sum;
					break;
				}
			}
		}
		return sum;
	}

	public int reverseDown(int x, int y, int type, int sum) {
		for (int i = y, in = 0; i < 10; ++i) {

			if (board[x][i] == 1 || board[x][i] == 4 || board[x][i] == 0) {
				break;
			} else if (board[x][i] == type) {
				for (int j = y; j < i; ++j) {

					board[x][j] = type;
					++in;
					++sum;

				}
				if (in >= 1) {
					--sum;
					break;
				}
				;
			}

		}
		return sum;

	}

	public int reverseUp(int x, int y, int type, int sum) {
		for (int i = y, in = 0; i > -1; --i) {

			if (board[x][i] == 1 || board[x][i] == 4 || board[x][i] == 0) {
				break;
			} else if (board[x][i] == type) {
				for (int j = y; j > i; --j) {
					board[x][j] = type;
					++in;
					++sum;

				}
				if (in >= 1) {
					--sum;
					break;
				}
				;
			}
		}
		return sum;
	}

	public int reverseRightAndDown(int x, int y, int type, int sum) {
		for (int i = 1;;) {

			if (x + i > 9 || y + i > 9)
				break;

			if (board[x + i][y + i] == 1 || board[x + i][y + i] == 4 || board[x + i][y + i] == 0) {
				break;
			} else if (board[x + i][y + i] == type) {
				int j = 1;
				while (x + j < x + i && y + j < y + i) {
					board[x + j][y + j] = type;
					++sum;
					++j;
				}
				break;
			}
			++i;
		}
		return sum;
	}

	public int reverseLeftAndUp(int x, int y, int type, int sum) {
		for (int i = 1;;) {
			if (x - i < 0 || y - i < 0)
				break;
			if (board[x - i][y - i] == 1 || board[x - i][y - i] == 4 || board[x - i][y - i] == 0) {
				break;
			} else if (board[x - i][y - i] == type) {
				int j = 1;
				while (x - j > x - i && y - j > y - i) {
					board[x - j][y - j] = type;
					++sum;
					++j;
				}
				break;
			}
			++i;

		}
		return sum;
	}

	public int reverseLeftAndDown(int x, int y, int type, int sum) {
		for (int i = 1;;) {
			if (x - i < 0 || y + i > 9)
				break;
			if (board[x - i][y + i] == 1 || board[x - i][y + i] == 4 || board[x - i][y + i] == 0) {
				break;
			} else if (board[x - i][y + i] == type) {
				int j = 1;
				while (x - j > x - i && y + j < y + i) {
					board[x - j][y + j] = type;
					++sum;
					++j;
				}
				break;
			}
			++i;

		}
		return sum;
	}

	public int reverseRightAndUp(int x, int y, int type, int sum) {
		for (int i = 1;;) {
			if (x + i > 9 || y - i < 0)
				break;
			if (board[x + i][y - i] == 1 || board[x + i][y - i] == 4 || board[x + i][y - i] == 0) {
				break;
			} else if (board[x + i][y - i] == type) {
				int j = 1;
				while (x + j < x + i && y - j > y - i) {
					board[x + j][y - j] = type;
					++sum;
					++j;
				}
				break;
			}
			++i;

		}
		return sum;
	}
}
