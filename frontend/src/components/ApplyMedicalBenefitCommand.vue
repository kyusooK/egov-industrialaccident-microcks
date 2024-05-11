<template>
    <v-card outlined>
        <v-card-title>
            ApplyMedicalBenefit
        </v-card-title>

        <v-card-text>
            <String label="BusinessCode" v-model="value.businessCode" :editMode="editMode"/>
            <String label="EmployeeId" v-model="value.employeeId" :editMode="editMode"/>
            <String label="Name" v-model="value.name" :editMode="editMode"/>
            <String label="HospitalCode" v-model="value.hospitalCode" :editMode="editMode"/>
            <String label="DoctorNote" v-model="value.doctorNote" :editMode="editMode"/>
            <String label="AccidentType" v-model="value.accidentType" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="applyMedicalBenefit"
            >
                ApplyMedicalBenefit
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
        name: 'ApplyMedicalBenefitCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.businessCode = '';
            this.value.employeeId = '';
            this.value.name = '';
            this.value.hospitalCode = '';
            this.value.doctorNote = '';
            this.value.accidentType = '';
        },
        watch: {
        },
        methods: {
            applyMedicalBenefit() {
                this.$emit('applyMedicalBenefit', this.value);
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

