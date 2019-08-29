package blackAndWhite;

import java.util.EventListener;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GameBoard extends JFrame implements MouseListener, ChangeListener, Runnable, ActionListener {

	private int stepNumber = 0;
	private boolean musicCheck = false;
	private boolean paintStart = false;
	
	private JLabel round;
	private JLabel count1;
	private JLabel count2;
	private JButton butStart;
	private static JLabel timer;
	
	private Image image;
	private File file;
	private Thread thr;
	private URL cb;
	private File ff;
	private AudioClip aau = null;
	private Rule rule = new Rule();
	private StartPanel startPanel = new StartPanel();
	
	public static void main(String[] args) {// 跑主程式
		GameBoard game = new GameBoard();
		game.setVisible(true);
		Timer time = new Timer(14, 60);
		time.start(timer);
	}

	public GameBoard() {

		JLabel boardTable;
		ImageIcon boardIcon = null;

		setSize(1200, 800);// FRAME大小
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		round = new JLabel();
		round.setSize(200, 100);
		round.setLocation(0, 0);
		add(round);
		round.setFont(new Font("標楷體", Font.BOLD, 50));
		round.setForeground(Color.white);
		round.setVisible(true);

		count1 = new JLabel("");// 記藍棋數量
		count1.setSize(200, 100);
		count1.setLocation(5, 120);
		add(count1);
		count1.setFont(new Font("標楷體", Font.BOLD, 50));
		count1.setForeground(Color.white);
		count1.setVisible(false);
		count1.setBackground(Color.BLACK);

		count2 = new JLabel("");
		count2.setSize(240, 100);
		count2.setLocation(5, 70);
		add(count2);
		count2.setFont(new Font("標楷體", Font.BOLD, 50));
		count2.setForeground(Color.white);
		count2.setVisible(false);
		count2.setBackground(Color.BLACK);

		butStart = startPanel.getStartButton();
		butStart.setActionCommand("music");
		butStart.addActionListener(this);

		add(startPanel);

		timer = new JLabel("timer");// 時間計時器
		timer.setSize(100, 40);
		timer.setLocation(570, 20);
		add(timer);
		timer.setVisible(true);
		timer.setFont(new Font("標楷體", Font.BOLD, 35));
		timer.setForeground(Color.WHITE);

		try {// 棋盤

			boardIcon = new ImageIcon("res/pic/board.jpg");

		} catch (Exception e) {
			e.printStackTrace();
		}
		boardTable = new JLabel(boardIcon);
		boardTable.setSize(700, 700);
		boardTable.setLocation(250, 50);
		add(boardTable);
		boardTable.setVisible(true);
		boardTable.addMouseListener(this);// 把label當整個棋盤

		try {// 背景圖片

			ImageIcon icon = new ImageIcon("res/pic/back.jpg");
			JLabel backPic = new JLabel(icon);
			backPic.setSize(1200, 800);
			backPic.setLocation(0, 0);
			add(backPic);
			backPic.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void actionPerformed(ActionEvent e) {
		startPanel.setVisible(false);
		count1.setVisible(true);
		count2.setVisible(true);
		String cmd = e.getActionCommand();// 加在actionperformed
		if (cmd.equals("music")) {
			paintStart = true;
			Thread thr = new Thread(this);
			thr.start();
		}
		repaint();
	}

	public Image getImage(String url) {
		Image pic = null;
		try {
			pic = ImageIO.read(new File(url));
		} catch (IOException e13) {
			// TODO Auto-generated catch block
			e13.printStackTrace();
		}
		return pic;

	}

	private void drawBufferedImage() {
		image = createImage(this.getWidth(), this.getHeight());
		Graphics g = image.getGraphics();
		g.setColor(Color.yellow);
		g.fillRect(0, 0, image.getWidth(null), image.getHeight(null));
		Image backg = getImage("res/pic/backgroundG.jpg");
		g.drawImage(backg, 0, 0, 1200, 800, this);
		for (int i = 0; i < 10; i++)// 畫出棋子位置
		{
			for (int j = 0; j < 10; j++) {
				switch (rule.getType(j, i))// 換role.gettype
				{
				case 1:
					Image barrier = getImage("res/pic/1.png");
					g.drawImage(barrier, 284 + 63 * j, 149 + 60 * i, 56, 56, this);
					break;
				case 2:
					Image chessA = getImage("res/pic/2.png");
					g.drawImage(chessA, 284 + 63 * j, 149 + 60 * i, 56, 56, this);
					break;
				case 3:
					Image chessB = getImage("res/pic/3.png");
					g.drawImage(chessB, 284 + 63 * j, 149 + 60 * i, 56, 56, this);
					break;

				case 4:
					Image rock = getImage("res/pic/4.png");
					g.drawImage(rock, 284 + 63 * j, 149 + 60 * i, 56, 56, this);
					break;

				default:
					break;
				}

			}
		}
	}

	public void mouseClicked(MouseEvent e) {
		int num = 0;
		int count1num = rule.getChessNumber(2);
		int count2num = rule.getChessNumber(3);
		if (stepNumber % 2 == 0) {
			round.setText("JOJO");
		} else {
			round.setText("Dio");
		}
		count1.setText("JOJO:" + Integer.toString(count1num));
		count2.setText("DIO:" + Integer.toString(count2num));

		thr = new Thread(this);

		thr.start();

		if (34 < e.getX() && e.getX() <= 667 && 30 < e.getY() && e.getX() <= 670) {
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if (32 + 63 * i <= e.getX() && e.getX() < 32 + 63 * (i + 1) && 50 + 60 * j <= e.getY()
							&& e.getY() < 50 + 60 * (j + 1)) {
						if (rule.getType(i, j) == 4) {

							if (stepNumber % 2 == 0) {
								gameProcess(i, j, 2, 3);
							} else if (stepNumber % 2 == 1) {
								gameProcess(i, j, 3, 2);
							}

						} else if (stepNumber == 0) {
							rule.canMove(2);
							repaint();
						}
					}
				}
			}
		}
	}

	public void gameProcess(int i, int j, int thisTerm, int nextTerm) {
		int num = 0;
		rule.clean();
		rule.setBoard(i, j, thisTerm);
		num = rule.reverse(i, j, thisTerm);
		soundEffect(thisTerm, num);
		++stepNumber;
		rule.canMove(nextTerm);
		repaint();
		if (rule.getChessNumber(4) == 0) {
			++stepNumber;
			rule.canMove(thisTerm);
			repaint();
			gameover();
		}
	}

	public void playSound(String url) {
		ff = new File(url);
		try {
			cb = ff.toURL();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (aau != null) {
			aau.stop();
		}
		aau = Applet.newAudioClip(cb);
		aau.play();
	}

	public void soundEffect(int role, int num) {
		if (role == 2) {
			if (num == 1) {
				playSound("res/muz/Converted-IAN-oraora.wav");
				System.out.println("Ora Ora Ora");
			} else if (num == 2) {
				playSound("res/muz/Converted-star.wav");
				System.out.println("Star");
			}
		}
		if (role == 3) {
			if (num == 1) {
				playSound("res/muz/Converted-mudamuda.wav"); // 引号里面的是音乐文件所在的路径
				System.out.println("Muda Muda Muda");
			} else if (num == 2) {
				playSound("res/muz/Converted-IAN-zawarudo.wav"); // 引号里面的是音乐文件所在的路径
				System.out.println("Za warudo");
			}
		}
	}

	public void gameover() {
		if (rule.getChessNumber(4) == 0) {
			System.out.println("game over");
			if (rule.isWin() == 1) {
				JOptionPane.showMessageDialog(this, "Jotaro WIN!!!", "Result", JOptionPane.INFORMATION_MESSAGE);
			} else if (rule.isWin() == 2) {
				JOptionPane.showMessageDialog(this, "Dio WIN!!!", "Result", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "Ko no Dio da!!!", "Result", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
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
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void run() {
		if (musicCheck == false) {
			try {// 此寫法 會是按第二次可以暫停第一次的 但是無法排列順序
				if (file == null) {
					file = new File("res/muz/complete.wav"); // 引?里面的是音?文件所在的路?
					AudioInputStream astr = AudioSystem.getAudioInputStream(file);
					AudioFormat afmt = astr.getFormat();
					DataLine.Info inf = new DataLine.Info(SourceDataLine.class, afmt);
					SourceDataLine sdl = (SourceDataLine) AudioSystem.getLine(inf);
					sdl.open(afmt);
					sdl.start();
					byte[] buf = new byte[65536];
					for (int n = 0; (n = astr.read(buf, 0, buf.length)) > 0;) {
						sdl.write(buf, 0, n);
					}
					sdl.drain();
					sdl.close();
				}
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			} catch (UnsupportedAudioFileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			musicCheck = !musicCheck;
		}
	}

	public void paint(Graphics g) {
		if (paintStart == false) {
			super.paint(g);
		}
		if (paintStart) {
			drawBufferedImage();
			g.drawImage(image, 0, 0, this);
		}
	}
}