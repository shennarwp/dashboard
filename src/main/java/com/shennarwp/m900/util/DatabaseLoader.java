package com.shennarwp.m900.util;

import com.shennarwp.m900.data.repository.LinkRepository;
import com.shennarwp.m900.data.service.LinkService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class DatabaseLoader
{
	static Logger logger = LogManager.getLogger(DatabaseLoader.class);
	private final LinkRepository repository;

	@Autowired
	public DatabaseLoader(LinkRepository linkRepository)
	{
		this.repository = linkRepository;
	}

	private List<String[]> readInitialCSV() {
		InputStream is = getClass().getClassLoader().getResourceAsStream("init.csv");
		assert is != null;
		List<String[]> initialData = new BufferedReader(new InputStreamReader(is))
										.lines()
										.map(s -> s.split(";"))
										.collect(Collectors.toList());
		logger.log(Level.INFO, "Successful reading initial data");
		return initialData;
	}

	@EventListener(ApplicationReadyEvent.class)
	public void initData() {
		repository.removeAll();
		readInitialCSV().stream()
				.map(LinkService::convertFromCsv)
				.forEach(repository::save);
		logger.log(Level.INFO, "Successful saving initial data to the database");
	}
}
