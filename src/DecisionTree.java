/**
 * @author Ciara Power
 */
import java.io.FileNotFoundException;
import java.util.HashMap;

public class DecisionTree {

	static FileReader reader;
	private static HashMap<Integer,Rule> rules;
	private static UserInterface userInterface;
	private static boolean debug=true;
	
	public static void main(String[] args) throws Exception {
		DecisionTree tree=new DecisionTree();
		rules=new HashMap<Integer,Rule>();
		reader=new FileReader("rules");
		reader.readTextFile("rules");
		createLinks();

		userInterface=new UserInterface(tree);
		userInterface.displayQ(rules.get(1));

	}
	
	
	
	public static void createLinks() {
		for (Integer number:rules.keySet()){
			Rule linkRule=rules.get(number);
			if(linkRule.getNoNumber()==0 && linkRule.getYesNumber()==0){
				if(debug) System.out.println("Terminating Rule - No Links Created");
			}
			else {
				if(linkRule.getNoAction()==null){
				Rule noRule=rules.get(linkRule.getNoNumber());
				linkRule.setNoAction(noRule);
				if(debug) System.out.println("NO -- Rule: "+linkRule.getNumber()+" linked to: "+noRule.getNumber());
			}
			if(linkRule.getYesAction()==null){
				Rule yesRule=rules.get(linkRule.getYesNumber());
				linkRule.setYesAction(yesRule);
				if(debug) System.out.println("YES -- Rule: "+linkRule.getNumber()+" linked to: "+yesRule.getNumber());
			}
			}
		}
		
	}

	public static void createRule(String number, String question, String noNumber, String yesNumber) {
	 if(rules.containsKey(number)){
		 if(debug)System.out.println("Rule already created!");
	 }
	 else{
		 Rule noRule=null;
		 Rule yesRule=null;
		 if(rules.containsKey(noNumber)){
			noRule=rules.get(noNumber);
		 }
		 if (rules.containsKey(yesNumber)){
			yesRule=rules.get(yesNumber);
		 }
		 Rule newRule=new Rule(Integer.parseInt(number),question,noRule,yesRule,Integer.parseInt(noNumber),Integer.parseInt(yesNumber));
		 rules.put(newRule.getNumber(),newRule);
		 if(debug)System.out.println("New Rule Added: \nNumber: "+newRule.getNumber()+"\nQuestion: "+newRule.getQuestion()
		 +"\nNo Action: "+newRule.getNoAction()+"\nYes Action: "+newRule.getYesAction()+"\nNo Number: "+newRule.getNoNumber()
		 +"\nYes Number: "+newRule.getYesNumber());
	 }
		
	}

	public void createRule(String number, String question) {
	  Rule newRule= new Rule(Integer.parseInt(number),question);
	  rules.put(newRule.getNumber(),newRule);
	}



	public Rule nextRule(String answer) {
		if(debug) System.out.println(userInterface.getVisualDisplay().getText().substring(0,1));
		Rule current=rules.get(Integer.parseInt(userInterface.getVisualDisplay().getText().substring(0,1)));

		Rule next=null;
		
		if(answer.equals("Yes")){
			next=current.getYesAction();
			if(next.getYesAction()==null && next.getNoAction()==null){
				if(debug)System.out.println("Termination Reached");
			}
		}
		if(answer.equals("No")){
			next=current.getNoAction();
			if(next.getYesAction()==null && next.getNoAction()==null){
				if(debug)System.out.println("Termination Reached");
			}
		}
		
		return next;
	}
	

}
