package info.stolarczyk.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Notes")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "dateCreated", "dateModified" }, allowGetters = true)

public class Notes  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull(message = "Field can not be empty")
	@Column(name = "Title")
	private String title;

	@NotNull(message = "Field can not be empty")
	@Column(name = "Content")
	private String content;

	@Column(name = "DateCreated", nullable = false, updatable = false)
	@Temporal(TemporalType.DATE)
	@CreatedDate
	private Date dateCreated;

	@Column(name = "DateModified", nullable = false)
	@Temporal(TemporalType.DATE)
	@LastModifiedDate
	private Date dateModified;

	public Notes() {
	}

	public Notes(@NotNull(message = "Field can not be empty") String title,
			@NotNull(message = "Field can not be empty") String content, Date dateCreated, Date dateModified) {
		super();
		this.title = title;
		this.content = content;
		this.dateCreated = dateCreated;
		this.dateModified = dateModified;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

}
