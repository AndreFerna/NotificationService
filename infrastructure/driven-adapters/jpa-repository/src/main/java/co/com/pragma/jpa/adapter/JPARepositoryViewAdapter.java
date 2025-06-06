package co.com.pragma.jpa.adapter;

import co.com.pragma.jpa.entities.ViewEntity;
import co.com.pragma.jpa.helper.AdapterOperations;
import co.com.pragma.jpa.mapper.ViewMapper;
import co.com.pragma.jpa.repository.JPAViewRepository;
import co.com.pragma.model.config.ErrorCode;
import co.com.pragma.model.config.PragmaException;
import co.com.pragma.model.view.View;
import co.com.pragma.model.view.gateways.ViewRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class JPARepositoryViewAdapter extends AdapterOperations<View, ViewEntity, Long, JPAViewRepository> implements ViewRepository {

    public JPARepositoryViewAdapter(JPAViewRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, View.class));
    }

    @Override
    public View findByIdentifier(String viewIdentifier) {
        ViewEntity viewEntity = repository.findByIdentifier(viewIdentifier);
        if (Objects.isNull(viewEntity)) {
            throw new PragmaException(ErrorCode.B409008);
        }
        return ViewMapper.toDomain(viewEntity);
    }

}
