package managedBean;

import javax.faces.bean.*;

import entity.EvalType;

@ManagedBean
@ApplicationScoped
public class EvalTypes {
	public EvalType[] getEvals() {
		return EvalType.values();
	}
}
