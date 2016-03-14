package br.com.cyk;

import java.io.IOException;
import java.util.List;

public class CYK {

	public static void main(String[] args) {
		try {			
			List<Production> productionsList = FileManager.retrieveProductionsFromFile(args[0]);
			String[][] mat = new String[args[1].length()+1][];		
			mat[0] = args[1].split("");
			mat[1] = new String[args[1].length()];
			for(int i=1;i<=args[1].length();i++){	
				if(i > 1)
					mat[i] = new String[mat[i-1].length-1];
				for(int j=0;j<mat[i].length;j++){
					mat[i][j] = "{";
					if(i==1){
						for(int k=0;k<productionsList.size();k++){
							if(productionsList.get(k).getSettings().contains(mat[i-1][j])){
									mat[i][j] += productionsList.get(k).getVariable()+",";
							}						
						}
					} else {
						//System.out.println(i);
						for(int k=i-1;k>0;k--){													
							String[] var1 = mat[k][j].replaceAll("[{}]", "").split(",");
							String[] var2 = mat[i-k][k+j].replaceAll("[{}]", "").split(",");														
							for(int m=0;m<var1.length;m++){
								for(int n=0;n<var2.length;n++){
									//System.out.print(var1[m]+var2[n]+" - ");
									for(int l=0;l<productionsList.size();l++){
										if(productionsList.get(l).getSettings().contains((var1[m]+var2[n]))){
											//System.out.print("> "+productionsList.get(l).getVariable());
											mat[i][j] = mat[i][j].replace(productionsList.get(l).getVariable()+",", "");
											mat[i][j] += productionsList.get(l).getVariable()+",";
										}
									}
								}
								//System.out.println("\n--------");
							}							
						}
					}
					mat[i][j] += "}";
					mat[i][j] = mat[i][j].replaceAll(",}", "}");
					//System.out.print(mat[i][j]);
				}
				//System.out.println("");
			}
			FileManager.writeResult(args[2], mat);
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}

}
