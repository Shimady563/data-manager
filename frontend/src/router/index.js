import { createRouter, createWebHistory } from 'vue-router';
import App from '@/App.vue';
import StudentModal from '@/components/StudentModal.vue';
import DisciplineModal from '@/components/DisciplineModal.vue';

const routes = [
  { 
    path: '/', 
    component: App,
    children: [
        { path: '/student', component: StudentModal },
        { path: '/discipline', component: DisciplineModal },
    ]
},
  
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;