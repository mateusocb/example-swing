package org.example.view.paciente;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.example.dao.PacienteDAO;
import org.example.entity.Paciente;

public class PacienteCreateView extends JFrame {
    private final JTextField txtCpf;
    private final JTextField txtNome;

    private final PacienteDAO pacienteDAO;

    public PacienteCreateView() {
        setTitle("Cadastro de Paciente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new GridLayout(3, 2));

        pacienteDAO = new PacienteDAO();

        JLabel labelNome = new JLabel("Nome:");
        add(labelNome);

        JLabel lblCpf = new JLabel("CPF:");
        add(lblCpf);

        txtNome = new JTextField();
        add(txtNome);

        txtCpf = new JTextField();
        add(txtCpf);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(this::onSave);
        add(btnSalvar);
    }

    private void onSave(ActionEvent actionEvent) {
        String nome = txtNome.getText();
        String cpf = txtCpf.getText();

        Paciente paciente = new Paciente();
        paciente.setNome(nome);
        paciente.setCpf(cpf);

        pacienteDAO.create(paciente);

        JOptionPane.showMessageDialog(null, "Paciente salvo com sucesso!");

        dispose();
    }
}