package co.com.pragma.model.view.gateways;

import co.com.pragma.model.view.View;

public interface ViewRepository {
    View findByIdentifier(String viewIdentifier);
}
