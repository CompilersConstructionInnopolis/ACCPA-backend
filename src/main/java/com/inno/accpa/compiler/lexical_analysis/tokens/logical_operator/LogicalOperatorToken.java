package com.inno.accpa.compiler.lexical_analysis.tokens.logical_operator;

import com.inno.accpa.compiler.lexical_analysis.tokens.Token;

public class LogicalOperatorToken extends Token {
    public LogicalOperatorToken(int row, int column, String content) {
        super(row, column, content);
    }
}
