package co.com.pragma.awsses;

import co.com.pragma.model.notification.Notification;
import co.com.pragma.model.notification.gateways.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ses.SesAsyncClient;
import software.amazon.awssdk.services.ses.model.*;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class SesService implements NotificationRepository {

    private final SesAsyncClient sesAsyncClient;

    @Override
    public void sendNotification(Notification notification, Set<String> emails) {

        String subject = "¡El Torneo Virtual ha comenzado! Prepárate";
        String content = """
                Hola estimados,
                                
                ¡Es momento de comenzar la batalla! Te damos la bienvenida al Torneo Virtual [Nombre del Torneo], 
                que dará inicio oficialmente el día [Fecha de inicio] a las [Hora].
                """;

        content = content
                .replace("[Nombre del Torneo]", notification.getName())
                .replace("[Fecha de inicio]", notification.getStartDate().toString())
                .replace("[Hora]", notification.getStartTime().toString());

        SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                .destination(Destination.builder().toAddresses(emails).build())
                .message(Message.builder().subject(Content.builder().data(subject).build()).body(Body.builder().text(Content.builder().data(content).build()).build()).build())
                .source("andrea.fernandez@pragma.com.co")
                .build();
        try {
            CompletableFuture<SendEmailResponse> reponse = sesAsyncClient.sendEmail(sendEmailRequest);
            log.info("Id de mensaje enviado {}", reponse.get().messageId());
        } catch (Exception e) {
            log.error("ocurrio un error al enviar el correo {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
