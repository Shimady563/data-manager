<template>
  <div class="filters-container">
    <div class="filter-item">
      <label for="groupSelect" class="form-label">Группа</label>
      <select
          :value="selectedGroup"
          @input="$emit('update:selectedGroup', $event.target.value)"
          class="form-select"
          id="groupSelect"
      >
        <option value="" disabled>Выберите группу</option>
        <option v-for="group in groups" :key="group" :value="group">
          {{ group }}
        </option>
      </select>
    </div>

    <div class="filter-item">
      <label for="courseSelect" class="form-label">Курс</label>
      <select
          :value="selectedCourse"
          @input="$emit('update:selectedCourse', $event.target.value)"
          class="form-select"
          id="courseSelect"
      >
        <option value="" disabled>Выберите курс</option>
        <optgroup label="Бакалавриат">
          <option v-for="course in bachelorCourses" :key="course" :value="course">
            {{ course }}
          </option>
        </optgroup>
        <optgroup label="Магистратура">
          <option v-for="course in masterCourses" :key="course" :value="course">
            {{ course }}
          </option>
        </optgroup>
      </select>
    </div>

    <div class="filter-item">
      <label for="searchInput" class="form-label">Поиск по ФИО</label>
      <input
          :value="searchQuery"
          @input="$emit('update:searchQuery', $event.target.value)"
          type="text"
          class="form-control"
          id="searchInput"
          placeholder="Введите ФИО"
      />
    </div>

    <div class="filter-buttons">
      <button class="btn btn-outline-secondary reset-button" @click="resetFilters">
        Сбросить
      </button>
    </div>
  </div>
</template>




<script>
export default {
  props: {
    groups: Array,
    bachelorCourses: Array,
    masterCourses: Array,
    selectedGroup: String,
    selectedCourse: String,
    searchQuery: String,
  },
  emits: ["update:selectedGroup", "update:selectedCourse", "update:searchQuery"],
  methods: {
    resetFilters() {
      this.$emit("update:selectedGroup", "");
      this.$emit("update:selectedCourse", "");
      this.$emit("update:searchQuery", "");
    },
  },
};
</script>


<style scoped>
.filters-container {
  width: 100%;
  background-color: #f8f9fa;
  border: 1px solid #e0e0e0;
  border-radius: 8px;
  padding: 16px;
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.filter-item {
  flex: 1;
  min-width: 280px;
}

.filter-item .form-label {
  font-size: 0.9rem;
  font-weight: bold;
  color: #495057;
}

.filter-item select,
.filter-item input {
  width: 100%;
  padding: 8px;
  border-radius: 6px;
  border: 1px solid #ced4da;
  transition: border-color 0.3s, box-shadow 0.3s;
}

.filter-item select:focus,
.filter-item input:focus {
  border-color: #007bff;
  box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
  outline: none;
}

.filter-buttons {
  display: flex;
  justify-content: flex-end;
  gap: 8px;
  margin-top: 16px; /* Отступ сверху, чтобы кнопка не слипалась с фильтрами */
}

.reset-button {
  padding: 8px 16px;
  border-radius: 6px;
  transition: background-color 0.3s;
}

.reset-button:hover {
  background-color: #e0e0e0;
}
</style>
