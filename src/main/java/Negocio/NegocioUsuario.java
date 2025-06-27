package Negocio;

import java.util.List;
import Dao.DaoUsuario;
import Entidades.Usuario;

import java.io.IOException;

import java.sql.CallableStatement;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Date;
import Entidades.Cliente;
import Entidades.Cuentas;
import Entidades.Movimiento;
import Entidades.Usuario;
import Otros.enmTipos;

public interface NegocioUsuario {
    public List<Usuario> listarUsuarios();
}