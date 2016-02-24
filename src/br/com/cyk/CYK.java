package br.com.cyk;

import java.io.IOException;
import java.util.List;

public class CYK {

	public static void main(String[] args) {
		try {
			args = new String[2];
			args[0]="/root/workspace/CYK/src/br/com/cyk/glc.txt";
			args[1]="aaabbb";
			List<Production> productionsList = FileManager.retrieveProductionsFromFile(args[0]);
			String[][] mat = new String[args[1].length()][args[1].length()];		
			mat[0] = args[1].split("");
			for(int i=1;i<args[1].length();i++){
				for(int j=0;j<mat[i-1].length;j++)
					for(int k=0;k<productionsList.size();k++){
						mat[i][k] = "{";
						for(int l=0;l<productionsList.get(k).getSettings().size();l++){
							if(productionsList.get(k).getSettings().get(l).equals(mat[i-1][k])){
								mat[i][k]+=productionsList.get(k).getVariable()+" ";
							}
						}
						mat[i][k] +="}";
						System.out.println(mat[i][k]);
					}
			}
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}

}
