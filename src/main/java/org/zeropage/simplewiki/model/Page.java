package org.zeropage.simplewiki.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "page")
public class Page {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private int id;
	@Column
	private String title;
	@Column
	private String contents;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval=true)
	@JoinColumn(name = "page")
	private List<Revision> history;
	//private List<Page> dereferenced;
	
	// Write new Page
	public Page(String title) {
		this.title = title;
		history = new LinkedList<Revision>();
		//dereferenced = new LinkedList<Page>();
	}
	
	// Load existing Page from DB
	public Page() {

	}

	public void edit(String contents, User contributor) {
		this.contents = contents;
		history.add(new Revision(new Date(), contributor, getRevision()+1));
	}

	public String getTitle() {
		return title;
	}

	public String getName() {
		return history.get(history.size() - 1).getName();
	}

	public Date getLastModifiedDate() {
		return history.get(history.size() - 1).getDate();
	}
	
	public String getContents() {
		return contents;
	}

	public int getRevision(){
		return history.size();
	}

	public List<Revision> getHistory() {
		return history;
	}
}
