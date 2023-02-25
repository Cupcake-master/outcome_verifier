package ru.itis.utils.visitors;

import com.github.javaparser.ast.stmt.SwitchEntry;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.utils.KeywordsCounter;
import com.github.javaparser.ast.*;


@Component
public class CaseKeywordVisitor extends VoidVisitorAdapter<Void> {

    private final KeywordsCounter keywordsCounter;

    @Autowired
    public CaseKeywordVisitor(KeywordsCounter keywordsCounter) {
        this.keywordsCounter = keywordsCounter;
    }

    @Override
    public void visit(SwitchStmt switchStmt, Void arg) {
        keywordsCounter.addKeyword("case");
        super.visit(switchStmt, arg);
    }
}
