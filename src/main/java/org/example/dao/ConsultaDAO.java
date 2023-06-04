package org.example.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.example.config.FileDB;
import org.example.entity.Consulta;


import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class ConsultaDAO {
    private final PacienteDAO pacienteDAO;
    private final MedicoDAO medicoDAO;

    public ConsultaDAO() {
        this.pacienteDAO = new PacienteDAO();
        this.medicoDAO = new MedicoDAO();
    }

    public List<Consulta> getList()  {
        File file = FileDB.getFile();
        List<Consulta> consultas = new ArrayList<>();
        Scanner scanner;

        try {
            scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String[] columns = scanner.nextLine().split(",");
                Consulta consulta = new Consulta();

                int crmMedico = parseInt(columns[0]);
                String cpfPaciente = columns[1];

                consulta.setPaciente(pacienteDAO.get(cpfPaciente));
                consulta.setMedico(medicoDAO.get(crmMedico));

                consulta.setValor(parseDouble(columns[2]));

                consultas.add(consulta);
            }

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return consultas;
    }

    public void create(Consulta consulta) {
        try {
            File file = FileDB.getFile();

            String line = ""
                    .concat(String.valueOf(consulta.getMedico().getCrm()))
                    .concat(",")
                    .concat(consulta.getPaciente().getCpf())
                    .concat(",")
                    .concat(String.valueOf(consulta.getValor()));

            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(line + "\n");

            bufferedWriter.close();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
