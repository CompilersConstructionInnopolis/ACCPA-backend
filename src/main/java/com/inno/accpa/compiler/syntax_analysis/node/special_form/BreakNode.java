package com.inno.accpa.compiler.syntax_analysis.node.special_form;

import com.inno.accpa.compiler.interpreter.NestedFormBreak;
import com.inno.accpa.compiler.syntax_analysis.node.ElementInterface;

public class BreakNode implements ElementInterface {
    @Override
    public ElementInterface evaluate() {
        NestedFormBreak.getInstance().leaveLocalScope();
        return this;
    }

    @Override
    public String toString() {
        return "BreakNode";
    }
}
