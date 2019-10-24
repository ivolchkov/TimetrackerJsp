package project.repository.activityDAO;

import project.domain.activity.Activity;
import project.repository.CRUDRepository;

import java.util.List;

public interface ActivityDAO extends CRUDRepository<Long, Activity> {
    List<Activity> findByNamePattern(String pattern);
}
