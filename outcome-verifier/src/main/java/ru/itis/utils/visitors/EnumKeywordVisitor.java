package ru.itis.utils.visitors;

import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.utils.KeywordsCounter;

@Component
public class EnumKeywordVisitor extends VoidVisitorAdapter<Void> {

    private final KeywordsCounter counter;

    @Autowired
    public EnumKeywordVisitor(KeywordsCounter counter) {
        this.counter = counter;
    }

    @Override
    public void visit(EnumDeclaration ed, Void arg) {
        counter.addKeyword("enum");
        super.visit(ed, arg);
    }
}
