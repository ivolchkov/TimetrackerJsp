package project.repository.backlogDao;

import project.entity.backlog.BacklogEntity;
import project.repository.CrudRepository;

public interface BacklogDao extends CrudRepository<Integer, BacklogEntity> {
}
