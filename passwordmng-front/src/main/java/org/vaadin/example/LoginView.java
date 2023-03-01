package org.vaadin.example;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

@Route("")
public class LoginView extends Composite<LoginOverlay> {

    public LoginView(@Autowired PassManService service){
        VaadinSession session = UI.getCurrent().getSession();

        LoginOverlay loginOverlay = getContent();
        loginOverlay.setTitle("StudyFlow");
        loginOverlay.setDescription("Inicio de sesión");
        loginOverlay.setOpened(true);

        loginOverlay.addLoginListener(event -> {

            int userIndex = 0;

            try {
                String mail = event.getUsername();
                String password = event.getPassword();
                boolean authenticated = service.authenticate(mail, password);

                if(authenticated){
                    session.setAttribute("mail", mail);
                    System.out.println("Login hecho");
                    System.out.println(session.getAttribute("mail"));
                    UI.getCurrent().navigate(UserView.class);
                }else{
                    System.out.println("Conexión fallida, credenciales incorrectas");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        });
    }
}
