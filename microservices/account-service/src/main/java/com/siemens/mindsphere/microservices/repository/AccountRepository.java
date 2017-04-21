package com.siemens.mindsphere.microservices.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.siemens.mindsphere.microservices.domain.Account;

@Component
public class AccountRepository {

	private List<Account> accounts;

	@Autowired
	public AccountRepository(List<Account> accounts) {
		super();
		this.accounts = accounts;
	}

	public Account save(Account account) {
		if (accounts.contains(account)) {
			accounts.remove((accounts.indexOf(account)));
			return accounts.add(account)? account : null;
		} else {
			return accounts.add(account)? account : null;
		}
	}

	public List<Account> findAll() {
		return accounts;
	}

	public Account findOne(String accountNumber) {
		if (null != accountNumber) {
			for (Account account : accounts) {
				if (null != account && account.getAccountNumber().equals(accountNumber)) {
					return account;
				}
			}
		}
		return null;
	}

	public boolean delete(String accountNumber) {
		if (null != accountNumber) {
			return accounts.remove(this.findOne(accountNumber));
		}
		return false;
	}

}
