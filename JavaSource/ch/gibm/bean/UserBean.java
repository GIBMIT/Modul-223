package ch.gibm.bean;

import java.io.Serializable;
import java.security.Principal;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import ch.gibm.entity.User;
import ch.gibm.facade.UserFacade;

@SessionScoped
@ManagedBean(name = "userBean")
public class UserBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private User user;

	public boolean isAdmin() {
		return this.getUser() != null ? this.user.isAdmin() : false;
	}

	public boolean isDefaultUser() {
		return this.getUser() != null ? this.user.isUser() : false;
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/pages/protected/index?faces-redirect=true";
	}

	public User getUser() {
		if (this.user == null) {
			Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();

			if (principal != null) {
				UserFacade userFacade = new UserFacade();
				this.user = userFacade.getUserByName(principal.getName());
			}
		}
		return this.user;
	}
}