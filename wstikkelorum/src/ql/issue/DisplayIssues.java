package ql.issue;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class DisplayIssues {
	public static void dislayIssue(Issue issue) {
		issue.print();
	}
	
	public List<JPanel> getIssues(List<Issue> issues){
		List<JPanel> issueList = new ArrayList<JPanel>();
		for(Issue issue : issues){
			issueList.add(issue.getUIElement());
		}
		return issueList;
	}
}
