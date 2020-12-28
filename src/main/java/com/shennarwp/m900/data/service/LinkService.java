package com.shennarwp.m900.data.service;

import com.shennarwp.m900.data.entity.LinkEntity;
import com.shennarwp.m900.data.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LinkService
{
	private final LinkRepository repository;

	@Autowired
	public LinkService(LinkRepository repository)
	{
		this.repository = repository;
	}


	public List<LinkEntity> getLinkEntityByCategory(String categoryName) {
		return repository.findByCategory(categoryName)
						.stream()
						.sorted(Comparator.comparing(LinkEntity::getTitle))
						.collect(Collectors.toList());
	}

	public static LinkEntity convertFromCsv(String[] line) {
		LinkEntity newLink = new LinkEntity();
		newLink.setTitle(line[0]);
		newLink.setUrl(line[1]);
		newLink.setCategory(line[2]);
		newLink.setImageName(line[3]);
		return newLink;
	}


}
