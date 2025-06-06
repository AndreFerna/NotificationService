package co.com.pragma.model.tournament.gateways;

import co.com.pragma.model.tournament.Tournament;

public interface TournamentRepository {
    Tournament findById(Long id);
}
