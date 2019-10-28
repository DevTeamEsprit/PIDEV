package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Message;
import entity.Utilisateur;
import managedBean.Loginbean;
import service.ServiceManager;

/**
 * Servlet implementation class ChatServlet
 */
@WebServlet("/EnvoiMessageServlet")
public class EnvoiMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private ServiceManager serviceManager;
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm");
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			long idUser =Long.parseLong(request.getParameter("id_user")) ;
			Utilisateur userSender = this.serviceManager.getUser(idUser);
			long idReceiver =Long.parseLong(request.getParameter("id_receiver")) ;
			Utilisateur userReceiver = this.serviceManager.getUser(idReceiver);
            
			String messageContent = request.getParameter("message");
			if(messageContent != null) {
				Message message = new Message();
				message.setContent(messageContent);
				message.setDate(new Date());
				message.setSender(userSender);
				message.setReceiver(userReceiver);
				this.serviceManager.addMessage(message);
			}
			
			List<Message> messages = this.serviceManager.getMessages(userSender, userReceiver);
            
            if(messages!=null){
                for(Message msg:messages){
                	

                	String date = this.simpleDateFormat.format(msg.getDate());
                    if(msg.getSender().getId()==userSender.getId()){
                        out.println("<div class='direct-chat-msg right'>");
                        out.println("<div class='direct-chat-info clearfix'>");
                        out.println("<span class='direct-chat-timestamp float-left'>"+date+"</span>");
                        out.println("</div>");
                        out.println("<img class='direct-chat-img' src='data:image/png;base64,"+userSender.getImage()+"' alt='message user image'>");
                        out.println("<div class='direct-chat-text'>");
                        out.println(msg.getContent());
                        out.println("</div>");
                        out.println("</div>");
                    }
                    else{
                        out.println("<div class='direct-chat-msg'>");
                        out.println("<div class='direct-chat-info clearfix'>");
                        out.println("<span class='direct-chat-timestamp float-right'>"+date+"</span>");
                        out.println("</div>");
                        out.println("<img class='direct-chat-img' src='data:image/png;base64,"+userReceiver.getImage()+"' alt='message user image'>");
                        out.println("<div class='direct-chat-text'>");
                        out.println(msg.getContent());
                        out.println("</div>");
                        out.println("</div>");
                    }
                }
            }
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

}
