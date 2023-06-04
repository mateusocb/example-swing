package org.example.view.medico;

import org.example.dao.MedicoDAO;
import org.example.entity.Medico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MedicoCreateView extends JFrame {
    private JTextField txtCrm;
    private JTextField txtNome;
    private JTextField txtVh;
    private MedicoDAO medicoDAO;

    public MedicoCreateView() {
        setTitle("Cadastro de Médico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new GridLayout(5, 2));

        medicoDAO = new MedicoDAO();

        JLabel lblNome = new JLabel("Nome:");
        add(lblNome);

        txtNome = new JTextField();
        add(txtNome);

        JLabel lblCrm = new JLabel("CRM:");
        add(lblCrm);

        txtCrm = new JTextField();
        add(txtCrm);

        JLabel lblVh = new JLabel("Valor Hora:");
        add(lblVh);

        txtVh = new JTextField();
        add(txtVh);


        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(this::onSaveButton);
        add(btnSalvar);
    }

    private void onSaveButton(ActionEvent actionEvent) {
        String nome = txtNome.getText();
        String crm = txtCrm.getText();
        String valorHora = txtVh.getText();

        Medico medico = new Medico();

        medico.setNome(nome);
        medico.setCrm(Integer.valueOf(crm));
        medico.setValorHora(Double.valueOf(valorHora));

        medicoDAO.create(medico);

        dispose();
    }
}