package org.example.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import org.example.view.consulta.paciente.ConsultaListView;
import org.example.view.medico.MedicoListView;
import org.example.view.paciente.PacienteListView;

public class MainView extends JFrame {
    public MainView() {
        setTitle("Tela Principal");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new GridLayout(1, 1));

        JButton btnPacienteGUI = new JButton("Paciente");
        btnPacienteGUI.addActionListener(this::showPacienteListView);
        add(btnPacienteGUI);

        JButton btnMedicoView = new JButton("Médico");
        btnMedicoView.addActionListener(this::showMedicoListView);
        add(btnMedicoView);

        JButton btnConsultaView = new JButton("Consulta");
        btnConsultaView.addActionListener(this::showConsultaListView);
        add(btnConsultaView);
    }

    public void showPacienteListView(ActionEvent actionEvent) {
        PacienteListView pacienteListView = new PacienteListView();
        pacienteListView.setVisible(true);
    }

    public void showMedicoListView(ActionEvent actionEvent) {
        MedicoListView medicoListView = new MedicoListView();
        medicoListView.setVisible(true);
    }

    public void showConsultaListView(ActionEvent actionEvent) {
        ConsultaListView consultaListView = new ConsultaListView();
        consultaListView.setVisible(true);
    }
}

