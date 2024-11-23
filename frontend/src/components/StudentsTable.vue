<template>
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
      <tr v-for="(student, index) in students" :key="index">
        <td>{{ index + 1 }}</td>
        <td>{{ student.fullName }}</td>
        <td>{{ student.group }}</td>
        <td>{{ student.course }}</td>
        <td>
          <input
              type="checkbox"
              :checked="student.hasRecordBook"
              @change="$emit('toggle-record-book', student)"
          />
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  props: {
    students: Array,
  },
  emits: ["toggle-record-book"],
};
</script>
