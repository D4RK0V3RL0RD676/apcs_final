// Help Panel for Game

package ui;

import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

import input.SimpleMouseListener;
import utils.FileUtils;
import utils.ImageUtils;
import utils.GameUtils;
import game.GameConstants;
import ui.AdBlockerFrame;
import ui.TransparentButton;

public class HelpPanel extends JPanel
{

	// Panel Constants
	private String title;
	private String subtitle;
	private int width;
	private int height;
	private AdBlockerFrame frame;

	// GUI Items
	private JLabel titleLabel;
	private JLabel helpLabel;
	private JButton backButton;
	private JLabel background;

	public HelpPanel(String t, String s, int w, int h, AdBlockerFrame f)
	{
		super(null);
		title = t;
		subtitle = s;
		width = w;
		height = h;
		frame = f;
		setBounds(0, 0, width, height);

		add(makeTitleLabel());
		add(makeHelpLabel());
		add(makeBackButton());
		add(makeBackground());

		setVisible(true);
	}

	private JLabel makeTitleLabel()
	{
		titleLabel = new JLabel(title + ": " + subtitle);
		titleLabel.setBounds(0, 30, width, 60);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(GameConstants.TEXT_COLOR);
		titleLabel.setFont(FileUtils.getFont(Font.BOLD, 48));
		return titleLabel;
	}

	private JLabel makeHelpLabel()
	{
		int w = 3*width/4;
		int h = 2*height/3;
		String html = FileUtils.getResourceContent("res/menu/help.html");
		Font f = new Font("Arial", Font.PLAIN, 18);
		helpLabel = new JLabel(html);
		helpLabel.setBounds((width-w)/2, 100, w, h);
		helpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		helpLabel.setForeground(GameConstants.TEXT_COLOR);
		helpLabel.setFont(f);
		return helpLabel;
	}

	private JButton makeBackButton()
	{
		int w = width/4;
		int h = height/6;
		Color nohover = GameConstants.BUTTON_COLOR;
		Color hover = GameConstants.BUTTON_HOVER_COLOR;
		backButton = new TransparentButton("Back");
		backButton.setBounds((width-w)/2, 3*height/4, w, h);
		backButton.setFont(FileUtils.getFont(Font.PLAIN, 30));
		backButton.setForeground(nohover);
		backButton.addMouseListener(new SimpleMouseListener()
		{
			public void mouseEntered(MouseEvent e) { backButton.setForeground(hover); }
			public void mouseExited(MouseEvent e) { backButton.setForeground(nohover); }
			public void mouseClicked(MouseEvent e) { frame.menu(); }
		});
		return backButton;
	}

	private JLabel makeBackground()
	{
		background = new JLabel();
		background.setBounds(0, 0, width, height);
		background.setIcon(ImageUtils.getImageIcon("res/menu/background.png", width, height));
		return background;
	}

}
