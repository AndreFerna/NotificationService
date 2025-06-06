package co.com.pragma.jpa.repository;

import co.com.pragma.jpa.entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;
import java.util.Set;

public interface JPAUserRepository extends CrudRepository<UserEntity, String>, QueryByExampleExecutor<UserEntity> {


    @Query(value = """
    select u.correo
    from usuario u
    left join venta v on v.usuario_id = u.id
    left join vista vi on vi.id = v.vista_id
    where vi.identificador = ?1
    """, nativeQuery = true)
    Set<String> findByEmails(String viewIdentifier);
}
