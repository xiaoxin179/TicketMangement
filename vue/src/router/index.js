import Vue from 'vue'
import VueRouter from 'vue-router'
import DormManagement from '../components/DormManagement'
import TicketMangement from '../components/TicketMangement'
// import SystemConfig from '../components/SystemConfig'
import Show from '../components/show.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'login',
    component: () => import('../views/Login')
  },
  {
    path: '/home',
    name: 'home',
    redirect: '/home/dormManagement',
    component: () => import('../views/Home'),
    children: [
      {
        path: '/home/dormManagement',
        name: 'dormManagement',
        component: DormManagement
      },
      {
        path: '/home/TicketMangement',
        name: 'TicketMangement',
        component: TicketMangement
      },
      // {
      //   path: '/home/systemConfig',
      //   name: 'systemConfig',
      //   component: SystemConfig
      // },
      {
        path: '/home/show',
        name: 'show',
        component: Show
      }
    ]
  }
]

const router = new VueRouter({
  routes
})

export default router
