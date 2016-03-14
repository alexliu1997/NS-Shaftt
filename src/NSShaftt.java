import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import java.io.*;

public class NSShaftt extends JPanel implements ActionListener, KeyListener {
	private JLabel lblLives, lblLevel, lblDifficulty2, lblRecord2;
	private JButton btnHelp, btnPlay, btnExit, btnClearRecord;
	private int level = 0, lives = 12, seconds = 0, bestLevel = 0, s = 0,
			platformPlayerIsOn = -1;
	private JMenuBar menuBar;
	private JMenu menu;
	private JRadioButtonMenuItem rbEasy, rbMedium, rbHard;
	private JCheckBoxMenuItem cbSpring, cbTemp, cbRolling;
	private Player p;
	private boolean start = false, moveRight = false, moveLeft = false,
			pause = false;
	private Timer gameTimer, platformTimer;
	private Platform[] platforms;
	private Random rnd;
	private TopSpike ts;
	private File highscores = new File("highscores.txt");
	private String name = "", line = "";

	public static void main(String[] args) {
		new NSShaftt();
	}

	public NSShaftt() {
		// lives
		lblLives = new JLabel();
		lblLives.setPreferredSize(new Dimension(140, 85));
		lblLives.setHorizontalAlignment(SwingConstants.CENTER);
		lblLives.setIcon(new ImageIcon("images\\lives" + lives + ".png"));

		// level
		lblLevel = new JLabel();
		Font f = new Font("Times New Roman", Font.BOLD, 36);
		lblLevel.setFont(f);
		lblLevel.setForeground(Color.YELLOW);
		lblLevel.setText("Level " + level);
		lblLevel.setPreferredSize(new Dimension(290, 30));
		lblLevel.setHorizontalAlignment(SwingConstants.CENTER);

		// title
		JLabel lblTitle = new JLabel();
		Font f2 = new Font("Britannic Bold", Font.PLAIN, 36);
		lblTitle.setFont(f2);
		Color c1 = new Color(122, 122, 122);
		lblTitle.setForeground(c1);
		lblTitle.setText("NS-Shaft");
		lblTitle.setPreferredSize(new Dimension(190, 30));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

		// difficulty
		JLabel lblDifficulty1 = new JLabel();
		Font f3 = new Font("Britannic Bold", Font.PLAIN, 24);
		lblDifficulty1.setFont(f3);
		lblDifficulty1.setForeground(c1);
		lblDifficulty1.setText("DIFFICULTY:");
		lblDifficulty1.setPreferredSize(new Dimension(190, 25));
		lblDifficulty1.setHorizontalAlignment(SwingConstants.CENTER);
		lblDifficulty2 = new JLabel();
		lblDifficulty2.setFont(f3);
		lblDifficulty2.setForeground(c1);
		lblDifficulty2.setPreferredSize(new Dimension(190, 25));
		lblDifficulty2.setText("Medium");

		// highest record
		JLabel lblRecord1 = new JLabel();
		lblRecord1.setFont(f3);
		lblRecord1.setForeground(c1);
		lblRecord1.setPreferredSize(new Dimension(190, 25));
		lblRecord1.setText("RECORD:");
		lblRecord1.setHorizontalAlignment(SwingConstants.CENTER);

		lblRecord2 = new JLabel();
		lblRecord2.setText("0");
		lblRecord2.setFont(f3);
		lblRecord2.setForeground(c1);
		lblRecord2.setPreferredSize(new Dimension(190, 25));
		lblRecord2.setHorizontalAlignment(SwingConstants.CENTER);
		// uses file to read the highest record that was saved before
		try {
			BufferedReader in = new BufferedReader(new FileReader(highscores));
			line = in.readLine();
			bestLevel = Integer.parseInt(line);
			line = in.readLine();
			name = line;
			lblRecord2.setText(String.valueOf(bestLevel) + " by " + name);
			in.close();
		} catch (IOException e) {
			bestLevel = 0;
			name = "";
		}
		// JButtons
		btnClearRecord = new JButton("CLEAR RECORD");
		btnClearRecord.setFocusable(false);
		btnClearRecord.addActionListener(this);

		btnHelp = new JButton("HELP");
		btnHelp.setFocusable(false);
		btnHelp.addActionListener(this);

		btnPlay = new JButton("PLAY");
		btnPlay.setFocusable(false);
		btnPlay.addActionListener(this);

		btnExit = new JButton("EXIT");
		btnExit.setFocusable(false);
		btnExit.addActionListener(this);

		// JMenu
		menuBar = new JMenuBar();
		menu = new JMenu("Options");
		menuBar.add(menu);

		// Radio buttons
		ButtonGroup difficulty = new ButtonGroup();
		rbEasy = new JRadioButtonMenuItem("Easy");
		difficulty.add(rbEasy);
		rbEasy.addActionListener(this);
		menu.add(rbEasy);
		rbMedium = new JRadioButtonMenuItem("Medium");
		rbMedium.setSelected(true);
		rbMedium.addActionListener(this);
		difficulty.add(rbMedium);
		menu.add(rbMedium);
		rbHard = new JRadioButtonMenuItem("Hard");
		rbHard.addActionListener(this);
		difficulty.add(rbHard);
		menu.add(rbHard);

		// checkboxes
		cbSpring = new JCheckBoxMenuItem("Spring platform");
		cbSpring.setSelected(true);
		cbSpring.addActionListener(this);
		menu.add(cbSpring);
		cbTemp = new JCheckBoxMenuItem("Temp platform");
		cbTemp.setSelected(true);
		cbTemp.addActionListener(this);
		menu.add(cbTemp);
		cbRolling = new JCheckBoxMenuItem("Rolling platform");
		cbRolling.setSelected(true);
		cbRolling.addActionListener(this);
		menu.add(cbRolling);

		gameTimer = new Timer(3, this);
		rnd = new Random();
		ts = new TopSpike();

		// panels
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 20));
		topPanel.setBackground(Color.BLACK);
		topPanel.add(lblLives);
		topPanel.add(lblLevel);
		topPanel.add(lblTitle);
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		rightPanel.setBackground(Color.BLACK);
		rightPanel.add(lblDifficulty1);
		rightPanel.add(Box.createVerticalStrut(10));
		rightPanel.add(lblDifficulty2);
		rightPanel.add(Box.createVerticalStrut(50));
		rightPanel.add(lblRecord1);
		rightPanel.add(Box.createVerticalStrut(10));
		rightPanel.add(lblRecord2);
		rightPanel.add(Box.createVerticalStrut(10));
		rightPanel.add(btnClearRecord);
		rightPanel.add(Box.createVerticalStrut(70));
		rightPanel.add(btnHelp);
		rightPanel.add(Box.createVerticalStrut(10));
		rightPanel.add(btnPlay);
		rightPanel.add(Box.createVerticalStrut(10));
		rightPanel.add(btnExit);

		setBackground(Color.black);
		setFocusable(true);
		setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
		addKeyListener(this);
		JFrame frame = new JFrame();
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(rightPanel, BorderLayout.EAST);
		frame.add(this, BorderLayout.CENTER);
		frame.setTitle("NS-Shaftt");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(810, 610);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setJMenuBar(menuBar);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		ts.draw(g2);
		if (start) {
			p.draw(g2);
			for (int i = 0; i < platforms.length; i++) {
				platforms[i].draw(g2);
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == btnHelp) {
			if (start) {
				// help is pressed in the middle of the game
				if (!pause) {
					pause = true;
					gameTimer.stop();
					platformTimer.stop();
					JOptionPane
							.showMessageDialog(
									null,
									"Use left and right arrow keys to control the player.\nTry to get as deep in the cave as possible.\nTo continue, press the help button.",
									"NS-Shaftt",
									JOptionPane.INFORMATION_MESSAGE);
				} else {
					pause = false;
					gameTimer.start();
					platformTimer.start();
				}
			} else {
				// the game hasn't started and the pause button is pressed
				JOptionPane
						.showMessageDialog(
								null,
								"Use left and right arrow keys to control the player.\nTry to get as deep in the cave as possible.\nYou can change settings by pressing the options button on the top left corner.\n If you want to pause the game, press the help button or press P.",
								"NS-Shaftt", JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (arg0.getSource() == btnClearRecord) {
			int option = JOptionPane.showConfirmDialog(null,
					"Are you sure you want to clear the record?", "NS-Shaftt",
					JOptionPane.YES_NO_OPTION);
			if (option == 0) {
				// clearing the record
				if (highscores.delete()) {
					JOptionPane.showMessageDialog(null, "File deleted!",
							"NS-Shaftt", JOptionPane.INFORMATION_MESSAGE);
					highscores = new File("highscores.txt");
					bestLevel = 0;
					name = "";
					lblRecord2.setText("0");
				} else {
					JOptionPane.showMessageDialog(null, "File not deleted!",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if (arg0.getSource() == btnPlay) {
			// start the game
			s = 0;
			moveRight = false;
			moveLeft = false;
			btnPlay.setEnabled(false);
			start = true;
			p = new Player();
			// Initialise platforms
			platforms = new Platform[7];
			for (int i = 0; i < platforms.length; i++) {
				int n = rnd.nextInt(5);
				if (n == 0) {
					platforms[i] = new NormalPlatform();
				} else if (n == 1) {
					platforms[i] = new SpikePlatform();
				} else if (n == 2) {
					// check if the checkbox is selected. If not, a normal
					// platform or spike platform is generated
					if (cbRolling.isSelected()) {
						platforms[i] = new RollingPlatform();
					} else {
						int m = rnd.nextInt(2);
						if (m == 0) {
							platforms[i] = new NormalPlatform();
						} else {
							platforms[i] = new SpikePlatform();
						}
					}
				} else if (n == 3) {
					if (cbSpring.isSelected()) {
						platforms[i] = new SpringPlatform();
					} else {
						int m = rnd.nextInt(2);
						if (m == 0) {
							platforms[i] = new NormalPlatform();
						} else {
							platforms[i] = new SpikePlatform();
						}
					}
				} else if (n == 4) {
					if (cbTemp.isSelected()) {
						platforms[i] = new TempPlatform();
					} else {
						int m = rnd.nextInt(2);
						if (m == 0) {
							platforms[i] = new NormalPlatform();
						} else {
							platforms[i] = new SpikePlatform();
						}
					}
				}
				// all platforms are on the screen initially
				platforms[i].setY(45 + i * 60);
			}
			// the platform which player initially stands on
			platforms[3] = new NormalPlatform();
			platforms[3].setLocation(260, 225);
			gameTimer.restart();
			seconds = 0;
			level = 0;
			lives = 12;
			lblLives.setIcon(new ImageIcon("images\\lives" + lives + ".png"));
			lblLevel.setText("Level " + level);
			menu.setEnabled(false);
			repaint();
		} else if (arg0.getSource() == btnExit) {
			int option = JOptionPane.showConfirmDialog(null,
					"Are you sure you want to exit?", "NS-Shaftt",
					JOptionPane.YES_NO_OPTION);
			if (option == 0) {
				System.exit(0);
			}
		} else if (rbEasy.isSelected()) {
			lblDifficulty2.setText("Easy");
		} else if (rbMedium.isSelected()) {
			lblDifficulty2.setText("Medium");
		} else if (rbHard.isSelected()) {
			lblDifficulty2.setText("Hard");
		}
		if (arg0.getSource() == gameTimer) {
			if (moveRight) {
				int x = p.getX();
				if (x < 605 - p.getWidth()) {
					x++;
					p.setX(x);
				}
			} else if (moveLeft) {
				int x = p.getX();
				if (x > 5) {
					x--;
					p.setX(x);
				}
			}
			seconds++;
			if (seconds == 100) {
				// the difficulty can be changed by pressing different radio
				// buttons
				if (rbEasy.isSelected()) {
					platformTimer = new Timer(15, this);
				} else if (rbMedium.isSelected()) {
					platformTimer = new Timer(10, this);
				} else if (rbHard.isSelected()) {
					platformTimer = new Timer(8, this);
				}
				platformTimer.restart();
			}
			if (seconds % 1000 == 0 && seconds != 0) {
				// when an amount of time has passed, the level will increase
				level++;
				lblLevel.setText("Level " + level);
			}
			// if player does not intersect any platforms, he moves down
			if (!(p.getRectBottom().intersects(platforms[0].getRect()))
					&& !(p.getRectBottom().intersects(platforms[1].getRect()))
					&& !(p.getRectBottom().intersects(platforms[2].getRect()))
					&& !(p.getRectBottom().intersects(platforms[3].getRect()))
					&& !(p.getRectBottom().intersects(platforms[4].getRect()))
					&& !(p.getRectBottom().intersects(platforms[5].getRect()))
					&& !(p.getRectBottom().intersects(platforms[6].getRect()))) {
				p.move();
			}
			// if player intersects any platforms, he moves up
			if (p.getRectBottom().intersects(platforms[0].getRect())
					|| p.getRectBottom().intersects(platforms[1].getRect())
					|| p.getRectBottom().intersects(platforms[2].getRect())
					|| p.getRectBottom().intersects(platforms[3].getRect())
					|| p.getRectBottom().intersects(platforms[4].getRect())
					|| p.getRectBottom().intersects(platforms[5].getRect())
					|| p.getRectBottom().intersects(platforms[6].getRect())) {
				if (p.getY() > 0) {
					p.moveOpposite();
				}
			}
		}
		if (arg0.getSource() == platformTimer) {
			for (int i = 0; i < platforms.length; i++) {
				platforms[i].move();
				checkCollision();
				// generate a new platform when it leaves the screen
				if (platforms[i].getY() == 0 - platforms[i].getHeight()) {
					int n = rnd.nextInt(5);
					if (n == 0) {
						platforms[i] = new NormalPlatform();
					} else if (n == 1) {
						platforms[i] = new SpikePlatform();
					} else if (n == 2) {
						if (cbRolling.isSelected()) {
							platforms[i] = new RollingPlatform();
						} else {
							int m = rnd.nextInt(2);
							if (m == 0) {
								platforms[i] = new NormalPlatform();
							} else {
								platforms[i] = new SpikePlatform();
							}
						}
					} else if (n == 3) {
						if (cbSpring.isSelected()) {
							platforms[i] = new SpringPlatform();
						} else {
							int m = rnd.nextInt(2);
							if (m == 0) {
								platforms[i] = new NormalPlatform();
							} else {
								platforms[i] = new SpikePlatform();
							}
						}
					} else if (n == 4) {
						if (cbTemp.isSelected()) {
							platforms[i] = new TempPlatform();
						} else {
							int m = rnd.nextInt(2);
							if (m == 0) {
								platforms[i] = new NormalPlatform();
							} else {
								platforms[i] = new SpikePlatform();
							}
						}
					}
				}
			}
		}
		repaint();
	}

	@Override
	// I use key pressed and key released to control the movement of player as
	// suggested by Taiki
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (start) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT && !pause) {
				p.setDirection(Player.WEST);
				moveLeft = true;
			}
			// User clicks the right arrow key
			else if (e.getKeyCode() == KeyEvent.VK_RIGHT && !pause) {
				p.setDirection(Player.EAST);
				moveRight = true;
			} else if (e.getKeyCode() == KeyEvent.VK_P) {
				// if player presses "p" during game, the game pauses
				if (!pause) {
					pause = true;
					gameTimer.stop();
					platformTimer.stop();
				} else {
					pause = false;
					gameTimer.start();
					platformTimer.start();
				}
			}
			repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (start) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				moveLeft = false;
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				moveRight = false;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void checkCollision() {
		// player intersects with spikes at the top
		if (p.getRectTop().intersects(ts.getRect())) {
			lives -= 5;
			lblLives.setIcon(new ImageIcon("images\\lives" + lives + ".png"));
			if (lives <= 0) {
				lblLives.setIcon(new ImageIcon("images\\lives0.png"));
				endGame();
			}
			p.setY(p.getY() + 40);
			btnPlay.setEnabled(true);
		}
		// player intersects with platforms
		for (int i = 0; i < platforms.length; i++) {
			if (platforms[i].getRect().intersects(p.getRectBottom())) {
				if (platforms[i].getType().equals("normal") && lives < 12) {
					// player adds 1 life when intersecting with all platforms
					// except spike platforms
					// make sure the life is only added by 1
					if (platformPlayerIsOn != i) {
						lives++;
					}
					platformPlayerIsOn = i;
					lblLives.setIcon(new ImageIcon("images\\lives" + lives
							+ ".png"));
				} else if (platforms[i].getType().equals("spike")) {
					// player minuses 5 lives when intersecting with spike
					// platforms
					if (platformPlayerIsOn != i) {
						p.isInjured = true;
						s = seconds + 40;
						lives -= 5;
						lblLives.setIcon(new ImageIcon("images\\lives" + lives
								+ ".png"));
						if (lives <= 0) {
							lblLives.setIcon(new ImageIcon("images\\lives0.png"));
							endGame();
						}
						platformPlayerIsOn = i;

					}
					// player changes image when he intersects with spike
					// platform
					if (s - seconds < 10) {
						p.isInjured = false;
					}
				} else if (platforms[i].getType().equals("rolling")) {
					if (lives < 12) {
						if (platformPlayerIsOn != i) {
							lives++;
						}
						platformPlayerIsOn = i;
						lblLives.setIcon(new ImageIcon("images\\lives" + lives
								+ ".png"));
					}
					if (seconds % 7 == 0) {
						// player moves when he is on a rolling platform
						if (platforms[i].getDirection() == 0) {
							p.setX(p.getX() - 1);
						} else {
							p.setX(p.getX() + 1);
						}
					}
				} else if (platforms[i].getType().equals("spring")) {
					if (lives < 12) {
						if (platformPlayerIsOn != i) {
							lives++;
						}
						platformPlayerIsOn = i;
						lblLives.setIcon(new ImageIcon("images\\lives" + lives
								+ ".png"));
					}
					// player jumps
					p.setY(p.getY() - 50);
				} else if (platforms[i].getType().equals("temp")) {
					if (platformPlayerIsOn != i) {
						s = seconds + 70;
						platformPlayerIsOn = i;
					}
					if (lives < 12) {
						if (platformPlayerIsOn != i) {
							lives++;
						}

						lblLives.setIcon(new ImageIcon("images\\lives" + lives
								+ ".png"));
					}
					// player moves down a little and the temporary platform
					// disappears
					if (s - seconds < 10) {
						p.setY(p.getY() + 5);
						platforms[i].imgPlatform = new ImageIcon("");
					}
				}
			}
		}
		if (p.getY() >= 430 && p.getY() <= 450) {
			endGame();
		}
	}

	public void endGame() {
		start = false;
		p.setY(470);
		for (int j = 0; j < platforms.length; j++) {
			platforms[j].setY(430);
		}
		platformTimer.stop();
		gameTimer.stop();
		if (level == 1) {
			JOptionPane.showMessageDialog(null, "You have completed " + level
					+ " level.", "NS-Shaftt", JOptionPane.INFORMATION_MESSAGE);
		} else if (level != 1) {
			JOptionPane.showMessageDialog(null, "You have completed " + level
					+ " levels.", "NS-Shaftt", JOptionPane.INFORMATION_MESSAGE);
		}
		// if player breaks the record
		if (level > bestLevel) {
			if (level != 1) {
				JOptionPane.showMessageDialog(null,
						"Congratulations! You have set a new record of "
								+ level + " levels!", "NS-Shaftt",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null,
						"Congratulations! You have set a new record of "
								+ level + " level!", "NS-Shaftt",
						JOptionPane.INFORMATION_MESSAGE);
			}
			bestLevel = level;
			// writing the new record and player's name into the file
			try {
				BufferedWriter out = new BufferedWriter(new FileWriter(
						highscores));
				name = JOptionPane.showInputDialog("Please enter your name: ");
				if (name == null) {
					name = "Anonymous";
				}
				if (name.length() == 0) {
					name = "Anonymous";
				}
				out.write(String.valueOf(bestLevel));
				out.newLine();
				out.write(name);
				out.close();
				JOptionPane.showMessageDialog(null,
						"Data has been written to the file!", "Finished!",
						JOptionPane.INFORMATION_MESSAGE);
				lblRecord2.setText(String.valueOf(bestLevel) + " by " + name);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage() + "!",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		btnPlay.setEnabled(true);
		menu.setEnabled(true);
	}
}