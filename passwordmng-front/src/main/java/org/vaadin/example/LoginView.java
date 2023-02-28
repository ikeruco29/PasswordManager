package org.vaadin.example;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route
public class LoginView extends Composite<LoginOverlay> {

    public LoginView(@Autowired PassManService service){
        LoginOverlay loginOverlay = getContent();
        loginOverlay.setTitle("StudyFlow");
        loginOverlay.setDescription("Inicio de sesión");
        loginOverlay.setOpened(true);

        loginOverlay.addLoginListener(event -> {

        service.loginUsername(event.getUsername(), event.getPassword());

        });
    }
}
