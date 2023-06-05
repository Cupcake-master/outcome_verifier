package ru.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.model.*;
import ru.itis.model.Module;
import ru.itis.repository.RequirementRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RequirementServiceImpl {

    private final RequirementRepository requirementRepository;
    private final ModuleServiceImpl moduleService;

    private final GradeServiceImpl gradeService;

    private final JavaKeywordsServiceImpl keywordsService;

    private final List<Result> results = new ArrayList<>();


    @Autowired
    public RequirementServiceImpl(RequirementRepository requirementRepository, ModuleServiceImpl moduleService,
                                  GradeServiceImpl gradeService, JavaKeywordsServiceImpl keywordsService) {
        this.requirementRepository = requirementRepository;
        this.moduleService = moduleService;
        this.gradeService = gradeService;
        this.keywordsService = keywordsService;
    }

    public List<Requirement> findAll() {
        return requirementRepository.findAll();
    }

    public void checkRepositoryAgainstEvaluationCriteria(Map<Boolean, List<Task>> solvedAndUnSolvedTasks,
                                                         Map<String, Integer> map) {
        List<Task> solvedTasks = solvedAndUnSolvedTasks.get(true);
        List<Task> unsolvedTasks = solvedAndUnSolvedTasks.get(false);
        List<Module> modules = moduleService.findAll();
        List<Grade> grades = gradeService.findAll();
        for (Module module : modules) {
            Result result = createResult(module, solvedTasks, unsolvedTasks, map, grades);
            results.add(result);
        }
        System.out.println(results);
    }

    public List<Result> getResults() {
        return results;
    }

    private Optional<Requirement> findByModuleAndGrade(Long module_id, Long grade_id) {
        Module module = moduleService.findById(module_id).get();
        Grade grade = gradeService.findById(grade_id).get();
        return requirementRepository.findByModuleAndGrade(module, grade);
    }

    private List<JavaKeywords> findAllByModuleAndGrade(Long module_id, Long grade_id) {
        Module module = moduleService.findById(module_id).get();
        Grade grade = gradeService.findById(grade_id).get();
        return keywordsService.findByModuleAndGrade(module, grade);
    }

    private List<JavaKeywords> findAllKeywordsByName(Set<String> strings, Long module_id) {
        List<JavaKeywords> javaKeywords = new ArrayList<>();
        List<JavaKeywords> finalJavaKeywords = javaKeywords;
        strings.stream().map(keywordsService::findByName)
                .forEachOrdered(optionalJavaKeywords ->
                        optionalJavaKeywords.ifPresent(finalJavaKeywords::add));
        javaKeywords = finalJavaKeywords.stream()
                .filter(keyword -> keyword.getModule_id().getId().equals(module_id))
                .collect(Collectors.toList());
        return javaKeywords;
    }

    private Result createResult(Module module, List<Task> solvedTasks, List<Task> unsolvedTasks,
                                Map<String, Integer> map, List<Grade> grades) {
        Result result = new Result();
        result.setModule_id(module);
        List<Task> solvedTasksList = getSolvedTasksList(module, solvedTasks);
        result.setSolvedTasks(solvedTasksList);
        List<Task> unSolvedTasksList = getUnsolvedTasksList(module, unsolvedTasks);
        List<JavaKeywords> usedJavaKeywords = findAllKeywordsByName(map.keySet(), module.getId());
        result.setKeywordsUsed(usedJavaKeywords);
        result.setUnSolvedTasks(unSolvedTasksList);
        Grade grade = getGrade(module, grades, solvedTasksList, usedJavaKeywords);
        result.setGrade_id(grade);
        return result;
    }

    private List<Task> getSolvedTasksList(Module module, List<Task> solvedTasks) {
        return solvedTasks.stream().filter(task ->
                        task.getModule_id().getId().equals(module.getId()))
                .collect(Collectors.toList());
    }

    private List<Task> getUnsolvedTasksList(Module module, List<Task> unsolvedTasks) {
        return unsolvedTasks.stream().filter(task ->
                        task.getModule_id().getId().equals(module.getId()))
                .collect(Collectors.toList());
    }

    private Grade getGrade(Module module, List<Grade> grades, List<Task> solvedTasksList,
                           List<JavaKeywords> usedJavaKeywords) {
        Grade grade = null;
        for (Grade grade1 : grades) {
            int sizeAllKeywordsByModel = findAllByModuleAndGrade(module.getId(), grade1.getId()).size();
            Requirement requirement = findByModuleAndGrade(module.getId(), grade1.getId()).get();
            if (requirement.getRequiredNumberOfTasksSolved() <= solvedTasksList.size()) {
                if (grade != null && grade.getName() > grade1.getName()) {
                    continue;
                } else {
                    if (usedJavaKeywords.size() / sizeAllKeywordsByModel * 100 > requirement.getRequiredPercentageOfKeywordsUsed()) {
                        grade = grade1;
                    }
                }
            }
        }
        return grade;
    }
}
