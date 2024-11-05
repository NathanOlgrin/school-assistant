package ru.school_assistant.model;

public enum QualificationCategory {

    INTERN ("Молодой специалист"),
    RELEVANT ("Педагог, соответствующий должности"),
    FIRST ("Педагог 1-й категории"),
    HIGHEST ("Педагог высшей категории");

    private String category;

    QualificationCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return category;
    }
}
