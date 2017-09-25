/**
 * @author Ciara Power
 */
public class Rule {

	private Rule noAction;
	private Rule yesAction;
	private int noNumber;
	private int yesNumber;
	private int number;
	private String question;
	
	public Rule(int number,String question, Rule noAction, Rule yesAction,int noNumber,int yesNumber) {
		this.number=number;
		this.question=question;
		this.noAction = noAction;
		this.yesAction = yesAction;
		this.noNumber=noNumber;
		this.yesNumber=yesNumber;
	}

	public Rule(int number, String question) {
		this.number=number;
		this.question=question;
		this.noAction = null;
		this.yesAction = null;
		this.noNumber=0;
		this.yesNumber=0;
	}

	public int getNoNumber() {
		return noNumber;
	}

	public void setNoNumber(int noNumber) {
		this.noNumber = noNumber;
	}

	public int getYesNumber() {
		return yesNumber;
	}

	public void setYesNumber(int yesNumber) {
		this.yesNumber = yesNumber;
	}

	public Rule getNoAction() {
		return noAction;
	}

	public void setNoAction(Rule noAction) {
		this.noAction = noAction;
	}

	public Rule getYesAction() {
		return yesAction;
	}

	public void setYesAction(Rule yesAction) {
		this.yesAction = yesAction;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
}
