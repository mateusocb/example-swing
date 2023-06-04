package org.example.view.medico;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.example.dao.MedicoDAO;
import org.example.entity.Medico;


import static java.lang.Integer.parseInt;

public class MedicoEditView extends JFrame {
    private final JTextField txtCrm;
    private final JTextField txtNome;
    private final MedicoDAO medicoDAO;
    private final Medico medico;
    private final String crm;

    public MedicoEditView(String crm) {
        setTitle("Edição de Médico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new GridLayout(3, 2));

        this.crm = crm;

        medicoDAO = new MedicoDAO();
        medico = medicoDAO.get(parseInt(crm));

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

        JButton btnSave = new JButton("Salvar");
        btnSave.addActionListener(this::onEditButton);

        add(btnSave);
    }

    public void onEditButton(ActionEvent actionEvent) {
        String nome = txtNome.getText();
        String crm = txtCrm.getText();

        medico.setNome(nome);
        medico.setCrm(parseInt(crm));

        medicoDAO.edit(this.crm, medico);

        JOptionPane.showMessageDialog(null, "Paciente editado com sucesso!");

        dispose();
    }
}