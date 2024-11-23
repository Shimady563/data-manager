<template>
  <div class="container">
    <div class="row">
      <div class="col-12">
        <button class="btn btn-dark custom-button" type="button" @click="showStudentModal = true">
          Загрузить список студентов
        </button>
        <button class="btn btn-dark custom-button" type="button" @click="showDisciplineModal = true">
          Загрузить список дисциплин
        </button>
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
      students.value = [
        {fullName: "Иванов Иван Иванович", group: "101", course: "Бакалавриат (1)", hasRecordBook: true},
        {fullName: "Петров Петр Петрович", group: "102", course: "Бакалавриат (2)", hasRecordBook: false},
        {fullName: "Сидорова Анна Сергеевна", group: "201", course: "Магистратура (1)", hasRecordBook: true},
      ];
    };

    const fetchDirections = async () => {
      directions.value = [
        {id: 1, name: "Математическое обеспечение и администрирование информационных систем"},
        {id: 2, name: "Фундаментальная информатика и информационные технологии"},
        {id: 3, name: "Прикладная математика и информатика"},
        {id: 4, name: "Прикладная математика и информатика"}
      ];
    };

    const submitStudentForm = (form) => {
      console.log("Форма студентов:", form);
      alert("Список студентов загружен!");
      showStudentModal.value = false;
    };

    const submitDisciplineForm = (form) => {
      console.log("Форма дисциплин:", form);
      alert("Список дисциплин загружен!");
      showDisciplineModal.value = false;
    };

    const toggleRecordBook = (student) => {
      student.hasRecordBook = !student.hasRecordBook;
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