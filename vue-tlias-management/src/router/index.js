import { createRouter, createWebHistory } from 'vue-router';
import Index from '@/views/index/index.vue';
import Clazz from '@/views/clazz/index.vue';
import Stu from '@/views/stu/index.vue';
import Dept from '@/views/dept/index.vue';
import Emp from '@/views/emp/index.vue';
import EmpReport from '@/views/report/emp/index.vue';
import StuReport from '@/views/report/stu/index.vue';
import Log from '@/views/log/index.vue';
import Login from '@/views/login/index.vue';
import Layout from '@/views/layout/index.vue';

const routes = [
  { path: '/',
    component: Layout,
    redirect: '/index',
    children: [
      { path: '/index', component: Index },
      { path: '/clazz', component: Clazz },
      { path: '/stu', component: Stu },
      { path: '/dept', component: Dept },
      { path: '/emp', component: Emp },
      { path: '/report/emp', component: EmpReport },
      { path: '/report/stu', component: StuReport },
      { path: '/log', component: Log }
    ]
  },
  { path: '/login', component: Login },
  //404：匹配所有不存在的路径，跳转到登录页
  { path: '/:pathMatch(.*)*', redirect: '/login' }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

//全局路由守卫：未登录用户自动跳转登录页
router.beforeEach((to, from, next) => {
  const isLogin = localStorage.getItem('loginUser')
  if (!isLogin && to.path !== '/login') {
    //未登录且访问非登录页，跳转登录
    next('/login')
  } else if (to.path === '/login' && isLogin) {
    //已登录用户访问登录页，跳转首页
    next('/')
  } else {
    next()
  }
})

export default router;
