package com.siemens.midsphere.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siemens.midsphere.domains.Project;
import com.siemens.midsphere.repositories.jpa.ProjectRepository;
import com.siemens.midsphere.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {
	
	
	@Autowired
	ProjectRepository repository;

	@Override
	public Project createProject(Project project) {
		// TODO Auto-generated method stub
		return repository.save(project);
	}

	@Override
	public Project updateProject(Project project) {
		// TODO Auto-generated method stub
		return repository.save(project);
	}

	@Override
	public Project getByProjectId(String projectId) {
		// TODO Auto-generated method stub
		return repository.findOne(projectId);
	}

}
