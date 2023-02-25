package ru.itis.utils.visitors;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.utils.KeywordsCounter;

@Component
public class ExtendsKeywordVisitor extends VoidVisitorAdapter<Void> {
    private final KeywordsCounter counter;

    @Autowired
    public ExtendsKeywordVisitor(KeywordsCounter counter) {
        this.counter = counter;
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration cid, Void arg) {
        if (cid.getExtendedTypes().isNonEmpty()) {
            counter.addKeyword("extends");
        }
        super.visit(cid, arg);
    }
}
