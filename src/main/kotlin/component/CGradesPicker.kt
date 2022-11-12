package component

import data.Course
import data.Student
import kotlinx.js.Record
import kotlinx.js.get
import react.FC
import react.Props
import react.StateInstance
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.ol
import react.router.useParams
import react.useContext

external interface GradesPickerProps : Props {
    var courseState: List<StateInstance<Course>>
}

val CGradesPicker = FC<GradesPickerProps>("GradesPicker") { props ->
    val params: Record<String, String> = useParams()
    val gradesName = params["name"]
    var studentList = listOf<Student>()
    props.courseState.map { studentList = it.component1().students }
    val studentListProgress = mutableListOf<Student>()
    var count = 0
    when (gradesName) {
        "excellent student" -> {
            studentList.mapIndexed { indexStudent, student ->
                props.courseState.map {
                    it.component1().grades.mapIndexed { indexGrade, grade ->
                        if (indexGrade == indexStudent && grade == 5)
                            count += 1
                    }
                }
                if (count == 3) studentListProgress.add(student)
                count = 0
            }
        }
        "good student" -> {
            studentList.mapIndexed { indexStudent, student ->
                props.courseState.map {
                    it.component1().grades.mapIndexed { indexGrade, grade ->
                        if (indexGrade == indexStudent && grade == 4)
                            count += 1
                        else if (indexGrade == indexStudent && grade == 5)
                            count += 2
                        else if (indexGrade == indexStudent && grade <= 3)
                            count -= 2
                    }
                }
                if (count in 3..5) studentListProgress.add(student)
                count = 0
            }
        }
        "loser student" -> {
            studentList.mapIndexed { indexStudent, student ->
                props.courseState.map {
                    it.component1().grades.mapIndexed { indexGrade, grade ->
                        if (indexGrade == indexStudent && grade <= 2)
                            count += 1
                    }
                }
                if (count > 0) studentListProgress.add(student)
                count = 0
            }
        }
    }
    div {
        ol {
            studentListProgress.map {
                li {
                    if (useContext(primer).first == "Short")
                        +"${it.firstname[0]}. ${it.surname}"
                    else
                        +"${it.firstname} ${it.surname}"
                }
            }
        }
    }
}
