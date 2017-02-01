package com.leituradados.teste;

import com.leituradados.controller.Leitor;

public class Teste {

	public static void main(String[] args) {

//		Usuario usuario = new Usuario(3,"Zefa","Tecnica em Tecnologia da Informação");
		
		
		Leitor leitor = new Leitor();
//		leitor.escreverArquivo(usuario);
		
		leitor.leitorArquivo();
		leitor.cadastrarUsuarioBanco();
		
		
		
		
	}
	
}