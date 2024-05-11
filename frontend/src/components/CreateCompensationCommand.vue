<template>
    <v-card outlined>
        <v-card-title>
            CreateCompensation
        </v-card-title>

        <v-card-text>
            <Number label="SickLeaveId" v-model="value.sickLeaveId" :editMode="editMode"/>
            <Number label="AssessmentId" v-model="value.assessmentId" :editMode="editMode"/>
            <Number label="AccidentId" v-model="value.accidentId" :editMode="editMode"/>
            <String label="EmployeeId" v-model="value.employeeId" :editMode="editMode"/>
            <Number label="AverageSalary" v-model="value.averageSalary" :editMode="editMode"/>
            <Number label="Period" v-model="value.period" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="createCompensation"
            >
                CreateCompensation
            </v-btn>
            
            <v-btn
                    color="primary"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>
   
    export default {
        name: 'CreateCompensationCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.sickLeaveId = 0;
            this.value.assessmentId = 0;
            this.value.accidentId = 0;
            this.value.employeeId = '';
            this.value.averageSalary = 0;
            this.value.period = 0;
        },
        watch: {
        },
        methods: {
            createCompensation() {
                this.$emit('createCompensation', this.value);
            },
            close() {
                this.$emit('closeDialog');
            },
            change() {
                this.$emit('input', this.value);
            },
        }
    }
</script>

