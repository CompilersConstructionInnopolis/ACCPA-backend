package com.inno.accpa.compiler.lexical_analysis.tokens.keyword;

import com.inno.accpa.compiler.lexical_analysis.tokens.Token;

public class KeywordToken extends Token {
    public KeywordToken(int row, int column, String content) {
        super(row, column, content);
    }
}
