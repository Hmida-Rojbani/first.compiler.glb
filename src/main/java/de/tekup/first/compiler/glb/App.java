package de.tekup.first.compiler.glb;

import java.util.List;

import de.tekup.first.compiler.glb.alex.AnalyseLex;
import de.tekup.first.compiler.glb.alex.Symbole;
import de.tekup.first.compiler.glb.alsynt.AnalyseSyntax;
import de.tekup.first.compiler.glb.alsynt.TableAnalyse;
import de.tekup.first.compiler.glb.reader.ReadTextAsString;

public class App 
{
    public static void main( String[] args )
    {
    	String s = ReadTextAsString.readFile("C:\\Users\\TekSliver\\Desktop\\test_compiler\\test.txt");
        List<Symbole> list = AnalyseLex.analyse(s);
    	//System.out.println( list.size() );
    	//System.out.println(TableAnalyse.production("E", ";"));
    	//list.forEach(s1-> System.out.print(s1.getLex()+", "));
        AnalyseSyntax.analyse(s);

    }
}
