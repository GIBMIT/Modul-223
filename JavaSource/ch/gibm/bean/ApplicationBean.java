package ch.gibm.bean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import ch.gibm.dao.EntityManagerHelper;

@ApplicationScoped
@ManagedBean(eager = true)
public class ApplicationBean {
	public ApplicationBean() {
		EntityManagerHelper.init();
	}
}
