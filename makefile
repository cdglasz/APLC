JC := javac
JR := java

CP := -cp /usr/local/lib/antlr-3.5.2-complete.jar
ANTLR := $(JR) $(CP) org.antlr.Tool

GRAMMARS := APL.g gen.g
GENERATED_SOURCES := APLLexer.java APLParser.java gen.java

RUNTIME_LIB_SOURCES := APLOperators.java
RUNTIME_LIB_OBJECTS := APLOperators.class

COMPILER_SOURCES := Compile.java $(GENERATED_SOURCES)
COMPILER_OBJECTS := Compile.class

SOURCES := $(COMPILER_SOURCES) $(RUNTIME_LIB_SOURCES)
OBJECTS := $(COMPILER_OBJECTS) $(RUNTIME_LIB_OBJECTS)

all: $(SOURCES) $(OBJECTS)

$(COMPILER_OBJECTS): $(COMPILER_SOURCES)
	$(JC) $(CP) $(COMPILER_SOURCES)

$(GENERATED_SOURCES): $(GRAMMARS)
	$(ANTLR) $(GRAMMARS)

$(RUNTIME_LIB_OBJECTS): $(RUNTIME_LIB_SOURCES)
	$(JC) $(RUNTIME_LIB_SOURCES)

clean:
	rm *.tokens
	rm *Lexer.java
	rm *Parser.java
	rm gen.java
	rm *.class
