package ru.itis.utils;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.github.javaparser.ast.type.ReferenceType;

import java.util.HashMap;
import java.util.Map;

@Component
public class KeywordVisitor extends VoidVisitorAdapter<Void> {
    private final Map<String, Integer> keywordCounts = new HashMap<>();

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

    // Для 2 модуля
    @Override
    public void visit(FieldDeclaration n, Void arg) {
        if (n.isPrivate()) {
            addKeyword("private");
        } else if (n.isProtected()) {
            addKeyword("protected");
        } else if (n.isPublic()) {
            addKeyword("public");
        }
        super.visit(n, arg);
    }

    @Override
    public void visit(MethodDeclaration n, Void arg) {
        if (n.isPrivate()) {
            addKeyword("private");
        } else if (n.isProtected()) {
            addKeyword("protected");
        } else if (n.isPublic()) {
            addKeyword("public");
        }
        super.visit(n, arg);
    }

    @Override
    public void visit(ConstructorDeclaration n, Void arg) {
        if (n.isPrivate()) {
            addKeyword("private");
        } else if (n.isProtected()) {
            addKeyword("protected");
        } else if (n.isPublic()) {
            addKeyword("public");
        }
        super.visit(n, arg);
    }


    // Для 2 модуля


    // Для 3 модуля

    @Override
    public void visit(ThrowStmt n, Void arg) {
        addKeyword("throw");
        super.visit(n, arg);
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration n, Void arg) {
        if (n.getExtendedTypes().size() > 0) {
            addKeyword("extends");
        }
        if (n.isInterface()) {
            for (ClassOrInterfaceType extendsType : n.getExtendedTypes()) {
                addKeyword("extends");
            }
        }
        super.visit(n, arg);
    }

    @Override
    public void visit(ClassOrInterfaceType n, Void arg) {
        if (n.getName().asString().equals("extends")) {
            addKeyword("extends");
        }
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

