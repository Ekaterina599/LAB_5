package data

data class Course(
    val name: String,
    val students: List<Student>,
    val marked: List<Boolean> = students.map { false },
    val grades: List<Int> = students.map{1}
)
val courseList =
    listOf("Math", "Phys", "Story").map { Course(it, studentList) }