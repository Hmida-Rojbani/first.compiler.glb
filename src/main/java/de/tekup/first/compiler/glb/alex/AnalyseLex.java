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
			//System.out.println("c : "+c);
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
			} else if(c == '<') {
				sb = new Symbole();
				index = automate_pp(data, index, sb);
				list.add(sb);
			} else if(c == '=') {
				sb = new Symbole();
				index = automate_eg(data, index, sb);
				list.add(sb);
			} else if(c == '+') {
				sb = new Symbole();
				index = automate_add(data, index, sb);
				list.add(sb);
			} else if(c == '-') {
				sb = new Symbole();
				index = automate_sous(data, index, sb);
				list.add(sb);
			} else if(c == '*') {
				sb = new Symbole();
				index = automate_mul(data, index, sb);
				list.add(sb);
			} else if(c == '/') {
				sb = new Symbole();
				index = automate_div(data, index, sb);
				list.add(sb);
			} else if(c == '(') {
				sb = new Symbole();
				index = automate_po(data, index, sb);
				list.add(sb);
			} else if(c == ')') {
				sb = new Symbole();
				index = automate_pf(data, index, sb);
				list.add(sb);
			} else if(c == ':') {
				sb = new Symbole();
				index = automate_dp(data, index, sb);
				list.add(sb);
			} else if(c == ';') {
				sb = new Symbole();
				index = automate_pv(data, index, sb);
				list.add(sb);
			}
			
			else {
				index ++;
			}
			
		}
		
		
		return list;
	}
	
	private static int automate_add(String data, int index, Symbole sb) {
		int etat =0;
		
		while (etat != 1 && index < data.length()) {
			char c = data.charAt(index);
			switch (etat) {
			case 0:
				if(c  == '+') {
					etat = 1;
					sb.setUnityLex("oparth");
					sb.setAttribut("ADD");
					sb.setLex("+");

				}
				break;
			
			}
			index++;
		}
		
		return index;
	}
	
	private static int automate_sous(String data, int index, Symbole sb) {
		int etat =0;
		
		while (etat != 1 && index < data.length()) {
			char c = data.charAt(index);
			switch (etat) {
			case 0:
				if(c  == '-') {
					etat = 1;
					sb.setUnityLex("oparth");
					sb.setAttribut("MINUS");
					sb.setLex("-");

				}
				break;
			
			}
			index++;
		}
		
		return index;
	}
	
	private static int automate_mul(String data, int index, Symbole sb) {
		int etat =0;
		
		while (etat != 1 && index < data.length()) {
			char c = data.charAt(index);
			switch (etat) {
			case 0:
				if(c  == '*') {
					etat = 1;
					sb.setUnityLex("oparth");
					sb.setAttribut("MUL");
					sb.setLex("*");

				}
				break;
			
			}
			index++;
		}
		
		return index;
	}
	
	private static int automate_div(String data, int index, Symbole sb) {
		int etat =0;
		
		while (etat != 1 && index < data.length()) {
			char c = data.charAt(index);
			switch (etat) {
			case 0:
				if(c  == '/') {
					etat = 1;
					sb.setUnityLex("oparth");
					sb.setAttribut("DIV");
					sb.setLex("/");

				}
				break;
			
			}
			index++;
		}
		
		return index;
	}
	
	private static int automate_po(String data, int index, Symbole sb) {
		int etat =0;
		
		while (etat != 1 && index < data.length()) {
			char c = data.charAt(index);
			switch (etat) {
			case 0:
				if(c  == '(') {
					etat = 1;
					sb.setUnityLex("sep");
					sb.setAttribut("PO");
					sb.setLex("(");

				}
				break;
			
			}
			index++;
		}
		
		return index;
	}
	
	private static int automate_pf(String data, int index, Symbole sb) {
		int etat =0;
		
		while (etat != 1 && index < data.length()) {
			char c = data.charAt(index);
			switch (etat) {
			case 0:
				if(c  == ')') {
					etat = 1;
					sb.setUnityLex("sep");
					sb.setAttribut("PF");
					sb.setLex(")");

				}
				break;
			
			}
			index++;
		}
		
		return index;
	}
	
	private static int automate_pv(String data, int index, Symbole sb) {
		int etat =0;
		
		while (etat != 1 && index < data.length()) {
			char c = data.charAt(index);
			switch (etat) {
			case 0:
				if(c  == ';') {
					etat = 1;
					sb.setUnityLex("sep");
					sb.setAttribut("PV");
					sb.setLex(";");

				}
				break;
			
			}
			index++;
		}
		
		return index;
	}
	
	private static int automate_dp(String data, int index, Symbole sb) {
		StringBuilder lexem = new StringBuilder("");
		int etat =0;
		
		while (etat != 3 && index < data.length()) {
			char c = data.charAt(index);
			switch (etat) {
			case 0:
				if(c  == ':') {
					etat = 1;
					lexem.append(c);

				}
				break;
			
			case 1:
				if( c =='=') {
					etat = 2;
					lexem.append(c);
				}
				else {
					etat = 3;
					
					sb.setUnityLex("sep");
					sb.setAttribut("DP");
					sb.setLex(":");
					index --;
				}

				break;
			case 2:
				etat = 3;
				
				sb.setUnityLex("sep");
				sb.setAttribut("AFF");
				sb.setLex(":=");
				index --;
				break;
			}
			index++;
		}
		
		return index;
	}
	
	private static int automate_eg(String data, int index, Symbole sb) {
		int etat =0;
		
		while (etat != 1 && index < data.length()) {
			char c = data.charAt(index);
			switch (etat) {
			case 0:
				if(c  == '=') {
					etat = 1;
					sb.setUnityLex("oprel");
					sb.setAttribut("EGL");
					sb.setLex("=");
					

				}
				break;
			
			}
			index++;
		}
		
		return index;
	}

	
	private static int automate_pp(String data, int index, Symbole sb) {
		StringBuilder lexem = new StringBuilder("");
		int etat =0;
		
		while (etat != 4 && index < data.length()) {
			char c = data.charAt(index);
			switch (etat) {
			case 0:
				if(c  == '<') {
					etat = 1;
					lexem.append(c);

				}
				break;
			
			case 1:
				if( c =='=') {
					etat =2;
					lexem.append(c);
				} else if( c =='>') {
					etat =3;
					lexem.append(c);
				}
				else {
					etat = 4;
					
					sb.setUnityLex("oprel");
					sb.setAttribut("PPQ");
					sb.setLex("<");
					index --;
				}

				break;
			case 2:
				etat = 4;
				
				sb.setUnityLex("oprel");
				sb.setAttribut("PPE");
				sb.setLex("<=");
				index --;
				break;
			case 3:
				etat = 4;
				
				sb.setUnityLex("oprel");
				sb.setAttribut("DIF");
				sb.setLex("<>");
				index --;
				break;
			}
			index++;
		}
		
		return index;
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
					sb.setLex(">");
					index --;
				}

				break;
			case 2:
				etat = 3;
				
				sb.setUnityLex("oprel");
				sb.setAttribut("PGE");
				sb.setLex(">=");
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
					sb.setLex("nb");
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
					sb.setLex("nbr");
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
					if (KeyWord.in(lexem.toString())) {
						sb.setUnityLex("keyword");
						sb.setRangeId(-1);
						sb.setLex(lexem.toString());
						sb.setAttribut(lexem.toString());
						index --;
					} else {
						
						sb.setUnityLex("id");
						sb.setLex("id");
						sb.setRangeId(Ids.add(lexem.toString()));
						sb.setAttribut(lexem.toString());
						index --;
					}
					
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
