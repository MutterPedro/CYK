package br.com.cyk;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
	
}
