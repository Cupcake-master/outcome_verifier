package ru.itis.utils;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class KeywordVisitor extends VoidVisitorAdapter<Void> {
    private Map<String, Integer> keywordCounts = new HashMap<>();

    public Map<String, Integer> getKeywordCounts() {
        return keywordCounts;
    }

    @Override
    public void visit(ForStmt n, Void arg) {
        addKeyword("for");
        super.visit(n, arg);
    }

    @Override
    public void visit(IfStmt n, Void arg) {
        addKeyword("if");
        super.visit(n, arg);
    }

    @Override
    public void visit(WhileStmt n, Void arg) {
        addKeyword("while");
        super.visit(n, arg);
    }

    /**
    @Override
    public void visit(NameExpr n, Void arg) {
        addKeyword(n.getName().getIdentifier());
        super.visit(n, arg);
    }

    @Override
    public void visit(SimpleName n, Void arg) {
        addKeyword(n.getIdentifier());
        super.visit(n, arg);
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration n, Void arg) {
        addKeyword(n.getName().getIdentifier());
        super.visit(n, arg);
    }

    @Override
    public void visit(FieldDeclaration n, Void arg) {
        addKeyword(n.getModifiers().toString());
        super.visit(n, arg);
    }
     **/

    @Override
    public void visit(VariableDeclarator n, Void arg) {
        addKeyword(n.getTypeAsString());
        super.visit(n, arg);
    }

    // Для 3 модуля
    @Override
    public void visit(TryStmt n, Void arg) {
        addKeyword("try");
        super.visit(n, arg);
    }

    @Override
    public void visit(CatchClause n, Void arg) {
        addKeyword("catch");
        super.visit(n, arg);
    }
    // Для 3 модуля

    private void addKeyword(String identifier) {
        System.out.println(identifier);
        switch (identifier) {
            case "abstract":
            case "assert":
            case "boolean":
            case "break":
            case "byte":
            case "case":
            case "catch":
            case "char":
            case "class":
            case "const":
            case "continue":
            case "default":
            case "do":
            case "double":
            case "else":
            case "enum":
            case "extends":
            case "false":
            case "final":
            case "finally":
            case "float":
            case "for":
            case "if":
            case "goto":
            case "implements":
            case "import":
            case "instanceof":
            case "int":
            case "interface":
            case "long":
            case "native":
            case "new":
            case "null":
            case "package":
            case "private":
            case "protected":
            case "public":
            case "return":
            case "short":
            case "static":
            case "strictfp":
            case "super":
            case "switch":
            case "synchronized":
            case "this":
            case "throw":
            case "throws":
            case "transient":
            case "true":
            case "try":
            case "void":
            case "volatile":
            case "while":
                Integer count = keywordCounts.get(identifier);
                if (count == null) {
                    count = 0;
                }
                keywordCounts.put(identifier, count + 1);
                break;
        }
    }
}

