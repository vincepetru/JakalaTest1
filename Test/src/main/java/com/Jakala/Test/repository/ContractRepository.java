package com.Jakala.Test.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Jakala.Test.model.Contract;
import com.Jakala.Test.model.User;

@Repository
public interface ContractRepository extends CrudRepository<Contract, Integer>
{
	List<Contract> findByStartdate(Date startDate);
	//List<Contract> findByContracttype(String contractType);
	List<Contract> findByUser(User user);
	@Query(
			"SELECT c FROM Contract c WHERE " 									+
            "(:name IS NULL OR LOWER(c.user.name) LIKE %:name%) AND " 			+
            "(:startdate IS NULL OR c.startdate = :startdate) AND " 			+
            "(:contracttype IS NULL OR c.contracttype = :contracttype) AND "	+
            "(:usertype IS NULL OR LOWER(c.user.usertype) = LOWER(:usertype))"
          )
    List<Contract> searchContracts
    (
            @Param("name") String name,
            @Param("startdate") Date startdate,
            @Param("contracttype") String contracttype,
            @Param("usertype") String usertype
    );
}
