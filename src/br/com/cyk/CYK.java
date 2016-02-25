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
				for(int j=0;j<mat[i-1].length;j++){
					mat[i][j] = "{";
					if(i==1){
						for(int k=0;k<productionsList.size();k++){
							if(productionsList.get(k).getSettings().contains(mat[i-1][j])){
									mat[i][j] += productionsList.get(k).getVariable()+",";
							}						
						}
					} else {
						for(int k=i-1;k>0;k--){							
							if(k+j < mat[i-1].length){
								//System.out.println((mat[k][j]+mat[i-k][k+j]).replaceAll("[{}]", ""));
								for(int l=0;l<productionsList.size();l++){
									if(productionsList.get(l).getSettings().contains((mat[k][j]+mat[i-k][k+j]).replaceAll("[{}]", ""))){
										mat[i][j] += productionsList.get(l).getVariable()+",";
									}
								}
							}
						}
					}
					mat[i][j] += "}";
					mat[i][j] = mat[i][j].replaceAll(",}", "}");
					System.out.print(mat[i][j]);
				}
				System.out.println("");
			}
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}

}
