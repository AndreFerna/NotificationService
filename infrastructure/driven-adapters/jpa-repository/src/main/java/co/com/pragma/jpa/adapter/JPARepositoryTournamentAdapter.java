package co.com.pragma.jpa.adapter;

import co.com.pragma.jpa.entities.TournamentEntity;
import co.com.pragma.jpa.helper.AdapterOperations;
import co.com.pragma.jpa.mapper.TournamentMapper;
import co.com.pragma.jpa.repository.JPATournamentRepository;
import co.com.pragma.model.config.ErrorCode;
import co.com.pragma.model.config.PragmaException;
import co.com.pragma.model.tournament.Tournament;
import co.com.pragma.model.tournament.gateways.TournamentRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JPARepositoryTournamentAdapter extends AdapterOperations<Tournament, TournamentEntity, Long, JPATournamentRepository>
        implements TournamentRepository {

    public JPARepositoryTournamentAdapter(JPATournamentRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Tournament.class));
    }

    @Override
    public Tournament findById(Long id) {
        Optional<TournamentEntity> tournamentEntity = repository.findById(id);
        if (tournamentEntity.isEmpty()) {
            throw new PragmaException(ErrorCode.B409006);
        }
        return TournamentMapper.toDomain(tournamentEntity.get());
    }
}
