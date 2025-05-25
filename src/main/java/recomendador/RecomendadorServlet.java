package recomendador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class RecomendadorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String rutaModelo = getServletContext().getRealPath("/WEB-INF/recomendador.model");
            String rutaDatos = getServletContext().getRealPath("/WEB-INF/datos.arff");

            WekaModel modelo = new WekaModel(rutaModelo, rutaDatos);

            // Validar que los parámetros existen y son números
            String nota1Str = request.getParameter("nota1");
            String nota2Str = request.getParameter("nota2");
            String nota3Str = request.getParameter("nota3");

            if (nota1Str == null || nota2Str == null || nota3Str == null) {
                request.setAttribute("error", "Debe ingresar las tres notas.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }

            double nota1 = Double.parseDouble(nota1Str);
            double nota2 = Double.parseDouble(nota2Str);
            double nota3 = Double.parseDouble(nota3Str);

            double[] notas = {nota1, nota2, nota3};
            String cursoRecomendado = modelo.recomendarCurso(notas);

            request.setAttribute("resultado", cursoRecomendado);
            request.getRequestDispatcher("resultado.jsp").forward(request, response);

        } catch (NumberFormatException nfe) {
            request.setAttribute("error", "Las notas deben ser números válidos.");
            request.getRequestDispatcher("index.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al procesar la recomendación: " + e.getMessage());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
