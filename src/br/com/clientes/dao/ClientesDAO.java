package br.com.clientes.dao;

import br.com.clientes.factory.ConnectionFactory;
import br.com.clientes.model.Clientes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ClientesDAO
{

    //CRUD

    public void save(Clientes clientes) throws Exception
    {
        String sql = "INSERT INTO clientes (nome, idade, cpf ) VALUES (?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try
        {
            conn = ConnectionFactory.createConnectionToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1, clientes.getNome());
            pstm.setString(2, clientes.getCpf());
            pstm.setInt(3, clientes.getIdade());

            pstm.execute();

            System.out.println("Contato salvo com sucesso!");
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if (pstm != null)
                {
                    pstm.close();
                }
                if (conn != null)
                {
                    conn.close();
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void update(Clientes clientes) throws Exception
    {

        String sql = "UPDATE clientes SET nome = ?, cpf = ?, idade = ? " +
                "WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try
        {
            conn = ConnectionFactory.createConnectionToMySQL();

            pstm = conn.prepareStatement(sql);

            pstm.setString(1, clientes.getNome());
            pstm.setString(2, clientes.getCpf());
            pstm.setInt(3, clientes.getIdade());
            pstm.setInt(4, clientes.getId());

            pstm.execute();
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally
        {
            try
            {
                if (pstm!=null)
                {
                    pstm.close();
                }
                if (conn!=null)
                {
                    conn.close();
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void deleteByID(int id)
    {
        String sql = "DELETE FROM clientes WHERE id = ?";

        Connection conn = null;

        PreparedStatement pstm = null;

        try
        {
            conn =  ConnectionFactory.createConnectionToMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setInt(1, id);

            pstm.execute();
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally
        {
            try
            {
                if (pstm!=null)
                {
                    pstm.close();
                }

                if (conn!=null)
                {
                    conn.close();
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public List<Clientes> getClientes() throws Exception
    {
        String sql = "SELECT * FROM clientes";

        List<Clientes> clientes = new ArrayList<Clientes>();

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet reset = null;

        try
        {
            conn = ConnectionFactory.createConnectionToMySQL();

            pstm = conn.prepareStatement(sql);

            reset = pstm.executeQuery();

            while (reset.next())
            {
                Clientes cliente = new Clientes();

                cliente.setId(reset.getInt("id"));
                cliente.setNome(reset.getString("nome"));
                cliente.setIdade(reset.getInt("idade"));
                cliente.setCpf(reset.getString("cpf"));

                clientes.add(cliente);
            }
        }catch(Exception e)
        {
          e.printStackTrace();
        }finally
        {
                try
                {
                    if (reset != null)
                    {
                        reset.close();
                    }
                    if (pstm != null)
                    {
                        pstm.close();
                    }
                    if (conn != null)
                    {
                       conn.close();
                    }
                } catch (Exception e)
                {
                  e.printStackTrace();
                }
        }
        return clientes;
    }
}

