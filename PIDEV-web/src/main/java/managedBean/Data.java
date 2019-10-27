package managedBean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import entity.TypeContrat;

@ManagedBean
@ApplicationScoped
public class Data {

		public TypeContrat[] type() {
			return TypeContrat.values();
		}
}
