package de.tekup.first.compiler.glb.alsynt;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

import de.tekup.first.compiler.glb.alex.AnalyseLex;

public class AnalyseSyntax {

	public static void analyse(String data) {
		List<String> tmp = AnalyseLex.analyse(data).stream()
												   .map(s-> s.getLex())
												   .collect(Collectors.toList());
					tmp.add("$");
		Queue<String> tampon = new LinkedList<String> (tmp);
				
		Stack<String> stack  = new Stack<>();
		stack.push("$");
		stack.push("S");
		
		while (!stack.peek().equals("$") && !tampon.peek().equals("$")) {
			System.out.print(stack);
			System.out.print("  |  ");
			System.out.println(tampon);
			
			String tete_stack = stack.peek();
			String tete_tampon = tampon.peek();
			
			if(tete_stack.equals("S") || tete_stack.equals("E") || tete_stack.equals("E'")) {
				List<String> regle = TableAnalyse.production(tete_stack, tete_tampon);
				if(regle != null) {
					if(regle.get(0).equals("eps")) {
						stack.pop();
					} else if (regle.get(0).equals("sync")) {
						stack.pop();
						System.err.print(tete_stack + " mal formé");
						System.out.println();
					}
					else {
						stack.pop();
						for (int i = regle.size()-1; i >=0; i--) {
							stack.push(regle.get(i));
						}
					}
				} else {
					tampon.poll();
					System.err.print(tete_tampon + " mal placé");
					System.out.println();
				}
			} else {
				if(tete_stack.equals(tete_tampon)) {
					stack.pop();
					tampon.poll();
				} else {
					stack.pop();
					System.err.print(tete_stack + " manquant");
					System.out.println();
				}
			}
			
			
		}
		System.out.print(stack);
		System.out.print("  |  ");
		System.out.println(tampon);
		
	}
}
