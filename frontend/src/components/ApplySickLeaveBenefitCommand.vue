<template>
    <v-card outlined>
        <v-card-title>
            ApplySickLeaveBenefit
        </v-card-title>

        <v-card-text>
            <String label="BusinessCode" v-model="value.businessCode" :editMode="editMode"/>
            <String label="EmployeeId" v-model="value.employeeId" :editMode="editMode"/>
            <Number label="Period" v-model="value.period" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="applySickLeaveBenefit"
            >
                ApplySickLeaveBenefit
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
        name: 'ApplySickLeaveBenefitCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.businessCode = '';
            this.value.employeeId = '';
            this.value.period = 0;
        },
        watch: {
        },
        methods: {
            applySickLeaveBenefit() {
                this.$emit('applySickLeaveBenefit', this.value);
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

