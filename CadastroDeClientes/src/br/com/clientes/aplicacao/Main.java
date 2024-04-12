package br.com.clientes.aplicacao;

import br.com.clientes.dao.ClientesDAO;
import br.com.clientes.model.Clientes;

public class Main {
    public static void main(String[] args) throws Exception {

        ClientesDAO clientesDAO = new ClientesDAO();

        Clientes clientes = new Clientes(1, "José Avelar", "685350180777", 25);
        clientes.setNome("José Avelar");
        clientes.setIdade(25);
        clientes.setCpf("685350180777");

        clientesDAO.save(clientes);
        Clientes c1 = new Clientes(1,"José Avelar Martins","685350180777" ,28);
        c1.setNome("José Avelar Martins");
        c1.setCpf("685350180777");
        c1.setIdade(28);
        c1.setId(1);

        clientesDAO.update(c1);

        clientesDAO.deleteByID(1);

        for (Clientes c : clientesDAO.getClientes()) {
            System.out.println("Cliente: " + c.getNome());
        }

    }
}

