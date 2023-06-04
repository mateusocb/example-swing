package org.example.view.consulta.paciente;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.example.dao.ConsultaDAO;
import org.example.dao.MedicoDAO;
import org.example.dao.PacienteDAO;
import org.example.entity.Consulta;
import org.example.entity.Medico;
import org.example.entity.Paciente;


import static java.lang.Double.parseDouble;

public class ConsultaCreateView extends JFrame {
    private JComboBox<Paciente> cBoxPaciente;
    private JComboBox<Medico> cBoxMedico;
    private final JTextField txtValor;

    private final PacienteDAO pacienteDAO;
    private final MedicoDAO medicoDAO;
    private final ConsultaDAO consultaDAO;

    public ConsultaCreateView() {
        setTitle("Cadastro de Paciente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new GridLayout(4, 2));

        pacienteDAO = new PacienteDAO();
        medicoDAO = new MedicoDAO();
        consultaDAO = new ConsultaDAO();

        buildMedicoComboBox();

        buildPacienteComboBox();

        JLabel lblValor = new JLabel("Valor:");
        add(lblValor);

        txtValor = new JTextField();
        add(txtValor);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(this::onSave);
        add(btnSalvar);
    }

    private void buildPacienteComboBox() {
        Paciente[] pacientes = pacienteDAO.getList().toArray(Paciente[]::new);

        JLabel lblPaciente = new JLabel("Paciente:");
        add(lblPaciente);

        cBoxPaciente = new JComboBox<>(pacientes);
        add(cBoxPaciente);
    }

    private void buildMedicoComboBox() {
        Medico[] medicos = medicoDAO.getList().toArray(Medico[]::new);

        JLabel lblMedico = new JLabel("Medico:");
        add(lblMedico);

        cBoxMedico = new JComboBox<>(medicos);
        add(cBoxMedico);
    }

    private void onSave(ActionEvent actionEvent) {
        Medico medico = (Medico) cBoxMedico.getSelectedItem();
        Paciente paciente = (Paciente) cBoxPaciente.getSelectedItem();

        String valor = txtValor.getText();

        Consulta consulta = new Consulta();
        consulta.setValor(parseDouble(valor));

        consulta.setMedico(medico);
        consulta.setPaciente(paciente);

        consultaDAO.create(consulta);

        JOptionPane.showMessageDialog(null, "Consulta salva com sucesso!");

        dispose();
    }
}