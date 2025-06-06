package co.com.pragma.jpa.adapter;

import co.com.pragma.jpa.entities.UserEntity;
import co.com.pragma.jpa.helper.AdapterOperations;
import co.com.pragma.jpa.repository.JPAUserRepository;
import co.com.pragma.model.user.User;
import co.com.pragma.model.user.gateways.UserRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class JPARepositoryUserAdapter extends AdapterOperations<User, UserEntity, String, JPAUserRepository> implements UserRepository {
    public JPARepositoryUserAdapter(JPAUserRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, User.class));
    }

    @Override
    public Set<String> findByEmails(String viewIdentifier) {
        return repository.findByEmails(viewIdentifier);
    }
}
