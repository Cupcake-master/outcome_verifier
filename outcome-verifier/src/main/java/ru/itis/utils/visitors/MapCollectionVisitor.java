package ru.itis.utils.visitors;

import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.springframework.stereotype.Component;
import ru.itis.utils.KeywordsCounter;

@Component
public class MapCollectionVisitor extends VoidVisitorAdapter<Void> {
    private final KeywordsCounter counter;

    public MapCollectionVisitor(KeywordsCounter counter) {
        this.counter = counter;
    }

    @Override
    public void visit(ClassOrInterfaceType type, Void arg) {
        if (type.getNameAsString().equals("Map")) {
            counter.addKeyword("Map");
        }
        super.visit(type, arg);
    }
}
