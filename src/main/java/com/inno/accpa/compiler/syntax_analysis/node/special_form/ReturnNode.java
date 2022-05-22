package com.inno.accpa.compiler.syntax_analysis.node.special_form;

import com.inno.accpa.compiler.syntax_analysis.node.ElementInterface;

public class ReturnNode implements ElementInterface {
    public ElementInterface element;

    public ReturnNode(ElementInterface element) {
        this.element = element;
    }

    @Override
    public ElementInterface evaluate() {
        return this;
    }

    @Override
    public String toString() {
        return "ReturnNode{" +
                "element=" + element +
                '}';
    }
}
