package co.com.pragma.model.user.gateways;

import java.util.List;
import java.util.Set;

public interface UserRepository {

    Set<String> findByEmails (String viewIdentifier);

}
