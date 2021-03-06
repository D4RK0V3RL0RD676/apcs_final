// Main Menu Panel for Game

package ui;

import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import utils.FileUtils;
import utils.ImageUtils;
import game.GameConstants;
import ui.TransparentButton;
import ui.AdBlockerFrame;

public class MenuPanel extends JPanel
{

	// Panel Constants
	private String title;
	private String subtitle;
	private int width;
	private int height;
	private AdBlockerFrame frame;

	// GUI Items
	private JPanel titlePanel;
	private JPanel buttonPanel;
	private JLabel background;

	public MenuPanel(String t, String s, int w, int h, AdBlockerFrame f)
	{
		super(null);
		title = t;
		subtitle = s;
		width = w;
		height = h;
		frame = f;
		setBounds(0, 0, width, height);

		makeTitlePanel();
		makeButtonPanel();
		makeBackground();

		setVisible(true);
	}

	private void makeTitlePanel()
	{
		int h = height/2-5;
		int w = 91*h/64;
		Font technoTitle = FileUtils.getFont(Font.BOLD, 48);
		Font technoSubtitle = FileUtils.getFont(Font.BOLD, 40);
		Color white = GameConstants.TEXT_COLOR;

		titlePanel = new JPanel(null);
		titlePanel.setBounds((width - w) / 2, 15, w, h);
		titlePanel.setOpaque(false);

		// Title Label
		JLabel titleLabel = new JLabel(title);
		titleLabel.setBounds(0, 30, w, 30);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(white);
		titleLabel.setFont(technoTitle);

		// Subtitle Label
		JLabel subtitleLabel = new JLabel(subtitle);
		subtitleLabel.setBounds(0, h-60, w, 30);
		subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		subtitleLabel.setForeground(white);
		subtitleLabel.setFont(technoSubtitle);

		// Youtube Image
		JLabel youtubeLabel = new JLabel();
		youtubeLabel.setBounds(0, 0, w, h);
		youtubeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		youtubeLabel.setIcon(ImageUtils.getImageIcon("res/menu/youtube.png", w, h));

		titlePanel.add(titleLabel);
		titlePanel.add(subtitleLabel);
		titlePanel.add(youtubeLabel);
		add(titlePanel);
	}

	private void makeButtonPanel()
	{
		int h = height/3;
		int w = 3*h/2;

		Font techno = FileUtils.getFont(Font.PLAIN, 30);
		Color nohover = GameConstants.BUTTON_COLOR;
		Color hover = GameConstants.BUTTON_HOVER_COLOR;

		buttonPanel = new JPanel(new GridLayout(4, 1));
		buttonPanel.setBounds((width-w)/2, 5*height/9, w, h);
		buttonPanel.setOpaque(false);

		// New Game Button
		TransparentButton newGame = new TransparentButton("NEW GAME")
		{
			public void onButtonClick() { frame.createGame(); }
		};
		newGame.setFont(techno);
	

		// Continue Game Button
		TransparentButton cont = new TransparentButton("CONTINUE")
		{
			public void onButtonClick() { frame.continueGame(); }
		};
		cont.setFont(techno);

		// Help Button
		TransparentButton help = new TransparentButton("HELP")
		{
			public void onButtonClick() { frame.showHelp(); }
		};
		help.setFont(techno);

		// Exit Button
		TransparentButton exit = new TransparentButton("EXIT")
		{
			public void onButtonClick() { frame.closed(); }
		};
		exit.setFont(techno);

		buttonPanel.add(newGame);
		buttonPanel.add(cont);
		buttonPanel.add(help);
		buttonPanel.add(exit);

		buttonPanel.setVisible(true);
		add(buttonPanel);
	}

	private void makeBackground()
	{
		background = new JLabel();
		background.setBounds(0, 0, width, height);
		background.setIcon(ImageUtils.getImageIcon("res/menu/background.png", width, height));
		add(background);
	}

}
