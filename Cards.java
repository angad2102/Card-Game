import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class Assignment. Has all the functionalities of the game.
 * @author  asingh
 *
 */
public class Cards {

	 JPanel MainPanel = new JPanel();
	 JPanel DealerPanel = new JPanel();
	 JPanel PlayerPanel = new JPanel();
	 JPanel RpCardBtnPanel = new JPanel();
	 JPanel ButtonPanel = new JPanel();
	 JPanel InfoPanel = new JPanel();

	JLabel label_Image1 = new JLabel();
	JLabel label_Image2 = new JLabel();
	JLabel label_Image3 = new JLabel();
	JLabel label_Image4 = new JLabel();
	JLabel label_Image5 = new JLabel();
	JLabel label_Image6 = new JLabel();

	JButton btn_rpcard1 = new JButton("Replace Card 1");
	JButton btn_rpcard2 = new JButton("Replace Card 2");
	JButton btn_rpcard3 = new JButton("Replace Card 3");
	JButton btn_start = new JButton("Start");
	JButton btn_result = new JButton("Result");

	JLabel label_bet = new JLabel();
	JLabel label_message = new JLabel();
	JLabel label_money = new JLabel();
	NumberFormat amountFormat;
	JTextField txt_inputbet = new JTextField(10);
	ImageIcon Image1 = new ImageIcon("card/card_back.gif");
	JFrame frame = new JFrame();
	 int count=0;
	 int money=100;

	 int cards[] = new int[52];

	int d1;
	int d2;
	int d3;
	int p1;
	int p2;
	int p3;
	int j=0;
	Boolean x=false;
	int y=0;
	int betvalue =0;

	JMenuBar MenuBar = new JMenuBar();
	JMenu menu1 = new JMenu("Control");
	JMenu menu2 = new JMenu("Help");;
	JMenuItem menuitem11 = new JMenuItem("Restart");
	JMenuItem menuitem12 = new JMenuItem("About");

	boolean started=false;

	/**
	 * Function to shuffle the contents of the array
	 * @param ar the array which has to be shuffled
	 */
	public void shuffleArray(int[] ar)
	  {
	    // If running on Java 6 or older, use `new Random()` on RHS here
	    Random rnd = ThreadLocalRandom.current();
	    for (int i = ar.length - 1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      // Simple swap
	      int a = ar[index];
	      ar[index] = ar[i];
	      ar[i] = a;
	    }
	  }

	/**
	 * Function to start the game
	 */
	public void go(){

	for(int i=0;i<52;i++)
	{
		cards[i]=i;
	}

	shuffleArray(cards);

	DealerPanel.add(label_Image1);
	DealerPanel.add(label_Image2);
	DealerPanel.add(label_Image3);

	PlayerPanel.add(label_Image4);
	PlayerPanel.add(label_Image5);
	PlayerPanel.add(label_Image6);


	RpCardBtnPanel.add(btn_rpcard1);
	btn_rpcard1.addActionListener(new Replace1());

	RpCardBtnPanel.add(btn_rpcard2);
	btn_rpcard2.addActionListener(new Replace2());

	RpCardBtnPanel.add(btn_rpcard3);
	btn_rpcard3.addActionListener(new Replace3());

	ButtonPanel.add(label_bet);
	ButtonPanel.add(txt_inputbet);
	ButtonPanel.add(btn_start);
	btn_start.addActionListener(new start());

	ButtonPanel.add(btn_result);
	btn_result.addActionListener(new result());

	InfoPanel.add(label_message);
	InfoPanel.add(label_money);

	label_Image1.setIcon(Image1);
	label_Image2.setIcon(Image1);
	label_Image3.setIcon(Image1);
	label_Image4.setIcon(Image1);
	label_Image5.setIcon(Image1);
	label_Image6.setIcon(Image1);

	MainPanel.setLayout(new GridLayout(5,1));
	MainPanel.add(DealerPanel);
	MainPanel.add(PlayerPanel);
	MainPanel.add(RpCardBtnPanel);
	MainPanel.add(ButtonPanel);
	MainPanel.add(InfoPanel);

	DealerPanel.setBackground(Color.green);
	PlayerPanel.setBackground(Color.green);
	RpCardBtnPanel.setBackground(Color.green);

	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().add(MainPanel);
	frame.setTitle("A Simple Card Game");
	frame.setSize(400, 700);
	frame.setVisible(true);
	txt_inputbet.setText("0");
	label_money.setText("Amount of money you have $"+money);

	frame.setJMenuBar(MenuBar);
	MenuBar.add(menu1);
	MenuBar.add(menu2);
	menu1.setMnemonic(KeyEvent.VK_A);
	menu2.setMnemonic(KeyEvent.VK_B);
	menu1.add(menuitem11);
	menu1.addSeparator();
	menu2.add(menuitem12);
	menu2.addSeparator();
	menuitem11.addActionListener(new menuitem11());
	label_message.setText("Please press the start button");

	}
	Boolean reset=false;
	/**
	 * Class to implement action listener for menuitem11
	 * @author  asingh
	 *
	 */
	public class menuitem11 implements ActionListener{

		/**
		 * contructor of the class
		 * @param e event
		 *
		 */
		public void actionPerformed(ActionEvent e)
		{
			if(reset==true)
			{
				reset=false;
			if(money<0)
			{
				money=100;
			}
			ImageIcon Image1 = new ImageIcon("card/card_back.gif");
			label_Image1.setIcon(Image1);
			label_Image2.setIcon(Image1);
			label_Image3.setIcon(Image1);
			label_Image4.setIcon(Image1);
			label_Image5.setIcon(Image1);
			label_Image6.setIcon(Image1);
			label_message.setText("Place a bet and press start!");
			y=0;
		}

		else
			label_message.setText("Game in progress");
		}
	}





	/**
	 * Class to implement actionListener for Replace1
	 * @author  asingh
	 *
	 */
	public class Replace1 implements ActionListener {
		/**
		 * Constructor of the class
		 * @param btn_rpcard1 event
		 *
		 */
	public void actionPerformed(ActionEvent btn_rpcard1) {
		if(x==true&&y<2&&started==true)
		{
			y++;
			p1=cards[j];
			j++;
			ImageIcon Image1 = new ImageIcon("card/card_"+((p1/13)+1)+((p1%13)+1)+".gif");
			label_Image4.setIcon(Image1);

		}
		else if(x==false)
			label_message.setText("Please press the start button");

		else if(started==false)
			label_message.setText("You cannot replace more cards.Please reset the game.");
		else if(y==2)
			label_message.setText("You cannot replace more cards. Please press the result button.");
		else
			label_message.setText("Please reset the game");


		 }

	}

	/**
	 * Class to implement Action Listener for Replace2
	 * @author  asingh
	 *
	 */
	public class Replace2 implements ActionListener {

		/**
		 * Constructor of the class
		 * @param btn_rpcard2 event
		 */
		public void actionPerformed(ActionEvent btn_rpcard2) {

		if(x==true&&y<2&&started==true)
		{
			y++;
			p2=cards[j];
			j++;
			ImageIcon Image5 = new ImageIcon("card/card_"+((p2/13)+1)+((p2%13)+1)+".gif");
			label_Image5.setIcon(Image5);

		}
		else if(x==false)
			label_message.setText("Please press the start button");

		else if(started==false)
			label_message.setText("You cannot replace more cards.Please Reset the game.");
		else if(y==2)
			label_message.setText("You cannot replace more cards. Please press the result button.");
		else
			label_message.setText("Please reset the game");

	}

	}

	/**
	 * Class to implement ActionListener or Replace3
	 * @author  asingh
	 *
	 */
	public class Replace3 implements ActionListener{
		/**
		* Constructor of the class
		 * @param btn_rpcard3 event
		 */
		public void actionPerformed(ActionEvent btn_rpcard3) {

		if(x==true&&y<2&&started==true)
		{
			y++;
			p3=cards[j];
			j++;
			ImageIcon Image3 = new ImageIcon("card/card_"+((p3/13)+1)+((p3%13)+1)+".gif");
			label_Image6.setIcon(Image3);

		}
		else if(x==false)
			label_message.setText("Please press the start button");

		else if(started==false)
			label_message.setText("You cannot replace more cards. Please reset the game.");
		else if(y==2)
			label_message.setText("You cannot replace more cards. Please press the result button.");
		else
			label_message.setText("Please reset the game");

		 }
	}


	/**
	 * Class to implement actionListener for start
	 * @author  asingh
	 *
	 */
	public class start implements ActionListener {


		/**
		 * Constructor of the class
		 * @param btn_start event
		 */
		public void actionPerformed(ActionEvent btn_start) {
		if(reset==false){
		if(started==false) {
		if( txt_inputbet.getText() !=null&&txt_inputbet.getText() !=""){
		int f = Integer.parseInt( txt_inputbet.getText() );
		if(f>0&&f<=money)
		{
			started=true;
			betvalue=f;
			label_message.setText("Bet Value set for $"+betvalue);
			if(money>0){
				x=true;
			count++;
			if(count>5)
				{
				shuffleArray(cards);
				count=0;
				}



			d1=cards[j];
			j++;
			d2=cards[j];
			j++;
			d3=cards[j];
			j++;
			p1=cards[j];
			j++;
			p2=cards[j];
			j++;
			p3=cards[j];
			j++;
			ImageIcon Image1 = new ImageIcon("card/card_"+((p1/13)+1)+((p1%13)+1)+".gif");
			ImageIcon Image2 = new ImageIcon("card/card_"+((p2/13)+1)+((p2%13)+1)+".gif");
			ImageIcon Image3 = new ImageIcon("card/card_"+((p3/13)+1)+((p3%13)+1)+".gif");

			label_Image4.setIcon(Image1);
			label_Image5.setIcon(Image2);
			label_Image6.setIcon(Image3);


			}

			else
			{
				label_message.setText("All of your money has been exhausted.  Please restart the game from the menu");
			}


		}
		else
			label_message.setText("Please input a correct value. Current Bet$"+betvalue);
		}
		else
			label_message.setText("Please input a betValue");



		 }
		else
			label_message.setText("Bet Value Cannot be changed and the game has already started");

	}


	else
		label_message.setText("Please reset the game first!");}

	}

	/**
	 * Class to implement ActionListener for result
	 * @author  asingh
	 *
	 */
	public class result implements ActionListener{


		/**
		* Constructor of the class
		 * @param btn_result event
		 */
		public void actionPerformed(ActionEvent btn_result) {
		if(started==true) {
		started=false;

		reset=true;
		ImageIcon Image1 = new ImageIcon("card/card_"+((d1/13)+1)+((d1%13)+1)+".gif");
		ImageIcon Image2 = new ImageIcon("card/card_"+((d2/13)+1)+((d2%13)+1)+".gif");
		ImageIcon Image3 = new ImageIcon("card/card_"+((d3/13)+1)+((d3%13)+1)+".gif");

		label_Image1.setIcon(Image1);
		label_Image2.setIcon(Image2);
		label_Image3.setIcon(Image3);

		int ruled=0;
		int rulep=0;
		int valued=0;
		int valuep=0;
		int rulepv=0;
		int ruledv=0;
		if(d1%13==10||d1%13==11||d1%13==12)
		{
			ruled++;
			ruledv+=d1%13+1;
		}

		if(d2%13==10||d2%13==11||d2%13==12)
		{
			ruled++;
			ruledv+=d2%13+1;

		}

		if(d3%13==10||d3%13==11||d3%13==12)
		{
			ruled++;
			ruledv+=d3%13+1;

		}

		if(p1%13==10||p1%13==11||p1%13==12)
		{
			rulep++;
			rulepv+=p1%13+1;

		}

		if(p2%13==10||p2%13==11||p2%13==12)
		{
			rulep++;
			rulepv+=p1%13+1;

		}

		if(p3%13==10||p3%13==11||p3%13==12)
		{
			rulep++;
			rulepv+=p1%13+1;

		}

		if(rulep>ruled)
		{
			label_message.setText("You win!");
			money+=betvalue;
			label_money.setText("Amount of money you have:$"+money);

		}
		else if (rulep<ruled)
		{
			label_message.setText("You Lose!");
			money-=betvalue;
			label_money.setText("Amount of money you have:$"+money);

		}
		else
		{
			valued=d1%13+d2%13+d3%13+3-ruledv;
			valuep=p1%13+p2%13+p3%13+3-rulepv;
			if(valued%10>valuep%10)
			{
				label_message.setText("You Lose!");
				money-=betvalue;
				label_money.setText("Amount of money you have:$"+money);

			}
			else if (valued%10<valuep%10)
			{
				label_message.setText("You win!");
				money+=betvalue;
				label_money.setText("Amount of money you have:$"+money);

			}
			else
			{
				label_message.setText("You Lose!");
				money-=betvalue;
				label_money.setText("Amount of money you have:$"+money);

			}
		}
	}}
	}

	/**
	 * Main Function
	 * @param args
	 */
	public static void main(String[] args){
		Cards a = new Cards();
		a.go();
	}
}
