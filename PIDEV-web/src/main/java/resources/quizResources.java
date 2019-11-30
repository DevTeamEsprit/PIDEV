package resources;

import java.awt.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Service.skill.CategoryService;
import Service.skill.QuizService;
import Service.skill.SkillService;
import entity.skill.Quiz;
import entity.skill.Skill;

@Path("gestionQuiz")
@RequestScoped
public class quizResources {

	@EJB
	CategoryService categoryService;
	@EJB
	SkillService skillService;
	@EJB
	QuizService quizService;

	@GET
	@Path("category")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCategories() {
		return Response.status(Response.Status.OK).entity(this.categoryService.ListAllCategories()).build();
	}

	@GET
	@Path("skill/{categoryId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSkillsByCategoryId(@PathParam(value = "categoryId") long categoryId) {
		return Response.status(Response.Status.OK).entity(this.skillService.getSkillsByCategoryId(categoryId)).build();
	}
	
	@GET
	@Path("quiz/{skillId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getQuizBySkillId(@PathParam(value = "skillId") long skillId) {
		
		return Response.status(Response.Status.OK).entity(this.skillService.getQuizBySkillId(skillId)).build();
		
	}
	@GET
	@Path("question/{quizId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getQuestionsByQuizId(@PathParam(value = "quizId") long quizId) {
		
		return Response.status(Response.Status.OK).entity(this.quizService.listQuestionsByQuizId(quizId)).build();
		
	}
	
	@GET
	@Path("quiz/{quizId}/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrCreateUserQuiz( @PathParam(value="userId") long userId, @PathParam(value = "quizId") long quizId) {
		
		return Response.status(Response.Status.OK).entity(this.quizService.getOrCreateUserQuiz(userId, quizId)).build();
		
	}

}
