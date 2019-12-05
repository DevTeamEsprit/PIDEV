package managedBean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import Service.mission.MissionServiceLocal;
import entity.Mission;
import entity.resultatMission;

@ManagedBean(name="missionBean")
@SessionScoped
public class MissionBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private MissionServiceLocal ms;
	 public Mission selectedMission;
	 private int missionIdup;
	
	private Mission mission;
	private List<Mission> missions;
	private List<Mission> missions2;
	private List<Mission> missions3;
	private List<Mission> missions4;

    private Integer IdToBeUpdated;
	private String startDateString = "";
	
	
	

	@PostConstruct
	public void init() {
		mission = new Mission();
		
	}

//	public void doUpdateM(Mission mission)
//	{this.setLocalisation(mission.getLocalisation());
//	this.setDate(mission.getDate());
//	this.setDuration(mission.getDuration());
//	this.setMissionIdup(mission.getId());
//	}
//	
//	public void msupdate(){
//		Date startDate = new Date();
//		try {
//			String pattern = "dd-MM-yyyy";
//			startDate=new SimpleDateFormat(pattern).parse(startDateString);  
//		} catch (Exception e) {
//			// TODO: handle exception`
//		}
//		ms.update(new Mission(missionIdup,localisation,startDate,duration));
//		}
//	public int getMissionIdup() {
//		return missionIdup;
//	}


	public void setMissionIdup(int missionIdup) {
		this.missionIdup = missionIdup;
	}


	public String doFail(int id ) {
		String navigateTo = "/mission/missions?faces-redirect=true";

		ms.updateFail(id);
		return navigateTo;

	}
	public String doSuc(int id ) {
		String navigateTo = "/mission/missions?faces-redirect=true";

		ms.updateSuc(id);
		return navigateTo;

	}
	public String doAcc(int id ) {
		String navigateTo = "/mission/missions?faces-redirect=true";

		ms.updateAcce(id);
		return navigateTo;

	}
	public String doDeletM(int id ) {
		String navigateTo = "/mission/missions?faces-redirect=true";

		ms.delete(id);
		return navigateTo;

	}
	
	public String addms() {
		String navigateTo = "/mission/missions?faces-redirect=true";

		Date startDate = new Date();
		try {
			String pattern = "dd-MM-yyyy";
			startDate=new SimpleDateFormat(pattern).parse(startDateString);  
		} catch (Exception e) {
			// TODO: handle exception`
		}
		System.out.println(startDateString);
		
	mission.setDate(startDate);
	mission.setResultat(resultatMission.WAITING);
	mission.setIdemp(2);
	ms.create(mission);
	System.out.println(mission.getLocalisation());

	mission = new Mission();

	return navigateTo;
	}

	

	public MissionServiceLocal getMs() {
		return ms;
	}

	public void setMs(MissionServiceLocal ms) {
		this.ms = ms;
	}

	public Mission getSelectedMission() {
		return selectedMission;
	}

	public void setSelectedMission(Mission selectedMission) {
		this.selectedMission = selectedMission;
	}

	public Mission getMission() {
		return mission;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}

	public List<Mission> getMissions() {
		missions = ms.showALL();

		return missions;
	}

	public void setMissions(List<Mission> missions) {
		this.missions = missions;
		
	}
	public List<Mission> getMissions2() {
		missions2 = ms.showEmpMission(2);

		return missions2;
	}

	public void setMissions2(List<Mission> missions2) {
		this.missions2 = missions2;
		
	}

	public List<Mission> getMissions3() {
		missions3 = ms.search(resultatMission.WAITING);

		return missions3;
	}

	public void setMissions3(List<Mission> missions3) {
		this.missions3 = missions3;
		
	}
	public List<Mission> getMissions4() {
		missions4 = ms.search(resultatMission.PRGRESSE);

		return missions4;
	}

	public void setMissions4(List<Mission> missions4) {
		this.missions4 = missions4;
		
	}

	
	
	public Integer getIdToBeUpdated() {
		return IdToBeUpdated;
	}

	public void setIdToBeUpdated(Integer idToBeUpdated) {
		IdToBeUpdated = idToBeUpdated;
	}

	public String getStartDateString() {
		return startDateString;
	}

	public void setStartDateString(String startDateString) {
		this.startDateString = startDateString;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
//	public List<Mission> getEmployesMissions() {
//		missions = ms.showALL();
//
//		return missions;
//		} 
//

//	
//	
//	public String doCreateMission() {
//		String navigateTo = "/mission/missions?faces-redirect=true";
//		Date startDate = new Date();
//		try {
//			String pattern = "dd-MM-yyyy";
//			startDate=new SimpleDateFormat(pattern).parse(startDateString);  
//		} catch (Exception e) {
//			// TODO: handle exception`
//		}
//		
//		mission.setDate(startDate);
//		mission.setResultat(resultatMission.WAITING);
//		ms.create(mission);
//		mission = new Mission();
//		return navigateTo;
//	}
//
//	



	
	
}
