package org.launchcode.models.data;

import org.launchcode.models.Menu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository //tells spring this interface is a repository
@Transactional // specifies methods should be wrapped by a database transaction
public interface MenuDao extends CrudRepository<Menu, Integer>{
}
