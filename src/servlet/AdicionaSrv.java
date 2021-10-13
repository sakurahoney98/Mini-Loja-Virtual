package servlet;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Cd;

/**
 * Servlet implementation class AdicionaSrv
 */
@WebServlet("/AdicionaSrv")
public class AdicionaSrv extends HttpServlet {
	ArrayList<Cd> list = new ArrayList<Cd>();
	double valorTotal = 0;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdicionaSrv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aux ="";
		for (int i = 1; i < 5; i++){
		aux = "titulo"+i;
		String titulo = request.getParameter(aux);
		aux="valor"+i;
		String valor = request.getParameter(aux);
		aux="quantidade"+i;
		String qtde = request.getParameter(aux) != null ? request.getParameter(aux).trim():"";
		
		
		
		if(qtde != null && !qtde.equals("") && !qtde.equals("0")) {
			Cd novoCd = new Cd();
			novoCd.setTitulo(titulo);
			novoCd.setValor(valor);
			novoCd.setQtde(qtde);
			
			this.list.add(novoCd);
		}
		}
		this.valorTotal = 0;
		for(Cd c: this.list) {
			String auxD = "";
			for(int i = 0; i < c.getValor().length() - 1 ;i++) {
				if(!c.getValor().substring(i, i + 1).equals(",")) {
				auxD += c.getValor().substring(i, i + 1);	
				}else {
					auxD +=".";
				}
			}
			
			double auxVT = (Double.parseDouble(auxD) - (Double.parseDouble(auxD) * 0.35)) * Double.parseDouble(c.getQtde());
			this.valorTotal +=  auxVT;
		}
		HttpSession sessao = request.getSession();
		
		DecimalFormat dm = new DecimalFormat("#,##0.00");
		dm.setRoundingMode(RoundingMode.DOWN);
		sessao.setAttribute("valorT", dm.format(this.valorTotal));
		
		
		
		sessao.setAttribute("lista", this.list);
		
		
		response.sendRedirect("Pagina_Inicial.html");
	}





	
	@Override
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		for(int i = 0; i < list.size();){
			String aux = request.getParameter(list.get(i).toString()) != null ? request.getParameter(list.get(i).toString()):"false";
			if(aux.equals("on")) {
				this.list.remove(list.get(i));
			}else {
				i++;
			}
			
		}
		
		this.valorTotal = 0;
		
		for(Cd c: this.list) {
			
			String auxD = "";
			for(int i = 0; i < c.getValor().length() - 1 ;i++) {
				if(!c.getValor().substring(i, i + 1).equals(",")) {
				auxD += c.getValor().substring(i, i + 1);	
				}else {
					auxD +=".";
				}
			}
			
			double auxVT = (Double.parseDouble(auxD) - (Double.parseDouble(auxD) * 0.35)) * Double.parseDouble(c.getQtde());
			this.valorTotal +=  auxVT;
		}
		

		HttpSession sessao = request.getSession();
		
		DecimalFormat dm = new DecimalFormat("#,##0.00");
		dm.setRoundingMode(RoundingMode.DOWN);
		
		sessao.setAttribute("valorT", dm.format(this.valorTotal));
		
		sessao.setAttribute("lista", this.list);
		
		response.sendRedirect("Carrinho.jsp");
	}

	public ArrayList<Cd> getList() {
		return list;
	}


	
	

}
