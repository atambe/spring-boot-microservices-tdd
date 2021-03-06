package com.siemens.midsphere.repositories.jpa;

import org.springframework.data.repository.CrudRepository;

import com.siemens.midsphere.domains.Address;

public interface AddressRepository extends CrudRepository<Address, String> {

}
