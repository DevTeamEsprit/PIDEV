package Service.skill;

import javax.ejb.Remote;

import entity.skill.QuestionResponse;
@Remote
public interface ResponseServiceRemote {

	public void addResponse(QuestionResponse response);
	
	
}
