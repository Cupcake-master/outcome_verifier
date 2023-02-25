package ru.itis.utils;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitor;
import org.springframework.stereotype.Component;
import ru.itis.utils.visitors.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class KeywordsCounter {

    private final Map<String, Integer> keywordCounts = new HashMap<>();

    public Map<String, Integer> getKeywordCounts(CompilationUnit cu) {
        Map<String, VoidVisitor<Void>> visitors = new LinkedHashMap<>();
        visitors.put("tryCatchVisitor", new TryCatchVisitor(this));
        visitors.put("ifElseKeywordVisitor", new IfElseKeywordVisitor(this));
        visitors.put("voidMethodVisitor", new VoidMethodVisitor(this));
        visitors.put("newKeywordVisitor", new NewKeywordVisitor(this));
        visitors.put("breakKeywordVisitor", new BreakKeywordVisitor(this));
        visitors.put("returnKeywordVisitor", new ReturnKeywordVisitor(this));
        visitors.put("forKeywordVisitor", new ForKeywordVisitor(this));
        visitors.put("doKeywordVisitor", new DoKeywordVisitor(this));
        visitors.put("whileKeywordVisitor", new WhileKeywordVisitor(this));
        visitors.put("caseKeywordVisitor", new CaseKeywordVisitor(this));
        visitors.put("continueKeywordVisitor", new ContinueKeywordVisitor(this));
        visitors.put("switchKeywordVisitor", new SwitchKeywordVisitor(this));
        visitors.put("finalKeywordVisitor", new FinalKeywordVisitor(this));
        visitors.put("staticKeywordVisitor", new StaticKeywordVisitor(this));
        visitors.put("accessModifierKeywordVisitor", new AccessModifierKeywordVisitor(this));
        visitors.put("newClassKeywordVisitor", new NewClassKeywordVisitor(this));
        visitors.put("implementsKeywordVisitor", new ImplementsKeywordVisitor(this));
        visitors.put("instanceOfKeywordVisitor", new InstanceOfKeywordVisitor(this));
        visitors.put("interfaceKeywordVisitor", new InterfaceKeywordVisitor(this));
        visitors.put("abstractKeywordVisitor", new AbstractKeywordVisitor(this));
        visitors.put("extendsKeywordVisitor", new ExtendsKeywordVisitor(this));
        visitors.put("enumKeywordVisitor", new EnumKeywordVisitor(this));

        visitors.values().forEach(visitor -> visitor.visit(cu, null));
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
