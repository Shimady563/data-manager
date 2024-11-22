<template>
  <div class="container">
    <!-- Фильтры -->
    <div class="row">
      <div class="col-12 col">

        <!-- Кнопка -->
        <button class="btn btn-dark custom-button" type="button" @click="addStudents">
          Добавить студентов
        </button>

        <!-- Таблица студентов -->
        <StudentsTable
            :students="students"
            @toggle-record-book="toggleRecordBook"
        />
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from "vue";
import StudentsTable from "./components/StudentsTable.vue";

export default {
  components: { StudentsTable },
  setup() {
    const students = ref([
      { fullName: "Иванов Иван Иванович", group: "101", course: "Бакалавриат (1)", hasRecordBook: true },
      { fullName: "Петров Петр Петрович", group: "102", course: "Бакалавриат (2)", hasRecordBook: false },
      { fullName: "Сидорова Анна Сергеевна", group: "201", course: "Магистратура (1)", hasRecordBook: true },
    ]);

    const addStudents = () => alert("Добавление студентов");

    const toggleRecordBook = (student) => {
      student.hasRecordBook = !student.hasRecordBook;
    };

    return {
      students,
      addStudents,
      toggleRecordBook,
    };
  },
};
</script>

<style>
.container{
  margin: 0 auto;
  max-width: 1280px;
  padding: 2rem;
}
</style>