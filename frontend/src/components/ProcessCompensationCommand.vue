<template>
    <v-card outlined>
        <v-card-title>
            ProcessCompensation
        </v-card-title>

        <v-card-text>
            <Number label="Amount" v-model="value.amount" :editMode="editMode"/>
            <String label="Method" v-model="value.method" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="processCompensation"
            >
                ProcessCompensation
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
        name: 'ProcessCompensationCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.amount = 0;
            this.value.method = '';
        },
        watch: {
        },
        methods: {
            processCompensation() {
                this.$emit('processCompensation', this.value);
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

