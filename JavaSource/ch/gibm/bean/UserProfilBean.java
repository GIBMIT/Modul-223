package ch.gibm.bean;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import javax.faces.context.FacesContext;
import com.sun.faces.context.flash.ELFlash;

import ch.gibm.entity.Language;
import ch.gibm.entity.User;
import ch.gibm.facade.UserFacade;

@SessionScoped
@ManagedBean(name = "userProfilBean")
public class UserProfilBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private User user;
	private User userforlogin;
	private UserFacade userFacade;
	private List<User> users;

	
	public String isAdmin() {
		if(this.getUserForLogin() != null) {
			return "";
		}
		else{
			return "disable";
		}
		
	}
	
	public User getUserForLogin() {
		if (this.userforlogin == null) {
			Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();

			if (principal != null) {
				UserFacade userFacade = new UserFacade();
				this.userforlogin = userFacade.getUserByName(principal.getName());
			}
		}
		return this.userforlogin;
	}
	
	public User getUser() {
		if (user == null) {
			Principal principal =
			FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
			if (principal != null) {
				UserFacade userFacade = new UserFacade();
				user = userFacade.getUserByName(principal.getName());
			}
		}
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	

	public void updateUser() {
		try {
			getUserFacade().updateUser(user);
			closeDialog();
			displayInfoMessageToUser("Updated with success");
			loadUsers();
			resetUser();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("A problem occurred while updating. Try again later");
			e.printStackTrace();
		}
	}

	public UserFacade getUserFacade() {
		if (userFacade == null) {
			userFacade = new UserFacade();
		}

		return userFacade;
	}
	private void loadUsers() {
		users = getUserFacade().listAll();
	}

	public void resetUser() {
		user = new User();
	}
}
