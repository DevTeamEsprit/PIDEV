package managedBean;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;

import org.primefaces.model.timeline.TimelineEvent;
import org.primefaces.model.timeline.TimelineModel;

@ManagedBean
@RequestScoped
public class CustomTimelineView {  
   
    private TimelineModel model;  
    private Date start;  
    private Date end;  
    private String title = "Panda";
  
    public CustomTimelineView() {  
        // set initial start / end dates for the axis of the timeline  
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));  
        Date now = new Date();  
        System.out.println("------------ start date ");

        cal.setTimeInMillis(now.getTime() - 4 * 60 * 60 * 1000);  
        start = cal.getTime();  
   
        cal.setTimeInMillis(now.getTime() + 8 * 60 * 60 * 1000);  
        end = cal.getTime();  
   
        // groups  
        String[] NAMES = new String[] {"User 1", "User 2", "User 3", "User 4", "User 5", "User 6"};  
        System.out.println("------------ "+start.getDate());
        // create timeline model  
        model = new TimelineModel();  
   
        for (String name : NAMES) {  
            now = new Date();  
            Date end = new Date(now.getTime() - 12 * 60 * 60 * 1000);  
   
            for (int i = 0; i < 5; i++) {  
                Date start = new Date(end.getTime() + Math.round(Math.random() * 5) * 60 * 60 * 1000);  
                end = new Date(start.getTime() + Math.round(4 + Math.random() * 5) * 60 * 60 * 1000);  
   
                long r = Math.round(Math.random() * 2);  
                String availability = (r == 0 ? "Unavailable" : (r == 1 ? "Available" : "Maybe"));  
   
                // create an event with content, start / end dates, editable flag, group name and custom style class  
                TimelineEvent event = new TimelineEvent(availability, start, end, true, name, availability.toLowerCase());  
                model.add(event);  
                System.out.println(event.getGroup());
            }  
        }  
    }  
    
    
    public TimelineModel getModel() {  
        return model;  
    }  
   
    public Date getStart() {  
        return start;  
    }  
   
    public Date getEnd() {  
        return end;  
    }


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public void setModel(TimelineModel model) {
		this.model = model;
	}


	public void setStart(Date start) {
		this.start = start;
	}


	public void setEnd(Date end) {
		this.end = end;
	}  
    
}