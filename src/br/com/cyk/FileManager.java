package br.com.cyk;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
	
	public static List<Production> retrieveProductionsFromFile(String fileName) throws IOException{
		List<Production> list = new ArrayList<Production>();
		File file = new File(fileName);
		
		if(file.exists()){
			FileReader fr = new FileReader(file);			 
			BufferedReader br = new BufferedReader(fr);
			while (br.ready()) {	
				String[] vars = br.readLine().split(" −> |[|]");
				String var = vars[0];
				List<String> settings = new ArrayList<String>();
				for(int i=1;i<vars.length;i++){					
					settings.add(vars[i].replaceAll(" ", ""));
				}
				Production prod = new Production(var,settings);
				list.add(prod);
			}
			br.close();
			fr.close();
		} else{
			System.out.println("Arquivo de entrada inexistente.");
		}
		
		return list;
	}
	
	public static boolean isChomsky(List<Production> list){
				
		for(int i= 0 ; i < list.size() ; i++){
			
			int tamCol = list.get(i).getSettings().size();
			
			
			if(tamCol >= 2){// se houver mais de uma palavras
				
				for(int j = 0; j < tamCol; j++){// percorre palavra por palavra
					
					if(list.get(i).getSettings().get(j).length() >= 2){
						for(int k = 0; k < list.get(i).getSettings().get(j).length(); k++){
							
					        Character caractere = list.get(i).getSettings().get(j).charAt(k);					        
					        
					        if(Character.isLowerCase(caractere)){					        	
					        	return false;
							}
					    } 
						
					}					
					else{
						Character caractere = list.get(i).getSettings().get(j).charAt(0);						
						
						  if(Character.isUpperCase(caractere)){						        	
					        	//Achou minuscula					        	
							  	return false;
					      }
					
					}
					if(i > 0 && list.get(i).getSettings().get(j).equals(".")){
						return false;
					}
					
				}
				
			}
			else{
				
				if(list.get(i).getSettings().get(0).length() >= 2){
					for(int k = 0; k < list.get(i).getSettings().get(0).length(); k++){
						
				        Character caractere = list.get(i).getSettings().get(0).charAt(k);				        
				        
				        if(Character.isLowerCase(caractere)){				        	
				        	return false;
						}
				    } 
					
				}
				else{
					Character caractere = list.get(i).getSettings().get(0).charAt(0);					
					if(Character.isUpperCase(caractere)){						        	
				        	//Achou minuscula				          
						  	return false;
				      }
				
				}
				if(i > 0 && list.get(i).getSettings().get(0).equals(".")){
					return false;
				}
			}
		}
		
		return true;
		
	}
	
	public static void writeResult(String fileName, String[][] mat) throws IOException{
		File file = new File(fileName);
		
		if (!file.exists()) {			
			file.createNewFile();
		} else{
			PrintWriter writer = new PrintWriter(file);
			writer.print("");
			writer.close();
		}
		
		FileWriter fw = new FileWriter(file, true);		
		BufferedWriter bw = new BufferedWriter(fw);
		
		 
		for(int i=mat.length-1;i>=0;i--){
			if(i > 0)
				bw.write(i+"\t");
			else
				bw.write("p\t");
			for(int j=0;j<mat[i].length;j++){				
				bw.write(mat[i][j]+"\t");
			}			
			bw.newLine();
		}	 
				 
		bw.close();
		fw.close();
		
	}
	
}
