JC := javac
JR := java

CP := -cp /usr/local/lib/antlr-3.5.2-complete.jar
ANTLR := $(JR) $(CP) org.antlr.Tool

GRAMMARS := APL.g gen.g
GENERATED_SOURCES := APLLexer.java APLParser.java gen.java

OPS_SOURCES := APLOperators.java
OPS_OBJECTS := APLOperators.class

COMPILER_SOURCES := Compile.java $(GENERATED_SOURCES)
COMPILER_OBJECTS := Compile.class

SOURCES := $(COMPILER_SOURCES) $(OPS_SOURCES) 
OBJECTS := $(COMPILER_OBJECTS) $(OPS_OBJECTS)

all: $(SOURCES) $(OBJECTS)

$(COMPILER_OBJECTS): $(COMPILER_SOURCES)
	$(JC) $(CP) $(COMPILER_SOURCES)

$(GENERATED_SOURCES): $(GRAMMARS)
	$(ANTLR) $(GRAMMARS)

$(OPS_OBJECTS): $(OPS_SOURCES)
	$(JC) $(OPS_SOURCES)

.apl:
	$(JR) Compile 

clean:
	rm *.tokens
	rm *Lexer.java
	rm *Parser.java
	rm gen.java
	rm *.class
