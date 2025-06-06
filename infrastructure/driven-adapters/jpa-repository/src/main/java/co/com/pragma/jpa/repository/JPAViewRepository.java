package co.com.pragma.jpa.repository;

import co.com.pragma.jpa.entities.ViewEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface JPAViewRepository extends CrudRepository<ViewEntity, Long>, QueryByExampleExecutor<ViewEntity> {
    ViewEntity findByIdentifier(String identifier);
}
