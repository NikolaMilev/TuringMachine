package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import controller.Controller;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TuringWindow {

	private JFrame frmTuringMachineSimulator;
	private ArrayList<JLabel> labelList = new ArrayList<JLabel>();
	JTextArea textArea;
	JTextArea textArea_1;
	Controller controller;
	JLabel lblErrors;
	JPanel panel;
	JButton btnSetTape;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					TuringWindow window = new TuringWindow();
					window.frmTuringMachineSimulator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TuringWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		controller = new Controller(this);
		frmTuringMachineSimulator = new JFrame();
		frmTuringMachineSimulator.setResizable(false);
		frmTuringMachineSimulator.setMinimumSize(new Dimension(600, 400));
		frmTuringMachineSimulator.setTitle("Turing machine simulator");
		frmTuringMachineSimulator.setBounds(100, 100, 680, 482);
		frmTuringMachineSimulator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTuringMachineSimulator.getContentPane().setLayout(null);
//		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

		
		panel = new JPanel();
		panel.setBounds(10, 11, 644, 39);
		frmTuringMachineSimulator.getContentPane().add(panel);
		
		btnSetTape = new JButton("Set tape");
		btnSetTape.setForeground(Color.DARK_GRAY);
		btnSetTape.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = JOptionPane.showInputDialog(null).trim();
				ArrayList<Character> characterList = new ArrayList<Character>(s.length());
				for(int i = 0; i < s.length(); i++){
					if(s.charAt(i)!= '1' && s.charAt(i)!= '0' && s.charAt(i)!= 'b'){
						addError("Input characters can only be \'1\', \'0\' or \'b\'");
						return;
					}
					characterList.add(s.charAt(i));
				}
				controller.readTape(characterList);
				addMessage("Tape successfully read.");
				
			}
		});
		panel.add(btnSetTape);
		
		JButton btnResetTape = new JButton("Reset tape");
		btnResetTape.setForeground(Color.DARK_GRAY);
		btnResetTape.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.resetTape();
				
			}
		});
		panel.add(btnResetTape);
		
		JButton btnReadInstructions = new JButton("Read instructions");
		btnReadInstructions.setForeground(Color.DARK_GRAY);
		btnReadInstructions.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.loadStates(textArea.getText());
				
			}
		});
		panel.add(btnReadInstructions);
		
		JButton btnExecuteNext = new JButton("Execute next");
		btnExecuteNext.setForeground(Color.DARK_GRAY);
		btnExecuteNext.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.executeNext();
			}
		});
		panel.add(btnExecuteNext);
		
		JButton btnExecuteAll = new JButton("Execute all");
		btnExecuteAll.setForeground(Color.DARK_GRAY);
		btnExecuteAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.executeAll();
				
			}
		});
		panel.add(btnExecuteAll);
		
		JButton btnResetAll = new JButton("Reset all");
		btnResetAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.resetMachine();
				textArea.setText("");
				textArea_1.setText("");
				lblErrors.setForeground(Color.GREEN);
				lblErrors.setText("No errors.");
				for(int i = 0; i < labelList.size(); i++)
					labelList.get(i).setText(" b ");
			}
		});
		panel.add(btnResetAll);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 56, 654, 54);
		panel_1.setAlignmentX(0.0f);
		panel_1.setAlignmentY(0.0f);
		frmTuringMachineSimulator.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setForeground(Color.GRAY);
		panel_2.setBorder(new LineBorder(Color.GRAY));
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setHgap(8);
		panel_2.setBounds(37, 0, 574, 34);
		panel_1.add(panel_2);
		
		JLabel lblA = new JLabel(" 0 ");
		lblA.setForeground(Color.DARK_GRAY);
		lblA.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_2.add(lblA);
		labelList.add(lblA);
		lblA.setBorder(new LineBorder(Color.GRAY));
		
		JLabel lblB = new JLabel(" b ");
		lblB.setForeground(Color.DARK_GRAY);
		lblB.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_2.add(lblB);
		labelList.add(lblB);
		lblB.setBorder(new LineBorder(Color.GRAY));
		
		JLabel lblC = new JLabel(" 0 ");
		lblC.setForeground(Color.DARK_GRAY);
		lblC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_2.add(lblC);
		labelList.add(lblC);
		lblC.setBorder(new LineBorder(Color.GRAY));
		
		JLabel lblD = new JLabel(" 0 ");
		lblD.setForeground(Color.DARK_GRAY);
		lblD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_2.add(lblD);
		labelList.add(lblD);
		lblD.setBorder(new LineBorder(Color.GRAY));
		
		JLabel lblE = new JLabel(" 0 ");
		lblE.setForeground(Color.DARK_GRAY);
		lblE.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_2.add(lblE);
		labelList.add(lblE);
		lblE.setBorder(new LineBorder(Color.GRAY));
		
		JLabel lblF = new JLabel(" 0 ");
		lblF.setForeground(Color.DARK_GRAY);
		lblF.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_2.add(lblF);
		labelList.add(lblF);
		lblF.setBorder(new LineBorder(Color.GRAY));
		
		JLabel lblG = new JLabel(" 0 ");
		lblG.setForeground(Color.DARK_GRAY);
		lblG.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_2.add(lblG);
		labelList.add(lblG);
		lblG.setBorder(new LineBorder(Color.GRAY));
		
		JLabel lblH = new JLabel(" 0 ");
		lblH.setForeground(Color.DARK_GRAY);
		lblH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_2.add(lblH);
		labelList.add(lblH);
		lblH.setBorder(new LineBorder(Color.GRAY));
		
		JLabel lblI = new JLabel(" 0 ");
		lblI.setForeground(Color.DARK_GRAY);
		lblI.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_2.add(lblI);
		labelList.add(lblI);
		lblI.setBorder(new LineBorder(Color.GRAY));
		
		JLabel lblJ = new JLabel(" 0 ");
		lblJ.setForeground(Color.DARK_GRAY);
		lblJ.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_2.add(lblJ);
		labelList.add(lblJ);
		lblJ.setBorder(new LineBorder(Color.GRAY));
		
		JLabel lblK = new JLabel(" 0 ");
		lblK.setForeground(Color.DARK_GRAY);
		lblK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_2.add(lblK);
		labelList.add(lblK);
		lblK.setBorder(new LineBorder(Color.GRAY));
		
		JLabel lblL = new JLabel(" 0 ");
		lblL.setForeground(Color.DARK_GRAY);
		lblL.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_2.add(lblL);
		labelList.add(lblL);
		lblL.setBorder(new LineBorder(Color.GRAY));
		
		JLabel lblM = new JLabel(" 0 ");
		lblM.setForeground(Color.DARK_GRAY);
		lblM.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_2.add(lblM);
		labelList.add(lblM);
		lblM.setBorder(new LineBorder(Color.GRAY));
		
		JLabel lblN = new JLabel(" 0 ");
		lblN.setForeground(Color.DARK_GRAY);
		lblN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_2.add(lblN);
		labelList.add(lblN);
		lblN.setBorder(new LineBorder(Color.GRAY));
		
		JLabel lblO = new JLabel(" 0 ");
		lblO.setForeground(Color.DARK_GRAY);
		lblO.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_2.add(lblO);
		labelList.add(lblO);
		lblO.setBorder(new LineBorder(Color.GRAY));
		
		JLabel lblP = new JLabel(" 0 ");
		lblP.setForeground(Color.DARK_GRAY);
		lblP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_2.add(lblP);
		labelList.add(lblP);
		lblP.setBorder(new LineBorder(Color.GRAY));
		
		JLabel lblQ = new JLabel(" 0 ");
		lblQ.setForeground(Color.DARK_GRAY);
		lblQ.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_2.add(lblQ);
		labelList.add(lblQ);
		lblQ.setBorder(new LineBorder(Color.GRAY));
		
		JLabel lblR = new JLabel(" 0 ");
		lblR.setForeground(Color.DARK_GRAY);
		lblR.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_2.add(lblR);
		labelList.add(lblR);
		lblR.setBorder(new LineBorder(Color.GRAY));
		
		JLabel lblS = new JLabel(" 0 ");
		lblS.setForeground(Color.DARK_GRAY);
		lblS.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_2.add(lblS);
		labelList.add(lblS);
		lblS.setBorder(new LineBorder(Color.GRAY));
		
		JLabel lblNewLabel = new JLabel("\u25B2");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(316, 40, 17, 14);
		lblNewLabel.setMinimumSize(new Dimension(20, 20));
		lblNewLabel.setMaximumSize(new Dimension(20, 20));
		lblNewLabel.setHorizontalTextPosition(0);
		panel_1.add(lblNewLabel);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(Color.GRAY));
		panel_3.setBounds(33, 141, 279, 285);
		frmTuringMachineSimulator.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setForeground(Color.DARK_GRAY);
		textArea.setLineWrap(true);
		textArea.setBounds(10, 6, 259, 268);
		panel_3.add(textArea);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(Color.GRAY));
		panel_4.setBounds(357, 141, 279, 285);
		frmTuringMachineSimulator.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		textArea_1 = new JTextArea();
		textArea_1.setForeground(Color.DARK_GRAY);
		textArea_1.setLineWrap(true);
		textArea_1.setBounds(10, 6, 259, 268);
		panel_4.add(textArea_1);
		
		JLabel lblProgram = new JLabel("Program");
		lblProgram.setForeground(Color.DARK_GRAY);
		lblProgram.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblProgram.setBounds(10, 116, 92, 21);
		frmTuringMachineSimulator.getContentPane().add(lblProgram);
		
		JLabel lblExecutedInstructions = new JLabel("Executed instructions");
		lblExecutedInstructions.setForeground(Color.DARK_GRAY);
		lblExecutedInstructions.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblExecutedInstructions.setBounds(332, 116, 178, 21);
		frmTuringMachineSimulator.getContentPane().add(lblExecutedInstructions);
		
		lblErrors = new JLabel("No errors.");
		lblErrors.setForeground(Color.GREEN);
		lblErrors.setBounds(355, 433, 269, 14);
		frmTuringMachineSimulator.getContentPane().add(lblErrors);
		
		for(int i = 0; i < labelList.size(); i++)
			labelList.get(i).setSize(new Dimension(20, 20));
	}
	
	public void changeExecutedStatesArea(String executedStatesString){
		textArea_1.setText(executedStatesString);
	}

	public void addError(String message) {
		lblErrors.setText(message);
		lblErrors.setForeground(Color.RED);
		
	}
	
	public void addMessage(String message) {
		lblErrors.setText(message);
		lblErrors.setForeground(Color.GREEN);
	}
	
	public void changeLabels(ArrayList<Character> characterList, int position){
		int i = 0;
		for(int j = 0; j < 9-position; j++)
			labelList.get(j).setText(" b ");
		for(int j = 9-position, k = 0; j < 9; j++, k++)
			labelList.get(j).setText(" "+ characterList.get(k) +" ");
			for(i = 0; i + position < characterList.size() && i + 9 < 19; i++)
			labelList.get(i+9).setText(" "+ characterList.get(i+position) +" ");
		for(; i + 9 < 19; i++)
			labelList.get(i + 9).setText(" b ");
	
	}
}
