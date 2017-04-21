package com.siemens.midsphere.repositories.jpa;

import org.springframework.data.repository.CrudRepository;

import com.siemens.midsphere.domains.Project;

public interface ProjectRepository extends CrudRepository<Project, String> {

}
