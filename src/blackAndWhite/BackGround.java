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

public class BackGround extends JFrame implements MouseListener, ChangeListener, Runnable, ActionListener {
	private int limitSec = 60;
	private int limitMin = 14;
	private int stepNumber = 0;
	private Role role = new Role();
	public JLabel timer;
	public Image image;
	private JPanel startPanel;
	private JLabel title;
	private JButton butStart;
	private ImageIcon startIcon;
	private JLabel Start;
	private boolean musicCheck = false;
	private File f;
	public boolean za = false;

	private URL cb;
	private File ff;
	private AudioClip aau1 = null;
	public JLabel countLab;
	public Thread thr;
	public JLabel count1;
	public JLabel count2;
	private boolean paintStart = false;

	public static void main(String[] args) {// 跑主程式
		BackGround frame = new BackGround();
		frame.setVisible(true);
		while (frame.limitMin >= 0) {

			if (frame.limitSec > 0) {

				frame.limitSec--;

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else if (frame.limitSec == 0) {
				frame.limitSec = 59;
				frame.limitMin--;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			frame.timer.setText(Integer.toString(frame.limitMin) + ":" + Integer.toString(frame.limitSec));
		}
	}

	public BackGround() {

		JLabel lb1;
		JPanel panel;
		JButton but1;
		JButton but2;

		ImageIcon chess11 = null;
		ImageIcon icon1 = null;
		JLabel countDio;
		JLabel countJo;
		setSize(1200, 800);// FRAME大小
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		countLab=new JLabel();
		countLab.setSize(200,100);
		countLab.setLocation(0,0);
		add(countLab);
		countLab.setFont(new Font("標楷體", Font.BOLD, 50));
		countLab.setForeground(Color.white);
		countLab.setVisible(true);
		startPanel = new JPanel();

		startPanel.setSize(1200, 800);
		startPanel.setLocation(0, 0);
		startPanel.setVisible(true);
		startPanel.setLayout(null);

		title = new JLabel("『ZA CHESS』");
		title.setSize(1200, 200);
		title.setLocation(0, 100);
		startPanel.add(title);
		title.setFont(new Font("標楷體", Font.BOLD, 165));
		title.setForeground(Color.white);

		count1 = new JLabel("suck");// 記藍棋數量
		count1.setSize(200, 100);
		count1.setLocation(5, 120);
		add(count1);
		count1.setFont(new Font("標楷體", Font.BOLD, 50));
		count1.setForeground(Color.white);
		count1.setVisible(false);
		count1.setBackground(Color.BLACK);

		count2 = new JLabel("suck u ");
		count2.setSize(240, 100);
		count2.setLocation(5, 70);
		add(count2);
		count2.setFont(new Font("標楷體", Font.BOLD, 50));
		count2.setForeground(Color.white);
		count2.setVisible(false);
		count2.setBackground(Color.BLACK);

		butStart = new JButton("GameStart");
		butStart.setSize(200, 100);
		butStart.setLocation(500, 360);
		startPanel.add(butStart);
		butStart.setActionCommand("music");
		butStart.addActionListener(this);

		try {// 開始畫面

			startIcon = new ImageIcon("E:\\eclipseWorkSpace\\BlackAndWhite2\\src\\JO\\back.jpg");

		} catch (Exception e) {
			e.printStackTrace();
		}
		Start = new JLabel(startIcon);
		Start.setSize(1200, 800);
		Start.setLocation(0, 0);
		startPanel.add(Start);
		Start.setVisible(true);

		add(startPanel);

		timer = new JLabel("timer");// 時間計時器
		timer.setSize(100, 40);
		timer.setLocation(570, 20);
		add(timer);
		timer.setVisible(true);
		timer.setFont(new Font("標楷體", Font.BOLD, 35));
		timer.setForeground(Color.WHITE);

	

		countJo = new JLabel("Score");// 左方計分表
		countJo.setSize(100, 40);
		countJo.setLocation(1000, 40);
		add(countJo);
		countJo.setVisible(true);
		countJo.setFont(new Font("標楷體", Font.BOLD, 35));
		countJo.setForeground(Color.white);

		try {// 棋盤

			icon1 = new ImageIcon("E:\\eclipseWorkSpace\\BlackAndWhite2\\src\\JO\\board.jpg");

		} catch (Exception e) {
			e.printStackTrace();
		}
		lb1 = new JLabel(icon1);
		lb1.setSize(700, 700);
		lb1.setLocation(250, 50);
		add(lb1);
		lb1.setVisible(true);
		lb1.addMouseListener(this);// 把label當整個棋盤

		try {// 背景圖片

			ImageIcon icon = new ImageIcon("E:\\eclipseWorkSpace\\BlackAndWhite2\\src\\JO/backgroundG.jpg");
			JLabel lb = new JLabel(icon);
			lb.setSize(1200, 800);
			lb.setLocation(0, 0);
			add(lb);
			lb.setVisible(true);
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
			za = true;
		}
		repaint();
	}

	private void drawBufferedImage() {
		image = createImage(this.getWidth(), this.getHeight());
		Graphics g = image.getGraphics();
		g.setColor(Color.yellow);
		g.fillRect(0, 0, image.getWidth(null), image.getHeight(null));
		Image backg = null;
		try {
			backg = ImageIO.read(new File("E:\\eclipseWorkSpace\\BlackAndWhite2\\src\\JO\\backgroundG.jpg"));
		} catch (IOException e13) {
			// TODO Auto-generated catch block
			e13.printStackTrace();
		}
		g.drawImage(backg, 0, 0, 1200, 800, this);
		for (int i = 0; i < 10; i++)// 畫出棋子位置
		{
			for (int j = 0; j < 10; j++) {
				switch (role.getType(j, i))// 換role.gettype
				{
				case 1:
					Image shit = null;
					try {
						shit = ImageIO.read(new File("E:\\eclipseWorkSpace\\BlackAndWhite2\\src\\JO\\1.png"));
					} catch (IOException e13) {
						// TODO Auto-generated catch block
						e13.printStackTrace();
					}
					g.drawImage(shit, 284 + 63 * j, 149 + 60 * i, 56, 56, this);

					break;
				case 2:
					Image kingB = null;
					try {
						kingB = ImageIO.read(new File("E:\\eclipseWorkSpace\\BlackAndWhite2\\src\\JO\\2.png"));
					} catch (IOException e14) {
						// TODO Auto-generated catch block
						e14.printStackTrace();
					}
//                   image = ImageIO.read(new File("example.jpg"));
					g.drawImage(kingB, 284 + 63 * j, 149 + 60 * i, 56, 56, this);
					break;
				case 3:
					Image guardB = null;
					try {
						guardB = ImageIO.read(new File("E:\\eclipseWorkSpace\\BlackAndWhite2\\src\\JO\\3.png"));
					} catch (IOException e13) {
						// TODO Auto-generated catch block
						e13.printStackTrace();
					}
					g.drawImage(guardB, 284 + 63 * j, 149 + 60 * i, 56, 56, this);
					break;

				case 4:
					Image rock = null;

					try {
						rock = ImageIO.read(new File("E:\\eclipseWorkSpace\\BlackAndWhite2\\src\\JO\\4.png"));

					} catch (IOException e13) {
						// TODO Auto-generated catch block
						e13.printStackTrace();
					}
					g.drawImage(rock, 284 + 63 * j, 149 + 60 * i, 56, 56, this);

					break;

				default:
					break;
				}

			}
		}
	}

	@SuppressWarnings("deprecation")
	public void mouseClicked(MouseEvent e) {
		int num = 0;
		int count1num = role.getChessNumber(2);
		int count2num = role.getChessNumber(3);
		if(stepNumber%2==0) {
			countLab.setText("JOJO");
		}
		else {
			countLab.setText("Dio");
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
						if (role.getType(i, j) == 4) {
							
							if (stepNumber % 2 == 0) {
								role.clean();
								role.setBoard(i, j, 2);
								num = role.reverse(i, j, 2);
								if (num == 1) {
									ff = new File("E:\\eclipseWorkSpace\\BlackAndWhite2\\src\\new\\new\\Converted-IAN-oraora.wav"); // 引号里面的是音乐文件所在的路径
									try {
										cb = ff.toURL();
									} catch (MalformedURLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									if (aau1 != null) {
										aau1.stop();
									}
									aau1 = Applet.newAudioClip(cb);
									aau1.play();
									System.out.println("Ora Ora Ora");
								}
								else if (num == 2) {
									ff = new File("E:\\eclipseWorkSpace\\BlackAndWhite2\\src\\new\\new\\Converted-star.wav"); // 引号里面的是音乐文件所在的路径
									try {
										cb = ff.toURL();
									} catch (MalformedURLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									if (aau1 != null) {
										aau1.stop();
									}
									aau1 = Applet.newAudioClip(cb);
									aau1.play();
									System.out.println("Star");
								}
								++stepNumber;
								role.canMove(3);
								repaint();
								if (role.getChessNumber(4) == 0) {
									++stepNumber;
									role.canMove(2);
									repaint();
									
									if (role.getChessNumber(4) == 0) {
										if (role.isWin() == 1) {
											JOptionPane.showMessageDialog(this, "Jotaro WIN!!!", "Result",
													JOptionPane.INFORMATION_MESSAGE);
										} else if (role.isWin() == 2) {
											JOptionPane.showMessageDialog(this, "Dio WIN!!!", "Result",
													JOptionPane.INFORMATION_MESSAGE);
										} else {
											JOptionPane.showMessageDialog(this, "Ko no Dio da!!!", "Result",
													JOptionPane.INFORMATION_MESSAGE);
										}
									}
								}

							} else if (stepNumber % 2 == 1) {
								role.clean();
								role.setBoard(i, j, 3);
								num = role.reverse(i, j, 3);
								if (num == 1) {
									ff = new File("E:\\eclipseWorkSpace\\BlackAndWhite2\\src\\new\\new\\Converted-mudamuda.wav"); // 引号里面的是音乐文件所在的路径
									try {
										cb = ff.toURL();
									} catch (MalformedURLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									if (aau1 != null) {
										aau1.stop();
									}
									aau1 = Applet.newAudioClip(cb);
									aau1.play();
									System.out.println("Muda Muda Muda");
								}
								else if (num == 2) {
									ff = new File("E:\\eclipseWorkSpace\\BlackAndWhite2\\src\\new\\new\\Converted-IAN-zawarudo.wav"); // 引号里面的是音乐文件所在的路径
									try {
										cb = ff.toURL();
									} catch (MalformedURLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									if (aau1 != null) {
										aau1.stop();
									}
									aau1 = Applet.newAudioClip(cb);
									aau1.play();
									System.out.println("Za warudo");
								}
								role.canMove(2);
								++stepNumber;
								repaint();

								if (role.getChessNumber(4) == 0) {
									++stepNumber;
									role.canMove(3);
									repaint();
									if (role.getChessNumber(4) == 0) {
										if (role.isWin() == 1) {
											JOptionPane.showMessageDialog(this, "Jotaro WIN!!!", "Result",
													JOptionPane.INFORMATION_MESSAGE);
										} else if (role.isWin() == 2) {
											JOptionPane.showMessageDialog(this, "Dio WIN!!!", "Result",
													JOptionPane.INFORMATION_MESSAGE);
										} else {
											JOptionPane.showMessageDialog(this, "Ko no Dio da!!!", "Result",
													JOptionPane.INFORMATION_MESSAGE);
										}

									}

								}
							}

						} else if (stepNumber == 0) {
							role.canMove(2);
							repaint();

						}
					}
				}
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

				if (f == null) {
					f = new File("E:\\eclipseWorkSpace\\BlackAndWhite2\\src\\complete.wav"); // 引?里面的是音?文件所在的路?
					AudioInputStream astr = AudioSystem.getAudioInputStream(f);
					AudioFormat afmt = astr.getFormat();
					DataLine.Info inf = new DataLine.Info(SourceDataLine.class, afmt);
					SourceDataLine l = (SourceDataLine) AudioSystem.getLine(inf);
					l.open(afmt);
					l.start();
					byte[] buf = new byte[65536];
					for (int n = 0; (n = astr.read(buf, 0, buf.length)) > 0;) {
						l.write(buf, 0, n);
					}
					l.drain();
					l.close();

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