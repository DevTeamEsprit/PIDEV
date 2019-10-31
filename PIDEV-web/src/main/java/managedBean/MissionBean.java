package managedBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import Service.mission.MissionServiceLocal;
import entity.Employe;
import entity.Mission;
import entity.resultatMission;

@ManagedBean(name="missionBean")
@SessionScoped
public class MissionBean {
	private static final long serialVersionUID = 1L;
	private int id;
	private String localisation;
	private int idemp;
	private Date date;
	private int duration;
	private boolean stat;
	@Enumerated(EnumType.STRING)
	private resultatMission resultat;
	@EJB
	private MissionServiceLocal ms;
	 public Mission selectedMission;

	
	private Mission mission;
	private List<Mission> missions;
    private Integer IdToBeUpdated;
	private String startDateString = "";
	
	
	

//	@PostConstruct
//	public void init() {
//		mission = new Mission();
//		
//	}

	
	
	
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
		System.out.println(selectedMission+"*******************************");
		Mission e = new Mission(id,localisation,1,startDate,duration,true,resultatMission.WAITING);
		
	ms.create(e);
	return navigateTo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	public int getIdemp() {
		return idemp;
	}

	public void setIdemp(int idemp) {
		this.idemp = idemp;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public boolean isStat() {
		return stat;
	}

	public void setStat(boolean stat) {
		this.stat = stat;
	}

	public resultatMission getResultat() {
		return resultat;
	}

	public void setResultat(resultatMission resultat) {
		this.resultat = resultat;
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
