// JFrame for game

package ui;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javafx.scene.media.AudioClip;
import java.awt.Toolkit;
import java.awt.CardLayout;

import window.ClosableWindow;
import window.ResizableComponent;
import game.SaveFile;
import game.LevelResources;
import utils.SoundUtils;
import ui.MenuPanel;
import ui.HelpPanel;
import ui.GamePanel;

public class AdBlockerFrame extends JFrame implements ClosableWindow.Listener, ResizableComponent.Listener
{

	// Frame Constants
	private  String title = "Ad Blocker";
	private String subtitle = "The Game";
	private int width;
	private int height;

	// GUI Items
	private CardLayout cardLayout;
	private MenuPanel menuPanel;
	private HelpPanel helpPanel;
	private GamePanel gamePanel;

	// Sound Items
	private AudioClip music;

	public AdBlockerFrame()
	{
		super();
		setSystemLookAndFeel();
		setTitle(title);
		resized();
		
		cardLayout = new CardLayout();
		getContentPane().setLayout(cardLayout);

		ClosableWindow cw = new ClosableWindow(this);
		addWindowListener(cw);

		ResizableComponent rs = new ResizableComponent(this);
		addComponentListener(rs);

		System.out.println("Opening Window");
		menuPanel = new MenuPanel(title, subtitle, width, height, this);
		helpPanel = new HelpPanel(title, subtitle, width, height, this);
		add(menuPanel, "menuPanel");
		add(helpPanel, "helpPanel");

		playBackgroundMusic();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setVisible(true);
	}

	private void setSystemLookAndFeel()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
			System.out.println("Warning: Couldn't change theme, reverting to default");
		}
	}

	public void menu()
	{
		System.out.println("Opening Main Menu");
		cardLayout.show(getContentPane(), "menuPanel");
	}

	public void startGame(SaveFile save)
	{
		stopBackgroundMusic();
		int level = save.getLevel();
		String levelTitle = (new LevelResources(level)).name();
		System.out.println("Starting Level " + level + ": " + levelTitle);
		gamePanel = new GamePanel(save, width, height, this);
		add(gamePanel, "gamePanel");
		cardLayout.show(getContentPane(), "gamePanel");
		gamePanel.requestFocusInWindow();
	}

	public void createGame()
	{
		System.out.println("Creating New Game");
		startGame(new SaveFile());
	}

	public void pickGame()
	{
		System.out.println("Choosing Save File");
		//continue game in card layout
	}

	public void showHelp()
	{
		System.out.println("Showing Help");
		cardLayout.show(getContentPane(), "helpPanel");
	}

	private void playBackgroundMusic()
	{
		music = SoundUtils.getAudioClip("res/menu/bread.mp3");
		music.setCycleCount(AudioClip.INDEFINITE);
		music.play();
	}

	private void stopBackgroundMusic()
	{
		music.stop();
	}

	public void resized()
	{
		width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		setSize(width, height);
	}

	public void closed()
	{
		System.out.println("Closing Window");
		System.exit(0);
	}

	public static void main(String[] args)
	{
		AdBlockerFrame f = new AdBlockerFrame();
	}

}
