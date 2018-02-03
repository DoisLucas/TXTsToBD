/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Beans.Esqueleto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author plocabral
 */
public class EsqueletoDAO {

    private Connection con = null;

    public EsqueletoDAO() throws Exception {
        con = BancoConnection.getConnection();
    }

    public boolean add_cliente(ArrayList<Esqueleto> array) {

        String sql = "INSERT INTO leitura.tabela_txt (num_documento, cedente_documento, sacado_documento, valor_documento, valor_baixa) VALUES (?,?,?,?,?)";

        PreparedStatement stmt = null;

        try {

            for (Esqueleto esqueleto : array) {
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, esqueleto.getNum_domcumento());
                stmt.setString(2, esqueleto.getCedente_documento());
                stmt.setString(3, esqueleto.getSacado_documento());
                stmt.setFloat(4, esqueleto.getValor_documento());
                stmt.setFloat(5, esqueleto.getValor_baixa());
                System.out.println(stmt);
                stmt.executeUpdate();
            }

            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            //JOptionPane.showMessageDialog(Singleton.getInstancia().getEntrar(), "Alerta: " + ex);
            return false;
        } finally {
            BancoConnection.closeConnection(con, stmt);
        }

    }
}