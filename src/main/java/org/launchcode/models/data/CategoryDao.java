package org.launchcode.models.data;

import org.launchcode.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository //tells spring this interface is a repository
@Transactional // specifies methods should be wrapped by a database transaction
public interface CategoryDao extends CrudRepository<Category, Integer>  {

}
