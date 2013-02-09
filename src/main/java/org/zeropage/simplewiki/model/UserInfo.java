package org.zeropage.simplewiki.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_info")
public class UserInfo {
	@Column
	public String password;
	@Column
	public String mail;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int id;

	public UserInfo() {
	}
	
	public UserInfo(String password, String mail){
		this.password = password;
		this.mail = mail;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void reset(){
		this.password = null;
		this.mail = null;
	}
}