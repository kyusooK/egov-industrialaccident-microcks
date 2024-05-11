<template>
    <v-card outlined>
        <v-card-title>
            UpdateInvestigation
        </v-card-title>

        <v-card-text>
            <String label="AssessorId" v-model="value.assessorId" :editMode="editMode"/>
            <String label="Results" v-model="value.results" :editMode="editMode"/>
            <String label="Comments" v-model="value.comments" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="updateInvestigation"
            >
                UpdateInvestigation
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
        name: 'UpdateInvestigationCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.assessorId = '';
            this.value.results = '';
            this.value.comments = '';
        },
        watch: {
        },
        methods: {
            updateInvestigation() {
                this.$emit('updateInvestigation', this.value);
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

