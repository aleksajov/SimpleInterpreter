# Variables
JAVAC = javac
JAVA = java

SRC_DIR = src
BIN_DIR = bin
MISC_DIR = misc

LEXER_SRC = $(MISC_DIR)/Lexer.jflex
PARSER_SRC = $(MISC_DIR)/Parser.cup

LEXER_OUT = $(SRC_DIR)/Lexer.java
PARSER_OUT = $(SRC_DIR)/parser.java
SYMBOLS_OUT = $(SRC_DIR)/sym.java

MAIN_CLASS = Main

# Default target
all: $(BIN_DIR)/$(MAIN_CLASS).class

# Generate lexer
$(LEXER_OUT): $(LEXER_SRC)
	$(JAVA) -d $(SRC_DIR) $(LEXER_SRC)

# Generate parser
$(PARSER_OUT) $(SYMBOLS_OUT): $(PARSER_SRC)
	$(JAVA) -cp ../:java-cup-11a.jar -destdir $(SRC_DIR) $(PARSER_SRC)

# Compile Java files
$(BIN_DIR)/%.class: $(SRC_DIR)/%.java $(LEXER_OUT) $(PARSER_OUT) $(SYMBOLS_OUT)
	@mkdir -p $(BIN_DIR)
	$(JAVAC) -d $(BIN_DIR) $<

# Run the program
run: all
	$(JAVA) -cp $(BIN_DIR) $(MAIN_CLASS)

# Clean up
clean:
	rm -rf $(BIN_DIR) $(LEXER_OUT) $(PARSER_OUT) $(SYMBOLS_OUT)

.PHONY: all run clean