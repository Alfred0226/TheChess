package blackAndWhite;

public class FindPath {
	private int[][] board;
	
	public FindPath(int[][] board)
	{
		this.board = board;
	}

	public int[][] getBoard()
	{
		return board;
	}	
	
	public void findPathUp(int x, int y, int type) {
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

	public void findPathDown(int x, int y, int type) {
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

	public void findPathLeft(int x, int y, int type) {
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

	public void findPathRight(int x, int y, int type) {
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

	public void findPathRightAndUp(int x, int y, int type) {
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

	public void findPathRightAndDown(int x, int y, int type) {
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

	public void findPathLeftAndUp(int x, int y, int type) {
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

	public void findPathLeftAndDown(int x, int y, int type) {
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
	public void findPathAll(int x, int y, int type)
	{
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
