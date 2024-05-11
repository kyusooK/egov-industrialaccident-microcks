<template>
    <v-card outlined>
        <v-card-title>
            RequestSickLeaveBenefit
        </v-card-title>

        <v-card-text>
            <String label="EmployeeId" v-model="value.employeeId" :editMode="editMode"/>
            <String label="BusinessCode" v-model="value.businessCode" :editMode="editMode"/>
            <Number label="Period" v-model="value.period" :editMode="editMode"/>
            <Number label="Id" v-model="value.id" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="requestSickLeaveBenefit"
            >
                RequestSickLeaveBenefit
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
        name: 'RequestSickLeaveBenefitCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.employeeId = '';
            this.value.businessCode = '';
            this.value.period = 0;
            this.value.id = 0;
        },
        watch: {
        },
        methods: {
            requestSickLeaveBenefit() {
                this.$emit('requestSickLeaveBenefit', this.value);
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

