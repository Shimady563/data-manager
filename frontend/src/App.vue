<template>
  <div class="container">
    <div class="row">
      <div class="col-12">
        <RouterLink to="/student">
          <button class="btn btn-dark custom-button" type="button" @click="showStudentModal = true">
            Загрузить список студентов
          </button>
        </RouterLink>
        <RouterLink to="/discipline">
          <button class="btn btn-dark custom-button" type="button" @click="showDisciplineModal = true">
            Загрузить список дисциплин
          </button>
        </RouterLink>
        <StudentsTable :students="students" @toggle-record-book="toggleRecordBook"/>
      </div>
    </div>

    <StudentModal
        :visible="showStudentModal"
        :directions="directions"
        :form="studentForm"
        @close="showStudentModal = false"
        @submit="submitStudentForm"
    />

    <DisciplineModal
        :visible="showDisciplineModal"
        :directions="directions"
        :form="disciplineForm"
        @close="showDisciplineModal = false"
        @submit="submitDisciplineForm"
    />
  </div>
</template>

<script>
import {ref, onMounted} from "vue";
import StudentsTable from "./components/StudentsTable.vue";
import StudentModal from "./components/StudentModal.vue";
import DisciplineModal from "./components/DisciplineModal.vue";

export default {
  components: {StudentsTable, StudentModal, DisciplineModal},
  setup() {
    const students = ref([]);
    const directions = ref([]);
    const showStudentModal = ref(false);
    const showDisciplineModal = ref(false);

    const studentForm = ref({
      course: "",
      direction: "",
      group: "",
      fileName: null,
    });

    const disciplineForm = ref({
      direction: "",
      fileName: null,
    });

    const fetchStudents = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/v1/students");
        students.value = await response.json();
        console.log("Fetched students from the backend");
      } catch (error) {
        console.error("Error fetching students:", error);
      }
};

    const fetchDirections = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/v1/majors");
        directions.value = await response.json();
        console.log("Fetched majors from the backend");
      } catch (error) {
        console.error("Error fetching disciplines:", error);
      }
    };

    const submitStudentForm = async (form) => {
      console.log(form);
      if (!form.direction) {
        alert("Выберите направление");
        return;
      }
      if (isNaN(form.course) || isNaN(parseInt(form.course))) {
        alert("Курс должен быть числом");
        return;
      }
      if (!form.group) {
        alert("Введите группу");
        return;
      }
      if (!form.file) {
        alert("Выберите файл");
        return;
      }

      try {
        const queryParams = new URLSearchParams({
          major: form.direction,
          group: form.group,
          course: form.course,
        }).toString();

        const formData = new FormData();
        formData.append("file", form.file);

        const response = await fetch(`http://localhost:8000/upload/excel/students/?${queryParams}`, {
          method: "POST",
          body: formData,
        });

        if (!response.ok) {
          const error = await response.json();
          console.error("Error uploading students:", error.detail);
          alert("Возникла ошибка при отправке студентов, попробуйте позже");
          return;
        }

        console.log("Uploaded students");
        alert("Список студентов загружен!");
        showStudentModal.value = false;
      } catch (error) {
        console.error("Error uploading students:", error);
      }
    };

    const submitDisciplineForm = async (form) => {
      if (!form.direction) {
        alert("Выберите направление");
        return;
      }
      if (!form.file) {
        alert("Выберите файл");
        return;
      }

      try {
        const queryParams = new URLSearchParams({
          major: form.direction,
        }).toString();

        const formData = new FormData();
        formData.append("file", form.file);

        const response = await fetch(`http://localhost:8000/upload/excel/disciplines?${queryParams}`, {
          method: "POST",
          body: formData,
        });

        if (!response.ok) {
          const error = await response.json();
          console.error("Error uploading disciplines:", error.detail);
          alert("Возникла ошибка при отправке дисциплин, попробуйте позже");
          return;
        }

        alert("Список дисциплин загружен!");
        showDisciplineModal.value = false;
      } catch (error) {
        console.error("Error uploading disciplines:", error);
      }
    };

    const toggleRecordBook = async (student, id) => {
      student.hasRecordBook = !student.hasRecordBook;
      try {
        const response = await fetch("http://localhost:8080/api/v1/students/" + id, {
          method: "PUT",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(student)
        })
        console.log("Updated student's record book");
      } catch (error) {
        console.error("Error updating student's record book:", error);
      }
    };

    onMounted(() => fetchStudents());
    onMounted(() => fetchDirections());

    return {
      students,
      directions,
      showStudentModal,
      showDisciplineModal,
      studentForm,
      disciplineForm,
      submitStudentForm,
      submitDisciplineForm,
      toggleRecordBook,
    };
  },
};
</script>