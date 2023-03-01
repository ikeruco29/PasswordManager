package org.vaadin.example;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.List;

@Route("/main")
public class UserView extends VerticalLayout {

    public UserView(@Autowired PassManService service) throws Exception {
        VaadinSession session = UI.getCurrent().getSession();

        VerticalLayout layoutPrincipal = new VerticalLayout();
        layoutPrincipal.add(redesGrid(service));

        //add(new H1("Bienvenido " + session.getAttribute("mail")));
        add(layoutPrincipal);
    }

    private VerticalLayout redesGrid(@Autowired PassManService service) throws Exception {
        HorizontalLayout inputs = new HorizontalLayout();
        VerticalLayout results = new VerticalLayout();

        Grid<Red> grid = new Grid<>();
        List<Red> lista;
        User usuario = service.getUser((String) UI.getCurrent().getSession().getAttribute("mail"));
        lista = service.getRedes(usuario.getUserIndex());

        grid.addColumn(Red::getNomRed).setHeader("Nombre");
        grid.addColumn(Red::getMail).setHeader("Email");
        grid.addColumn(Red::getPassword).setHeader("Contraseña");

        grid.setItems(lista);
        results.add(grid);

        Dialog dialog = new Dialog();
        dialog.setModal(true);
        dialog.getElement().setAttribute("aria-label", "Añadir nuevo producto");
        VerticalLayout dialogLayout = createDialogLayout(service, dialog);
        dialog.add(dialogLayout);

        Button aniadir = new Button("Añadir red social", e -> dialog.open());

        return new VerticalLayout(results, aniadir, dialog);
    }


    private VerticalLayout createDialogLayout(@Autowired PassManService service, Dialog dialog) throws ParseException {
        // CREACIÓN DE TODOS LOS ELEMENTOS PARA INTRODUCIR TEXTO
        HorizontalLayout hText = new HorizontalLayout();
        TextField nombre = new TextField();
        nombre.setLabel("Nombre de la red");

        TextField mail = new TextField();
        mail.setLabel("Email");

        TextField password = new TextField();
        password.setLabel("Contraseña");
        password.setWidthFull();

        // Creamos el horizontal layout para meter los botones después
        HorizontalLayout h = new HorizontalLayout();
        Button boton = new Button("Cerrar", e -> dialog.close());   // Botón para cerrar el diálogo

        Button botonAniadir = new Button("Añadir", e -> {
            try {
                Red red = new Red();  // Objeto anime que se va a añadir
                red.setNomRed(nombre.getValue());   // Introducción de variables
                red.setMail(mail.getValue());
                red.setPassword(password.getValue());
                red.setRedId(0);
                red.setUserId(service.getUser((String) UI.getCurrent().getSession().getAttribute("mail")).getUserIndex());
                System.out.println(red);
                service.addRed(red);

                Notification notification = Notification
                        .show("Red social añadida");

                dialog.close(); // Cerramos el diálogo

                UI.getCurrent().getPage().reload();

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        hText.add(nombre, mail);
        HorizontalLayout h2 = new HorizontalLayout();
        h2.add(password);
        h.add(boton, botonAniadir);
        VerticalLayout v = new VerticalLayout();
        v.add(hText, h2, h);

        return v;
    }

}
