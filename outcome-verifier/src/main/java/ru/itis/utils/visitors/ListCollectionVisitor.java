package ru.itis.utils.visitors;

import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.utils.KeywordsCounter;

@Component
public class ListCollectionVisitor extends VoidVisitorAdapter<Void> {

    private final KeywordsCounter keywordsCounter;

    @Autowired
    public ListCollectionVisitor(KeywordsCounter keywordsCounter) {
        this.keywordsCounter = keywordsCounter;
    }

    @Override
    public void visit(ClassOrInterfaceType type, Void arg) {
        if (type.getNameAsString().equals("List")) {
            keywordsCounter.addKeyword("List");
        }
        super.visit(type, arg);
    }
}
