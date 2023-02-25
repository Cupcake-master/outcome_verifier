package ru.itis.utils.visitors;

import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.springframework.stereotype.Component;
import ru.itis.utils.KeywordsCounter;

@Component
public class AbstractKeywordVisitor extends VoidVisitorAdapter<Void> {

    private final KeywordsCounter counter;

    public AbstractKeywordVisitor(KeywordsCounter counter) {
        this.counter = counter;
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration cid, Void arg) {
        if (cid.isAbstract()) {
            counter.addKeyword("abstract");
        }
        super.visit(cid, arg);
    }

    @Override
    public void visit(EnumDeclaration ed, Void arg) {
        if (ed.isEnumConstantDeclaration()) {
            return;
        }
        if (ed.getModifiers().contains(com.github.javaparser.ast.Modifier.abstractModifier())) {
            counter.addKeyword("abstract");
        }
        super.visit(ed, arg);
    }

    @Override
    public void visit(FieldDeclaration fd, Void arg) {
        if (fd.getModifiers().contains(com.github.javaparser.ast.Modifier.abstractModifier())) {
            counter.addKeyword("abstract");
        }
        super.visit(fd, arg);
    }

    @Override
    public void visit(MethodDeclaration md, Void arg) {
        if (md.getModifiers().contains(com.github.javaparser.ast.Modifier.abstractModifier())) {
            counter.addKeyword("abstract");
        }
        super.visit(md, arg);
    }
}
