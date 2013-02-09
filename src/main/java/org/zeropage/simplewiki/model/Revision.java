package org.zeropage.simplewiki.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "revision", uniqueConstraints = @UniqueConstraint(columnNames = {"page", "number"}))
public class Revision {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column
	private int number;
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="contributor")
	private User contributor;
	@Column
	private Date modified;
	
	public Revision(){}
	
	public Revision(Date date, User contributor, int number) {
		this.contributor = contributor;
		this.modified = date;
		this.number = number;
	}
	
	public int getNumber() {
		return number;
	}

	public String getName() {
		return contributor.getId();
	}

	public Date getDate() {
		return modified;
	}

/*
	@Embeddable
	public static class RevisionId implements Serializable{
		@ManyToOne
		private Page page;
		private int number;
		
		public RevisionId(){}
		
		public RevisionId(Page page, int number){
			this.page = page;
			this.number = number;
		}
	}
*/
}
