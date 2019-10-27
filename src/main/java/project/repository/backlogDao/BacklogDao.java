package project.repository.backlogDao;

import project.domain.backlog.Backlog;
import project.repository.CrudRepository;

public interface BacklogDao extends CrudRepository<Integer, Backlog> {
}
