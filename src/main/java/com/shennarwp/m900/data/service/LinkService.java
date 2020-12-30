package com.shennarwp.m900.data.service;

import com.shennarwp.m900.data.entity.LinkEntity;
import com.shennarwp.m900.data.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * service class for LinkEntity, will be called / used by Vaadin-views
 */
@Service
public class LinkService
{
	private final LinkRepository repository;

	/** constructor, injected by spring */
	@Autowired
	public LinkService(LinkRepository repository)
	{
		this.repository = repository;
	}


	/**
	 * find LinkEntity objects by category name
	 * @param categoryName name of the category to be searched
	 * @return List of LinkEntity object having the same category name
	 */
	public List<LinkEntity> getLinkEntityByCategory(String categoryName) {
		return repository.findByCategory(categoryName)
						.stream()
						.sorted(Comparator.comparing(LinkEntity::getTitle))
						.collect(Collectors.toList());
	}

	/**
	 * convert from String[] to LinkEntity object
	 * @param line each line read from csv file
	 * @return new LinkEntity object
	 */
	public static LinkEntity convertFromCsv(String[] line) {
		LinkEntity newLink = new LinkEntity();
		newLink.setTitle(line[0]);
		newLink.setUrl(line[1]);
		newLink.setCategory(line[2]);
		newLink.setImageName(line[3]);
		return newLink;
	}
}
