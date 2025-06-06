package co.com.pragma.jpa.repository;

import co.com.pragma.jpa.entities.TournamentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface JPATournamentRepository extends CrudRepository<TournamentEntity, Long>, QueryByExampleExecutor<TournamentEntity> {

    TournamentEntity findByUniqueCode(String uniqueCode);
}
