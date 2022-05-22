package com.inno.accpa.compiler.syntax_analysis.node.special_form;

import com.inno.accpa.compiler.interpreter.AtomsTable;
import com.inno.accpa.compiler.interpreter.DefinedFunction;
import com.inno.accpa.compiler.interpreter.PredefinedFunction;
import com.inno.accpa.compiler.syntax_analysis.node.AtomNode;
import com.inno.accpa.compiler.syntax_analysis.node.ElementInterface;

public class SetQNode implements ElementInterface {
    AtomNode atom;
    ElementInterface element;

    public SetQNode(AtomNode atom, ElementInterface element) {
        this.atom = atom;
        this.element = element;
    }

    @Override
    public ElementInterface evaluate() {
        if (PredefinedFunction.isKeyword(atom.name)) {
            throw new RuntimeException("Can't reassign keyword: " + atom.name);
        }
        if (DefinedFunction.isDefinedFunction(atom.name)) {
            throw new RuntimeException("There is a defined function: " + atom.name);
        }
        atom.value = element.evaluate();
        AtomsTable.getInstance().addAtom(atom);
        return null;
    }

    @Override
    public String toString() {
        return "SetQ{" +
                "atom=" + atom +
                ", element=" + element +
                '}';
    }
}
