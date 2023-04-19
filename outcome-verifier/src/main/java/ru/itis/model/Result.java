package ru.itis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Module module_id;

    private Grade grade_id;

    private List<JavaKeywords> keywordsUsed;

    private List<JavaKeywords> unusedKeywords;

    private List<Task> solvedTasks;

    private List<Task> unSolvedTasks;
}
