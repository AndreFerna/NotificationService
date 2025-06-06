package co.com.pragma.jpa.mapper;

import co.com.pragma.jpa.entities.TournamentEntity;
import co.com.pragma.model.tournament.Tournament;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TournamentMapper {

    public static Tournament toDomain(TournamentEntity tournamentEntity) {
        return Tournament.builder()
                .idTournament(tournamentEntity.getId())
                .name(tournamentEntity.getName())
                .description(tournamentEntity.getDescription())
                .startDate(tournamentEntity.getStartDate())
                .endDate(tournamentEntity.getEndDate())
                .uniqueCode(tournamentEntity.getUniqueCode())
                .build();
    }

}
