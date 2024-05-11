<template>
    <v-card outlined>
        <v-card-title>
            CreateSickLeaveBenefit
        </v-card-title>

        <v-card-text>
            <Number label="AccessmentId" v-model="value.accessmentId" :editMode="editMode"/>
            <Number label="AccidentId" v-model="value.accidentId" :editMode="editMode"/>
            <String label="BusinessCode" v-model="value.businessCode" :editMode="editMode"/>
            <String label="EmployeeId" v-model="value.employeeId" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="createSickLeaveBenefit"
            >
                CreateSickLeaveBenefit
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
        name: 'CreateSickLeaveBenefitCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.accessmentId = 0;
            this.value.accidentId = 0;
            this.value.businessCode = '';
            this.value.employeeId = '';
        },
        watch: {
        },
        methods: {
            createSickLeaveBenefit() {
                this.$emit('createSickLeaveBenefit', this.value);
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

