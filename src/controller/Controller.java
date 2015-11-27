package controller;

import java.util.ArrayList;
import model.Model;
import model.NoAdvancementException;
import model.NonDeterministicException;
import view.TuringWindow;

public class Controller {
	boolean isTapeRead;
	boolean areInstructionsRead;
	private Model model;
	private TuringWindow turingWindow;
	
	public Controller(TuringWindow turingWindow){
		model = new Model();
		this.turingWindow = turingWindow;
	}
	
	public void loadStates(String statesText){
		try {
			model.readStates(statesText);
			if(model.isEmpty())
				turingWindow.addError("No instructions read.");
			else
				turingWindow.addMessage("Successfully read instructions!");
		} catch (NonDeterministicException e) {
			turingWindow.addError(e.getMessage());
		}
	}
	
	public void executeNext(){
		try {
			model.advance();
			turingWindow.changeExecutedStatesArea(model.getExecutedInstructions());
			turingWindow.changeLabels(model.getTape(), model.getHeadPosition());
			if(model.isInFinalState())
				turingWindow.addMessage("The program was successfully run!");
		} catch (NoAdvancementException e) {
			turingWindow.addError(e.getMessage());
		}
	}
	
	public void executeAll(){
			try {
				model.advanceAll();
				turingWindow.changeExecutedStatesArea(model.getExecutedInstructions());
				turingWindow.changeLabels(model.getTape(), model.getHeadPosition());
				turingWindow.addMessage("The program was successfully run!");
			} catch (NoAdvancementException e) {
				turingWindow.addError(e.getMessage());
			}
	}
	
	public void resetTape(){
		if(model == null)
			return;
		model.resetTape();
		turingWindow.changeExecutedStatesArea(model.getExecutedInstructions());
		turingWindow.changeLabels(model.getTape(), model.getHeadPosition());
	}
	
	public void readTape(ArrayList<Character> tapeList){
		model.setTape(tapeList);
		turingWindow.changeLabels(model.getTape(), model.getHeadPosition());
	}
	
	public void resetMachine(){
		this.model = new Model();
	}


}
