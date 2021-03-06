package org.launchcode.models.data;

import org.launchcode.models.Cheese;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by LaunchCode
 */
@Repository //tells spring this interface is a repository
@Transactional // specifies methods should be wrapped by a database transaction
public interface CheeseDao extends CrudRepository<Cheese, Integer> {
}
//No primative types (int) in this
//Crud repository gives methods to put data into database and get it out
//DAO data access object
//crud create read update delete