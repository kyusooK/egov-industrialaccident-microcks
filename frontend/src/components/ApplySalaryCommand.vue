<template>
    <v-card outlined>
        <v-card-title>
            ApplySalary
        </v-card-title>

        <v-card-text>
            <String label="AverageSalary" v-model="value.averageSalary" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="applySalary"
            >
                ApplySalary
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
        name: 'ApplySalaryCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.averageSalary = '';
        },
        watch: {
        },
        methods: {
            applySalary() {
                this.$emit('applySalary', this.value);
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

