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
            Result result = new Result();
            result.setModule_id(module);
            List<Task> solvedTasksList = solvedTasks.stream().filter(task ->
                            task.getModule_id().getId().equals(module.getId()))
                    .collect(Collectors.toList());
            result.setSolvedTasks(solvedTasksList);
            List<Task> unSolvedTasksList = unsolvedTasks.stream().filter(task ->
                            task.getModule_id().getId().equals(module.getId()))
                    .collect(Collectors.toList());
            result.setKeywordsUsed(findAllKeywordsByName(map.keySet(), module.getId()));
            result.setUnSolvedTasks(unSolvedTasksList);
            Grade grade = null;
            for (Grade grade1 : grades) {
                Requirement requirement = findByModuleAndGrade(module.getId(), grade1.getId()).get();
                if (requirement.getRequiredNumberOfTasksSolved() <= solvedTasksList.size()) {
                    if (grade != null && grade.getName() > grade1.getName()) {
                        continue;
                    } else {
                        grade = grade1;
                    }
                }
            }
            result.setGrade_id(grade);
            results.add(result);
        }
        System.out.println(results);
    }

    public List<Result> getResults(){
        return results;
    }

    private Optional<Requirement> findByModuleAndGrade(Long module_id, Long grade_id) {
        Module module = moduleService.findById(module_id).get();
        Grade grade = gradeService.findById(grade_id).get();
        return requirementRepository.findByModuleAndGrade(module, grade);
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
}
