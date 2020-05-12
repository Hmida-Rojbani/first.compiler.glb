package de.tekup.first.compiler.glb.alex;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Symbole {
	
	private String unityLex;
	private String attribut;
	private int val;
	private double valRel;

}
