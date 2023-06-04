package org.example.view.paciente;

import org.example.dao.PacienteDAO;
import org.example.entity.Paciente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PacienteCreateView extends JFrame {
    private JTextField txtCpf;
    private JTextField txtNome;

    private PacienteDAO pacienteDAO;

    public PacienteCreateView() {
        setTitle("Cadastro de Paciente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new GridLayout(3, 2));

        JLabel labelNome = new JLabel("Nome:");
        add(labelNome);

        JLabel lblCpf = new JLabel("CPF:");
        add(lblCpf);

        txtNome = new JTextField();
        add(txtNome);

        txtCpf = new JTextField();
        add(txtCpf);

        pacienteDAO = new PacienteDAO();

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