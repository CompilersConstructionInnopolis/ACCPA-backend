package com.inno.accpa.compiler.lexical_analysis.tokens.parenthesis;

import com.inno.accpa.compiler.lexical_analysis.tokens.Token;

public class ParenthesisToken extends Token {
    public ParenthesisToken(int row, int column, String content) {
        super(row, column, content);
    }
}
