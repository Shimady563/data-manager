<template>
  <div v-if="visible" class="modal-backdrop">
    <div class="modal">
      <h5>Загрузить список дисциплин</h5>
      <label>Направление:</label>
      <select class="custom-select" v-model="form.direction">
        <option disabled value="">Выберите направление</option>
        <option v-for="direction in directions" :key="direction.id" :value="direction.id">
          {{ direction.name }}
        </option>
      </select>
      <label></label>
      <div class="file-upload">
        <input type="file" id="disciplineFile" class="file-input" @change="onFileChange" />
        <label for="disciplineFile" class="file-label">Выбрать файл</label>
        <span class="file-name" v-if="form.fileName">{{ form.fileName }}</span>
      </div>
      <div class="modal-actions">
        <button class="btn btn-secondary" @click="$emit('close')">Отмена</button>
        <button class="btn btn-primary" @click="submitForm">Загрузить</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    visible: { type: Boolean, required: true },
    directions: { type: Array, required: true },
    form: { type: Object, required: true },
  },
  methods: {
    onFileChange(event) {
      const file = event.target.files[0];
      if (file) {
        this.form.fileName = file.name;
      }
    },
    submitForm() {
      this.$emit("submit", this.form);
    },
  },
};
</script>

<s