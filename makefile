JC := javac
JR := java
JAR := jar cf

GRMDIR := grammar
SRCDIR := src
OBJDIR := build
JARDIR := jar

SRCDEST := -fo $(SRCDIR)
OBJDEST := -d $(OBJDIR)
JARDEST := $(JARDIR)/aplc.jar

GRMEXT := g
TKNEXT := tokens
SRCEXT := java
OBJEXT := class

CP := -cp /usr/local/lib/antlr-3.5.2-complete.jar
ANTLR := $(JR) $(CP) org.antlr.Tool

_GRM := APL gen
GRM := $(patsubst %,$(GRMDIR)/%.$(GRMEXT),$(_GRM))

_GEN := APLLexer APLParser gen
GEN := $(patsubst %,$(SRCDIR)/%.$(SRCEXT),$(_GEN))
TKN := $(patsubst %,$(SRCDIR)/%.$(TKNEXT),$(_GRM))
GENOBJ := $(patsubst %,$(OBJDIR)/%.$(OBJEXT),$(_GEN))

_OPS := APLOps
OPS := $(patsubst %,$(SRCDIR)/%.$(SRCEXT),$(_OPS))
OPSOBJ := $(patsubst %,$(OBJDIR)/%.$(OBJEXT),$(_OPS))

_TSR := APLTensor
TSR := $(patsubst %,$(SRCDIR)/%.$(SRCEXT),$(_TSR))
TSROBJ := $(patsubst %,$(OBJDIR)/%.$(OBJEXT),$(_TSR))

_CMP := $(_GEN) Compile
CMP := $(patsubst %,$(SRCDIR)/%.$(SRCEXT),$(_CMP))
CMPOBJ := $(patsubst %,$(OBJDIR)/%.$(OBJEXT),$(_CMP))


SOURCES := $(CMP) $(OPS) $(TSR)
OBJECTS := $(CMPOBJ) $(OPSOBJ) $(TSROBJ)
JARS := $(JARDIR)/aplc.jar

all: $(SOURCES) $(OBJECTS) jar

$(CMPOBJ): $(CMP)
	$(JC) $(CP) $(OBJDEST) $(CMP)

$(GEN): $(GRM)
	$(ANTLR) $(GRM) $(SRCDEST) 

$(TSROBJ): $(TSR) $(OPS)
	$(JC) $(OBJDEST) $(TSR) $(OPS)

$(OPSOBJ): $(OPS) $(TSR)
	$(JC) $(OBJDEST) $(OPS) $(TSR)

jar: $(OBJECTS) $(JARS)

$(JARS): $(OBJECTS)
	$(JAR) $(JARS) -C $(OBJDIR) .

clean:
	rm $(TKN)
	rm $(GEN)
	rm $(OBJDIR)/*
	rm $(JARS)
