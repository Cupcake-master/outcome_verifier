package ru.itis.utils;

import com.github.javaparser.ast.CompilationUnit;
import org.springframework.stereotype.Component;
import ru.itis.utils.visitors.IfElseVisitor;
import ru.itis.utils.visitors.TryCatchVisitor;
import ru.itis.utils.visitors.VoidMethodVisitor;

import java.util.HashMap;
import java.util.Map;

@Component
public class KeywordsCounter {

    private final Map<String, Integer> keywordCounts = new HashMap<>();

    public Map<String, Integer> getKeywordCounts(CompilationUnit cu) {
        TryCatchVisitor tryCatchVisitor = new TryCatchVisitor(this);
        IfElseVisitor ifElseVisitor = new IfElseVisitor(this);
        VoidMethodVisitor voidMethodVisitor = new VoidMethodVisitor(this);
        tryCatchVisitor.visit(cu, null);
        ifElseVisitor.visit(cu, null);
        voidMethodVisitor.visit(cu, null);
        return keywordCounts;
    }

    public void addKeyword(String identifier) {
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
