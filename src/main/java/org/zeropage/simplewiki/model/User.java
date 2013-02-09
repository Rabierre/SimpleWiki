package org.zeropage.simplewiki.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
	private String id;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "info")
	private UserInfo info;
	private boolean isActiveUser = false;
	
	public User(){}
	
	public User(String id, String password, String mail){
		this.id = id;
		this.info = new UserInfo(password, mail);
		this.isActiveUser = true;
	}

	public String getId() {
		return id.toString();
	}

	public String getPassword() {
		return info.getPassword();
	}

	public UserInfo getInfo() {
		return info;
	}	
	
	public void removeInfo(){
		if(isActiveUser){
			info.reset();
			isActiveUser = false;
		}
	}
}
 