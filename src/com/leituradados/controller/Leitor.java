package com.leituradados.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.leituradados.model.UsuarioDAO;

public class Leitor {

	private File dir = new File("../Arquivos");
	private File arquivo = new File(dir, "nomes.txt");
	private ArrayList<Usuario> listaUsuarios = new ArrayList<>();
	private UsuarioDAO uDAO = new UsuarioDAO();

	//métodos do arquivo de texto
	
	public void escreverArquivo(Usuario usuario) {

		try {
			if (dir.exists())
				if (arquivo.exists()) {
					FileWriter fileWriter = new FileWriter(arquivo, true); // o
																			// parametro
																			// false
																			// indica
																			// que
																			// td
																			// que
																			// tem
																			// dentro
																			// do
																			// arquivo
																			// será
																			// removido
					PrintWriter printWriter = new PrintWriter(fileWriter);

					printWriter.print(usuario.getId() + ";");
					printWriter.print(usuario.getNome() + ";");
					printWriter.println(usuario.getObservacao());

					printWriter.flush();
					printWriter.close();
				}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void leitorArquivo() {

		try {

			FileReader fileReader = new FileReader(arquivo); // arquivo que será
																// lido
			BufferedReader bufferedReader = new BufferedReader(fileReader); // objeto
																			// que
																			// permite
																			// fazer
																			// a
																			// leitura

			String linha = "";
			List<String> result = new ArrayList<>();

			while ((linha = bufferedReader.readLine()) != null) {
				if (linha != null && !linha.isEmpty()) {
					result.add(linha);
				}
			}

			fileReader.close();
			bufferedReader.close();

			// separar cada linha do arquivo
			for (String s : result) {
				String[] usuario = s.split(";");

				Usuario u = new Usuario();
				u.setId(Integer.valueOf(usuario[0]));
				u.setNome(usuario[1]);
				u.setObservacao(usuario[2]);

				System.out.println(u.toString());

				
//				uDAO.cadastrarUsuario(u);
				listaUsuarios.add(u);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void cadastrarUsuarioBanco (){
		try {
			for (Usuario u : listaUsuarios) {
				if(uDAO.existeUsuario(u))
					System.out.println("ERRO! Usuário já existe.");
				uDAO.cadastrarUsuario(u);
			}
			//return listaUsuarios;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
