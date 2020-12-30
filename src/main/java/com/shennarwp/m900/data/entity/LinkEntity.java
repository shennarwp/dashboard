package com.shennarwp.m900.data.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "LINKENTITY")
@Getter @Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class LinkEntity
{
	@Id @NonNull @GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@NonNull
	@Column(name = "TITLE")
	private String title;

	@NonNull
	@Column(name = "URL")
	private String url;

	@Column(name = "CATEGORY")
	private String category;

	@Column(name = "IMAGENAME")
	private String imageName;

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof LinkEntity))
			return false;
		LinkEntity other = (LinkEntity) obj;

		return getTitle().equals(other.getTitle()) &&
				getCategory().equals(other.getCategory()) &&
				getUrl().equals(other.getUrl()) &&
				getImageName().equals(other.getImageName());
	}

	@Override
	public int hashCode() {
		return getTitle().hashCode() + getCategory().hashCode() +
				getUrl().hashCode() + getImageName().hashCode();
	}
}
