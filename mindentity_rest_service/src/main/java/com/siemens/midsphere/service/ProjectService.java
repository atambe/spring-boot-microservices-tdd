package com.siemens.midsphere.service;

import org.springframework.stereotype.Service;

import com.siemens.midsphere.domains.Project;

public interface ProjectService {
	
	Project createProject(Project project);

	Project updateProject(Project project);
	
	Project getByProjectId(String projectId);
	
	
}
