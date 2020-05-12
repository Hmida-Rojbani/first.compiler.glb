package de.tekup.first.compiler.glb.alex;

import java.util.ArrayList;
import java.util.List;

public class AnalyseLex {
	
	public static List<Symbole> analyse(String data) {
		List<Symbole> list = new ArrayList<Symbole>();
		int index = 0;
		Symbole sb = null;
		while (index < data.length()) {
			char c = data.charAt(index);
			if( c == ' ' || c == '\t' || c== '\n'){
				index ++;
			}else if (Character.isAlphabetic(c) ) {
				sb = new Symbole();
				index = automate_id(data, index, sb);
				list.add(sb);
			} else if(Character.isDigit(c)) {
				sb = new Symbole();
				index = automate_nb(data, index, sb);
				list.add(sb);
			} else if(c == '>') {
				sb = new Symbole();
				index = automate_pg(data, index, sb);
				list.add(sb);
			}
			
			else {
				index ++;
			}
			
		}
		
		
		return list;
	}
	
	private static int automate_pg(String data, int index, Symbole sb) {
		StringBuilder lexem = new StringBuilder("");
		int etat =0;
		
		while (etat != 3 && index < data.length()) {
			char c = data.charAt(index);
			switch (etat) {
			case 0:
				if(c  == '>') {
					etat = 1;
					lexem.append(c);

				}
				break;
			
			case 1:
				if( c =='=') {
					etat =2;
					lexem.append(c);
				}
				else {
					etat = 3;
					
					sb.setUnityLex("oprel");
					sb.setAttribut("PGQ");
					index --;
				}

				break;
			case 2:
				etat = 3;
				
				sb.setUnityLex("oprel");
				sb.setAttribut("PGE");
				index --;
				break;
			}
			index++;
		}
		
		return index;
	}
	
	private static int automate_nb(String data, int index, Symbole sb) {
		StringBuilder lexem = new StringBuilder("");
		int etat =0;
		
		while (etat != 3 && index < data.length()) {
			char c = data.charAt(index);
			switch (etat) {
			case 0:
				if(Character.isDigit(c)) {
					etat = 1;
					lexem.append(c);

				}
				break;
			
			case 1:
				if( c =='.') {
					etat =2;
					lexem.append(c);
				}
				else if(!Character.isDigit(c) ) {
					etat = 3;
					
					sb.setUnityLex("nb");
					sb.setVal(Integer.parseInt(lexem.toString()));
					index --;
				}
				else
				{
					lexem.append(c);

				}
				break;
			case 2:
				if(!Character.isDigit(c) ) {
					etat = 3;
					
					sb.setUnityLex("nbr");
					sb.setValRel(Double.parseDouble(lexem.toString()));
					index --;
				}
				else
				{
					lexem.append(c);

				}
				break;
			}
			index++;
		}
		
		return index;
	}
	
	private static int automate_id(String data, int index, Symbole sb) {
		
		StringBuilder lexem = new StringBuilder("");
		int etat =0;
		
		while (etat != 2 && index < data.length()) {
			char c = data.charAt(index);
			switch (etat) {
			case 0:
				if(Character.isAlphabetic(c)) {
					etat = 1;
					lexem.append(c);

				}
				break;
			
			case 1:
				if(!Character.isAlphabetic(c) && !Character.isDigit(c)) {
					etat = 2;
					// TODO check with KeyWord Array, Ids Array
					sb.setUnityLex("id");
					sb.setAttribut(lexem.toString());
					index --;
				}
				else
				{
					lexem.append(c);

				}
				break;
			}
			index++;
		}
		
		
		return index;
	}

}
