package com.inno.accpa.compiler.syntax_analysis.node.special_form;

import com.inno.accpa.compiler.interpreter.AtomsTable;
import com.inno.accpa.compiler.interpreter.FunctionsTable;
import com.inno.accpa.compiler.syntax_analysis.node.AtomNode;
import com.inno.accpa.compiler.syntax_analysis.node.ElementInterface;
import com.inno.accpa.compiler.syntax_analysis.node.FunctionAtom;
import com.inno.accpa.compiler.syntax_analysis.node.ListNode;

public class ProgNode implements ElementInterface {
    ElementInterface arguments;
    ElementInterface elements;

    public ProgNode(ElementInterface arguments, ElementInterface elements) {
        this.arguments = arguments;
        this.elements = elements;
    }

    @Override
    public ElementInterface evaluate() {
        if (!FunctionAtom.checkFunctionArguments(arguments)) {
            throw new RuntimeException("The first parameter should be a list of atoms" +
                    "that represent the local context of the form." +
                    "Provided: " + arguments);
        }
        AtomsTable.getInstance().introduceLocalContext();
        FunctionsTable.getInstance().introduceLocalContext();

        for (AtomNode atom : FunctionAtom.getListFunctionArguments(arguments)) {
            AtomsTable.getInstance().addAtom(atom);
        }
        if (elements instanceof ListNode) {
            for (ElementInterface element : ((ListNode) this.elements).elements) {
                ElementInterface evaluatedElement = element.evaluate();
                if (evaluatedElement instanceof ReturnNode) {
                    ElementInterface returnResult = ((ReturnNode) evaluatedElement).element.evaluate();
                    AtomsTable.getInstance().leaveLocalContext();
                    FunctionsTable.getInstance().leaveLocalContext();
                    return returnResult;
                }
            }
        } else {
            ElementInterface evaluatedElement = elements.evaluate();
            if (evaluatedElement instanceof ReturnNode) {
                ElementInterface returnResult = ((ReturnNode) evaluatedElement).element.evaluate();
                AtomsTable.getInstance().leaveLocalContext();
                FunctionsTable.getInstance().leaveLocalContext();
                return returnResult;
            }
            AtomsTable.getInstance().leaveLocalContext();
            FunctionsTable.getInstance().leaveLocalContext();
            return evaluatedElement;
        }
        AtomsTable.getInstance().leaveLocalContext();
        FunctionsTable.getInstance().leaveLocalContext();
        return null;
    }

    @Override
    public String toString() {
        return "ProgNode{" +
                "list=" + arguments +
                ", element=" + elements +
                '}';
    }
}
