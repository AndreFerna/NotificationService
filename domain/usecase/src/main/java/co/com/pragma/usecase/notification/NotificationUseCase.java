package co.com.pragma.usecase.notification;

import co.com.pragma.model.config.ErrorCode;
import co.com.pragma.model.config.PragmaException;
import co.com.pragma.model.notification.Notification;
import co.com.pragma.model.notification.gateways.NotificationRepository;
import co.com.pragma.model.tournament.Tournament;
import co.com.pragma.model.tournament.gateways.TournamentRepository;
import co.com.pragma.model.user.gateways.UserRepository;
import co.com.pragma.model.view.View;
import co.com.pragma.model.view.gateways.ViewRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@RequiredArgsConstructor
public class NotificationUseCase {
    private final NotificationRepository notificationRepository;
    private final TournamentRepository tournamentRepository;
    private final UserRepository userRepository;
    private final ViewRepository viewRepository;

    public void sendNotification(Notification notification) {
        View view = viewRepository.findByIdentifier(notification.getViewIdentifier());

        Set<String> emails = userRepository.findByEmails(notification.getViewIdentifier());
        if (emails.isEmpty()) {
            throw new PragmaException(ErrorCode.B409012);
        }

        Tournament tournament = tournamentRepository.findById(view.getIdTournament());

        LocalDate date = tournament.getStartDate().toLocalDate();
        LocalTime time = tournament.getStartDate().toLocalTime();
        notification.setName(tournament.getName());
        notification.setStartDate(date);
        notification.setStartTime(time);

        notificationRepository.sendNotification(notification, emails);
    }

}
