package com.example.workoutdiary.enums

/**
 * Enum representing exercise type.
 */
enum class ExerciseType(val value: String) {
    LEGS("Legs"),
    BACK("Back"),
    CHEST("Chest"),
    ARMS("Arms"),
    BICEPS("Biceps"),
    TRICEPS("Triceps"),
    ABS("Abs"),
    CARDIO("Cardio"),
    RECREATIONAL("Recreational");

    companion object {
        fun stringValues(): List<String> {
            return values().map { type -> type.value }.toList()
        }
    }
}