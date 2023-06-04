package org.example.view.medico;

import org.example.dao.MedicoDAO;
import org.example.entity.Medico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MedicoEditView extends JFrame {
    private final JTextField txtCrm;
    private final JTextField txtNome;
    private MedicoDAO medicoDAO;
    private Medico medico;
    private String crm;

    public MedicoEditView(String crm) {
        setTitle("Edição de Médico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new GridLayout(3, 2));

        JLabel labelNome = new JLabel( "Nome:");
        add(labelNome);

        txtNome = new JTextField();
        txtNome.setText(medico.getNome());
        add(txtNome);

        JLabel lblCpf = new JLabel("CPF:");
        add(lblCpf);

        txtCrm = new JTextField();
        txtCrm.setText(String.valueOf(medico.getCrm()));
        add(txtCrm);

        medicoDAO = new MedicoDAO();

        this.crm = crm;

        medico = medicoDAO.get(crm);

        JButton btnSave = new JButton("Salvar");
        btnSave.addActionListener(this::onEditButton);

        add(btnSave);
    }

    public void onEditButton(ActionEvent actionEvent) {
        String nome = txtNome.getText();
        String crm = txtCrm.getText();

        medico.setNome(nome);
        medico.setCrm(Integer.valueOf(crm));

        medicoDAO.edit(crm, medico);

        JOptionPane.showMessageDialog(null, "Paciente editado com sucesso!");

        dispose();
    }
}