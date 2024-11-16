<template>
  <div class="container mt-4">

    <!-- Фильтры -->
    <div class="row mb-3 filters">
      <div class="col-md-4">
        <label for="groupSelect" class="form-label">Группа</label>
        <select v-model="selectedGroup" class="form-select custom-select" id="groupSelect">
          <option value="" disabled>Выберите группу</option>
          <option v-for="group in groups" :key="group" :value="group">
            {{ group }}
          </option>
        </select>
      </div>

      <div class="col-md-4">
        <label for="courseSelect" class="form-label">Курс</label>
        <select v-model="selectedCourse" class="form-select custom-select" id="courseSelect">
          <option value="" disabled>Выберите курс</option>

          <!-- Бакалавриат -->
          <optgroup label="Бакалавриат">
            <option v-for="course in bachelorCourses" :key="course" :value="course">
              {{ course }}
            </option>
          </optgroup>

          <!-- Магистратура -->
          <optgroup label="Магистратура">
            <option v-for="course in masterCourses" :key="course" :value="course">
              {{ course }}
            </option>
          </optgroup>
        </select>
      </div>

      <div class="col-md-4">
        <label for="searchInput" class="form-label">Поиск по ФИО</label>
        <input
            v-model="searchQuery"
            type="text"
            class="form-control search-input"
            id="searchInput"
            placeholder="Введите ФИО"
        />
      </div>
    </div>

    <!-- Кнопка импорт/экспорт -->
    <button
        class="btn btn-dark custom-button"
        type="button"
        @click="addStudents"
    >
      Добавить студентов
    </button>

    <!-- Список студентов -->
    <div class="table-container">
      <table class="custom-table">
        <thead>
        <tr>
          <th>#</th>
          <th>ФИО</th>
          <th>Группа</th>
          <th>Курс</th>
          <th>Зачетная книжка</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(student, index) in filteredStudents" :key="index">
          <td>{{ index + 1 }}</td>
          <td>{{ student.fullName }}</td>
          <td>{{ student.group }}</td>
          <td>{{ student.course }}</td>
          <td>
            <input
                type="checkbox"
                :checked="student.hasRecordBook"
                @change="toggleRecordBook(student)"
            />
          </td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>


<script>
import { ref, computed } from "vue";

export default {
  setup() {
    const groups = ref(["101", "102", "103", "201", "202"]);
    const bachelorCourses = ref([
      "Бакалавриат (1)",
      "Бакалавриат (2)",
      "Бакалавриат (3)",
      "Бакалавриат (4)"
    ]);
    const masterCourses = ref([
      "Магистратура (1)",
      "Магистратура (2)"
    ]);
    const selectedGroup = ref("");
    const selectedCourse = ref("");
    const searchQuery = ref("");

    const students = ref([
      { fullName: "Иванов Иван Иванович", group: "101", course: "Бакалавриат (1)", hasRecordBook: true },
      { fullName: "Петров Петр Петрович", group: "102", course: "Бакалавриат (2)", hasRecordBook: false },
      { fullName: "Сидорова Анна Сергеевна", group: "201", course: "Магистратура (1)", hasRecordBook: true },
    ]);

    const filteredStudents = computed(() => {
      return students.value.filter(student => {
        return (
            (!selectedGroup.value || student.group === selectedGroup.value) &&
            (!selectedCourse.value || student.course === selectedCourse.value) &&
            (!searchQuery.value || student.fullName.toLowerCase().includes(searchQuery.value.toLowerCase()))
        );
      });
    });

    const addStudents = () => alert("Добавление студентов");

    const toggleRecordBook = (student) => {
      student.hasRecordBook = !student.hasRecordBook;
    };

    return {
      groups,
      bachelorCourses,
      masterCourses,
      selectedGroup,
      selectedCourse,
      searchQuery,
      students,
      filteredStudents,
      addStudents,
      toggleRecordBook,
    };
  },
};
</script>