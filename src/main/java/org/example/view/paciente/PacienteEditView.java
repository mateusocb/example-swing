package org.example.view.paciente;

import org.example.dao.PacienteDAO;
import org.example.entity.Paciente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PacienteEditView extends JFrame {
    private final JTextField txtCpf;
    private final JTextField txtNome;
    private PacienteDAO pacienteDAO;
    private Paciente paciente;
    private String cpf;

    public PacienteEditView(String cpf) {
        setTitle("Edição de Paciente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new GridLayout(3, 2));

        JLabel labelNome = new JLabel( "Nome:");
        JLabel lblCpf = new JLabel("CPF:");

        txtNome = new JTextField();
        txtCpf = new JTextField();

        pacienteDAO = new PacienteDAO();

        this.cpf = cpf;

        paciente = pacienteDAO.get(cpf);

        txtNome.setText(paciente.getNome());
        txtCpf.setText(paciente.getCpf());


        JButton btnSave = new JButton("Salvar");
        btnSave.addActionListener(this::onEditButton);

        add(txtNome);
        add(labelNome);
        add(lblCpf);
        add(txtCpf);
        add(btnSave);
    }

    public void onEditButton(ActionEvent actionEvent) {
        String nome = txtNome.getText();
        String cpf2 = txtCpf.getText();

        paciente.setNome(nome);
        paciente.setCpf(cpf2);

        pacienteDAO.edit(cpf, paciente);

        JOptionPane.showMessageDialog(null, "Paciente editado com sucesso!");

        dispose();
    }
}