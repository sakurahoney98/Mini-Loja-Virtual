package bean;

import javax.servlet.http.HttpSession;

public class Cd {
String titulo="";
String valor="0";
String qtde ="0";

public String getQtde() {
	return qtde;
}

public void setQtde(String qtde) {
	this.qtde = qtde;
}

public Cd() {
	// TODO Auto-generated constructor stub
}

public String getTitulo() {
	return titulo;
}

public void setTitulo(String titulo) {
	this.titulo = titulo;
}

public String getValor() {
	return valor;
}

public void setValor(String valor) {
	this.valor = valor;
	
}


}
