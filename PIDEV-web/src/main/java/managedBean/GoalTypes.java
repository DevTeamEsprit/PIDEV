package managedBean;

import javax.faces.bean.*;

import entity.GoalType;

@ManagedBean
@ApplicationScoped
public class GoalTypes {
	public GoalType[] getGoals() {
		return GoalType.values();
	}
}
