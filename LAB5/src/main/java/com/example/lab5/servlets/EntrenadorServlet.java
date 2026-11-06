package com.example.lab5.servlets;

import com.example.lab5.beans.Entrenador;
import com.example.lab5.daos.DaoClase;
import com.example.lab5.daos.DaoEntrenador;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// value = "/inicio" -> la vista queda en http://localhost:8080/inicio
// (con el Application context de Tomcat en "/")
@WebServlet(name = "EntrenadorServlet", value = "/inicio")
public class EntrenadorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        DaoEntrenador daoEntrenador = new DaoEntrenador();
        DaoClase daoClase = new DaoClase();
        RequestDispatcher view;

        switch (action) {
            case "lista":
                request.setAttribute("listaEntrenadores", daoEntrenador.listarEntrenadores());
                view = request.getRequestDispatcher("entrenador/inicio.jsp");
                view.forward(request, response);
                break;

            case "formCrear":
                view = request.getRequestDispatcher("entrenador/nuevoEntrenador.jsp");
                view.forward(request, response);
                break;

            case "editar": {
                Entrenador entrenador = obtenerDesdeParametro(request, daoEntrenador);
                if (entrenador == null) {
                    response.sendRedirect(request.getContextPath() + "/inicio");
                } else {
                    request.setAttribute("entrenador", entrenador);
                    view = request.getRequestDispatcher("entrenador/editarEntrenador.jsp");
                    view.forward(request, response);
                }
                break;
            }

            case "detalle": {
                Entrenador entrenador = obtenerDesdeParametro(request, daoEntrenador);
                if (entrenador == null) {
                    response.sendRedirect(request.getContextPath() + "/inicio");
                } else {
                    request.setAttribute("entrenador", entrenador);
                    request.setAttribute("listaClases",
                            daoClase.listarClasesPorEntrenador(entrenador.getIdEntrenador()));
                    view = request.getRequestDispatcher("entrenador/detalleEntrenador.jsp");
                    view.forward(request, response);
                }
                break;
            }

            case "borrar": {
                boolean borrado = false;
                try {
                    borrado = daoEntrenador.borrarEntrenador(Integer.parseInt(request.getParameter("id")));
                } catch (NumberFormatException ignored) {
                }
                if (borrado) {
                    response.sendRedirect(request.getContextPath() + "/inicio");
                } else {
                    // Se avisa en la vista que no se pudo borrar
                    response.sendRedirect(request.getContextPath() + "/inicio?error=borrar");
                }
                break;
            }

            default:
                response.sendRedirect(request.getContextPath() + "/inicio");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action") == null ? "crear" : request.getParameter("action");
        DaoEntrenador daoEntrenador = new DaoEntrenador();

        Entrenador entrenador = new Entrenador();
        entrenador.setNombre(request.getParameter("nombre"));
        entrenador.setEspecialidad(request.getParameter("especialidad"));
        entrenador.setTelefono(request.getParameter("telefono"));

        switch (action) {
            case "crear":
                daoEntrenador.registrarEntrenador(entrenador);
                break;
            case "actualizar":
                entrenador.setIdEntrenador(Integer.parseInt(request.getParameter("idEntrenador")));
                daoEntrenador.actualizarEntrenador(entrenador);
                break;
        }
        response.sendRedirect(request.getContextPath() + "/inicio");
    }

    // Lee el parámetro "id" de la URL y busca al entrenador (null si no es válido)
    private Entrenador obtenerDesdeParametro(HttpServletRequest request, DaoEntrenador dao) {
        try {
            return dao.obtenerEntrenadorPorId(Integer.parseInt(request.getParameter("id")));
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
