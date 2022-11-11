package component

import data.Course
import kotlinx.js.Record
import kotlinx.js.get
import react.FC
import react.Props
import react.StateInstance
import react.dom.html.ReactHTML.div
import react.router.useParams

external interface CoursePickerProps : Props {
    var courses: List<StateInstance<Course>>
}

val CCoursePicker = FC<CoursePickerProps>("CoursePicker") { props ->
    val params: Record<String, String> = useParams()
    val courseName = params["name"]
    val courseStateInstance = props.courses.firstOrNull {
        it.component1().name == courseName
    }
    if (courseStateInstance == null) {
        div {
//            +"No course with name $courseName"
        }
    } else {
        CCourse {
            course = courseStateInstance.component1()
            setCourse = courseStateInstance.component2()
        }
    }
}