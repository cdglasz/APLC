/*******************************************************************************
 *  This is the toplevel driver program for the APL compiler
 *  For language details please see the grammar file 'grammar.txt'.
 *******************************************************************************/

import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.*;
import java.io.*;
import javax.tools.*;

public class Compile {
    
    public static void main(String[] args) throws Exception {
        
        // check if we have an input file, if not print out error and abort
        if (args.length != 1) {
            System.out.println("Usage: java Compile <input file>");
            System.exit(0);
        } else if (!args[0].endsWith(".apl")) {
            System.out.println("Input file must have a .apl extension");
            System.exit(0);
        }
        
        String name = args[0].substring(0, args[0].indexOf('.'));
        
        // set up and initialize our lexer and parser objects
        // open up the input file
        ANTLRFileStream input = new ANTLRFileStream(args[0]);
        // create the lexer with the input stream
        APLLexer lexer = new APLLexer(input);
        // create a token stream for the parser
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // create a parser object with the token stream
        APLParser parser = new APLParser(tokens);
        
        // call the toplevel recursive descent parsing function to construct
        // our IR
        CommonTree ast = (CommonTree)parser.prog().getTree();
        //        System.err.println("AST: "+ast.toStringTree());
        //        System.err.println("Vars: "+parser.user_variables().toString());
        //        System.err.println("Funcs: "+parser.user_functions().toString());
        
        
        // tree node stream for code generator
        CommonTreeNodeStream nodes = new CommonTreeNodeStream(ast);
        // create new tree parser
        gen generator = new gen(nodes);
        
        // Create the code
        String code = header(name);
        code += generator.prog();
        code += footer(generator.functions());
        
        BufferedWriter output = null;
        try {
            File file = new File(name + ".java");
            output = new BufferedWriter(new FileWriter(file));
            output.write(code);
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            if ( output != null ) output.close();
        }
        
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int compilationResult = compiler.run(null, null, null, name + ".java");
        if (compilationResult == 0) {
            System.out.println("Compilation complete.");
            System.out.println("Run with 'java " + name + "'.");
        } else {
            System.out.println("Compilation Failed");
        }
    }
    
    public static String header(String name) {
        String code = "import java.util.*;\n";
        code += "public class " + name + " {\n";
        code += "\tpublic static void main(String[] args) {\n";
        code += "\t\tdouble[] left, right;\n";
        return code;
    }
    
    public static String footer(String functions) {
        String code = "\t}\n";
        code += functions;
        code += "}\n";
        return code;
    }
}