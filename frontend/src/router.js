
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import AccidentAccidentManager from "./components/listers/AccidentAccidentCards"
import AccidentAccidentDetail from "./components/listers/AccidentAccidentDetail"

import AssessmentAssessmentManager from "./components/listers/AssessmentAssessmentCards"
import AssessmentAssessmentDetail from "./components/listers/AssessmentAssessmentDetail"
import AssessmentSickLeaveManager from "./components/listers/AssessmentSickLeaveCards"
import AssessmentSickLeaveDetail from "./components/listers/AssessmentSickLeaveDetail"

import CompensationCompensationManager from "./components/listers/CompensationCompensationCards"
import CompensationCompensationDetail from "./components/listers/CompensationCompensationDetail"

import StatisticsStatisticsManager from "./components/listers/StatisticsStatisticsCards"
import StatisticsStatisticsDetail from "./components/listers/StatisticsStatisticsDetail"

import NotificationNotificationManager from "./components/listers/NotificationNotificationCards"
import NotificationNotificationDetail from "./components/listers/NotificationNotificationDetail"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/accidents/accidents',
                name: 'AccidentAccidentManager',
                component: AccidentAccidentManager
            },
            {
                path: '/accidents/accidents/:id',
                name: 'AccidentAccidentDetail',
                component: AccidentAccidentDetail
            },

            {
                path: '/assessments/assessments',
                name: 'AssessmentAssessmentManager',
                component: AssessmentAssessmentManager
            },
            {
                path: '/assessments/assessments/:id',
                name: 'AssessmentAssessmentDetail',
                component: AssessmentAssessmentDetail
            },
            {
                path: '/assessments/sickLeaves',
                name: 'AssessmentSickLeaveManager',
                component: AssessmentSickLeaveManager
            },
            {
                path: '/assessments/sickLeaves/:id',
                name: 'AssessmentSickLeaveDetail',
                component: AssessmentSickLeaveDetail
            },

            {
                path: '/compensations/compensations',
                name: 'CompensationCompensationManager',
                component: CompensationCompensationManager
            },
            {
                path: '/compensations/compensations/:id',
                name: 'CompensationCompensationDetail',
                component: CompensationCompensationDetail
            },

            {
                path: '/statistics/statistics',
                name: 'StatisticsStatisticsManager',
                component: StatisticsStatisticsManager
            },
            {
                path: '/statistics/statistics/:id',
                name: 'StatisticsStatisticsDetail',
                component: StatisticsStatisticsDetail
            },

            {
                path: '/notifications/notifications',
                name: 'NotificationNotificationManager',
                component: NotificationNotificationManager
            },
            {
                path: '/notifications/notifications/:id',
                name: 'NotificationNotificationDetail',
                component: NotificationNotificationDetail
            },



    ]
})
