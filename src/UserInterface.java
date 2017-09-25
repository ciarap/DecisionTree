/**
 * @author Ciara Power
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class UserInterface
implements ActionListener    // interface which listens for key presses by user
{
	private DecisionTree tree;

	private JFrame frame;       // frame of  window
	private JTextArea visualDisplay;
	private boolean debug=true;
	

	/**
	 * Create a user interface for a given dictionary.
	 */
	public UserInterface(DecisionTree tree)
	{
		this.tree =tree;
		makeFrame();    //call to method which creates the window
		frame.setVisible(true);    //display window created

	}
	/**
	 * Make this interface visible again. (Has no effect if it is already
	 * visible.)
	 */
	public void setVisible(boolean visible)
	{
		frame.setVisible(visible);
	}

	/**
	 * Make the frame for the user interface.
	 */
	private void makeFrame()
	{
		frame = new JFrame("Decision Tree");    // initialise frame 

		JPanel contentPane = (JPanel)frame.getContentPane();
		contentPane.setPreferredSize(new Dimension(800, 250));    //set sizes
		contentPane.setLayout(new BorderLayout(10, 10));
		contentPane.setBorder(new EmptyBorder( 10, 10, 10, 10));
		contentPane.setBackground(Color.WHITE);//new Color(153,11,205));    // white background behind button panel on calculator

		Font font1 = new Font("Comic Sans MS", Font.BOLD, 24);    // font 
		Font font2 = new Font("Comic Sans MS", Font.PLAIN, 20);    // font 
		
		//PANEL 1
		JPanel panel1 = new JPanel();    // search translation panel
		Border blackline = BorderFactory.createLineBorder(Color.black);   // black border for panel
		TitledBorder title = BorderFactory.createTitledBorder(blackline, "QUESTION DISPLAY"); //titled border created  with the black line border
		title.setTitleFont(font1);  //border title font set 
		title.setTitleJustification(TitledBorder.CENTER);  //title in center of border
		panel1.setBorder(title);  //set this border on the translation panel
		
		
		visualDisplay = new JTextArea();    // field to enter the word to be translated 
		visualDisplay.setEditable(false);
		visualDisplay.setFont(font2);
		visualDisplay.setPreferredSize( new Dimension( 700, 50 ) );   // text field size
		panel1.add(visualDisplay);
		contentPane.add(panel1, BorderLayout.NORTH);
		
		//PANEL 2
		JPanel panel2 = new JPanel(new GridLayout(1,2));    // search translation panel
		TitledBorder title2 = BorderFactory.createTitledBorder(blackline, "OPTIONS"); //titled border created  with the black line border
		title2.setTitleFont(font1);  //border title font set 
		title2.setTitleJustification(TitledBorder.CENTER);  //title in center of border
		panel2.setBorder(title2);  //set this border on the translation panel

		///P2 ROW 1 COL 1
		addButton(panel2,"Yes");
		
		///P2 ROW 1 COL 2
		addButton(panel2,"No");
		
		
		contentPane.add(panel2, BorderLayout.CENTER);

		frame.pack();
		

	}

	/**
	 * Add a button to the button panel.
	 */
	private void addButton(Container panel, String buttonText)
	{
		JButton button = new JButton(buttonText);
		button.addActionListener(this);
		button.setBackground(new Color(214,135,253));    //color set to a purple tone
		button.setFont(new Font("Comic Sans MS", Font.BOLD, 26));   //font changed to comic sans ms , and size changed
		button.setForeground(Color.WHITE);  // the text appearing on each button set to white
		panel.add(button);
	}

	/**
	 * An interface action has been performed. Find out what it was and
	 * handle it.
	 */
	public void actionPerformed(ActionEvent event) 
	{
		String command = event.getActionCommand();
    if(command=="Yes"){
    	Rule next=tree.nextRule("Yes");
    	if(next.getYesAction()==null && next.getNoAction()==null){   // termination node so no next step
    		visualDisplay.setText(next.getNumber()+ " : "+next.getQuestion());
    		JOptionPane.showMessageDialog(frame, "Questionnaire Finished!","End",1);
    		System.exit(0);
    	}
    	else{
	  displayQ(next);
    	}
     }
    if(command=="No"){
    	Rule next=tree.nextRule("No");
    	if(next.getYesAction()==null && next.getNoAction()==null){ // termination node so no next step
    		visualDisplay.setText(next.getNumber()+ " : "+next.getQuestion());
    		JOptionPane.showMessageDialog(frame, "Questionnaire Finished!","End",1);
    		System.exit(0);
    	}
    	else{
	  displayQ(next);
    	}
    }
	}
	
	public void displayQ(Rule rule){
		visualDisplay.setText(rule.getNumber()+ " : "+rule.getQuestion());
		
	}
	public JTextArea getVisualDisplay() {
		return visualDisplay;
	}
	public void setVisualDisplay(JTextArea visualDisplay) {
		this.visualDisplay = visualDisplay;
	}
}