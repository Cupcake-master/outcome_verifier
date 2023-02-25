package ru.itis.utils.visitors;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.type.VoidType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.utils.KeywordsCounter;

@Component
public class VoidMethodVisitor extends VoidVisitorAdapter<Void> {

    private final KeywordsCounter keywordsCounter;

    @Autowired
    public VoidMethodVisitor(KeywordsCounter keywordsCounter) {
        this.keywordsCounter = keywordsCounter;
    }

    @Override
    public void visit(MethodDeclaration n, Void arg) {
        if (n.getType() instanceof VoidType) {
            keywordsCounter.addKeyword("void");
        }
        super.visit(n, arg);
    }
}
